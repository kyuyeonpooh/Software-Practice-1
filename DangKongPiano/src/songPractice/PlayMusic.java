package songPractice;

import javax.swing.JLabel;
import javax.swing.JPanel;
import piano.Piano;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* class for playing music as score */
public class PlayMusic extends JPanel {

  private static BlockingQueue<String> queue; /* in order to check what user type */
  private static boolean isOn = false;       /* state of PlayMusic */

  private Piano piano;            /* piano which will be on the screen */
  private String song;            /* name of selected song */
  private BufferedReader reader;  /* reader melody from music score file */
  private String[] tokens;        /* array of melodies in score file */
  JLabel[] text;                  /* show score on the screen */

  /* constructor for play music */
  public PlayMusic(String song) {
    setLayout(null);
    setSize(1120, 550);
    setPiano();
    setSong(song);
    setBlockingQueue();
    getSong();
    listen();
  }

  /* listen whether user type correct melody */
  void listen() {
    BlockingQ queue = new BlockingQ(this);
    queue.start();
  }

  /* set piano for playing music */
  public void setPiano() {
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

  /* getter for piano */
  public Piano getPiano() {
    return piano;
  }

  /* setter for song, print which song is selected to console */
  void setSong(String song) {
    this.song = song;
    System.out.println(this.song + " is selected ");
  }

  /* get selected song from resource */
  void getSong() {
    InputStream fis;
    try {
      fis = new FileInputStream("./resource/music/" + this.song + ".txt");
      Reader isr = new InputStreamReader(fis);
      setBr(new BufferedReader(isr));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /* setter for BlockingQueue */
  void setBlockingQueue() {
    setQueue(new ArrayBlockingQueue<String>(50));
  }

  /* setter for IsOn */
  public static void setIsOn(boolean isOn) {
    PlayMusic.isOn = isOn;
  }

  /* getter for IsOn */
  public static boolean getIsOn() {
    return isOn;
  }

  /* setter for bufferedReader : br */
  public void setBr(BufferedReader br) {
    this.reader = br;
  }

  /* getter for bufferedReader : br */
  public BufferedReader getBr() {
    return reader;
  }

  /* setter for tokens */
  public void setTokens(String[] tokens) {
    this.tokens = tokens;
  }

  /* getter for tokens */
  public String[] getTokens() {
    return tokens;
  }

  /* setter for text */
  public void setText(JLabel[] text) {
    this.text = text;
  }

  /* getter for text */
  public JLabel[] getText() {
    return text;
  }

  public static BlockingQueue<String> getQueue() {
    return queue;
  }

  public static void setQueue(BlockingQueue<String> queue) {
    PlayMusic.queue = queue;
  }
}

/* class for checking all the time whether user type well */
class BlockingQ extends Thread {
  
  PlayMusic playMusic;
  private String[] tokens;

  /* constructor */
  public BlockingQ(PlayMusic playMusic) {
    this.playMusic = playMusic;
    tokens = playMusic.getTokens();
  }

  /* listen until song completed */
  public void run() {
    int length = 0;
    while (true) {
      try {
        length = readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        if (length == 0)
          break;
        else {
          int correctCount = 0;
          while (correctCount != length) {
            String melody = PlayMusic.getQueue().take();
            if (melody.equals(tokens[correctCount])) {
              playMusic.text[correctCount].setForeground(Color.ORANGE);
              correctCount++;
            }
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      delete(length);
    }
    setEnd();
  }

  /* read a line of file, and print melody */
  int readLine() throws IOException {
    String data = playMusic.getBr().readLine();
    if (data == null) {
      return 0;
    }
    tokens = data.split(" ");
    int len = tokens.length;

    playMusic.text = new JLabel[len];
    for (int i = 0; i < len; i++) {
      playMusic.text[i] = setMelody(playMusic.text[i], i, tokens[i]);
      playMusic.text[i].setText(tokens[i]);
      playMusic.text[i].setForeground(Color.BLACK);
    }
    return len;
  }

  /* show melody to screen */
  JLabel setMelody(JLabel label, int xpos, String melody) {
    String txt = melody;
    label = new JLabel(txt);
    label.setFont(new Font("Times New Roman", Font.BOLD, 50));
    label.setBounds(xpos * 100 + 50, 35, 100, 60);
    playMusic.add(label);
    return label;
  }

  /* delete a line of melody */
  void delete(int len) {
    for (int i = 0; i < len; i++) {
      playMusic.text[i].setText("");
      ;
    }
  }

  /* display "END" when player finished music */
  void setEnd() {
    playMusic.text[0].setText("E");
    playMusic.text[0].setForeground(Color.RED);
    playMusic.text[0].setFont(new Font("Comic Sans MS", Font.BOLD, 60));
    playMusic.text[0].setBounds(350, 25, 100, 60);
    playMusic.text[1].setText("N");
    playMusic.text[1].setForeground(Color.BLUE);
    playMusic.text[1].setFont(new Font("Comic Sans MS", Font.BOLD, 60));
    playMusic.text[1].setBounds(500, 25, 100, 60);
    playMusic.text[2].setText("D");
    playMusic.text[2].setForeground(Color.BLACK);
    playMusic.text[2].setFont(new Font("Comic Sans MS", Font.BOLD, 60));
    playMusic.text[2].setBounds(650, 25, 100, 60);
  }

  /* setter for tokens */
  public void setTokens(String[] tokens) {
    this.tokens = tokens;
  }

  /* getter for tokens */
  public String[] getTokens() {
    return tokens;
  }

}