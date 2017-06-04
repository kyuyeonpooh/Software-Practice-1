package songPractice;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChooseSong extends JPanel implements ListSelectionListener {

  private JList<String> list;
  private String[] songs;
  private JScrollPane listScroll;
  private JLabel textLabel;
  private String selectedSong;

  public ChooseSong() {
    setLayout(null);
    setSongList();
    setList();

    textLabel = new JLabel("Choose Song!");
    textLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
    textLabel.setBounds(20, 0, 800, 116);
    add(textLabel);
  }
  
  @Override
  public void valueChanged(ListSelectionEvent evt) {
    int selected = list.getSelectedIndex();
    textLabel.setText("<" + songs[selected] + "> is selected!");
    selectedSong = songs[selected];
  }
  
  public void setSongList() {
    String[] list = { "School Bell", "Little Star" };
    songs = list.clone();
  }

  public void setList() {
    listScroll = new JScrollPane();
    listScroll.setBounds(14, 115, 1037, 351);

    list = new JList<String>(songs);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setFont(new Font("Comic Sans MS", Font.BOLD, 30));

    listScroll.setViewportView(list);
    add(listScroll);
    list.addListSelectionListener(this);
  }

  public String getSelectedSong() {
    return selectedSong;
  }
  
}
