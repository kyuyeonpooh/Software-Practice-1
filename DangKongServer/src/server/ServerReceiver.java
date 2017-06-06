package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerReceiver extends Thread {
  
  private Socket socket;
  
  public ServerReceiver(Socket socket) {
    this.socket = socket;
  }
  
  @Override
  public void run() {
    while (true) {
      try {
        DataInputStream input = new DataInputStream(socket.getInputStream());
        String melody = input.readUTF();
        System.out.println(melody);
      } catch (IOException e) {
        Server.player1.stop();
        Server.player2.stop();
      }
    }
  }
  
}
