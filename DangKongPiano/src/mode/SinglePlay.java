package mode;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import piano.Piano;

public class SinglePlay extends JFrame {
	private static int numberOf = 0;

	public SinglePlay() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SinglePlay.setNumberOf(SinglePlay.getNumberOf() + 1);
					
					Piano piano = new Piano();
				    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				    setTitle("Single Play");
				    setSize(1100,450); 
					getContentPane().add(piano);
					piano.setLayout(null);
					setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				SinglePlay.setNumberOf(SinglePlay.getNumberOf() - 1);
			}
		});
	}

	public static int getNumberOf() {
		return numberOf;
	}

	public static void setNumberOf(int numberOfPiano) {
		SinglePlay.numberOf = numberOfPiano;
	}

}