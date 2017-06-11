package piano;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;

/* class for keyboard of piano */
public class Keyboard {
 
  private String [] melodies;      /* string arrays for melody names for each key */
  private int [] keyEvents;        /* key event arrays for each key */
  private int [] buttonBounds;     /* xBounds for each key */
  private ArrayList<Key> keyboard; /* keyboard with has all keys in array list */
  private JButton [] buttons;      /* buttons for each key */
  
  /* constructor for keyboard */
  public Keyboard() throws IOException { 
    super();
    this.setMelodies();
    this.setKeyEvents();
    this.setButtonBounds();
    this.setKeyboard(); 
    this.setButtons();
  }
  
  /* initialize melodies */
  public void setMelodies() {
    this.melodies = new String []{
      "A0", "B0Flat", "B0", "C1", "C1Sharp",
      "D1", "E1Flat", "E1", "F1", "F1Sharp",
      "G1", "A1Flat", "A1", "B1Flat", "B1",
      "C2", "C2Sharp", "D2", "E2Flat", "E2",
      "F2", "F2Sharp", "G2", "A2Flat", "A2",
      "B2Flat", "B2"
    };
  }
    
  /* getter for melodies */
  public String [] getMelodies() {
    return this.melodies;
  }
    
  /* initialize keyEvents */
  public void setKeyEvents() {
    this.keyEvents = new int[]{
      KeyEvent.VK_Z, KeyEvent.VK_S, KeyEvent.VK_X, KeyEvent.VK_C, KeyEvent.VK_F, 
      KeyEvent.VK_V, KeyEvent.VK_G, KeyEvent.VK_B, KeyEvent.VK_N, KeyEvent.VK_J, 
      KeyEvent.VK_M, KeyEvent.VK_K, KeyEvent.VK_COMMA, KeyEvent.VK_L, KeyEvent.VK_PERIOD,
      KeyEvent.VK_Q, KeyEvent.VK_2, KeyEvent.VK_W, KeyEvent.VK_3, KeyEvent.VK_E,
      KeyEvent.VK_R, KeyEvent.VK_5, KeyEvent.VK_T, KeyEvent.VK_6, KeyEvent.VK_Y,
      KeyEvent.VK_7, KeyEvent.VK_U
    };
  }
    
  /* getter for keyEvents */
  public int [] getKeyEvents() {
    return this.keyEvents;
  }
    
  /* initialize buttonBounds */
  public void setButtonBounds() {
    this.buttonBounds = new int[]{
      25, 52, 83, 141, 169,
      199, 226, 257, 315, 341,
      373, 399, 430, 457, 488,
      546, 573, 604, 631, 662,
      720, 747, 778, 805, 836,
      863, 894
    };
  }
    
  /* getter for buttonBounds  */
  public int [] getButtonBounds() {
    return this.buttonBounds;
  }

  /* add each key to keyboard array list */
  public void setKeyboard() throws IOException {
    this.keyboard = new ArrayList<Key> ();    
    for(int i = 0; i < melodies.length; i++){
      Key key = new Key(melodies[i], keyEvents[i], buttonBounds[i]);
      this.keyboard.add(key);
    }
  }
  
  /* getter for keyboard */
  public ArrayList<Key> getKeyboard(){
    return this.keyboard;
  }
  
  /* set buttons of keyboard */
  public void setButtons() {
    this.buttons = new JButton[melodies.length];
    for(int i = 0; i < melodies.length; i++){
      buttons[i] = this.keyboard.get(i).getButton();
    }
  }
     
  /* getter for buttons */
  public JButton [] getButtons() {
     return this.buttons;
  }
     
}