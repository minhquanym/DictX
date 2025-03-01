package DictionaryRoot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import org.json.*;

public class GoogleTranslator {
    /**
     * translate single word.
     * @param targetLang target string language.
     * @param explainLang explain string language.
     * @param text word which want to translate.
     * @return translated word from google api.
     * @throws Exception exception.
     */
    public String translateSingleWord(String targetLang, String explainLang, String text)
            throws Exception {
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
        return parseResultSingle(content.toString());
    }

    /**
     * parse result single word.
     * @param jsonString json string which is get.
     * @return String to print.
     */
    private String parseResultSingle(String jsonString) {
        String ans = "";
        JSONArray obj = new JSONArray(jsonString);

        // meaning
        JSONArray obj2 = new JSONArray(obj.get(0).toString());
        JSONArray meaning = new JSONArray(obj2.get(0).toString());

        ans += (String) ("\n" + meaning.get(1) + "   |   " + meaning.get(0) + "\n");
        // different meaning
        JSONArray obj3 = new JSONArray(obj.get(1).toString());
        for (Object o : obj3) {
            JSONArray example = new JSONArray(o.toString());
            ans += (String) ("\n-  " + example.get(0) + ":  ");
            JSONArray exampleWords = new JSONArray(example.get(1).toString());
            for (Object o2 : exampleWords) {
                ans += (String) (o2.toString() + ", ");
            }
            ans = ans.substring(0, ans.length() - 2);
            ans += (String) ("\n");
        }
        return ans;
    }

    /**
     * translate a paragraph use google translate api.
     * @param targetLang target string language.
     * @param explainLang explain string language.
     * @param text paragraph.
     * @return translated paragraph.
     * @throws Exception exception.
     */
    public String translateParagraph(String targetLang, String explainLang, String text)
            throws Exception {
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
        return parseResultParagraph(content.toString());
    }

    /**
     * parse string from jsonString to string.
     * @param jsonString json String which be get.
     * @return string to print.
     */
    private String parseResultParagraph(String jsonString) {
        JSONArray obj = new JSONArray(jsonString);
        JSONArray obj2 = new JSONArray(obj.get(0).toString());
        JSONArray obj_final = new JSONArray(obj2.get(0).toString());
        return (String) (obj_final.get(0));
    }

    /**
     * main function to test Google translator.
     * @param args args.
     * @throws Exception exception.
     */
    public static void main(String[] args) throws Exception {
        // Can only call 100 requests per hour
        GoogleTranslator A = new GoogleTranslator();
        String result = A.translateSingleWord("en", "vi", "abaca");
        System.out.println(result);

    }
}