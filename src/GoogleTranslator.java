import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.Buffer;
import org.json.*;

public class GoogleTranslator {
    public String callTranslateApi(String targetLang, String explainLang, String text)
            throws IOException {
        String api = "https://translate.googleapis.com/translate_a/single?client=gtx&sl="
            + targetLang + "&tl=" + explainLang + "&dt=t&q=" + URLEncoder.encode(text, "UTF-8");
        URL url = new URL(api);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        return parseResult(content.toString());
    }
    private String parseResult(String jsonString) {
        JSONArray obj = new JSONArray(jsonString);
        JSONArray obj2 = new JSONArray(obj.get(0).toString());
        JSONArray obj_final = new JSONArray(obj2.get(0).toString());
        return (String)(obj_final.get(1) + "\t" + obj_final.get(0));
    }
    public static void main(String[] args) throws IOException {
        GoogleTranslator A = new GoogleTranslator();
        String result = A.callTranslateApi("en", "vi", "legendary");
        System.out.println(result);
    }
}
