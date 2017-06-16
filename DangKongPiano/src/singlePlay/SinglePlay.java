package singlePlay;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;

import piano.Piano;

/**
 * class for single play
 * @author team2
 *
 */
public class SinglePlay extends JFrame {

  private static boolean isOn = false;   /* state of single play */
  
  private Piano piano;                   /* piano */

  /** constructor for single play */
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
  
  /** set piano to screen */
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
   * getter for piano
   * @return Piano
   */
  public Piano getPiano(){
    return this.piano;
  }
  
  /**
   * setter for isOn
   * @param isOn set this.isOn with it
   */
  public static void setIsOn(boolean isOn) {
    SinglePlay.isOn = isOn;
  }
  
  /**
   * getter for isOn
   * @return isOn
   */
  public static boolean getIsOn() {
    return isOn;
  }

}