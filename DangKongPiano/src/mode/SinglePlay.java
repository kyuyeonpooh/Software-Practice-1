package mode;

import java.awt.EventQueue;
import piano.Piano;

public class SinglePlay {
  private static int IsOn=0;
	
  public SinglePlay() {
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

public static int getIsOn() {
	return IsOn;
}

public static void setIsOn(int isOn) {
	IsOn = isOn;
}

}