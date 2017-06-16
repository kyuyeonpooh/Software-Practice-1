package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Thread for communicating between server and player
 * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
 * 
 */
public class ServerCommunicator extends Thread {
  
  /** Socket to communicate with each player */
  private Socket socket;
  /* Player number */
  private int id;
  
  /**
   * Constructor of ServerCommunicator
   * @param socket socket for each player
   * @param id player number
   */
  public ServerCommunicator(Socket socket, int id) {
    this.socket = socket;
    this.id = id;
  }
  
  /**
   * Send melody to the other player
   * Player1 to Player2, Player2 to Player1
   */
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
