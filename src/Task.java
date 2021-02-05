package src;

import java.util.HashMap;
import java.util.Scanner;

public class Task {

    public Task() {
    }

    public Person login(HashMap<String, Person> allUsers) {
        System.out.println("Please log in");

        Person currentUser = null;
        String usrName;
        String pw;
        do {
            usrName = scanString();
            pw = scanString();
            try {
                currentUser = allUsers.get(usrName);
                if (pw.equals(currentUser.getPassWord())) {
                    System.out.println("Login successful");
                    break;
                } else {
                    System.out.println("Username or password is not correct"); //Egentligen är bara password fel men vill inte förvirra användaren
                }
            } catch (Exception e) {
                System.out.println("User not found, try again");
            }
        } while (currentUser == null || !pw.equals(currentUser.getPassWord()));
        return currentUser;
    }

    public <T extends HasDescription> T showMenuAndGetChoice(T[] menuItems) {

        System.out.println("Make a choice:\n--------");
        int i = 1;
        for (T test : menuItems) {
            System.out.println(i + " " + test.getDescription());
            i++;
        }
        int choiceIndex;
        do {
            choiceIndex = scanInt();
            try {
                return menuItems[choiceIndex - 1];
            } catch (Exception e) {
                System.out.println("That's not a valid option");
            }
        } while (choiceIndex > menuItems.length - 1 || choiceIndex < 1);
        return null;
    }

    public String scanString() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public int scanInt() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a number");
            scanner.next();
        }
        return scanner.nextInt();
    }

}
