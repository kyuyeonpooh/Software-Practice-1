package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  
  private static Thread player1;
  private static Thread player2;

  public static void main(String[] args) throws IOException {
    ServerSocket server = null;
    server = new ServerSocket(1225);
    System.out.println("Server is ready.");
    Socket socket1 = server.accept();
    System.out.println("Player 1 arrived!");
    Socket socket2 = server.accept();
    System.out.println("Player 2 arrived!");
    player1 = new ServerReceiver(socket1);
    player2 = new ServerReceiver(socket2);
    player1.start();
    player2.start();
  }

}
