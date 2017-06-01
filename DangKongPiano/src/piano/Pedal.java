package piano;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.Font;

public class Pedal {
  
  private static boolean isPedaled;

  private Color color;  
  private AbstractAction pressed;
  private JButton button;

  public Pedal() {
    this.setIsPedaled(false);
    this.setColor();
    this.setPressed();
    this.setButton();
  }
  
  private class ColorEffect extends Thread {    
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
  
  public void setIsPedaled(boolean isPedaled) {
    Pedal.isPedaled = isPedaled;
  }  
  
  public static boolean getIsPedaled() {
    return isPedaled;
  }

  public void setColor() {
    this.color = Color.RED;
  }

  public Color getColor() {
    return color;
  }

  @SuppressWarnings("serial")
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

  public AbstractAction getPressed() {
    return this.pressed;
  }

  public void setButton() {
    this.button = new JButton("OFF");
    this.button.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
    this.button.setVerticalTextPosition(JButton.CENTER);
    this.button.setForeground(Color.WHITE);
    this.button.addActionListener(this.pressed);
    this.button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, 0), "pedal");
    this.button.getActionMap().put("pedal", this.pressed);
    this.button.setBackground(this.color);
    this.button.setBounds(970, 101, 101, 101);
  }

  public JButton getButton() {
    return button;
  }

}
