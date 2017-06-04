package piano;

import java.io.IOException;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class Piano extends JPanel implements Serializable {

  private Keyboard keyboard;
  private Pedal pedal;

  public Piano() throws IOException {
    this.setPanel();
    this.setKeyboard();
    this.setPedal();
  }

  public void setPanel() {
    JLabel pedalMode = new JLabel("Pedal Mode");
    pedalMode.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
    pedalMode.setBounds(972, 78, 110, 18);
    add(pedalMode);
  }

  public void setKeyboard() throws IOException {
    this.keyboard = new Keyboard();
    for (int i = 0; i < keyboard.getMelodies().length; i++)
      add(keyboard.getButtons()[i]);
  }

  public Keyboard getKeyboards() {
    return this.keyboard;
  }

  public void setPedal() {
    this.pedal = new Pedal();
    add(pedal.getButton());
  }

  public Pedal getPedal() {
    return this.pedal;
  }

}