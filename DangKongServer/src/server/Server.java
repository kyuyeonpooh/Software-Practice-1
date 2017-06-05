package server;

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
        Thread thread = new CommThread(socket);
        thread.start();
        if(!player1.isAlive()){
          player1 = thread;
        }
        else if(!player2.isAlive()){
          player2 = thread;
        }
        else{
          /* implement user-defined exception here */
          System.out.println("Error");
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
