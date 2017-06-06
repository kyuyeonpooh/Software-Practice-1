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
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PlayMusic extends JPanel {

  private static boolean isOn = false;

  private Piano piano;
  private String song;
  private BufferedReader br;
  public static BlockingQueue<String> q;
  private static boolean playOn=false;
  String[] tokens;
  static JLabel[] text;

  public PlayMusic(String song){
    setLayout(null);
    setSize(1100, 550);
    setPiano();
    setSong(song);
    setBlockingQueue();
    getSong();
    readLines();
  }
  
  void readLines(){
		int len = 0;
		while(true){
			try {
				len = readLine();
			} catch (IOException e) {
				
			}
			try {
				if(len==0)	break;
				listen(len);
			} catch (InterruptedException e) {
				
			}
		}

  }
 
  void listen(int len) throws InterruptedException{
	  BlockingQ q = new BlockingQ(this.q, len, text, tokens);
	  q.start();
  }
  
  void setBlockingQueue(){
	  q = new ArrayBlockingQueue<String>(20);
  }

  int readLine() throws IOException {
    String data = br.readLine();
    if(data==null)	return 0;
    tokens = data.split(" ");     
    int len = tokens.length;
    
    text = new JLabel[len];
    for(int i = 0; i<len ; i++){
    	text[i]=setLetter(text[i], i, tokens[i]);
    }
    return len;
  }
  
  JLabel setLetter(JLabel lbl, int xpos, String melody){
	String txt=melody;
	lbl = new JLabel(txt);
	lbl.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
	lbl.setBounds(xpos*200+100, 25, 200, 60);
	add(lbl);
	return lbl;
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
          PlayMusic.setPlayOn(true);
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

public static boolean isPlayOn() {
	return playOn;
}

public static void setPlayOn(boolean playOn) {
	PlayMusic.playOn = playOn;
}

}


class BlockingQ extends Thread{
	public BlockingQueue<String> q=null;
	int len;
	JLabel[] text;
	String[] tokens;
	
	public BlockingQ(BlockingQueue<String> q, int len, JLabel[] text, String[] tokens) {
		this.q=q;
		this.len=len;
		this.text=text;
		this.tokens=tokens;
	}

	public void run(){
		try {
			int correctCnt=0;
			while(correctCnt!=len){
				String s = q.take();
				if(s.equals(tokens[correctCnt])){
					System.out.println("Consumed: "+s);
					text[correctCnt].setForeground(Color.ORANGE);
					correctCnt++;
				}
				else{
					System.out.println("mis match: "+s+"/"+tokens[correctCnt]);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

