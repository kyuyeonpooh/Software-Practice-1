package multiPlay;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import piano.Pedal;
import piano.Piano;

public class MultiPlay extends JFrame {

  private static boolean isOn = false;
  private static BlockingQueue<String> queue;

  private Piano piano;
  Socket socket;
  ClientSender sender;
  ClientReceiver receiver;

  public MultiPlay() {
    setQueue();
    setPiano();
    sender = new ClientSender();
    receiver = new ClientReceiver();
    sender.start();
    receiver.start();
    setTitle("Multi Play");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLayout(null);
    setSize(1120, 500);
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
      }
    }

  }

  private class ClientReceiver extends Thread {

    @Override
    public void run() {
      try {
        Thread.sleep(2000);
        String melody = null;
        while (true) {
          DataInputStream receive = new DataInputStream(socket.getInputStream());
          melody = receive.readUTF();
          System.out.println("Received" + melody);
          if(melody != null)
            playMelody(melody);
        }
      } catch (IOException | InterruptedException e) {
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
      clip.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setPiano() {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          MultiPlay.setIsOn(true);
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

  public Piano getPiano() {
    return this.piano;
  }

  public static boolean getIsOn() {
    return isOn;
  }

  public static void setIsOn(boolean isOn) {
    MultiPlay.isOn = isOn;
  }

  public static BlockingQueue<String> getQueue() {
    return queue;
  }

  public static void setQueue() {
    MultiPlay.queue = new ArrayBlockingQueue<String>(10);
  }

}