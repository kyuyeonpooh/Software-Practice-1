package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/* class for server of multi-play */
public class Server {

  private static Thread player1;      /* thread for player1 */
  private static Thread player2;      /* thread for player2 */
  private static Socket socket1;      /* socket for player1 */
  private static Socket socket2;      /* socket for playter2 */
  private static ServerSocket server; /* ServerSocket for multi-play */

  /* server on */
  public static void main(String[] args) throws IOException {
    setServer();
    setSocket1();
    setSocket2();
    setPlayer1();
    setPlayer2();
  }

  /* setter for server */
  public static void setServer() throws IOException {
    server = new ServerSocket(5000);
    System.out.println("Server is ready.");
  }

  /* getter for server */
  public static ServerSocket getServer() {
    return Server.server;
  }

  /* set socket for player 1 */
  public static void setSocket1() throws IOException {
    socket1 = server.accept();
    DataOutputStream send1 = new DataOutputStream(socket1.getOutputStream());
    send1.writeUTF("You are player 1.");
    System.out.println("Player 1 arrived!");
  }

  /* getter for socket 1 */
  public static Socket getSocket1() {
    return Server.socket1;
  }

  /* set socket for player 2 */
  public static void setSocket2() throws IOException {
    socket2 = server.accept();
    DataOutputStream send1 = new DataOutputStream(socket2.getOutputStream());
    send1.writeUTF("You are player 2.");
    System.out.println("Player 2 arrived!");
  }

  /* getter for socket 2 */
  public static Socket getSocket2() {
    return Server.socket2;
  }

  /* create serverCommunicator for player1, and start */
  public static void setPlayer1() {
    player1 = new ServerCommunicator(socket1, 1);
    player1.start();
  }

  /* getter for player 1 */
  public static Thread getPlayer1() {
    return Server.player1;
  }

  /* create serverCommunicator for player2, and start */
  public static void setPlayer2() {
    player2 = new ServerCommunicator(socket2, 2);
    player2.start();
  }

  /* getter for player 2 */
  public static Thread getPlayer2() {
    return Server.player2;
  }

}