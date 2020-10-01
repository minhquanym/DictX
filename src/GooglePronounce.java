import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.json.*;

public class GooglePronounce {
    public InputStream getMp3PronounceAudio(String lang, String text)
            throws IOException {
        String api = "http://translate.google.com/translate_tts?ie=UTF-8&tl="+
                lang + "&client=tw-ob&q=" + URLEncoder.encode(text, "UTF-8");
        URL url = new URL(api);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        InputStream audioSrc = con.getInputStream();
        return new BufferedInputStream(audioSrc);
    }
    public void play(InputStream audio) throws JavaLayerException {
        new Player(audio).play();
    }
    public void pronounce(String lang, String text) throws IOException, JavaLayerException {
        this.play(this.getMp3PronounceAudio(lang, text));
    }
    public static void main(String[] args) throws IOException, JavaLayerException {
        GooglePronounce A = new GooglePronounce();
        A.pronounce("vi", "Quoc Trung la con ga.");
    }
}
