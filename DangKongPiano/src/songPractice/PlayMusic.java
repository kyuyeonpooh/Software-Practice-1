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
 * Class for playing music with song score
 * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
 *
 */
public class PlayMusic extends JPanel {
  
  /** Placeholder of user input */
  private static BlockingQueue<String> queue; 
  /** State of PlayMusic */
  private static boolean isOn = false;       

  /** Piano which will be on the screen */
  private Piano piano;          
  /** Name of selected song */
  private String song;            
  /** Reader of melody in song score file */
  private BufferedReader reader;  
  /** Array of melodies in score file */
  private String[] tokens;    
  /** Score on the screen */
  JLabel[] text;                  

  /**
   * Constructor of PlayMusic
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

  /**
   * listen and check whether user typed correct melody
   */
  void listen() {
    BlockingQ queue = new BlockingQ(this);
    queue.start();
  }

  /**
   * Set piano on the screen
   */
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
   * Getter for piano
   * @return piano
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

  /**
   * Get selected song from the resource
   */
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

  /**
   * Initialize blocking queue
   */
  void setBlockingQueue() {
    setQueue(new ArrayBlockingQueue<String>(50));
  }

  /**
   * Setter for isOn
   * @param isOn current state of PlayMusic
   */
  public static void setIsOn(boolean isOn) {
    PlayMusic.isOn = isOn;
  }

  /**
   * Getter for isOn
   * @return isOn
   */
  public static boolean getIsOn() {
    return isOn;
  }

  /**
   * Setter for bufferedReader
   * @param br bufferedReader to set
   */
  public void setBr(BufferedReader br) {
    this.reader = br;
  }

  /**
   * Getter for br
   * @return br
   */
  public BufferedReader getBr() {
    return reader;
  }

  /**
   * Setter for tokens
   * @param tokens melodies user needs to type
   */
  public void setTokens(String[] tokens) {
    this.tokens = tokens;
  }

  /**
   * Getter for tokens
   * @return tokens
   */
  public String[] getTokens() {
    return tokens;
  }


  /**
   * Setter for text 
   * @param text state of selected song
   */
  public void setText(JLabel[] text) {
    this.text = text;
  }

  /**
   * Getter for text
   * @return text
   */
  public JLabel[] getText() {
    return text;
  }

  /**
   * Setter for BlockingQueue for melodies
   * @param queue (checking whether melodies are correct)
   */
  public static void setQueue(BlockingQueue<String> queue) {
    PlayMusic.queue = queue;
  }
  
  /**
   * Getter for BlockingQueue for melodies
   * @return queue (checking whether melodies are correct)
   */
  public static BlockingQueue<String> getQueue() {
    return queue;
  }
  
}

/**
 * Class that checks every time if user typed correctly
 * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
 *
 */
class BlockingQ extends Thread {
  
  /** playMusic with this variable */
  PlayMusic playMusic;
  /** each token of the song score */
  private String[] tokens;

  /**
   * Constructor of BlockingQ
   * @param playMusic set playMusic with this
   */
  public BlockingQ(PlayMusic playMusic) {
    this.playMusic = playMusic;
    tokens = playMusic.getTokens();
  }

  /**
   * Listen and check until song is completed
   */
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
   * Read each line of file and print it on the screen
   * @return length of melodies
   * @throws IOException fail to open the score
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
   * Show melody on the screen
   * @param label	this show melody on the screen
   * @param xpos x position of label
   * @param melody melody user needs to type
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
   * Deleter of each line
   * @param len the number of labels(melodies) to delete
   */
  void delete(int len) {
    for (int i = 0; i < len; i++) {
      playMusic.text[i].setText("");
      ;
    }
  }

  /**
   * Display "END" when player finished music
   */
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
   * Setter for tokens
   * @param tokens melodies to type
   */
  public void setTokens(String[] tokens) {
    this.tokens = tokens;
  }

  /**
   * Getter for tokens
   * @return tokens
   */
  public String[] getTokens() {
    return tokens;
  }

}