package songPractice;

import javax.swing.JLabel;
import javax.swing.JPanel;
import piano.Piano;
import java.awt.Color;
import java.awt.Font;
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

	private Piano piano;
	private String song;
	private BufferedReader reader;
	public static BlockingQueue<String> q;
	private static boolean isOn = false;
	private String[] tokens;
	JLabel[] text;

	public PlayMusic(String song) {
		setLayout(null);
		setSize(1120, 550);
		setPiano();
		setSong(song);
		setBlockingQueue();
		getSong();
		listen();
	}

	void listen() {
		BlockingQ queue = new BlockingQ(this);
		queue.start();
	}

	public void setPiano() {
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

	public Piano getPiano() {
		return piano;
	}

	void setSong(String song) {
		this.song = song;
		System.out.println(this.song + " is selected ");
	}

	void getSong() {
		InputStream fis;
		try {
			fis = new FileInputStream("./resource/music/" + this.song + ".txt");
			Reader isr = new InputStreamReader(fis);
			setBr(new BufferedReader(isr));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	void setBlockingQueue() {
		q = new ArrayBlockingQueue<String>(50);
	}

	public static void setIsOn(boolean isOn) {
		PlayMusic.isOn = isOn;
	}

	public static boolean getIsOn() {
		return isOn;
	}

	public void setBr(BufferedReader br) {
		this.reader = br;
	}

	public BufferedReader getBr() {
		return reader;
	}

	public void setTokens(String[] tokens) {
		this.tokens = tokens;
	}

	public String[] getTokens() {
		return tokens;
	}

	public void setText(JLabel[] text) {
		this.text = text;
	}

	public JLabel[] getText() {
		return text;
	}
}

class BlockingQ extends Thread {
	PlayMusic playMusic;
	private String[] tokens;

	public BlockingQ(PlayMusic playMusic) {
		this.playMusic = playMusic;
		tokens = playMusic.getTokens();
	}

	public void run() {
		int length = 0;
		while (true) {
			try {
				length = readLine();
			} catch (IOException e) {
			  e.printStackTrace();
			}
			try {
				if (length == 0)
					break;
				else {
					int correctCount = 0;
					while (correctCount != length) {
						String melody = playMusic.q.take();
						if (melody.equals(tokens[correctCount])) {
							playMusic.text[correctCount].setForeground(Color.ORANGE);
							correctCount++;
						}
					}
				}
			} catch (InterruptedException e) {
			  e.printStackTrace();
			}
			delete(length);
		}
		setEnd();
	}

	int readLine() throws IOException {
		String data = playMusic.getBr().readLine();
		if (data == null) {
			return 0;
		}
		tokens = data.split(" ");
		int len = tokens.length;

		playMusic.text = new JLabel[len];
		for (int i = 0; i < len; i++) {
			playMusic.text[i] = setLetter(playMusic.text[i], i, tokens[i]);
			playMusic.text[i].setText(tokens[i]);
			playMusic.text[i].setForeground(Color.BLACK);
		}
		return len;
	}

	JLabel setLetter(JLabel label, int xpos, String melody) {
		String txt = melody;
		label = new JLabel(txt);
		label.setFont(new Font("Times New Roman", Font.BOLD, 50));
		label.setBounds(xpos * 100 + 100, 25, 100, 60);
		playMusic.add(label);
		return label;
	}

	void delete(int len) {
		for (int i = 0; i < len; i++) {
			playMusic.text[i].setText("");
			;
		}
	}

	void setEnd() {
		playMusic.text[0].setText("E");
		playMusic.text[0].setForeground(Color.RED);
		playMusic.text[0].setFont(new Font("Comic Sans MS", Font.BOLD, 60));
		playMusic.text[0].setBounds(200, 25, 100, 60);
		playMusic.text[1].setText("N");
		playMusic.text[1].setForeground(Color.BLUE);
		playMusic.text[1].setFont(new Font("Comic Sans MS", Font.BOLD, 60));
		playMusic.text[1].setBounds(350, 25, 100, 60);
		playMusic.text[2].setText("D");
		playMusic.text[2].setForeground(Color.BLACK);
		playMusic.text[2].setFont(new Font("Comic Sans MS", Font.BOLD, 60));
		playMusic.text[2].setBounds(500, 25, 100, 60);
	}

	public void setTokens(String[] tokens) {
		this.tokens = tokens;
	}

	public String[] getTokens() {
		return tokens;
	}

}
