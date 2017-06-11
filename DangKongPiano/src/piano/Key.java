package piano;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import multiPlay.MultiPlay;
import songPractice.PlayMusic;

/* key class forms keyboard */
public class Key {

  private String melody;                      /* melody name for each key */
  private Color color;                        /* black or white color */
  private File soundFile;                     /* sound file which will be played */
  private AudioInputStream audioInputStream;  /* audio stream needed to play music file */
  private Clip clip;                          /* clip needed for audio stream */
  private AbstractAction pressed;             /* action what to do when key is pressed */
  private JButton button;                     /* key button on the screen */
  
  /* constructor for key class */
  public Key(String melody, int keyCode, int xBound) throws IOException {
    this.setMelody(melody);
    this.setColor();
    this.setPressed();
    this.setButton(keyCode, xBound);
  }
  
  /* color effect which shows what user pressed with gray color */
  private class ColorEffect extends Thread {
    
    @Override
    public void run() {
      button.setBackground(Color.GRAY);
      try {
        Thread.sleep(200);
        button.setBackground(color);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    
  }
  
  /* setter for melody name */
  public void setMelody(String melody) {
    this.melody = melody;
  }
  
  /* revise melody when pedal mode is on */
  public String reviseMelody(String melody) {
    if (Pedal.getIsPedaled())
      return melody + "_Pedal";
    else
      return melody;
  }
  
  /* return melody name */
  public String getMelody() {
    return this.melody;
  }
  
  /* set which color among white and black */
  public void setColor() {
    if (this.melody.length() > 2)
      this.color = Color.BLACK;
    else
      this.color = Color.WHITE;
  }
  
  /* getter for color */
  public Color getColor() {
    return color;
  }
  
  /* initialize sound file with given melody */
  public void setSoundFile() {
    this.soundFile = new File("./resource/pianoSound/" + this.reviseMelody(this.melody) + ".wav");
  }
  
  /* getter for sound file */
  public File getSoundFile() {
    return this.soundFile;
  }
  
  /* initialize audio input stream */
  public void setAudioInputStream() throws IOException {
    try {
      this.setSoundFile();
      this.audioInputStream = AudioSystem.getAudioInputStream(this.soundFile);
    } catch (UnsupportedAudioFileException e) {
      e.printStackTrace();
    }
  }
  
  /* getter for audio input stream */
  public AudioInputStream getAudioInputStream() {
    return this.audioInputStream;
  }
  
  /* initialize clip which will be used for playing sound file */
  public void setClip() throws IOException {
    try {
      this.clip = AudioSystem.getClip();
      this.clip.open(this.audioInputStream);
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    }
  }
  
  /* getter for clip */
  public Clip getClip() {
    return this.clip;
  }
  
  /* set action for when button is pressed */
  public void setPressed() {
    this.pressed = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        try {
          setAudioInputStream();
          setClip();
          /* put melody in multiplay blocking queue */
          if(MultiPlay.getIsOn()){
            MultiPlay.getQueue().put(melody);
          }
          /* put melody in playmusic blocking queue */
          else if(PlayMusic.getIsOn()){
            PlayMusic.getQueue().put(melody);
          }
          /* shows pressed effect */
          Thread effect = new ColorEffect();
          effect.start();
          /* play music file */
          clip.start();
        } catch (IOException | InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
  }
  
  /* return action */
  public AbstractAction getPressed() {
    return this.pressed;
  }
  
  /* initialize buttons with bounds and listeners */
  public void setButton(int keyCode, int xBound) {
    this.button = new JButton();
    this.button.addActionListener(this.pressed);
    /* key bindings with maps */
    this.button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, 0), this.melody);
    this.button.getActionMap().put(this.melody, this.pressed);
    this.button.setBackground(this.color);
    if (this.color == Color.BLACK)
      this.button.setBounds(xBound, 12, 60, 90);
    else
      this.button.setBounds(xBound, 101, 60, 200);
  }
  
  /* return key button */
  public JButton getButton() {
    return button;
  }

}