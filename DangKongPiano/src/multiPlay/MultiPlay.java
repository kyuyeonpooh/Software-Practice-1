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

public class MultiPlay extends JFrame {

  private static boolean isOn = false;
  private static BlockingQueue<String> queue;

  private Piano piano;
  private Socket socket;
  private ClientSender sender;
  private ClientReceiver receiver;
  private HashMap<String, Integer> melodyHash;

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
    getContentPane().setBackground(Color.CYAN);
    setVisible(true);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        MultiPlay.setIsOn(false);
      }
    });
  }

  private class ClientSender extends Thread {

    @Override
    public void run() {
      try {
        socket = new Socket("localhost", 5000);
        DataInputStream playerNum = new DataInputStream(socket.getInputStream());
        System.out.println(playerNum.readInt());
        while (true) {
          DataOutputStream output = new DataOutputStream(socket.getOutputStream());
          output.writeUTF(queue.take());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

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

  private class MultiColorEffect extends Thread {

    private String melody;

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

  public void setMelodyHash() {
    melodyHash = new HashMap<String, Integer>();
    String[] melodies;
    melodies = piano.getKeyboard().getMelodies();
    int index = 0;
    for (String melody : melodies) {
      melodyHash.put(melody, index++);
    }
  }

  public HashMap<String, Integer> getMelodyHash() {
    return this.melodyHash;
  }

  public void setPiano() {
    try {
      piano = new Piano();
    } catch (IOException e) {
      e.printStackTrace();
    }
    piano.setBackground(Color.CYAN);
    piano.setLayout(null);
    piano.setBounds(0, 125, 1100, 351);
    add(piano);
  }

  public Piano getPiano() {
    return this.piano;
  }

  public static void setIsOn(boolean isOn) {
    MultiPlay.isOn = isOn;
  }

  public static boolean getIsOn() {
    return isOn;
  }

  public static void setQueue() {
    MultiPlay.queue = new ArrayBlockingQueue<String>(10);
  }

  public static BlockingQueue<String> getQueue() {
    return queue;
  }

}