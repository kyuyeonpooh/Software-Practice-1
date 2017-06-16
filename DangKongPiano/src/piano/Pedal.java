package piano;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.Font;

/**
 * Class for pedal that compose piano
 * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
 *
 */
public class Pedal {
	
  /** State of pedal */
  private static boolean isPedaled;  

  /** Color for pedal ON/OFF */
  private Color color;               
  /** Action for pedal pressed */
  private AbstractAction pressed;    
  /** Button for pedal */
  private JButton button;            

  /** Constructor of Pedal */
  public Pedal() {
    this.setIsPedaled(false);
    this.setColor();
    this.setPressed();
    this.setButton();
  }
  
  /**
   * Class to change color of pedal when pressed
   * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
   *
   */
  private class ColorEffect extends Thread {    
    
    /** change button's color in red or green */
    @Override
    public void run() {
      if(button.getBackground() == Color.RED){
        button.setForeground(Color.BLACK);
        button.setBackground(Color.GREEN);
        button.setText("ON");
      }
      else{
        button.setForeground(Color.WHITE);
        button.setBackground(Color.RED);
        button.setText("OFF");        
      }
    }
    
  }
  
  /**
   * Setter for isPedaled
   * @param isPedaled set with this
   */
  public void setIsPedaled(boolean isPedaled) {
    Pedal.isPedaled = isPedaled;
  }  
  
  /**
   * Getter for isPedaled
   * @return isPedaled
   */
  public static boolean getIsPedaled() {
    return isPedaled;
  }

  /**
   * Setter for color
   */
  public void setColor() {
    this.color = Color.RED;
  }

  /**
   * Getter for color
   * @return color
   */
  public Color getColor() {
    return color;
  }

  /** 
   * Set action to change isPedaled state 
   */
  public void setPressed() {
    this.pressed = new AbstractAction() {
      /**
       * Set isPedal to the other state
       */
      @Override
      public void actionPerformed(ActionEvent evt) {
        Thread effect = new ColorEffect();
        effect.start();
        if (isPedaled)
          setIsPedaled(false);
        else
          setIsPedaled(true);
      }
    };
  }

  /**
   * Getter for pressed 
   * @return pressed
   */
  public AbstractAction getPressed() {
    return this.pressed;
  }

  /**
   * Setter for button
   */
  public void setButton() {
    this.button = new JButton("OFF");
    this.button.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
    this.button.setVerticalTextPosition(JButton.CENTER);
    this.button.setForeground(Color.WHITE);
    this.button.addActionListener(this.pressed);
    this.button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "pedal");
    this.button.getActionMap().put("pedal", this.pressed);
    this.button.setBackground(this.color);
    this.button.setBounds(970, 101, 101, 101);
  }

  /**
   * Getter for button
   * @return button
   */
  public JButton getButton() {
    return button;
  }

}