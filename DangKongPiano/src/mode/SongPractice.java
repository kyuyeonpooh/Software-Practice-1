package mode;

import java.awt.EventQueue;
import java.io.IOException;

import piano.Piano;

public class SongPractice {
	  public SongPractice() {
		    EventQueue.invokeLater(new Runnable() {
		      public void run() {
		        try {
		          Piano piano = new Piano();
		          piano.setVisible(true);
		        } catch (Exception e) {
		          e.printStackTrace();
		        }
		      }
		    });
		  }
}
