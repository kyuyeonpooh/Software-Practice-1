package multiPlay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;


public class Client extends Thread implements ActionListener{
  @Override
  public void run() {
    try {
      Socket socket = new Socket("localhost", 1225);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
   
    
  }


}
