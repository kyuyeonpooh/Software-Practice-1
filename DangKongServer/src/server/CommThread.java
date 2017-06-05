package server;

import java.net.Socket;

public class CommThread extends Thread {
  
  private Socket socket;
  
  public CommThread(Socket socket) {
    this.socket = socket;
  }
  
  @Override
  public void run() {
    super.run();
  }
  
}
