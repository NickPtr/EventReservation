/*Nikos Potaris
  icsd15173*/
import java.util.Scanner;


public class Main {

    //arxikopoiis metablitwn
    static Scanner scanner = new Scanner(System.in);
    static Scanner scanner2 = new Scanner(System.in);
    static Scanner scanner3 = new Scanner(System.in);
    static Scanner scanner4 = new Scanner(System.in);
    static Scanner scanner5 = new Scanner(System.in);
    static Scanner scanner6 = new Scanner(System.in);
    static Scanner scanner7 = new Scanner(System.in);
    static Scanner scanner9 = new Scanner(System.in);

    static int in, del, add, seats;
    static int ok=1;
    static String uname, pass, mail, confpass, title, type, date;
    static Admin admin = new Admin();
    static User user = new User();

    public static void main(String[] args) {

        //enarksi epanalipsis mexri na dwsei tin katalili epilogi o xristis
        do {
            //menou epilogwn
            System.out.print("If you have an acount and you want to log in press <<1>>\nIf you don't have an acount and you want to sign up press <<2>>\nIf you want to close the programm press <<0>>\nMake your choise: ");
            in = scanner9.nextInt();
            if (in == 1) {
                System.out.print("\nGive your username: ");
                uname = scanner2.nextLine();
                System.out.print("\nGive your password: ");
                pass = scanner3.nextLine();
                //elenxos gia ton tupo logariasmou pou ginete sundesi
                if (uname.contains("admin")) {
                    System.out.println("Ok Admin");
                    admin.LogIn(uname, pass);//dimiourgia antikeimenou "admin"
                    if (admin.getConf() == 1) {//elenxos gia uparksi tou logariasmou
                        do {
                            //menou epilogwn "admin"
                            System.out.println("\nIf you want to add an event please press<<1>>\nIf you want to delete an event please press<<2>>\nIf you want to log out please press<<0>>");
                            System.out.print("Make your choice: ");
                            add = scanner3.nextInt();
                            if (add == 1) {
                                System.out.print("Title: ");
                                title = scanner4.nextLine();
                                System.out.print("Type: ");
                                type = scanner5.nextLine();
                                System.out.print("Date: ");
                                date = scanner6.nextLine();
                                System.out.print("Seats: ");
                                seats = scanner7.nextInt();
                                admin.Add(title, type, date, seats);//kaloume sinartisi "Add"
                            } else if (add == 2) {

                                admin.Print();

                                System.out.print("Title: ");
                                title = scanner4.nextLine();
                                System.out.print("Type: ");
                                type = scanner5.nextLine();
                                System.out.print("Date: ");
                                date = scanner6.nextLine();
                                System.out.print("Seats: ");
                                seats = scanner7.nextInt();
                                admin.Delete(title, type, date, seats);//kaloume sinartisi "Delete"

                            } else if (add == 0) {
                                System.out.println("Bye!!");
                                break;
                            } else {
                                System.out.println("\nWrong choise please try again!\n");
                            }
                        } while (add != 0);

                    }

                } else {
                    System.out.println("Ok User");

                    user.LogIn(uname, pass);//dimiourgia antikeimenou "user"
                    if (user.getConf() == 1) {//elenxos gia uparksi tou logariasmou
                        do {
                            System.out.println("\nIf you want to delete your account please press <<1>>\nIf you want to make a reservation please press<<2>>\nIf you want to cancel your reservation please press<<3>>\nIf you want to log out please press<<0>>");
                            System.out.print("Make your choise: ");
                            del = scanner4.nextInt();
                            if (del == 1) {
                                user.Delete(uname, pass);//kaloume sinartisi "Delete"
                                del = 0;
                            } else if (del == 2) {
                                System.out.println("\nEvents:\n");
                                user.Print();//kaloume sinartisi "Print"
                                System.out.println("Type the event you want to makethe reservation for");
                                System.out.print("Title: ");
                                title = scanner5.nextLine();
                                System.out.print("Date: ");
                                date = scanner6.nextLine();
                                System.out.print("Seats: ");
                                seats = scanner7.nextInt();

                                user.Reservation(title, date, seats);//kaloume sinartisi "Reservation"
                                if (user.getConfSeats() == 1) {
                                    do {
                                        Scanner scanner8 = new Scanner(System.in);
                                        System.out.print("Seats: ");
                                        seats = scanner8.nextInt();
                                        user.Reservation(title, date, seats);
                                    } while (user.getConfSeats() != 0);

                                } else if (user.getConfSeats() == 2) {

                                }

                            } else if (del == 3) {

                                user.Cancel(title, date, seats);//kaloume sinartisi "Cancel"

                            } else if (del == 0) {
                                System.out.println("Bye!!");
                                break;
                            } else {
                                System.out.println("\nWrong choise please try again!\n");
                            }
                        } while (del != 0);

                    }

                }

            } else if (in == 2) {
                //epilogi gia dimiourgia logariasmou
                System.out.print("\n\nGive your username: ");
                uname = scanner2.nextLine();
                System.out.print("\n\nGive your e-mail: ");
                mail = scanner3.nextLine();
                do {//elenxos gia egirotita kwdikou
                    System.out.print("\n\nGive your password: ");
                    pass = scanner4.nextLine();
                    System.out.print("\n\nConfirm your password: ");
                    confpass = scanner5.nextLine();
                    if (!pass.equals(confpass)) {
                        System.out.println("\nNot matched password!! Try again!!");
                    }
                } while (!pass.equals(confpass));

                user.SignIn(uname, mail, pass, confpass);//kaloume sinartisi "SignIn"

            } else if (in == 0) {
                System.out.println("\nBye!!\n");
            } else {
                System.out.println("\nWrong choise please try again!\n");
            }
        } while (in != 0);
        System.exit(0);
    }
}
