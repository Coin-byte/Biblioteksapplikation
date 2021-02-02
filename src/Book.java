public class Book {
    private int bookId;
    private String name;
    private String author;
    private String description;


    public Book(String name,  String author){
     ///   bookId.incrementAndGet();
        int bookId = 0;
        this.name = name;
        this.author = author;
        description = "....";
    }

    //deserialize the line from the file
    public Book(String line){
        /// use regex för att hitta book name och  description
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

    public void setBookId(int bookId) {
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

    public int getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return "Book Title: " + getName() + " \nAuthor: " + getAuthor() + "\nShort description: " + getDescription() +"\n ";
    }
}
