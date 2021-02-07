package src;

import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class User extends Person {

    List<Book> borrowedBooks = new ArrayList<>();


    public User(String name, String password) {

        super(name, password);

    }

    public void printBookList() {
        for (Book book : borrowedBooks) {
            System.out.println("Book name: " + book.getName());
            System.out.println("Author: " + book.getAuthor());
        }
    }

    /// Abbas shit......

    public void addBook(List<Book> collect) {
        collect.stream().forEach(x -> {
            borrowedBooks.add(x);
            System.out.println("The book borrowed successfully!");
            reminder(x);
        });
    }

    public void removeBook(List<Book> collect) {
        collect.stream().forEach(x -> borrowedBooks.remove(x));
    }

    public void reminder(Book book) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (borrowedBooks.contains(book)) {
                    System.out.println("\nDu behöver lämna tillbaka boken: " + book.getName() + "\n");
                }

            }
        };
         timer.schedule(task,30000);
    }
    //Abbas shit ......

    public void printAllBorrowedBooks() {
        borrowedBooks.stream().forEach(System.out::println);
    }


    @Override
    public String toString() {
        return "User: " + super.getUserName() +
                "\nBorrowed Books:\n" + borrowedBooks.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
                .trim();
    }

}
