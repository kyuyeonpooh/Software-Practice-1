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

  public PlayMusic(String song){
    setLayout(null);
    setSize(1100, 550);
    setPiano();
    setSong(song);
    getSong();
  }

  void readLine() throws IOException {
    String data = br.readLine();
    
  }

  void getSong(){
    InputStream fis;
	try {
		fis = new FileInputStream("./resource/music/" + this.song + ".csv");
	    Reader isr = new InputStreamReader(fis);
	    br = new BufferedReader(isr);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  void setSong(String song) {
	this.song = song;
    System.out.println(this.song+" is selected ");
  }

  void setPiano() {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          PlayMusic.setIsOn(true);
          piano = new Piano();
          piano.setLayout(null);
          piano.setBounds(0, 125, 1100, 351);
          add(piano);          
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
