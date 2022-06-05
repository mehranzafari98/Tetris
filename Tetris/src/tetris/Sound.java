package tetris;

import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound {

    public  void Sound(String path) {
        URL file = null;
        try {
            file = new URL("file:" + path);
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        }
        Applet.newAudioClip(file).play();
    }
}
