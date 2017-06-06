package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
  
  private static Thread player1;
  private static Thread player2;

  public static void main(String[] args) throws IOException {
    ServerSocket server = null;
    server = new ServerSocket(1225);
    System.out.println("Server is ready.");

    try {
		System.out.println(InetAddress.getLocalHost().getHostAddress());
	} catch (UnknownHostException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
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

    Socket socket1 = server.accept();
    DataOutputStream output1 = new DataOutputStream(socket1.getOutputStream());
    output1.writeInt(1);
    System.out.println("Player 1 arrived!");
    Socket socket2 = server.accept();
    DataOutputStream output2 = new DataOutputStream(socket2.getOutputStream());
    output2.writeInt(2);
    System.out.println("Player 2 arrived!");
    player1 = new ServerReceiver(socket1);
    player2 = new ServerReceiver(socket2);
    player1.start();
    player2.start();
  }

}
