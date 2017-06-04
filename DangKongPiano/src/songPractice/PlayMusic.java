package songPractice;

import javax.swing.JPanel;
import piano.Piano;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class PlayMusic extends JPanel {

  private static boolean isOn = false;

  private Piano piano;
  private String song;
  private BufferedReader br;

  public PlayMusic(String song) throws FileNotFoundException {
    setLayout(null);
    setSize(1100, 550);
    setPiano();
    setSong(song);
    // getSong();
  }

  void readLine() throws IOException {
    String data = br.readLine();

  }

  void getSong() throws FileNotFoundException {
    InputStream fis = new FileInputStream("./resource/music/" + this.song + ".csv");
    Reader isr = new InputStreamReader(fis);
    br = new BufferedReader(isr);
  }

  void setSong(String song) {
    this.song = song;
  }

  void setPiano() {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          PlayMusic.setIsOn(true);
          piano = new Piano();
          piano.setBounds(0, 125, 1100, 351);
          add(piano);
          piano.setLayout(null);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

  }

  public static boolean getIsOn() {
    return isOn;
  }

  public static void setIsOn(boolean isOn) {
    PlayMusic.isOn = isOn;
  }

}
