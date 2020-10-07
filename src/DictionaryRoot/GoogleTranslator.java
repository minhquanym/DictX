package DictionaryRoot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import org.json.*;

public class GoogleTranslator {
    public String callTranslateApi(String targetLang, String explainLang, String text)
            throws IOException {
        String api = "https://translate.google.com/translate_a/single?client=gtx&sl="
                + targetLang + "&tl=" + explainLang + "&hl=" + explainLang
                + "&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=sos&dt=ss&dt=t&otf=2&ssel=0&tsel=0&q="
                + URLEncoder.encode(text, "UTF-8");
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
        String ans = "";
        JSONArray obj = new JSONArray(jsonString);

        // meaning
        JSONArray obj2 = new JSONArray(obj.get(0).toString());
        JSONArray meaning = new JSONArray(obj2.get(0).toString());

        ans += (String)("\t" + meaning.get(1) + "\t|\t" + meaning.get(0) + "\n");
        // different meaning
        JSONArray obj3 = new JSONArray(obj.get(1).toString());
        for (Object o : obj3) {
            JSONArray example = new JSONArray(o.toString());
            ans += (String)("\t-\t" + example.get(0) + ":\n");
            JSONArray exampleWords = new JSONArray(example.get(1).toString());
            ans += (String)("\t\t");
            for (Object o2 : exampleWords) {
                ans += (String)(o2.toString() + ", ");
            }
            ans = ans.substring(0, ans.length() - 2);
            ans += (String)("\n");
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        GoogleTranslator A = new GoogleTranslator();
        String result = A.callTranslateApi("en", "vi", "champion");
        System.out.println(result);
    }
}