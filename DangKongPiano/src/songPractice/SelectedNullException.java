package songPractice;

import javax.swing.JLabel;

public class SelectedNullException extends Exception{
	public SelectedNullException(JLabel jLabel){
		jLabel.setText("YOU DON'T SELECT SONG");
	}
}

