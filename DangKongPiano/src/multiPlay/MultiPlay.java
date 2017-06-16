package multiPlay;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;

import piano.Keyboard;
import piano.Pedal;
import piano.Piano;

/**
 * Class with window for multiplay mode
 * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
 *
 */
public class MultiPlay extends JFrame {
  
  /** State of multiplay */
  private static boolean isOn = false;       
  /** Blocking queue used for send melodies that user pressed to server */
  private static BlockingQueue<String> queue;
  
  /** Piano which will be shown on the screen */
  private Piano piano;
  /** Socket to communicate with server */
  private Socket socket;
  /** Thread which sends melody with pressed key */
  private ClientSender sender;
  /** Thread which receives melody from other player */
  private ClientReceiver receiver;            
  /** HashMap whose key is melody name and value is index number in keyboard */
  private HashMap<String, Integer> melodyHash;
  
  /**
   * Constructor of MultiPlay
   */
  public MultiPlay() {
    MultiPlay.setIsOn(true);
    setQueue();
    setPiano();
    setMelodyHash();
    sender = new ClientSender();
    receiver = new ClientReceiver();
    sender.start();
    receiver.start();
    setTitle("Multi Play");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(null);
    setSize(1120, 500);
    setVisible(true);
    /* window listener to notify that this play mode is closed */
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        MultiPlay.setIsOn(false);
      }
    });
  }
  
  /**
   * Thread which constantly send melodies that user pressed to server
   * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
   * 
   */
  private class ClientSender extends Thread {
    
    /**
     * Send the pressed melody to the server
     * You can modify IP address and port number here
     */
    @Override
    public void run() {
      try {
        socket = new Socket("localhost", 5000);
        DataInputStream message = new DataInputStream(socket.getInputStream());
        System.out.println(message.readUTF());
        while (true) {
          DataOutputStream send = new DataOutputStream(socket.getOutputStream());
          send.writeUTF(queue.take());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }
  
  /**
   * Thread which constantly receive melodies that other user pressed from server
   * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
   *
   */
  private class ClientReceiver extends Thread {
    
    /**
     * Receive, print and play the melody from the server that other player pressed
     */
    @Override
    public void run() {
      try {
        Thread.sleep(2000);
        String receivedMelody;
        while (true) {
          DataInputStream receive = new DataInputStream(socket.getInputStream());
          receivedMelody = receive.readUTF();
          System.out.println("Received" + receivedMelody);
          if (receivedMelody != null)
            playMelody(receivedMelody);
        }
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  /**
   * Thread which paints keys that other player pressed in red color
   * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
   *
   */
  private class MultiColorEffect extends Thread {

    private String melody; /* melody that other player pressed */
    
    /**
     * Constructor for MultiColorEffect
     * @param melody name of melody that other player pressed
     */
    private MultiColorEffect(String melody) {
      this.melody = melody;
    }
    
    /**
     * Paint key buttwon with red color for 200ms
     */
    @Override
    public void run() {
      Keyboard keyboard = piano.getKeyboard();
      JButton[] buttons = keyboard.getButtons();
      int index = melodyHash.get(melody);
      buttons[index].setBackground(Color.RED);
      try {
        Thread.sleep(200);
        Color color = keyboard.getKeyboard().get(index).getColor();
        buttons[index].setBackground(color);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
  
  /**
   * Plays melody that other play pressed and show what it is
   * @param melody
   */
  private void playMelody(String melody) {
    File soundFile;
    if (Pedal.getIsPedaled()) {
      soundFile = new File("./resource/pianoSound/" + melody + "_Pedal.wav");
    } else {
      soundFile = new File("./resource/pianoSound/" + melody + ".wav");
    }
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      MultiColorEffect multiEffect = new MultiColorEffect(melody);
      multiEffect.start();
      clip.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Set melodyHash whose key is name of melody and value is index of keyboard
   */
  public void setMelodyHash() {
    melodyHash = new HashMap<String, Integer>();
    String[] melodies;
    melodies = piano.getKeyboard().getMelodies();
    int index = 0;
    for (String melody : melodies) {
      melodyHash.put(melody, index++);
    }
  }
  
  /**
   * Getter for MelodyHash
   * @return melodyHash
   */
  public HashMap<String, Integer> getMelodyHash() {
    return this.melodyHash;
  }
  
  /**
   * Set piano on the screen
   */
  public void setPiano() {
    try {
      piano = new Piano();
    } catch (IOException e) {
      e.printStackTrace();
    }
    piano.setLayout(null);
    piano.setBounds(0, 125, 1100, 351);
    add(piano);
  }
  
  /**
   * Getter for piano
   * @return piano
   */
  public Piano getPiano() {
    return this.piano;
  }
  
  /**
   * Setter for isOn
   * @param isOn current state of MultiPlay mode
   */
  public static void setIsOn(boolean isOn) {
    MultiPlay.isOn = isOn;
  }
  
  /**
   * Getter for isOn
   * @return isOn
   */
  public static boolean getIsOn() {
    return isOn;
  }
  
  /**
   * Initialize blocking queue
   */
  public static void setQueue() {
    MultiPlay.queue = new ArrayBlockingQueue<String>(10);
  }
  
  /**
   * Getter for blocking queue
   * @return queue
   */
  public static BlockingQueue<String> getQueue() {
    return queue;
  }

}