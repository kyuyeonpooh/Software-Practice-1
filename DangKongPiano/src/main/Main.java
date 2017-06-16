package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import multiPlay.MultiPlay;
import singlePlay.SinglePlay;
import songPractice.PlayMusic;
import songPractice.SongPractice;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;

/**
 * New window generated for main menu
 * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
 *
 */
public class Main extends JFrame {
  
  /** Panel with background, title, and buttons */
  private JPanel mainPanel;  
  /** Title label */
  private JLabel titleLabel;      
  /** Buttons with three play modes */
  private JButton[] buttons;    
  /** Action what to do when each button is pressed */
  private AbstractAction pressed;  
  
  /**
   * Main function
   * @param args nothing
   */
  public static void main(String[] args) {
    new Main();
  }
  
  /**
   * Constructor of Main class
   */
  public Main() {
    setTitle("DangKongPiano");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);
    setSize(900, 500);
    this.setPanel();
    this.setTitleLabel("DangKongPiano");
    this.setPressed();
    this.setButtons();
    setContentPane(mainPanel);
    setVisible(true);
  }
  
  /**
   * Set mainPanel background with given image
   */
  public void setPanel() {
    ImageIcon background = new ImageIcon("./resource/Image/background.jpg");
    mainPanel = new JPanel() {
      public void paintComponent(Graphics g) {
        g.drawImage(background.getImage(), 0, 0, null);
        setOpaque(false);
        super.paintComponent(g);
      }
    };
    mainPanel.setLayout(null);
  }
  
  /**
   * Getter for mainPanel
   * @return mainPanel
   */
  public JPanel getPanel() {
    return this.mainPanel;
  }
  
  /**
   * Setter for titleLabel 
   * @param titleName set titleLable in this name
   */
  public void setTitleLabel(String titleName) {
    titleLabel = new JLabel();
    titleLabel.setBounds(220, 32, 500, 55);
    titleLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 50));
    titleLabel.setText(titleName);
    titleLabel.setForeground(Color.ORANGE);
    titleLabel.setBackground(Color.BLACK);
    this.mainPanel.add(titleLabel);
  }
  
  /** 
   * Getter for titleLabel 
   * @return titleLable
   */
  public JLabel getTitleLabel() {
    return this.titleLabel;
  }
  
  /**
   * Set each buttons for each three play modes 
   */
  public void setButtons() {
    buttons = new JButton[5];
    String[] buttonNames = { "SinglePlay", "MultiPlay", "SongPractice" };
    int i = 0;
    for (String buttonName : buttonNames) {
      ImageIcon buttonImage = new ImageIcon("./resource/Image/" + buttonName + ".png");
      buttons[i] = setButton(buttonImage, 50 + i * 260);
      buttons[i].setIcon(buttonImage);
      buttons[i].setActionCommand(buttonName);
      buttons[i].addActionListener(this.pressed);
      mainPanel.add(buttons[i]);
      i += 1;
    }
  }
  
 /**
  * Setter for each button with given image
  * @param icon set button with this image icon
  * @param xPos button's x position
  */
  private JButton setButton(ImageIcon icon, int xPos) {
    JButton button = new JButton(icon);
    button.setBounds(xPos, 270, 250, 146);
    return button;
  }
  
  /**
   * Getter for buttons
   * @return buttons
   */
  public JButton[] getButtons() {
    return this.buttons;
  }
  
  /**
   * Set what to do when each play mode button is clicked
   */
  public void setPressed() {
    this.pressed = new AbstractAction() {
      /**
       * Get action command and start with given command
       */
      @Override
      public void actionPerformed(ActionEvent evt) {
        String command = evt.getActionCommand();
        if (command.equals("SinglePlay") && !SinglePlay.getIsOn()) {
          new SinglePlay();
        } else if (command.equals("MultiPlay") && !MultiPlay.getIsOn()) {
          new MultiPlay();
        } else if (command.equals("SongPractice") && !PlayMusic.getIsOn()) {
          new SongPractice();
        } else { /* when user tries to open play mode that is already opened */
          try {
            throw new AlreadyOnException();
          } catch (AlreadyOnException e) {
          }
        }
      }
    };
  }
  
  /**
   * Getter for pressed
   * @return pressed
   */
  public AbstractAction getPressed() {
    return this.pressed;
  }

}
