package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mode.PlayMusic;
import mode.SinglePlay;
import mode.SongPractice;
import piano.Piano;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;

public class Main extends JFrame{	
	JPanel mainPanel;
	JButton[] buttons;
	JLabel title;
	private AbstractAction pressed;
	
	public static void main(String[] args) {
		Main DangKong = new Main();
	}	
	
	public Main(){
		this.setFrame();
		this.settingTitle("DangKongPiano");
		this.setPressed();
		this.setButtons();

		setContentPane(mainPanel);
		setVisible(true);
	}
	
	void setFrame(){
		ImageIcon background = new ImageIcon("./resource/Image/background6.JPG");
		mainPanel = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(background.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,500);
		setTitle("DangKongPiano");
		mainPanel.setForeground(Color.WHITE);
		mainPanel.setLayout(null);
	}
	
	public void settingTitle(String titlename){
		title = new JLabel();
		title.setForeground(Color.orange);
		title.setBackground(Color.BLACK);
		title.setBounds(220, 32, 500, 55);
		title.setFont(new Font("Bauhaus 93", Font.BOLD, 50));
		title.setText(titlename);
		mainPanel.add(title);		
	}
	
	void setButtons(){
		buttons = new JButton[5];
		String[] buttonNames={"SinglePlay", "MultiPlay", "SongPractice"};
		int i = 0;
		for(String buttonName: buttonNames){
			buttons[i]=setButton(buttonName, 50+i*260);
			mainPanel.add(buttons[i]);
		    buttons[i].setActionCommand(buttonName);
			buttons[i++].addActionListener(this.pressed);
		}
	}

	JButton setButton(String buttonName, int xPos){
		JButton btn = new JButton(buttonName);

		btn.setBounds(xPos, 270, 250, 146);
		btn.setForeground(Color.BLACK);
		btn.setBackground(Color.WHITE);
		
	    btn.setVerticalTextPosition(JButton.CENTER);
		btn.setFont(new Font("Bauhaus 93", Font.PLAIN, 33));
		return btn;
	}

	public void setPressed() {
		this.pressed = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String command = evt.getActionCommand();
				if (command.equals("SinglePlay")&&SinglePlay.getNumberOf()==0){
					SinglePlay SP = new SinglePlay();
				}
				else if(command.equals("MultiPlay"))
					System.out.println("multiplay");
				else if(command.equals("SongPractice")&&PlayMusic.getNumberOf()==0){
					try {
						SongPractice SP = new SongPractice();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
					System.out.println("??");
			}
		};
	}

}
