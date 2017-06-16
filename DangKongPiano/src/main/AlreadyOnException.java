package main;
/**
 * exception for when user opens same play mode which is already on
 * @author team 2
 *
 */
public class AlreadyOnException extends Exception {
  /**
   * constructor of AlreadyOnException 
   */
  public AlreadyOnException() {
    System.out.println("You can't open it twice.");
  }
  
}
