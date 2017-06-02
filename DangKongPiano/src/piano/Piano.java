package piano;

import javax.swing.JFrame;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.JLabel;

import mode.SinglePlay;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Piano extends JFrame implements Serializable {
  private static int numberOfPiano=0;
  private Keyboard keyboard;
  private Pedal pedal;

  public Piano() throws IOException {
	Piano.numberOfPiano++;  
	  
    this.setFrame();
    this.setKeyboard();
    this.setPedal();
  
    this.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent e) { 
               Piano.numberOfPiano--;
        }
    });
  }

  public void setFrame() {
    JLabel pedalMode = new JLabel("Pedal Mode");
    pedalMode.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
    pedalMode.setBounds(972, 78, 110, 18);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setBounds(100, 100, 1100, 372);
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

public static int getNumberOfPiano() {
	return numberOfPiano;
}

public static void setNumberOfPiano(int numberOfPiano) {
	Piano.numberOfPiano = numberOfPiano;
}
}