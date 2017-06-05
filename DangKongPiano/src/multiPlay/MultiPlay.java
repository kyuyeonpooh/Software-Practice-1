package multiPlay;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

import piano.Piano;

public class MultiPlay extends JFrame {

  private static boolean isOn = false;

  private Piano piano;

  public MultiPlay() {
    setPiano();
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

  private class Client extends Thread {
    @Override
    public void run() {
      try {
        Socket socket = new Socket("localhost", 1225);
      } catch (IOException e) {
        e.printStackTrace();
      }
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

}
