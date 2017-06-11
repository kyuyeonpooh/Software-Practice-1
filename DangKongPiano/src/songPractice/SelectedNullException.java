package songPractice;

import javax.swing.JLabel;

/* Exception for selecting nothing */
public class SelectedNullException extends Exception {

  public SelectedNullException(JLabel jLabel) {
    jLabel.setText("YOU DON'T SELECT SONG");
  }

}
