import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Book {
    private String bookId;
    private String name;
    private String author;
    private String description;
    private LocalDate date;
    private boolean  borrow;


    public Book(String book,  String author , String bookId , String description){
        this.bookId = bookId;
        this.book = book;
        this.author = author;
        this.description = description;
    }

    //deserialize the line from the file
    public Book(String line){
        String sub = line;
        bookId = sub.substring(sub.indexOf("bookId=")+7 , sub.indexOf("book=")-1);
        book = sub.substring( sub.indexOf("book=")+6 , sub.indexOf("author=")-3);
        author = sub.substring(sub.indexOf("author=")+8 , sub.indexOf("description=") -3);
        description = sub.substring(sub.indexOf("description=")+13 ,sub.indexOf("}")-1 );
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", book='" + name + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
