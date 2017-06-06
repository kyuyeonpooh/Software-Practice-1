package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  
  public static Thread player1;
  public static Thread player2;
  public static Socket socket1;
  public static Socket socket2;
  public static ServerSocket server;
  
  public static void main(String[] args) throws IOException {
    server = new ServerSocket(5000);
    System.out.println("Server is ready.");
    socket1 = server.accept();
    DataOutputStream output1 = new DataOutputStream(socket1.getOutputStream());
    output1.writeInt(1);
    System.out.println("Player 1 arrived!");
    socket2 = server.accept();
    DataOutputStream output2 = new DataOutputStream(socket2.getOutputStream());
    output2.writeInt(2);
    System.out.println("Player 2 arrived!");
    player1 = new ServerReceiver(socket1, 1);
    player2 = new ServerReceiver(socket2, 2);
    player1.start();
    player2.start();
  }

}
