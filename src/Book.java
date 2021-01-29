import java.util.concurrent.atomic.AtomicInteger;

public class Book {
    int bookId;
    String book;
    String author;
    String description;


    public Book(String book,  String author){
     ///   bookId.incrementAndGet();
        int bookId = 0;
        this.book = book;
        this.author = author;
        description = "....";
    }

    //deserialize the line from the file
    public Book(String line){
        /// use regex för att hitta book name och  description
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBook() {
        return book;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public int getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", book='" + book + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
