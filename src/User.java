package src;

import java.util.ArrayList;
import java.util.List;

public class User extends Person {

    List<Book> borrowedBooks = new ArrayList<>();



    private List<Book> checkBookInfo = new ArrayList<>();





    public User(String name, String password) {

        super(name,password);

       addBooksToList();
       printBookList();

       addBookToBorrowedBooks(new Book("Metro2033","Dmitry Glukhovsky","Dmitry Glukhovsky","Dmitry Glukhovsky"));


    }
    public void addBooksToList(){
        checkBookInfo.add(new Book("Hjärnstark","Anders Hansen","Dmitry Glukhovsky","Dmitry Glukhovsky"));
        checkBookInfo.add(new Book("Hobbit","J.R.R. Tolkien","Dmitry Glukhovsky","Dmitry Glukhovsky"));
    }

    public void printBookList(){
        for (Book book:borrowedBooks) {
            System.out.println("Book name: "+ book.getName());
            System.out.println("Author: "+ book.getAuthor());
        }


    }
    public void addBookToBorrowedBooks(Book newBook){
        borrowedBooks.add(newBook);
        System.out.println(getUserName()+" Have lent the books");

        for (Book book:borrowedBooks) {


            System.out.println(book.getName());
            System.out.println(book.getAuthor());

         }
    }

    /// Abbas shit......

    public void addBook(List<Book> collect){
        collect.stream().forEach(x ->borrowedBooks.add(x));
    }

    public void removeBook(List<Book> collect){
        collect.stream().forEach(x ->borrowedBooks.remove(x));
    }

    //Abbas shit ......

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
