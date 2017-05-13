package piano;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Key {

  private String melody;
  private Color color;
  private File soundFile;
  private AudioInputStream audioInputStream;
  private Clip clip;
  private AbstractAction pressed;
  private JButton button;
  private Pedal pedal;

  public Key(String melody, int keyCode, int xBound) throws IOException {
    this.setMelody(melody);
    this.setColor();
    this.setPressed();
    this.setPedal(pedal);
    this.setButton(keyCode, xBound);
  }

  public void setMelody(String melody) {
    this.melody = melody;
  }

  public String reviseMelody(String melody) {

    if (Pedal.isPedaled())
      return melody + "_Pedal";
    else
      return melody;
  }

  public String getMelody() {
    return this.melody;
  }

  public void setColor() {
    if (this.melody.length() > 2)
      this.color = Color.BLACK;
    else
      this.color = Color.WHITE;
  }

  public Color getColor() {
    return color;
  }

  public void setSoundFile() {
    this.soundFile = new File("./resource/pianoSound/" + this.reviseMelody(melody) + ".wav");
  }

  public File getSoundFile() {
    return this.soundFile;
  }

  public void setAudioInputStream() throws IOException {
    try {
      this.setSoundFile();
      this.audioInputStream = AudioSystem.getAudioInputStream(this.soundFile);
    } catch (UnsupportedAudioFileException e) {
      e.printStackTrace();
    }
  }

  public AudioInputStream getAudioInputStream() {
    return this.audioInputStream;
  }

  public void setClip() throws IOException {
    try {
      this.clip = AudioSystem.getClip();
      this.clip.open(audioInputStream);
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    }
  }

  public Clip getClip() {
    return this.clip;
  }

  @SuppressWarnings("serial")
  public void setPressed() {
    this.pressed = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        try {
          setAudioInputStream();
          setClip();
          clip.start();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    };
  }

  public AbstractAction getPressed() {
    return this.pressed;
  }

  public void setButton(int keyCode, int xBound) {
    this.button = new JButton();
    this.button.addActionListener(this.pressed);
    this.button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, 0), this.melody);
    this.button.getActionMap().put(this.melody, this.pressed);
    this.button.setBackground(this.color);
    if (this.color == Color.BLACK)
      this.button.setBounds(xBound, 12, 60, 90);
    else
      this.button.setBounds(xBound, 101, 60, 200);
  }

  public JButton getButton() {
    return button;
  }

  public void setPedal(Pedal pedal) {
    this.pedal = pedal;
  }

  public Pedal getPedal() {
    return pedal;
  }

}
