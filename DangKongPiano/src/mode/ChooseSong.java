package mode;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChooseSong extends JPanel implements ListSelectionListener{
	JList<String> list;
	JLabel lblChooseSong;
	String[] songList;
	JLabel stateTxt;
	JScrollPane listScroll;
	String selectedSong;
	
	public ChooseSong(){
		setLayout(null);
		setSongList();
		setList();
		
		lblChooseSong = new JLabel("Choose Song!");
		lblChooseSong.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		lblChooseSong.setBounds(70, 0, 800, 116);
		add(lblChooseSong);
	}
	
	void setSongList(){
		String[] list = {"schoolBell","littleStar"};
		songList = list.clone();
	}
	
	void setList(){
		listScroll = new JScrollPane();
		listScroll.setBounds(14, 115, 1037, 351);
		
		list = new JList<String>(songList);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

		listScroll.setViewportView(list);
		add(listScroll);
		list.addListSelectionListener(this);
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		int selected=list.getSelectedIndex();
		lblChooseSong.setText("< "+songList[selected]+" > is selected!");
		selectedSong=songList[selected];
	}
	
	public String getSelectedSong(){
		return selectedSong;
	}
}
