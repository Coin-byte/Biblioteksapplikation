

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static java.time.temporal.ChronoUnit.DAYS;

public class User extends Person {

    List<Book> borrowedBooks = new ArrayList<>();


    public User(String name, String password) {

        super(name, password);

    }

    public void printBookList() {
        for (Book book : borrowedBooks) {
            System.out.println("Book name: " + book.getName());
            System.out.println("Author: " + book.getAuthor() + "\n");
            if(DAYS.between(LocalDate.now(), book.getReturnDate()) >= 0){
                System.out.println("Return in " + DAYS.between(LocalDate.now(), book.getReturnDate()) + " days.");
            }else{
                System.out.println(book.getName() + " is late, return it immediately! \n");
            }
        }
    }

    /// Abbas shit......

    public void addBook(List<Book> collect) {
        collect.stream().forEach(x -> {
            borrowedBooks.add(x);
            System.out.println("The book borrowed successfully!");
        });
    }

    public void removeBook(Book book) {
        borrowedBooks.removeIf(b -> b.getName().matches(book.getName()));
        System.out.println("Returned " + book.getName() + " to the library");
    }

/*    public void reminder(Book book) {
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
    }*/

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
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
