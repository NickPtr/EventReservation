
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin implements Both {

    //arxikopoihsi metablitwn
    static File myObj = new File("info.txt");
    static File event = new File("event.txt");
    static File temp = new File("delevent.txt");
    int conf = 0;

    public Admin() {//constructor

    }

    public void SignIn(String Uname, String email, String pass, String Confpass) {

    }
    //sinartisi gia to log in tou admin
    public void LogIn(String Uname, String pass) {

        int count = 0;
        int length = 0;
        setConf(0);
        try {

            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                length++;
                String data = myReader.nextLine();
                if (data.equals(Uname + " " + pass)) {
                    System.out.println("\nWelcome!!\n");

                    setConf(1);
                    break;

                } else {

                    count++;
                }
            }
            if (count == length) {
                System.out.println("\nWrong Username or password!!\n");
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    //sinartisi gia tin eisagwgi neou event
    public void Add(String title, String type, String date, int pos) {

        try {

            FileWriter myWriter = new FileWriter("event.txt", true);
            BufferedWriter write = new BufferedWriter(myWriter);
            write.write("Title: " + title + "\tType: " + type + "\tDate: " + date + "\tSeats: " + pos);
            write.newLine();
            write.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException ex) {
            System.out.println("An error occurred.");
        }

    }
    //sinartisi gia tin diagrafi event
    public void Delete(String title, String type, String date, int pos) {
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
            BufferedReader reader = new BufferedReader(new FileReader(event));
            String removeID = "Title: " + title + "\tType: " + type + "\tDate: " + date + "\tSeats: " + pos;
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine;
                if (trimmedLine.equals(removeID)) {
                    currentLine = "";
                }
                bw.write(currentLine + System.getProperty("line.separator"));

            }
            System.out.println("The event has been deleted!!\n");
            bw.close();
            reader.close();
            boolean delete = event.delete();
            boolean b = temp.renameTo(event);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //sinartisi gia tin emfanisi twn event
    public void Print() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(event));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                System.out.println(currentLine);
            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //Set gia to flag pou pigenei stin "Main" gia elenxo
    public void setConf(int conf) {
        this.conf = conf;
    }
    //Get gia to flag pou pigenei stin "Main" gia elenxo
    public int getConf() {
        return conf;
    }

}
