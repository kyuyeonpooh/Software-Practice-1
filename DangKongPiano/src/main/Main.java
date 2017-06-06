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
import java.io.FileNotFoundException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;

public class Main extends JFrame {

  private JPanel mainPanel;
  private JLabel titleLabel;
  private JButton[] buttons;
  private AbstractAction pressed;

  public static void main(String[] args) {
    new Main();
  }

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

  public JPanel getPanel() {
    return this.mainPanel;
  }

  public void setTitleLabel(String titleName) {
    titleLabel = new JLabel();
    titleLabel.setBounds(220, 32, 500, 55);
    titleLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 50));
    titleLabel.setText(titleName);
    titleLabel.setForeground(Color.ORANGE);
    titleLabel.setBackground(Color.BLACK);
    this.mainPanel.add(titleLabel);
  }

  public JLabel getTitleLabel() {
    return this.titleLabel;
  }

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

  private JButton setButton(ImageIcon icon, int xPos) {
    JButton button = new JButton(icon);
    button.setBounds(xPos, 270, 250, 146);
    return button;
  }

  public JButton[] getButtons() {
    return this.buttons;
  }

  public void setPressed() {
    this.pressed = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        String command = evt.getActionCommand();
        if (command.equals("SinglePlay") && !SinglePlay.getIsOn()) {
          new SinglePlay();
        } else if (command.equals("MultiPlay") && !MultiPlay.getIsOn()) {
          new MultiPlay();
        } else if (command.equals("SongPractice") && !PlayMusic.getIsOn()) {
          new SongPractice();
        } else {
          try {
            throw new AlreadyOnException();
          } catch (AlreadyOnException e) {
          }
        }
      }
    };
  }

  public AbstractAction getPressed() {
    return this.pressed;
  }

}
