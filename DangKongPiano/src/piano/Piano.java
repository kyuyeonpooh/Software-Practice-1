package piano;

import javax.swing.JFrame;
import java.util.ArrayList;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Piano {

  private JFrame frame;
  private String [] melodies;
  private int [] keyEvents;
  private int [] buttonBounds;
  private ArrayList<Keyboard> keyboards;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Piano piano = new Piano();
          piano.frame.setVisible(true);
 
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public Piano() throws IOException {
    this.setFrame();
    this.setMelodies();
    this.setKeyEvents();
    this.setButtonBounds();
    this.setKeyboards();
  }

  public void setFrame() {
    frame = new JFrame();
    frame.setBounds(100, 100, 1100, 372);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(false);
    frame.setTitle("DangKong Piano");
    frame.getContentPane().setLayout(null);
  }

  public JFrame getFrame() {
    return this.frame;
  }

  public void setMelodies() {
    this.melodies = new String[]{
      "A0", "B0Flat", "B0", "C1", "C1Sharp",
      "D1", "E1Flat", "E1", "F1", "F1Sharp",
      "G1", "A1Flat", "A1", "B1Flat", "B1",
      "C2", "C2Sharp", "D2", "E2Flat", "E2",
      "F2", "F2Sharp", "G2", "A2Flat", "A2",
      "B2Flat", "B2"
    };
  }
  
  public String[] getMelodies() {
    return this.melodies;
  }
  
  public void setKeyEvents() {
    this.keyEvents = new int[]{
      KeyEvent.VK_Q, KeyEvent.VK_2, KeyEvent.VK_W, KeyEvent.VK_E, KeyEvent.VK_4,
      KeyEvent.VK_R, KeyEvent.VK_5, KeyEvent.VK_T, KeyEvent.VK_Y, KeyEvent.VK_7,
      KeyEvent.VK_U, KeyEvent.VK_8, KeyEvent.VK_I, KeyEvent.VK_9, KeyEvent.VK_O,
      KeyEvent.VK_Z, KeyEvent.VK_S, KeyEvent.VK_X, KeyEvent.VK_D, KeyEvent.VK_C,
      KeyEvent.VK_V, KeyEvent.VK_G, KeyEvent.VK_B, KeyEvent.VK_H, KeyEvent.VK_N,
      KeyEvent.VK_J, KeyEvent.VK_M
    };
  }
  
  public int [] getKeyEvents() {
    return this.keyEvents;
  }
  
  public void setButtonBounds() {
    this.buttonBounds = new int[]{
      25, 52, 83, 141, 169,
      199, 226, 257, 315, 341,
      373, 399, 430, 457, 488,
      546, 573, 604, 631, 662,
      720, 747, 778, 805, 836,
      863, 894
    };
  }
  
  public int [] getButtonBounds() {
    return this.buttonBounds;
  }

  public void setKeyboards() throws IOException {
    this.keyboards = new ArrayList<Keyboard> ();
    for(int i = 0; i < melodies.length; i++){
      Keyboard keyboard = new Keyboard(melodies[i], keyEvents[i], buttonBounds[i]);
      this.keyboards.add(keyboard);
      frame.getContentPane().add(keyboard.getButton());
    }
  }
  
  public ArrayList<Keyboard> getKeyboards() {
    return this.keyboards;
  }

}