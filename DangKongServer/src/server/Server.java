package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  private static Thread player1;
  private static Thread player2;

  public static void main(String[] args) {
    ServerSocket server = null;
    try {
      server = new ServerSocket(1225);
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Server is ready.");
    while (true) {
      try {
        Socket socket = server.accept();
        System.out.println("New connection");
        DataInputStream input = new DataInputStream(socket.getInputStream());
        String melody = input.readUTF();
        System.out.println(melody);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
