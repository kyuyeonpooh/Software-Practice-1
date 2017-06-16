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

import multiPlay.MultiPlay;
import songPractice.PlayMusic;

/**
 * Key class forms keyboard
 * @author Team 2 ; Kim Kyu Yeon, Kim Yeon Jae
 *
 */
public class Key {

  /** Melody name for each key */
  private String melody;           
  /** Black or white color */
  private Color color;       
  /** Sound file which will be played */
  private File soundFile;               
  /** Audio stream needed to play music file */
  private AudioInputStream audioInputStream;  
  /** Clip needed for audio stream */
  private Clip clip;                 
  /** Action what to do when key is pressed */
  private AbstractAction pressed;             
  /** Key button on the screen */
  private JButton button;                     
  
  /**
   * Constructor of key class
   * @param melody melody name for each key
   * @param keyCode	key code for listening action
   * @param xBound x position of button bound
   * @throws IOException for invalid sound file
   */
  public Key(String melody, int keyCode, int xBound) throws IOException {
    this.setMelody(melody);
    this.setColor();
    this.setPressed();
    this.setButton(keyCode, xBound);
  }
  
  /**
   * Color effect which paints key in gray color which user pressed
   * @author Team2 : Kim Kyu Yeon, Kim Yeon Jae
   *
   */
  private class ColorEffect extends Thread {
        
    /**
     * Change color of button in gray color for 200ms
     */
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
  
  /**
   * Setter for melody name
   * @param melody set melody name with this
   */
  public void setMelody(String melody) {
    this.melody = melody;
  }
  
  /**
   * Revise melody when pedal mode is on
   * @param melody original
   * @return melody revised
   */
  public String reviseMelody(String melody) {
    if (Pedal.getIsPedaled())
      return melody + "_Pedal";
    else
      return melody;
  }
  
  /**
   * Return melody name
   * @return melody
   */
  public String getMelody() {
    return this.melody;
  }
  
  /** Set which color among white and black */
  public void setColor() {
    if (this.melody.length() > 2)
      this.color = Color.BLACK;
    else
      this.color = Color.WHITE;
  }
  
  /**
   * Getter for color
   * @return color
   */
  public Color getColor() {
    return color;
  }
  
  /** Initialize sound file with given melody */
  public void setSoundFile() {
    this.soundFile = new File("./resource/pianoSound/" + this.reviseMelody(this.melody) + ".wav");
  }
  
  /**
   * Getter for sound file
   * @return soundFile
   */
  public File getSoundFile() {
    return this.soundFile;
  }
  
  /**
   * Initialize audio input stream
   * @throws IOException for invalid sound file
   */
  public void setAudioInputStream() throws IOException {
    try {
      this.setSoundFile();
      this.audioInputStream = AudioSystem.getAudioInputStream(this.soundFile);
    } catch (UnsupportedAudioFileException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Getter for audio input stream
   * @return autioInputStream
   */
  public AudioInputStream getAudioInputStream() {
    return this.audioInputStream;
  }
  
  /**
   * Initialize clip which will be used for playing sound file
   * @throws IOException for sound file
   */
  public void setClip() throws IOException {
    try {
      this.clip = AudioSystem.getClip();
      this.clip.open(this.audioInputStream);
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Getter for clip
   * @return clip
   */
  public Clip getClip() {
    return this.clip;
  }
  
  /** Set action for when button is pressed */
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
  
  /**
   * Return pressed
   * @return pressed
   */
  public AbstractAction getPressed() {
    return this.pressed;
  }
  
  /**
   * Initialize buttons with bounds and listeners
   * @param keyCode unique key code for each key button
   * @param xBound x position of bound
   */
  public void setButton(int keyCode, int xBound) {
    this.button = new JButton();
    this.button.addActionListener(this.pressed);
    /* Key bindings with maps */
    this.button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, 0), this.melody);
    this.button.getActionMap().put(this.melody, this.pressed);
    this.button.setBackground(this.color);
    if (this.color == Color.BLACK)
      this.button.setBounds(xBound, 12, 60, 90);
    else
      this.button.setBounds(xBound, 101, 60, 200);
  }
  
  /**
   * Return key button
   * @return button
   */
  public JButton getButton() {
    return button;
  }

}