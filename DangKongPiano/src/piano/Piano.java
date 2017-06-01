package piano;

import javax.swing.JFrame;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.JLabel;
import java.awt.Font;

public class Piano extends JFrame implements Serializable {

  private Keyboard keyboard;
  private Pedal pedal;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Piano piano = new Piano();
          piano.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public Piano() throws IOException {
    this.setFrame();
    this.setKeyboard();
    this.setPedal();
  }

  public void setFrame() {
    JLabel pedalMode = new JLabel("Pedal Mode");
    pedalMode.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
    pedalMode.setBounds(972, 78, 110, 18);
    setBounds(100, 100, 1100, 372);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(false);
    setTitle("DangKong Piano");
    getContentPane().setLayout(null);    
    getContentPane().add(pedalMode);
  }

  public void setKeyboard() throws IOException {
    this.keyboard = new Keyboard();
    for (int i = 0; i < keyboard.getMelodies().length; i++)
      getContentPane().add(keyboard.getButtons()[i]);
  }

  public Keyboard getKeyboards() {
    return this.keyboard;
  }

  public void setPedal() {
    this.pedal = new Pedal();
    getContentPane().add(pedal.getButton());
  }

  public Pedal getPedal() {
    return this.pedal;
  }
}