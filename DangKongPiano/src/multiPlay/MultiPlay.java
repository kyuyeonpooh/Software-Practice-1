package multiPlay;

<<<<<<< HEAD
=======
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

>>>>>>> branch 'master' of https://github.com/poopbear/DangKong
import piano.Piano;

<<<<<<< HEAD
public class MultiPlay {
=======
public class MultiPlay extends JFrame {
>>>>>>> branch 'master' of https://github.com/poopbear/DangKong

<<<<<<< HEAD
	public MultiPlay() {
	}
=======
  private static boolean isOn = false;

  public MultiPlay() {
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        MultiPlay.setIsOn(false);
      }
    });
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          MultiPlay.setIsOn(true);
          Piano piano = new Piano();
          piano.setLayout(null);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setTitle("Multi Play");
          setSize(1120, 380);
          getContentPane().add(piano);
          setVisible(true);
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
    MultiPlay.isOn = isOn;
  }
>>>>>>> branch 'master' of https://github.com/poopbear/DangKong

}
