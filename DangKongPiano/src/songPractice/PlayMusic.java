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

/**
 * class for playing music as score
 * @author team2
 *
 */
public class PlayMusic extends JPanel {

  private static BlockingQueue<String> queue; /* in order to check what user type */
  private static boolean isOn = false;       /* state of PlayMusic */

  private Piano piano;            /* piano which will be on the screen */
  private String song;            /* name of selected song */
  private BufferedReader reader;  /* reader melody from music score file */
  private String[] tokens;        /* array of melodies in score file */
  JLabel[] text;                  /* show score on the screen */

  /**
   * constructor for play music
   * @param song selected song from ChoooseSong
   */
  public PlayMusic(String song) {
    setLayout(null);
    setSize(1120, 550);
    setPiano();
    setSong(song);
    setBlockingQueue();
    getSong();
    listen();
  }

  /** listen whether user type correct melody */
  void listen() {
    BlockingQ queue = new BlockingQ(this);
    queue.start();
  }

  /** set piano for playing music */
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

  /** 
   * getter for piano
   * @return piano (will play with player)
   */
  public Piano getPiano() {
    return piano;
  }

  /**
   * setter for song, print which song is selected to console
   * @param song set which song is selected
   */
  void setSong(String song) {
    this.song = song;
    System.out.println(this.song + " is selected ");
  }

  /** get selected song from resource */
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

  /** setter for BlockingQueue */
  void setBlockingQueue() {
    setQueue(new ArrayBlockingQueue<String>(50));
  }

  /**
   * setter for isOn
   * @param isOn state of PlayMusic
   */
  public static void setIsOn(boolean isOn) {
    PlayMusic.isOn = isOn;
  }

  /**
   * getter for isOn
   * @return whether PlayMusic is on
   */
  public static boolean getIsOn() {
    return isOn;
  }

  /**
   * setter for bufferedReader : br
   * @param br BufferedReader for setting
   */
  public void setBr(BufferedReader br) {
    this.reader = br;
  }

  /**
   * getter for bufferedReader : br
   * @return
   */
  public BufferedReader getBr() {
    return reader;
  }

  /**
   * setter for tokens
   * @param tokens melodies to type
   */
  public void setTokens(String[] tokens) {
    this.tokens = tokens;
  }

  /**
   * getter for tokens
   * @return melodies user has to type
   */
  public String[] getTokens() {
    return tokens;
  }


  /**
   * setter for text 
   * @param text for state of selected song
   */
  public void setText(JLabel[] text) {
    this.text = text;
  }

  /**
   * getter for text
   * @return text labels
   */
  public JLabel[] getText() {
    return text;
  }

  /**
   * getter for BlockingQueue for melodies
   * @return queue( checking whether melodies are correct )
   */
  public static BlockingQueue<String> getQueue() {
    return queue;
  }

  /**
   * setter for BlockingQueue for melodies
   * @param queue( checking whether melodies are correct )
   */
  public static void setQueue(BlockingQueue<String> queue) {
    PlayMusic.queue = queue;
  }
}

/**
 * class for checking all the time whether user type well  
 * @author team2
 *
 */
class BlockingQ extends Thread {
  
  PlayMusic playMusic;
  private String[] tokens;

  /**
   * constructor
   * @param playMusic
   */
  public BlockingQ(PlayMusic playMusic) {
    this.playMusic = playMusic;
    tokens = playMusic.getTokens();
  }

  /** listen until song completed */
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


  /**
   * read a line of file, and print melody 
   * @return length of melodies
   * @throws IOException
   */
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

  /**
   * show melody to screen
   * @param label	for show melody to screen
   * @param xpos	x position of label
   * @param melody	melody user has to type
   * @return label
   */
  JLabel setMelody(JLabel label, int xpos, String melody) {
    String txt = melody;
    label = new JLabel(txt);
    label.setFont(new Font("Times New Roman", Font.BOLD, 50));
    label.setBounds(xpos * 100 + 50, 35, 100, 60);
    playMusic.add(label);
    return label;
  }

  /**
   * delete a line of melody
   * @param len the number of labels(melodies) to delete
   */
  void delete(int len) {
    for (int i = 0; i < len; i++) {
      playMusic.text[i].setText("");
      ;
    }
  }

  /** display "END" when player finished music */
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

  /**
   * setter for tokens
   * @param tokens melodies to type
   */
  public void setTokens(String[] tokens) {
    this.tokens = tokens;
  }

  /**
   * getter for tokens
   * @return tokens to type
   */
  public String[] getTokens() {
    return tokens;
  }

}