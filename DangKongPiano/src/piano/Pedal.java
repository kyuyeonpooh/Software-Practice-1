package piano;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.Font;

public class Pedal {

  private Color color;
  private static boolean pedaled;
  private AbstractAction pressed;
  private JButton button;

  public Pedal(int keyCode, int xBound) throws IOException {
    this.setColor();
    this.setPressed();
    this.setButton(keyCode, xBound);
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
      public void actionPerformed(ActionEvent evt) {
        if (pedaled)
          setPedaled(false);
        else
          setPedaled(true);
      }
    };
  }

  public AbstractAction getPressed() {
    return this.pressed;
  }

  public void setButton(int keyCode, int xBound) {
    this.button = new JButton("PEDAL");
    button.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
    this.button.addActionListener(this.pressed);
    this.button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, 0), "pedal");
    this.button.getActionMap().put("pedal", this.pressed);
    this.button.setBackground(this.color);
    this.button.setBounds(xBound, 101, 101, 101);
  }

  public JButton getButton() {
    return button;
  }

  public static boolean isPedaled() {
    return pedaled;
  }

  public void setPedaled(boolean pedaled) {
    Pedal.pedaled = pedaled;
  }

}
