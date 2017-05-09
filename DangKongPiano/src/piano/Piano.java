package piano;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

import java.io.File;

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
    /* Title and Window Set */
    frame = new JFrame();
    frame.setBounds(100, 100, 870, 528);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);
    frame.setVisible(false);
    frame.setTitle("DangKong Piano");

    /* Keyboard Set */
    JButton keyBoardA0 = new JButton("A");
    
    keyBoardA0.addKeyListener(new KeyListener() {
      @Override
      public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'q'){
          try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/37.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }
      }
      @Override
      public void keyReleased(KeyEvent e) {}
      @Override
      public void keyTyped(KeyEvent e) {}
    });
    keyBoardA0.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/37.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardA0.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardA0.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardA0.setBounds(14, 235, 61, 197);
    frame.getContentPane().add(keyBoardA0);

    JButton keyBoardB0Flat = new JButton("Bb0");
    keyBoardB0Flat.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/38.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardB0Flat.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardB0Flat.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardB0Flat.setBounds(55, 39, 61, 197);
    frame.getContentPane().add(keyBoardB0Flat);

    JButton keyBoardB0 = new JButton("B");
    keyBoardB0.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/39.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardB0.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardB0.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardB0.setBounds(96, 235, 61, 197);
    frame.getContentPane().add(keyBoardB0);

    JButton keyBoardC1 = new JButton("C");
    keyBoardC1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/40.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardC1.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardC1.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardC1.setBounds(178, 235, 61, 197);
    frame.getContentPane().add(keyBoardC1);

    JButton keyBoardC1Sharp = new JButton("C#1");
    keyBoardC1Sharp.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/41.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardC1Sharp.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardC1Sharp.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardC1Sharp.setBounds(219, 39, 61, 197);
    frame.getContentPane().add(keyBoardC1Sharp);

    JButton keyBoardD1 = new JButton("D");
    keyBoardD1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/42.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardD1.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardD1.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardD1.setBounds(260, 235, 61, 197);
    frame.getContentPane().add(keyBoardD1);

    JButton keyBoardE1Flat = new JButton("Eb1");
    keyBoardE1Flat.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/43.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardE1Flat.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardE1Flat.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardE1Flat.setBounds(306, 39, 61, 197);
    frame.getContentPane().add(keyBoardE1Flat);

    JButton keyBoardE1 = new JButton("E");
    keyBoardE1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/44.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardE1.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardE1.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardE1.setBounds(342, 235, 61, 197);
    frame.getContentPane().add(keyBoardE1);

    JButton keyBoardF1 = new JButton("F");
    keyBoardF1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/45.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardF1.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardF1.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardF1.setBounds(424, 235, 61, 197);
    frame.getContentPane().add(keyBoardF1);

    JButton keyBoardF1Sharp = new JButton("F#1");
    keyBoardF1Sharp.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/46.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardF1Sharp.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardF1Sharp.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardF1Sharp.setBounds(460, 39, 61, 197);
    frame.getContentPane().add(keyBoardF1Sharp);

    JButton keyBoardG1 = new JButton("G");
    keyBoardG1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/47.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardG1.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardG1.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardG1.setBounds(506, 235, 61, 197);
    frame.getContentPane().add(keyBoardG1);

    JButton keyBoardA1Flat = new JButton("Ab1");
    keyBoardA1Flat.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/48.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardA1Flat.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardA1Flat.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardA1Flat.setBounds(548, 39, 61, 197);
    frame.getContentPane().add(keyBoardA1Flat);

    JButton keyBoardA1 = new JButton("A");
    keyBoardA1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/49.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardA1.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardA1.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardA1.setBounds(588, 235, 61, 197);
    frame.getContentPane().add(keyBoardA1);

    JButton keyBoardB1Flat = new JButton("Bb1");
    keyBoardB1Flat.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/50.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardB1Flat.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardB1Flat.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardB1Flat.setBounds(635, 39, 61, 197);
    frame.getContentPane().add(keyBoardB1Flat);

    JButton keyBoardB1 = new JButton("B");
    keyBoardB1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/51.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardB1.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardB1.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardB1.setBounds(673, 235, 61, 197);
    frame.getContentPane().add(keyBoardB1);

    JButton keyBoardC2 = new JButton("C");
    keyBoardC2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resource/pianoSound/52.wav"));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
    keyBoardC2.setVerticalAlignment(SwingConstants.BOTTOM);
    keyBoardC2.setFont(new Font("Consolas", Font.BOLD, 20));
    keyBoardC2.setBounds(764, 235, 61, 197);
    frame.getContentPane().add(keyBoardC2);
  }
}
