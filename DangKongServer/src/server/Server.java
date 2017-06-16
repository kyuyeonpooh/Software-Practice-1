package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class for server in MultiPlay mode
 * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
 *
 */
public class Server {
  
  /** Thread for player1 */
  private static Thread player1;
  /** Thread for player2 */
  private static Thread player2;
  /** Socket for player1 */
  private static Socket socket1;
  /** Socket for player2 */
  private static Socket socket2;
  /** ServerSocket for MulitPlay */
  private static ServerSocket server;

  /**
   * Main function for server
   * @param args nothing
   * @throws IOException for invalid server
   */
  public static void main(String[] args) throws IOException {
    setServer();
    setSocket1();
    setSocket2();
    setPlayer1();
    setPlayer2();
  }

  /**
   * Initialize server
   * @throws IOException for invalid server
   * You can change port number here
   */
  public static void setServer() throws IOException {
    server = new ServerSocket(5000);
    System.out.println("Server is ready.");
  }

  /**
   * Getter for server
   * @return server
   */
  public static ServerSocket getServer() {
    return Server.server;
  }

  /**
   * Set socket for player1
   * @throws IOException for invalid connection
   */
  public static void setSocket1() throws IOException {
    socket1 = server.accept();
    DataOutputStream send1 = new DataOutputStream(socket1.getOutputStream());
    send1.writeUTF("You are player 1.");
    System.out.println("Player 1 arrived!");
  }

  /**
   * Getter for socket1
   * @return socket1
   */
  public static Socket getSocket1() {
    return Server.socket1;
  }

  /**
   * Set socket for player2
   * @throws IOException for invalid connection
   */
  public static void setSocket2() throws IOException {
    socket2 = server.accept();
    DataOutputStream send1 = new DataOutputStream(socket2.getOutputStream());
    send1.writeUTF("You are player 2.");
    System.out.println("Player 2 arrived!");
  }

  /**
   * Getter for socket2
   * @return socket2
   */
  public static Socket getSocket2() {
    return Server.socket2;
  }

  /**
   * Create serverCommunicator for player1, and start
   */
  public static void setPlayer1() {
    player1 = new ServerCommunicator(socket1, 1);
    player1.start();
  }

  /**
   * Getter for player1
   * @return player1
   */
  public static Thread getPlayer1() {
    return Server.player1;
  }

  /**
   * Create serverCommunicator for player2, and start
   */
  public static void setPlayer2() {
    player2 = new ServerCommunicator(socket2, 2);
    player2.start();
  }

  /**
   * Getter for player1
   * @return player1
   */
  public static Thread getPlayer2() {
    return Server.player2;
  }

}