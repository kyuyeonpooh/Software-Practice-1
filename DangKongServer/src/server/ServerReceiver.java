package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerReceiver extends Thread {
  
  private Socket socket;
  private int id;
  
  public ServerReceiver(Socket socket, int id) {
    this.socket = socket;
    this.id = id;
  }
  
  @Override
  public void run() {
    while (true) {
      try {
        DataInputStream receive = new DataInputStream(socket.getInputStream());
        String melody = receive.readUTF();
        System.out.println(melody);
        if(id == 1){
          DataOutputStream send = new DataOutputStream(Server.socket2.getOutputStream());
          send.writeUTF(melody);
        }
        else{
          DataOutputStream send = new DataOutputStream(Server.socket1.getOutputStream());
          send.writeUTF(melody);
        }
      } catch (IOException e) {
        Server.player1.stop();
        Server.player2.stop();
      }      
    }
  }
  
}
