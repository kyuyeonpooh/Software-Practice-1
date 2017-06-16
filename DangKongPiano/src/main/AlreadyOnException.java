package main;

/**
 * Exception for when user opens same play mode which is already on
 * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
 *
 */
public class AlreadyOnException extends Exception {
  
  /**
   * Constructor of AlreadyOnException 
   */
  public AlreadyOnException() {
    System.out.println("You can't open it twice.");
  }
  
}
