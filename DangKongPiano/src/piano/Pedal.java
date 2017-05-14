package piano;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

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
  
  public void setIsPedaled(boolean isPedaled) {
    Pedal.isPedaled = isPedaled;
  }
  
  
  public static boolean getIsPedaled() {
    return isPedaled;
  }

  public void setColor() {
    this.color = Color.ORANGE;
  }

  public Color getColor() {
    return color;
  }

  @SuppressWarnings("serial")
  public void setPressed() {
    this.pressed = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent evt) {
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
    this.button = new JButton("PEDAL");
    this.button.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
    this.button.addActionListener(this.pressed);
    this.button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "pedal");
    this.button.getActionMap().put("pedal", this.pressed);
    this.button.setBackground(this.color);
    this.button.setBounds(970, 101, 101, 101);
  }

  public JButton getButton() {
    return button;
  }

}
