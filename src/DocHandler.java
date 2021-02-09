

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DocHandler {
    List<Book> books = new ArrayList<>();
    HashMap<String , String> allUsers = new HashMap<>();


    public void writeToBooksFile(Object object) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream obj = null;

        try {
            fileOutputStream = new FileOutputStream("AllBooks.ser", false);
            obj = new ObjectOutputStream(fileOutputStream);
            obj.writeObject(object);
            obj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void writeToUsersFile(HashMap<String, Person> allUsers) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream obj = null;

        try {
            fileOutputStream = new FileOutputStream("AllUsers.ser", false);
            obj = new ObjectOutputStream(fileOutputStream);
            obj.writeObject(allUsers);
            obj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public Object readObject(String fileName){

        ObjectInputStream objectinputstream = null;

        Object object = null;

        try {

            FileInputStream streamIn = new FileInputStream(fileName);

            objectinputstream = new ObjectInputStream(streamIn);

            object = objectinputstream.readObject();

            objectinputstream.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return object;
    }

}
