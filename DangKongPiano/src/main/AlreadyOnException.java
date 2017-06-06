package main;

public class AlreadyOnException extends Exception {
  
  public AlreadyOnException() {
    System.out.println("You can't open it twice.");
  }
  
}
