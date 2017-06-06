package songPractice;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SongPractice extends JFrame {

	private JButton startButton;
	private ChooseSong chooseSong;
	private PlayMusic playMusic;

	public SongPractice() {
		this.chooseSong = new ChooseSong();
		getContentPane().add(this.chooseSong);

		setStartButton();
		setTitle("Song Practice");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1120, 550);
		setVisible(true);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				PlayMusic.setIsOn(false);
			}
		});
	}

	void setStartButton() {
		startButton = new JButton("START!");
		startButton.setForeground(Color.BLACK);
		startButton.setBackground(Color.WHITE);
		startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chooseSong.getSelectedSong() == null) {
					new SelectedNullException(chooseSong.getTextLabel());
				} else {
					getContentPane().removeAll();
					playMusic = new PlayMusic(chooseSong.getSelectedSong());
					getContentPane().add(playMusic);
					revalidate();
					repaint();
				}
			}
		});

		startButton.setBounds(867, 22, 150, 72);
		chooseSong.add(startButton);
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setChooseSong(ChooseSong chooseSong) {
		this.chooseSong = chooseSong;
	}

	public ChooseSong getChooseSong() {
		return chooseSong;
	}

	public void setPlayMusic(PlayMusic playMusic) {
		this.playMusic = playMusic;
	}

	public PlayMusic getPlayMusic() {
		return playMusic;
	}

}
