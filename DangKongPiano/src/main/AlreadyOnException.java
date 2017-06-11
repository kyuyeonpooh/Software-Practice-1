package main;

/* exception for when user opens same play mode which is already on */
public class AlreadyOnException extends Exception {
  
  public AlreadyOnException() {
    System.out.println("You can't open it twice.");
  }
  
}
