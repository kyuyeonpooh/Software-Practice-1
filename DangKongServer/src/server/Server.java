package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  private static Thread player1;
  private static Thread player2;
  private static Socket socket1;
  private static Socket socket2;
  private static ServerSocket server;

  public static void main(String[] args) throws IOException {
    setServer();
    setSocket1();
    setSocket2();
  }

  public static void setServer() throws IOException {
    server = new ServerSocket(5000);
    System.out.println("Server is ready.");
  }

  public static ServerSocket getServer() {
    return Server.server;
  }

  public static void setSocket1() throws IOException {
    socket1 = server.accept();
    DataOutputStream send1 = new DataOutputStream(socket1.getOutputStream());
    send1.writeUTF("You are player 1.");
    System.out.println("Player 1 arrived!");
  }

  public static Socket getSocket1() {
    return Server.socket1;
  }

  public static void setSocket2() throws IOException {
    socket2 = server.accept();
    DataOutputStream send1 = new DataOutputStream(socket2.getOutputStream());
    send1.writeUTF("You are player 2.");
    System.out.println("Player 2 arrived!");
  }

  public static Socket getSocket2() {
    return Server.socket2;
  }

  public static void setPlayer1() {
    player1 = new ServerCommunicator(socket1, 1);
    player1.start();
  }

  public static Thread getPlayer1() {
    return Server.player1;
  }

  public static void setPlayer2() {
    player2 = new ServerCommunicator(socket2, 2);
    player2.start();
  }

  public static Thread getPlayer2() {
    return Server.player2;
  }

}
