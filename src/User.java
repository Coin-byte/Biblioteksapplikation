import java.util.ArrayList;
import java.util.List;

public class User extends Person {

    List<Book> borrowedBooks = new ArrayList<>();



    private List<Book> checkBookInfo = new ArrayList<>();





    public User(String name, String password) {

        super(name,password);

       addBooksToList();
       printBookList();
       addBookToBorrowedBooks(new Book("Metro2033","Dmitry Glukhovsky"));
        addBookToBorrowedBooks(new Book("Test","JRR Tolkien"));

    }
    public void addBooksToList(){
        checkBookInfo.add(new Book("Hjärnstark","Anders Hansen"));
        checkBookInfo.add(new Book("Hobbit","J.R.R. Tolkien"));
    }

    public void printBookList(){
        for (Book book:checkBookInfo) {
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
