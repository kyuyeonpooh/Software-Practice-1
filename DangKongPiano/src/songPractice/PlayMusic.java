package songPractice;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import piano.Piano;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class PlayMusic extends JPanel {

  private static boolean isOn = false;

  private AbstractAction pressed;
  private Piano piano;
  private String song;
  private BufferedReader br;

  public PlayMusic(String song){
    setLayout(null);
    setSize(1100, 550);
    setPiano();
    setSong(song);
    getSong();
    readLines();
  }
  
  void readLines(){
	try {
		int len = readLine();
		listen(len);
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  
  void listen(int len){
//	setPressed();
//	setButton(keyCode, xBound);
  }
/*
  public void setPressed() {
	    this.pressed = new AbstractAction() {
	      @Override
	      public void actionPerformed(ActionEvent evt) {
	        try {
	          Thread effect = new ColorEffect();
	          effect.start();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    };
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
  */
  int readLine() throws IOException {
    String data = br.readLine();
    int len = data.length();
    JLabel[] text = new JLabel[len];
    for(int i = 0; i<len ; i++){
    	setLetter(text[i], i, data.charAt(i));
    }
    return len;
  }

  void setLetter(JLabel lbl, int xpos, char letter){
	String txt=Character.toString(letter);
	lbl = new JLabel(txt);
	lbl.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
	lbl.setBounds(xpos*100+100, 25, 60, 60);
	add(lbl);
  }
  
  void getSong(){
    InputStream fis;
	try {
		fis = new FileInputStream("./resource/music/" + this.song + ".txt");
	    Reader isr = new InputStreamReader(fis);
	    br = new BufferedReader(isr);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
  }

  void setSong(String song) {
	this.song = song;
    System.out.println(this.song+" is selected ");
  }

  void setPiano() {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          PlayMusic.setIsOn(true);
          piano = new Piano();
          piano.setLayout(null);
          piano.setBounds(0, 125, 1100, 351);
          add(piano);          
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public static boolean getIsOn() {
    return isOn;
  }

  public static void setIsOn(boolean isOn) {
    PlayMusic.isOn = isOn;
  }

}
