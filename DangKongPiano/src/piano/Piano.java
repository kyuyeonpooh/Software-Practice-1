package piano;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class for instantiating piano
 * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
 *
 */
public class Piano extends JPanel {

  /** Keyboard of piano */
  private Keyboard keyboard;   
  /** Pedal of piano */
  private Pedal pedal;         

  /**
   * Constructor of Piano
   * @throws IOException for missing sound file
   */
  public Piano() throws IOException {
    this.setKeyboard();
    this.setPedal();
    this.setPanel();
  }

  
  /**
   * Initialize keyboard
   * @throws IOException for invalid sound file
   */
  public void setKeyboard() throws IOException {
    this.keyboard = new Keyboard();
    for (int i = 0; i < keyboard.getMelodies().length; i++)
      add(keyboard.getButtons()[i]);
  }

  /**
   * Getter for keyboard
   * @return keyboard
   */
  public Keyboard getKeyboard() {
    return this.keyboard;
  }

  /** 
   * Initialize pedal
   */
  public void setPedal() {
    this.pedal = new Pedal();
    add(pedal.getButton());
  }

  /**
   * Getter for pedal
   * @return pedal
   */
  public Pedal getPedal() {
    return this.pedal;
  }

  /**
   * Set label for "Pedal Mode"
   */
  public void setPanel() {
    JLabel pedalMode = new JLabel("Pedal Mode");
    pedalMode.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
    pedalMode.setBounds(972, 78, 110, 18);
    add(pedalMode);
  }

}