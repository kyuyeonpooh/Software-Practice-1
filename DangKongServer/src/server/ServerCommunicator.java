package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerCommunicator extends Thread {

  private Socket socket;  /* socket to communicate with each player */
  private int id;         /* player number */
  
  /* constructor for class serer communicator */
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
        /* if player 1, send melody data to player 2 */
        if (id == 1) {
          DataOutputStream send = new DataOutputStream(Server.getSocket2().getOutputStream());
          send.writeUTF(melody);
        } else if (id == 2) { /* if player 2, send melody data to player 1 */
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
