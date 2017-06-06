package singlePlay;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;

import piano.Piano;

public class SinglePlay extends JFrame {

  private static boolean isOn = false;
  
  private Piano piano;

  public SinglePlay() {
    SinglePlay.setIsOn(true);
    setPiano();
    setTitle("Single Play");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(null);
    setSize(1120, 500);
    getContentPane().setBackground(Color.ORANGE);
    setVisible(true);
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        SinglePlay.setIsOn(false);
      }
    });      
  }
  
  public void setPiano(){
    try {
      piano = new Piano();
    } catch (IOException e) {
      e.printStackTrace();
    }
    piano.setBackground(Color.ORANGE);
    piano.setLayout(null);
    piano.setBounds(0, 70, 1100, 351);
    add(piano);
  }

  public Piano getPiano(){
    return this.piano;
  }
  
  public static void setIsOn(boolean isOn) {
    SinglePlay.isOn = isOn;
  }
  
  public static boolean getIsOn() {
    return isOn;
  }

}