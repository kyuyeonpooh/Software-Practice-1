package singlePlay;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;

import piano.Piano;

/**
 * Class with window for multiplay mode
 * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
 *
 */
public class SinglePlay extends JFrame {
  
  /** State of single play */
  private static boolean isOn = false;   
  
  /** Piano which will be shown on the screen */
  private Piano piano;                   

  /** Constructor of SinglePlay */
  public SinglePlay() {
    SinglePlay.setIsOn(true);
    setPiano();
    setTitle("Single Play");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(null);
    setSize(1120, 500);
    setVisible(true);
    /* window listener to notify that this play mode is closed */
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        SinglePlay.setIsOn(false);
      }
    });      
  }
  
  /**
   * Set piano on the screen
   */
  public void setPiano(){
    try {
      piano = new Piano();
    } catch (IOException e) {
      e.printStackTrace();
    }
    piano.setLayout(null);
    piano.setBounds(0, 70, 1100, 351);
    add(piano);
  }

  /**
   * Getter for piano
   * @return piano
   */
  public Piano getPiano(){
    return this.piano;
  }
  
  /**
   * Setter for isOn
   * @param isOn current state of SinglePlay mode
   */
  public static void setIsOn(boolean isOn) {
    SinglePlay.isOn = isOn;
  }
  
  /**
   * Getter for isOn
   * @return isOn
   */
  public static boolean getIsOn() {
    return isOn;
  }

}