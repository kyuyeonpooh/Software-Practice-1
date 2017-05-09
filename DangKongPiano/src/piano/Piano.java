package piano;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Piano {

  private JFrame frame;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Piano window = new Piano();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public Piano() {
    initialize();
  }

  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 785, 549);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);
    frame.setVisible(false);
    frame.setTitle("DangKong Piano");
    
    JButton btnNewButton = new JButton("A");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)
      }
    });
    btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
    btnNewButton.setFont(new Font("Consolas", Font.BOLD, 20));
    btnNewButton.setBounds(21, 290, 61, 197);
    frame.getContentPane().add(btnNewButton);
    
    JButton btnB = new JButton("B");
    btnB.setVerticalAlignment(SwingConstants.BOTTOM);
    btnB.setFont(new Font("Consolas", Font.BOLD, 20));
    btnB.setBounds(103, 290, 61, 197);
    frame.getContentPane().add(btnB);
    
    JButton button_1 = new JButton("C");
    button_1.setVerticalAlignment(SwingConstants.BOTTOM);
    button_1.setFont(new Font("Consolas", Font.BOLD, 20));
    button_1.setBounds(185, 290, 61, 197);
    frame.getContentPane().add(button_1);
    
    JButton btnD = new JButton("D");
    btnD.setVerticalAlignment(SwingConstants.BOTTOM);
    btnD.setFont(new Font("Consolas", Font.BOLD, 20));
    btnD.setBounds(267, 290, 61, 197);
    frame.getContentPane().add(btnD);
    
    JButton btnE = new JButton("E");
    btnE.setVerticalAlignment(SwingConstants.BOTTOM);
    btnE.setFont(new Font("Consolas", Font.BOLD, 20));
    btnE.setBounds(349, 290, 61, 197);
    frame.getContentPane().add(btnE);
    
    JButton btnF = new JButton("F");
    btnF.setVerticalAlignment(SwingConstants.BOTTOM);
    btnF.setFont(new Font("Consolas", Font.BOLD, 20));
    btnF.setBounds(431, 290, 61, 197);
    frame.getContentPane().add(btnF);
    
    JButton btnG = new JButton("G");
    btnG.setVerticalAlignment(SwingConstants.BOTTOM);
    btnG.setFont(new Font("Consolas", Font.BOLD, 20));
    btnG.setBounds(513, 290, 61, 197);
    frame.getContentPane().add(btnG);
    
    JButton btnA = new JButton("A");
    btnA.setVerticalAlignment(SwingConstants.BOTTOM);
    btnA.setFont(new Font("Consolas", Font.BOLD, 20));
    btnA.setBounds(595, 290, 61, 197);
    frame.getContentPane().add(btnA);
    
    JButton btnB_1 = new JButton("B");
    btnB_1.setVerticalAlignment(SwingConstants.BOTTOM);
    btnB_1.setFont(new Font("Consolas", Font.BOLD, 20));
    btnB_1.setBounds(677, 290, 61, 197);
    frame.getContentPane().add(btnB_1);
    
    JButton btnEb = new JButton("Eb");
    btnEb.setVerticalAlignment(SwingConstants.BOTTOM);
    btnEb.setFont(new Font("Consolas", Font.BOLD, 20));
    btnEb.setBounds(313, 94, 61, 197);
    frame.getContentPane().add(btnEb);
    
    JButton btnF_1 = new JButton("F#");
    btnF_1.setVerticalAlignment(SwingConstants.BOTTOM);
    btnF_1.setFont(new Font("Consolas", Font.BOLD, 20));
    btnF_1.setBounds(467, 94, 61, 197);
    frame.getContentPane().add(btnF_1);
    
    JButton btnC = new JButton("C#");
    btnC.setVerticalAlignment(SwingConstants.BOTTOM);
    btnC.setFont(new Font("Consolas", Font.BOLD, 20));
    btnC.setBounds(226, 94, 61, 197);
    frame.getContentPane().add(btnC);
    
    JButton btnAb = new JButton("Ab");
    btnAb.setVerticalAlignment(SwingConstants.BOTTOM);
    btnAb.setFont(new Font("Consolas", Font.BOLD, 20));
    btnAb.setBounds(555, 94, 61, 197);
    frame.getContentPane().add(btnAb);
    
    JButton btnBb = new JButton("Bb");
    btnBb.setVerticalAlignment(SwingConstants.BOTTOM);
    btnBb.setFont(new Font("Consolas", Font.BOLD, 20));
    btnBb.setBounds(642, 94, 61, 197);
    frame.getContentPane().add(btnBb);
    
    JButton btnB_2 = new JButton("Bb");
    btnB_2.setVerticalAlignment(SwingConstants.BOTTOM);
    btnB_2.setFont(new Font("Consolas", Font.BOLD, 20));
    btnB_2.setBounds(62, 94, 61, 197);
    frame.getContentPane().add(btnB_2);
  }
}
