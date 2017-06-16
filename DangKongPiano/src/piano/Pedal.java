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
 * class for pedal of piano
 * @author samsung
 *
 */
public class Pedal {
  
  private static boolean isPedaled;  /* state for pedal */

  private Color color;               /* color for pedal as state */
  private AbstractAction pressed;    /* AbstractAction for pressed */
  private JButton button;            /* button for pedal */

  /** constructor */
  public Pedal() {
    this.setIsPedaled(false);
    this.setColor();
    this.setPressed();
    this.setButton();
  }
  
  /**
   * class to change color of pedal when pressed
   * @author team2
   *
   */
  private class ColorEffect extends Thread {    
    
    @Override
    /** change button's color */
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
   * setter for isPedaled
   * @param isPedaled set this.isPedaled with it
   */
  public void setIsPedaled(boolean isPedaled) {
    Pedal.isPedaled = isPedaled;
  }  
  
  /**
   * getter for isPedaled
   * @return isPedaled
   */
  public static boolean getIsPedaled() {
    return isPedaled;
  }

  /** setter for color */
  public void setColor() {
    this.color = Color.RED;
  }

  /**
   * getter for color
   * @return Color
   */
  public Color getColor() {
    return color;
  }

  /** set Action to change isPedaled state when listen */
  public void setPressed() {
    this.pressed = new AbstractAction() {
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
   * getter for pressed 
   * @return AbstractAction
   */
  public AbstractAction getPressed() {
    return this.pressed;
  }

  /** setter for button */
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
   * getter for button
   * @return JButton
   */
  public JButton getButton() {
    return button;
  }

}