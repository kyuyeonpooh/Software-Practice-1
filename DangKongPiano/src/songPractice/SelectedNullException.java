package songPractice;

import javax.swing.JLabel;

/**
 * Exception for selecting nothing
 * @author team 2
 *
 */
public class SelectedNullException extends Exception {

  /**
   * Exception for selected nothing
   * @param jLabel to show to screen
   */
  public SelectedNullException(JLabel jLabel) {
    jLabel.setText("YOU DON'T SELECT SONG");
  }

}
