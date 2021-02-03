import java.util.ArrayList;
import java.util.List;

public class User extends Person {

    List<Book> borrowedBooks = new ArrayList<>();


    public User(String name, String password) {

        super(name,password);

    }

    public void printBookList(){
        for (Book book:borrowedBooks) {
            System.out.println("Book name: "+ book.getName());
            System.out.println("Author: "+ book.getAuthor());
        }


    }

    public void addBookToBorrowedBooks(Book newBook){
        borrowedBooks.add(newBook);
        System.out.println(getUserName()+" Have lent the book " + newBook.toString());
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
