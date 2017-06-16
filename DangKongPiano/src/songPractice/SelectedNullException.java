package songPractice;

import javax.swing.JLabel;

/**
 * Exception for selecting nothing
 * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
 *
 */
public class SelectedNullException extends Exception {

  /**
   * Exception for selected nothing
   * @param jLabel label with message shown on the screen
   */
  public SelectedNullException(JLabel jLabel) {
    jLabel.setText("YOU DON'T SELECT SONG");
  }

}
