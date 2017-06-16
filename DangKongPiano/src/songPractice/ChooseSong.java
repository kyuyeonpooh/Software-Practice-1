package songPractice;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Class for choosing song among songs in the list 
 * @author Team 2: Kim Kyu Yeon, Kim Yeon Jae
 *
 */
public class ChooseSong extends JPanel implements ListSelectionListener {
  
  /** List of songs */
  private JList<String> list;
  /** List of song names */
  private String[] songList;
  /* Scroll of list */
  private JScrollPane listScroll;
  /* Show whether user choose something */
  private JLabel textLabel;
  /* Show selected music */
  private String selectedSong;

  /** Constructor of ChooseSong */
  public ChooseSong() {
    setLayout(null);
    setSongList();
    setList();
    setTextLabel();
  }


  /**
   * Get selected song
   * @param evt event of selecting songs among the list
   */
  public void valueChanged(ListSelectionEvent evt) {
    int selected = list.getSelectedIndex();
    textLabel.setText("<" + songList[selected] + "> is selected!");
    selectedSong = songList[selected];
  }

  /**
   * Set the list and show it to the user 
   */
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

  /**
   * Getter for list
   * @return list
   */
  public JList<String> getList() {
    return this.list;
  }

  /**
   * Set songList with song names
   */
  public void setSongList() {
    String[] list = { "School Bell", "Little Star", "Mountain Rabbit", "Baduk's Ring", "Cannon" };
    songList = list.clone();
  }


  /**
   * Getter for songList
   * @return songList
   */
  public String[] getSongList() {
    return this.songList;
  }

  /**
   * Setter for listScroll
   * @param listScroll list scroll for song list
   */
  public void setListScroll(JScrollPane listScroll) {
    this.listScroll = listScroll;
  }


  /**
   * Getter for listScroll
   * @return listScroll
   */
  public JScrollPane getListScroll() {
    return listScroll;
  }

  /**
   * Set state whether user select song 
   */
  void setTextLabel() {
    textLabel = new JLabel("Choose Song!");
    textLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
    textLabel.setBounds(20, 0, 800, 116);
    add(textLabel);
  }

  /**
   * Getter for textLabel
   * @return textLabel
   */
  public JLabel getTextLabel() {
    return textLabel;
  }

 /**
  * Setter for selectedSong
  * @param selectedSong song that user chose
  */
  public void setSelectedSong(String selectedSong) {
    this.selectedSong = selectedSong;
  }

  /**
   * Getter for selectedSong
   * @return selectedSong
   */
  public String getSelectedSong() {
    return selectedSong;
  }

}