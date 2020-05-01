import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;
//import javax.swing.table.DefaultTableModel;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class GUI{

  private JFrame fenster;
  private Graphics grafik;
  private JPanel panel1;
  private GUI_Lauscher lauscher;
  private AnimalCrossingGuide acGuide;
  private JButton knopf1;
  private JLabel labelMonth;
  private JComboBox<String> dropdown_month;
  private JLabel labelAnimal;
  private JComboBox<String> dropdown_animal;
  private JLabel labelPlace;
  private JComboBox<String> dropdown_place;
  private JLabel labelCatched;
  private JComboBox<String> dropdown_catched;
  private JLabel labelPrice;
  private JTextField filterPrice;
  private JTextField wasCatched_textField;
  private JButton wasCatched_button;
  private JLabel notFound;
  private JButton saveAndQuit;
  private JTable j;
  private JScrollPane sp;
  private JTable tableInsect;
  private JScrollPane spInsect;
  private JTable tableArt;
  private JScrollPane spArt;
  private JLabel labelTest;



  public GUI(){
    fenster = new JFrame("ACNH Catching Guide");
    fenster.setSize(590,500);
    fenster.setVisible(true);
    fenster.setLocation(50,50);
    fenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //https://www.elsetge.cat/pngvi/iiRmiiR_animal-crossing-grass-wallpaper-animal-crossing-textures-ground/

    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

    fenster.getContentPane().add(container);
    fenster.setBackground(new Color(34,191,23));
    lauscher = new GUI_Lauscher();

    panel1 = new JPanel();
    //panel1.setLayout(null);
    panel1.setPreferredSize(new Dimension(100, 100));
    panel1.setBackground(new Color(34,191,23));
    container.add(panel1);

    labelMonth = new JLabel();
    labelMonth.setText("Monat:");
    panel1.add(labelMonth);

    String[] choicesMonth = {"Januar","Februar","Marz","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"};
    dropdown_month = new JComboBox<String>(choicesMonth);
    dropdown_month.setSelectedItem(choicesMonth[Calendar.getInstance().get(Calendar.MONTH)]);
    dropdown_month.setBounds(10,100,150,50);
    dropdown_month.setVisible(true);
    dropdown_month.addActionListener(lauscher);
    panel1.add(dropdown_month);

    labelAnimal = new JLabel();
    labelAnimal.setText("Tier:");
    panel1.add(labelAnimal);

    String[] choicesAnimal = {"Fisch","Insekt","Kunst"};
    dropdown_animal = new JComboBox<String>(choicesAnimal);
    dropdown_animal.setBounds(10,100,150,50);
    dropdown_animal.setVisible(true);
    dropdown_animal.addActionListener(lauscher);
    panel1.add(dropdown_animal);

    labelPlace = new JLabel();
    labelPlace.setText("Ort:");
    panel1.add(labelPlace);

    String[] choicesPlace = {"---","Fluss","Teich","Wasserfall","Flussmuendung","Meer"};
    dropdown_place = new JComboBox<String>(choicesPlace);
    dropdown_place.setBounds(10,100,150,50);
    dropdown_place.setVisible(true);
    dropdown_place.addActionListener(lauscher);
    panel1.add(dropdown_place);

    labelCatched = new JLabel();
    labelCatched.setText("Gefangen:");
    panel1.add(labelCatched);

    String[] choicesCatched = {"---","ja","nein"};
    dropdown_catched = new JComboBox<String>(choicesCatched);
    dropdown_catched.setBounds(10,100,150,50);
    dropdown_catched.setVisible(true);
    dropdown_catched.addActionListener(lauscher);
    panel1.add(dropdown_catched);

    labelPrice = new JLabel();
    labelPrice.setText("Preis >");
    panel1.add(labelPrice);

    filterPrice = new JTextField("0",4);
    filterPrice.addActionListener(lauscher);
    panel1.add(filterPrice);

    wasCatched_textField = new JTextField("",10);
    wasCatched_textField.addActionListener(lauscher);
    panel1.add(wasCatched_textField);

    wasCatched_button = new JButton("gefangen");
    wasCatched_button.setBounds(200,100,150,50);
    wasCatched_button.addActionListener(lauscher);
    panel1.add(wasCatched_button);

    knopf1 = new JButton("Filter");
    knopf1.setBounds(200,100,150,50);
    knopf1.addActionListener(lauscher);
    panel1.add(knopf1);

    labelTest = new JLabel();
    labelTest.setText(acGuide.getOsPath());
    panel1.add(labelTest);

    saveAndQuit = new JButton("Save & Quit");
    saveAndQuit.setBounds(200,100,150,50);
    saveAndQuit.addActionListener(lauscher);
    panel1.add(saveAndQuit);

    String[][] dataFish = {
            { "","","","","","",""}
        };

    // Column Names
    String[] columnNamesFish = { "Fisch", "Monat", "Uhrzeit", "Ort", "Groesse", "Wert" };
    // Initializing the JTable
    j = new JTable(dataFish, columnNamesFish);
    j.setBounds(200, 40, 200, 300);

    // adding it to JScrollPane
    sp = new JScrollPane(j);
    //sp.setBounds(0,0,2000,2000);
    sp.setPreferredSize(new Dimension(500, 500));
    sp.setBackground(new Color(34,191,23));
    sp.setVisible(false);
    container.add(sp);

    String[][] dataInsect = {
            { "","","","",""}
        };

    String[] columnNamesInsect = { "Insekt", "Monat", "Uhrzeit", "Ort", "Wert" };
    // Initializing the JTable
    tableInsect = new JTable(dataInsect, columnNamesInsect);
    tableInsect.setBounds(200, 40, 200, 300);

    // adding it to JScrollPane
    spInsect = new JScrollPane(tableInsect);
    //sp.setBounds(0,0,2000,2000);
    spInsect.setPreferredSize(new Dimension(500, 500));
    spInsect.setBackground(new Color(34,191,23));
    spInsect.setVisible(false);
    container.add(spInsect);

    String[][] dataArt = {
            { "","","",""}
        };

    String[] columnNamesArt = { "Kunstwerk", "Originalgemaelde", "Kuenstler", "Fehler der Faelschung" };
    // Initializing the JTable
    tableArt = new JTable(dataArt, columnNamesArt);
    tableArt.setBounds(200, 40, 200, 300);

    // adding it to JScrollPane
    spArt = new JScrollPane(tableArt);
    //sp.setBounds(0,0,2000,2000);
    spArt.setPreferredSize(new Dimension(500, 500));
    spArt.setBackground(new Color(34,191,23));
    spArt.setVisible(false);
    container.add(spArt);

    notFound = new JLabel();
    notFound.setText("Nichts gefunden");
    notFound.setVisible(false);
    panel1.add(notFound);

    lauscher.doFiltering();

    panel1.repaint();
    sp.repaint();
    container.repaint();
    fenster.revalidate();
    fenster.repaint();
    fenster.doLayout();
  }

  public class GUI_Lauscher implements ActionListener
  {

      public GUI_Lauscher() {
          acGuide = new AnimalCrossingGuide();
      }

      public int monthToInt(String month){
        switch(month){
          case "Januar":
            return 1;
          case "Februar":
            return 2;
          case "Marz":
            return 3;
          case "April":
            return 4;
          case "Mai":
            return 5;
          case "Juni":
            return 6;
          case "Juli":
            return 7;
          case "August":
            return 8;
          case "September":
            return 9;
          case "Oktober":
            return 10;
          case "November":
            return 11;
          case "Dezember":
            return 12;
        }
        return -1;
      }

      public void actionPerformed(ActionEvent ereignis){
        if (ereignis.getActionCommand() == "Filter"){
          doFiltering();
        }
        if (ereignis.getActionCommand() == "gefangen"){
          if(!wasCatched_textField.getText().equals("")){
            acGuide.addToSaveData(wasCatched_textField.getText());
            wasCatched_textField.setText("");
            doFiltering();
          }
        }if (ereignis.getActionCommand() == "Save & Quit"){
          acGuide.saveData(acGuide.getOsPath() + "/saves.dat", acGuide.getSaveFile());
          System.exit(0);

      }

    }

    public void doFiltering(){
      try{
        int selectedMonth = monthToInt(dropdown_month.getSelectedItem().toString());
        String place = dropdown_place.getSelectedItem().toString();
        String catched = dropdown_catched.getSelectedItem().toString();
        int price = 0;
        try{
          price = Integer.parseInt(filterPrice.getText());
        }catch(Exception e){
          filterPrice.setText("0");
          //TODO Errormessage
        }

        if (dropdown_animal.getSelectedItem().toString().equals("Fisch")){
          String[][] dataFish = acGuide.filter("ac_fish.csv", selectedMonth, place, catched, price);
          String[] columnNamesFish = {"Fisch", "Monat", "Uhrzeit", "Ort", "Groesse", "Wert" };
          sp.getViewport().remove(j);
          j.setModel(new DefaultTableModel(dataFish, columnNamesFish));
          j.setDefaultRenderer(String[].class, new MultiLineTableCellRenderer());
          sp.getViewport().add(j, null);
          spInsect.setVisible(false);
          spArt.setVisible(false);
          sp.setVisible(true);
          notFound.setVisible(false);
        }else if (dropdown_animal.getSelectedItem().toString().equals("Insekt")){
          String[][] dataInsect = acGuide.filter("ac_insect.csv", selectedMonth, place, catched, price);
          String[] columnNamesInsect = {"Insekt", "Monat", "Uhrzeit", "Ort", "Wert" };
          spInsect.getViewport().remove(tableInsect);
          tableInsect.setModel(new DefaultTableModel(dataInsect, columnNamesInsect));
          tableInsect.setDefaultRenderer(String[].class, new MultiLineTableCellRenderer());
          spInsect.getViewport().add(tableInsect, null);
          sp.setVisible(false);
          spArt.setVisible(false);
          spInsect.setVisible(true);
          notFound.setVisible(false);
        }else if (dropdown_animal.getSelectedItem().toString().equals("Kunst")){
          String[][] dataArt = acGuide.filter("ac_art.csv", selectedMonth, place, catched, price);
          String[] columnNamesArt = { "Kunstwerk", "Originalgemaelde", "Kuenstler", "Fehler der Faelschung" };
          spArt.getViewport().remove(tableArt);
          tableArt.setModel(new DefaultTableModel(dataArt, columnNamesArt));
          tableArt.setDefaultRenderer(String[].class, new MultiLineTableCellRenderer());
          spArt.getViewport().add(tableArt, null);
          spInsect.setVisible(false);
          sp.setVisible(false);
          spArt.setVisible(true);
          notFound.setVisible(false);
        }
        // Initializing the JTable


        fenster.revalidate();
        fenster.repaint();
        fenster.doLayout();
      }catch(Exception e){
        notFound.setVisible(true);
        sp.setVisible(false);
        spInsect.setVisible(false);
        spArt.setVisible(false);
      }
    }
  }
}

//https://stackoverflow.com/questions/9955595/how-to-display-multiple-lines-in-a-jtable-cell
class MultiLineTableCellRenderer extends JList<String> implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //make multi line where the cell value is String[]
        if (value instanceof String[]) {
            setListData((String[]) value);
        }

        //cell backgroud color when selected
        if (isSelected) {
            setBackground(UIManager.getColor("Table.selectionBackground"));
        } else {
            setBackground(UIManager.getColor("Table.background"));
        }

        return this;
    }
}
