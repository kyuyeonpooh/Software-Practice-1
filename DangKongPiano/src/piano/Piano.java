package piano;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Piano extends JPanel {

  private Keyboard keyboard;
  private Pedal pedal;

  public Piano() throws IOException {
    this.setKeyboard();
    this.setPedal();
    this.setPanel();
  }

  public void setKeyboard() throws IOException {
    this.keyboard = new Keyboard();
    for (int i = 0; i < keyboard.getMelodies().length; i++)
      add(keyboard.getButtons()[i]);
  }

  public Keyboard getKeyboard() {
    return this.keyboard;
  }

  public void setPedal() {
    this.pedal = new Pedal();
    add(pedal.getButton());
  }

  public Pedal getPedal() {
    return this.pedal;
  }

  public void setPanel() {
    JLabel pedalMode = new JLabel("Pedal Mode");
    pedalMode.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
    pedalMode.setBounds(972, 78, 110, 18);
    add(pedalMode);
  }

}