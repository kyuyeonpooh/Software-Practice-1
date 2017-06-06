package songPractice;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SongPractice extends JFrame {

  private ChooseSong chooseSong;
  private PlayMusic playMusic;
  private JButton start;

  public SongPractice() {
    this.chooseSong = new ChooseSong();

    getContentPane().add(this.chooseSong);
    setButton();
    setTitle("Song Practice");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(1100, 550);
    setVisible(true);

    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        PlayMusic.setIsOn(false);
      }
    });
  }

  void setButton() {
    start = new JButton("START!");
    start.setForeground(Color.BLACK);
    start.setBackground(Color.WHITE);
    start.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    start.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (chooseSong.getSelectedSong() == null) {
          new SelectedNullException(chooseSong.getTextLabel());
        } else {
          playMusic = new PlayMusic(chooseSong.getSelectedSong());
          getContentPane().removeAll();
          getContentPane().add(playMusic);
          revalidate();
          repaint();
        }
      }
    });

    start.setBounds(867, 22, 150, 72);
    chooseSong.add(start);
  }
}
