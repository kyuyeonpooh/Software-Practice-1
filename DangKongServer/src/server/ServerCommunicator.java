package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerCommunicator extends Thread {

  private Socket socket;
  private int id;

  public ServerCommunicator(Socket socket, int id) {
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
        if (id == 1) {
          DataOutputStream send = new DataOutputStream(Server.getSocket2().getOutputStream());
          send.writeUTF(melody);
        } else if (id == 2) {
          DataOutputStream send = new DataOutputStream(Server.getSocket1().getOutputStream());
          send.writeUTF(melody);
        }
      } catch (IOException e) {
        Server.getPlayer1().stop();
        Server.getPlayer2().stop();
      }
    }
  }

}
