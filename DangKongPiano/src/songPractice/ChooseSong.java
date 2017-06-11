package songPractice;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/* class for choosing song among list */
public class ChooseSong extends JPanel implements ListSelectionListener {

  private JList<String> list;     /* list of songs to screen */
  private String[] songList;      /* list of songs in project */
  private JScrollPane listScroll; /* listScroll for selecting music */
  private JLabel textLabel;       /* show whether user choose something */
  private String selectedSong;    /* String for selected music */

  /* constructor */
  public ChooseSong() {
    setLayout(null);
    setSongList();
    setList();
    setTextLabel();
  }

  /* get selected song */
  public void valueChanged(ListSelectionEvent evt) {
    int selected = list.getSelectedIndex();
    textLabel.setText("<" + songList[selected] + "> is selected!");
    selectedSong = songList[selected];
  }

  /* set list, and show to user */
  public void setList() {
    listScroll = new JScrollPane();
    listScroll.setBounds(14, 115, 1037, 351);

    list = new JList<String>(songList);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setFont(new Font("Comic Sans MS", Font.BOLD, 30));

    listScroll.setViewportView(list);
    add(listScroll);
    list.addListSelectionListener(this);
  }

  /* return list */
  public JList<String> getList() {
    return this.list;
  }

  /* set list of songs */
  public void setSongList() {
    String[] list = { "School Bell", "Little Star", "Mountain Rabbit", "Baduk's Ring", "Cannon" };
    songList = list.clone();
  }

  /* return list of songs */
  public String[] getSongList() {
    return this.songList;
  }

  /* set list scroll */
  public void setListScroll(JScrollPane listScroll) {
    this.listScroll = listScroll;
  }

  /* return list of songs */
  public JScrollPane getListScroll() {
    return listScroll;
  }

  /* show state whether user select song */
  void setTextLabel() {
    textLabel = new JLabel("Choose Song!");
    textLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
    textLabel.setBounds(20, 0, 800, 116);
    add(textLabel);
  }

  /* return state(textLabel) */
  public JLabel getTextLabel() {
    return textLabel;
  }

  /* setter for variable : selected song */
  public void setSelectedSong(String selectedSong) {
    this.selectedSong = selectedSong;
  }

  /* getter for variable : selected song */
  public String getSelectedSong() {
    return selectedSong;
  }

}