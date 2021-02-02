package src;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class DocHandler {

    List<Book> books = new ArrayList<>();
    HashMap<String , String> allUsers = new HashMap<>();

    List<Book> readFromBook(){
        File myObj = new File("Books.txt"); // Specify the filename
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                books.add(new Book(data));
                System.out.println(data);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while opening  book file ");
            e.printStackTrace();
        }

        return books;
    }

    void writeToBooks(String stringLine){
        File bookfile = new File("Books.txt");

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(bookfile, true));
            bw.append(stringLine);
            bw.newLine();

            bw.close();
            System.out.println("Successfully wrote to the Books file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    void writeToUsers(String stringLine){

        try {
            File userfile = new File("Users.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(userfile, true));
            bw.append(stringLine);
            bw.newLine();

            bw.close();
            System.out.println("Successfully wrote to the Users file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    HashMap<String, String> readFromUser(){
        try {
            File myObj = new File("Users.txt"); // Specify the filename
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String username = data.substring(data.indexOf("username=")+10 , data.indexOf("password=")-3);
                String password = data.substring(data.indexOf("password=")+10 , data.indexOf("}")-2);
                allUsers.put(username , password);
                System.out.println(data);
                System.out.println(username);
                System.out.println(password);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while opening  User file ");
            e.printStackTrace();
        }

        return allUsers;
    }

}


