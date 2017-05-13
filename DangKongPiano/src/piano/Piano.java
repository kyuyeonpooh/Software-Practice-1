package piano;

import javax.swing.JFrame;
import java.awt.EventQueue;
import java.io.IOException;

public class Piano {

  private JFrame frame;
  private Keyboard keyboard;

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
    this.setKeyboard();
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

  public void setKeyboard() throws IOException {
    this.keyboard = new Keyboard();
    for (int i = 0; i <= keyboard.getMelodies().length; i++)
      frame.getContentPane().add(keyboard.getButtons()[i]);
  }

  public Keyboard getKeyboards() {
    return this.keyboard;
  }

}