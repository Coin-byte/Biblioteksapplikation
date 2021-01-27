import java.util.ArrayList;
import java.util.List;

public class User extends Person {

    List<Book> Borrowed = new ArrayList<>();

    public User(String name, String password) {
        super(name,password);
    }
}
