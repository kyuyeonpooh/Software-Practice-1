package songPractice;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * Class for managing flow of SongPractice
 * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
 *
 */
public class SongPractice extends JFrame {
  
  /** button for start playing music */
  private JButton startButton;   
  /** panel for choosing song */
  private ChooseSong chooseSong;
  /** panel for playing music */
  private PlayMusic playMusic;   

  /**
   * Constructor of SongPractice
   */
  public SongPractice() {
    this.chooseSong = new ChooseSong();
    getContentPane().add(this.chooseSong);

    setStartButton();
    setTitle("Song Practice");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(1120, 550);
    setVisible(true);
    /* window listener to notify that this play mode is closed */
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        PlayMusic.setIsOn(false);
      }
    });
  }

  /**
   * Initialize start button
   */
  void setStartButton() {
    startButton = new JButton("START!");
    startButton.setForeground(Color.BLACK);
    startButton.setBackground(Color.WHITE);
    startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    /* starts play music for selected music */
    startButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        /* throws exception if nothing choosed */
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

  /**
   * Getter for startButton
   * @return startButton
   */
  public JButton getStartButton() {
    return startButton;
  }

 /**
  * Setter for chooseSong
  * @param chooseSong song user chosen in this panel
  */
  public void setChooseSong(ChooseSong chooseSong) {
    this.chooseSong = chooseSong;
  }

 /**
  * Getter for chooseSong
  * @return chooseSong
  */
  public ChooseSong getChooseSong() {
    return chooseSong;
  }

  /**
   * Setter for playMusic
   * @param playMusic set with this
   */
  public void setPlayMusic(PlayMusic playMusic) {
    this.playMusic = playMusic;
  }

  /**
   * Getter for playMusic
   * @return playMusic
   */
  public PlayMusic getPlayMusic() {
    return playMusic;
  }

}