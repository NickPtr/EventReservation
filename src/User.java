
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class User implements Both {

    //arxikopoihsi metablitwn
    static File myObj = new File("info.txt");
    static File temp = new File("del.txt");
    static File event = new File("event.txt");
    static File newevent = new File("newevent.txt");
    int ok = 0;
    int conf = 0;
    int confseats = 0;

    //constructor
    public User() {

    }
    //sinartisi gia to sing in tou user
    public void SignIn(String Uname, String email, String pass, String Confpass) {

        try {

            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.startsWith(Uname)) {
                    System.out.println("\nThis username is not available!! Try again!!\n");
                    ok = 1;
                }

            }

            if (ok == 0) {
                FileWriter myWriter = new FileWriter("info.txt", true);
                BufferedWriter write = new BufferedWriter(myWriter);
                write.write(Uname + " " + pass);
                write.newLine();
                write.close();

                System.out.println("\nSuccesfull Sing up!!\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException ex) {
            System.out.println("An error occurred.");
        }

    }
    //sinartisi gia to log in tou user
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
    //sinartisi gia tin diagrafi tou logariasmou 
    public void Delete(String Uname, String pass) {
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
            BufferedReader reader = new BufferedReader(new FileReader(myObj));
            String removeID = Uname + " " + pass;
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine;
                if (trimmedLine.equals(removeID)) {
                    currentLine = "";
                }
                bw.write(currentLine + System.getProperty("line.separator"));

            }
            System.out.println("Your account has deleted!!\n");
            bw.close();
            reader.close();
            boolean delete = myObj.delete();
            boolean b = temp.renameTo(myObj);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //sinartisi gia tin kratisi se event
    public void Reservation(String title, String date, int pos) {

        try {
            int flag = 0;
            setConfSeats(0);
            Scanner myReader;
            myReader = new Scanner(event);
            while (myReader.hasNextLine()) {
                FileWriter myWriter = new FileWriter("newevent.txt", true);
                BufferedWriter write = new BufferedWriter(myWriter);
                String data = myReader.nextLine();
                if (data.startsWith("Title: " + title)) {
                    if (data.contains(date)) {
                        String[] toRep = data.split("\t");
                        String[] ok = toRep[3].split(": ");
                        if ((Integer.parseInt(ok[1])) >= pos && (Integer.parseInt(ok[1])) != 0) {
                            data = data.replace(toRep[3], "Seats: " + (Integer.parseInt(ok[1]) - pos));
                            flag = 1;
                        } else if ((Integer.parseInt(ok[1])) == 0) {
                            flag = 2;
                        }
                    }
                }
                if (flag != 2) {
                    write.write(data);
                    write.newLine();
                }

                write.close();
                if (flag == 2) {
                    break;
                }
            }
            myReader.close();
            boolean delete = event.delete();
            boolean b = newevent.renameTo(event);
            if (flag == 0) {
                JFrame parent = new JFrame();

                JOptionPane.showMessageDialog(parent, "Not enough seats!");
                setConfSeats(1);
            }

            if (flag == 1) {
                Scanner scanner7 = new Scanner(System.in);
                Scanner scanner8 = new Scanner(System.in);
                JFrame parent = new JFrame();

                JOptionPane.showMessageDialog(parent, "Please continue with the payment\nTotal amount: " + pos * 10 + "€");

                System.out.print("\nCredit Card Number: ");
                String card = scanner7.nextLine();
                System.out.print("Your name: ");
                String nameRes = scanner8.nextLine();

                FileWriter myWriter = new FileWriter("payment.txt", true);
                BufferedWriter write = new BufferedWriter(myWriter);
                write.write(nameRes + " " + card);
                write.newLine();
                write.close();

            }
            if (flag == 2) {
                JFrame parent = new JFrame();

                JOptionPane.showMessageDialog(parent, "Sorry\nNo seats remaining");
                setConfSeats(2);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //sinartisi gia tin akirosi kratisis se event
    public void Cancel(String title, String date, int pos) {
        try {

            Scanner myReader;
            myReader = new Scanner(event);
            while (myReader.hasNextLine()) {
                FileWriter myWriter = new FileWriter("newevent.txt", true);
                BufferedWriter write = new BufferedWriter(myWriter);
                String data = myReader.nextLine();
                if (data.startsWith("Title: " + title)) {
                    if (data.contains(date)) {
                        String[] toRep = data.split("\t");
                        String[] ok = toRep[3].split(": ");
                        if ((Integer.parseInt(ok[1])) >= pos) {
                            data = data.replace(toRep[3], "Seats: " + (Integer.parseInt(ok[1]) + pos));
                        }
                    }
                }
                write.write(data);
                write.newLine();
                write.close();
            }
            myReader.close();
            boolean delete = event.delete();
            boolean b = newevent.renameTo(event);

            JFrame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Reservation canceled!");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
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
    public void setConfSeats(int confseats) {
        this.confseats = confseats;
    }
    //Get gia to flag pou pigenei stin "Main" gia elenxo
    public int getConfSeats() {
        return confseats;
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
