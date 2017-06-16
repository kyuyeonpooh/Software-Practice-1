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

/* new window generated for multiplay */
public class MultiPlay extends JFrame {

  private static boolean isOn = false;        /* state of multiplay */
  private static BlockingQueue<String> queue; /* blocking queue used for send melodies that user pressed to server */

  private Piano piano;                         /* piano which will be shown on the screen */
  private Socket socket;                       /* socket to communicate with server */
  private ClientSender sender;                 /* thread which sends melody with pressed key */
  private ClientReceiver receiver;             /* thread which receives melody from other player */
  private HashMap<String, Integer> melodyHash; /* hash map whose key is melody name and value is index number in Keyboard */
  
  /* constructor for multiplay */
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
  
  /* thread to send melodies that user pressed to server */
  private class ClientSender extends Thread {

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
  
  /* thread to receive melodies that other user pressed from server */
  private class ClientReceiver extends Thread {

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
  
  /* thread that shows which key other player pressed with red color */
  private class MultiColorEffect extends Thread {

    private String melody; /* melody that other player pressed */

    private MultiColorEffect(String melody) {
      this.melody = melody;
    }

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
  
  /* when ClientReceiver thread gets data from server, this plays melody with that data */
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
  
  /* hash map whose key is name of melody and value is index of Keyboard */
  public void setMelodyHash() {
    melodyHash = new HashMap<String, Integer>();
    String[] melodies;
    melodies = piano.getKeyboard().getMelodies();
    int index = 0;
    for (String melody : melodies) {
      melodyHash.put(melody, index++);
    }
  }
  
  /* return hash map */
  public HashMap<String, Integer> getMelodyHash() {
    return this.melodyHash;
  }
  
  /* set piano on the screen */
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
  
  /* return piano */
  public Piano getPiano() {
    return this.piano;
  }
  
  /* setter for isOn */
  public static void setIsOn(boolean isOn) {
    MultiPlay.isOn = isOn;
  }
  
  /* getter for isOn */
  public static boolean getIsOn() {
    return isOn;
  }
  
  /* initialize blocking queue */
  public static void setQueue() {
    MultiPlay.queue = new ArrayBlockingQueue<String>(10);
  }
  
  /* return blocking queue */
  public static BlockingQueue<String> getQueue() {
    return queue;
  }

}