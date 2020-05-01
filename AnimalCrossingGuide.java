import java.io.File;
import javax.swing.JFileChooser;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AnimalCrossingGuide{

  private String osPath;
  private ArrayList<String> saveFile;


  public AnimalCrossingGuide(){
    try{
      osPath = new java.io.File( "." ).getCanonicalPath();
    }catch(Exception e){

    }

    System.out.println(osPath + "/saves.dat");
    saveFile = loadData(osPath + "/saves.dat");
  }

  public static void main(String[] args) {
    new GUI();
  }

  public String[][] filter(String filename, int month, String place, String catched, int price){
    ArrayList<String[]> filtered = new ArrayList<String[]>();
    try{
      File file = new File(osPath + "/" + filename);
      System.out.println(osPath + "/" + filename);
      Scanner myReader = new Scanner(file);
      while (myReader.hasNextLine()){
        String[] data = myReader.nextLine().split(";");
        if (!filename.equals("ac_art.csv") && !monthFilter(data[1], month))
          continue;
        data[1] = beautyMonth(data[1]);
        if (!place.equals("---") && !data[3].equals(place))
          continue;
        if (catched.equals("ja") && !saveFile.contains(data[0]))
          continue;
        if (catched.equals("nein") && saveFile.contains(data[0]))
          continue;
        if (filename.equals("ac_fish.csv") && Integer.parseInt(data[5]) < price)
          continue;
        if (filename.equals("ac_insect.csv") && Integer.parseInt(data[4]) < price)
          continue;
        filtered.add(data);
      }
      myReader.close();
    }catch(FileNotFoundException e){
      System.out.println("An error occured.");
      e.printStackTrace();
    }

    return toStringArray(filtered);
  }

  public Boolean monthFilter(String time, int month){
    String[] times = time.split("&");
    for(int i = 0; i < times.length; i++){
      String[] months = times[i].split("-");
      try{
        if(month >= Integer.parseInt(months[0]) && month <= Integer.parseInt(months[1]))
          return true;
      }catch(Exception e){
        return false;
      }
    }
    return false;

  }

  public ArrayList<String> loadData(String filename){
    ArrayList<String> saveFile = new ArrayList<String>();
    try{
      File file = new File(filename);
      Scanner myReader = new Scanner(file);
      while (myReader.hasNextLine()){
        saveFile.add(myReader.nextLine());
      }
      myReader.close();
    }catch(FileNotFoundException e){
      System.out.println("An error occured.");
      e.printStackTrace();
    }
    return saveFile;
  }

  public ArrayList<String> getSaveFile(){
    return saveFile;
  }

  public void addToSaveData(String toAdd){
    saveFile.add(toAdd);
  }

  public String getOsPath(){
    return osPath;
  }

  public void saveData(String filename, ArrayList<String> data){
    try{
      PrintWriter printwriter = new PrintWriter(filename);
      for (int i = 0; i < data.size(); i++){
        printwriter.println(data.get(i));
      }
      printwriter.close();
    }catch(FileNotFoundException e){
      System.out.println("An error occured.");
      e.printStackTrace();
    }

  }

  public String beautyMonth(String time){
    try{
      String out = "";
      String[] times = time.split("&");
      for(int i = 0; i < times.length; i++){
        String[] month = times[i].split("-");
        if(i + 1 < times.length){
          if(month[1].equals("12") && times[i+1].split("-")[0].equals("1")){
            month[1] = times[i+1].split("-")[1];
            i++;
          }else if(month[0].equals("1") && times[i+1].split("-")[1].equals("12")){
            month[0] = times[i+1].split("-")[0];
            i++;
          }
        }
        if(month[0].equals(month[1])){
          out = out + numberToMonth(Integer.parseInt(month[0]));
        }else{
          out = out + numberToMonth(Integer.parseInt(month[0])) + " - " + numberToMonth(Integer.parseInt(month[1]));
        }
        if (i < times.length-1)
          out = out + ", ";
        }
        return out;
      }catch(Exception e){
        return time;
      }
  }

  public String numberToMonth(int number){
    //String[] months = {"Januar", "Februar", "Marz", "April", "Mai", "Juni", "Juli",
    //                   "August", "September", "Oktober", "November", "Dezember"};
    String[] months = {"Jan", "Feb", "Mar", "Apr", "Mai", "Jun", "Jul",
                       "Aug", "Sep", "Okt", "Nov", "Dez"};
    return months[number-1];
  }

  public String[][] toStringArray(ArrayList<String[]> arraylist){
    String[][] array = new String[arraylist.size()][arraylist.get(0).length];
    for(int i = 0; i < arraylist.size(); i++){
      for(int j = 0; j < arraylist.get(i).length; j++){
        array[i][j] = arraylist.get(i)[j];
      }
    }
    return array;
  }
}
