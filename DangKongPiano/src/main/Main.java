package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import mode.SinglePlay;
import piano.Piano;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
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
		this.setTitle("DangKongPiano");
		this.setPressed();
		this.setButtons();
		
		setContentPane(mainPanel);
		setVisible(true);
	}
	
	void setFrame(){
		mainPanel = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,500);
		mainPanel.setForeground(Color.WHITE);
		mainPanel.setLayout(null);
	}
	
	public void setTitle(String titlename){
		title = new JLabel();
		title.setForeground(Color.BLACK);
		title.setBackground(Color.WHITE);
		title.setBounds(300, 32, 255, 55);
		title.setFont(new Font("BernhardFashion BT", Font.BOLD, 33));
		title.setText(titlename);
		mainPanel.add(title);		
	}
	
	void setButtons(){
		buttons = new JButton[5];
		String[] buttonNames={"SinglePlay", "MultiPlay", "SongPractice"};
		int i = 0;
		for(String buttonName: buttonNames){
			buttons[i]=setButton(buttonName, 54+i*260);
			mainPanel.add(buttons[i]);
		    buttons[i].setActionCommand(buttonName);
			buttons[i++].addActionListener(this.pressed);
		}
	}

	JButton setButton(String buttonName, int xPos){
		JButton btn = new JButton(buttonName);

		btn.setBounds(xPos, 146, 250, 146);
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
				if (command.equals("SinglePlay")&&Piano.getNumberOfPiano()==0){
					SinglePlay SP = new SinglePlay();
				}
				else if(command.equals("MultiPlay"))
					System.out.println("multiplay");
				else if(command.equals("SongPractice")&&Piano.getNumberOfPiano()==0)
					System.out.println("song practice");
				else
					System.out.println("??");
			}
		};
	}

}
