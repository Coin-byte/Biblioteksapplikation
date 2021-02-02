package src;

import javax.xml.namespace.QName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private Person currentUser;
    private HashMap<String, Person> allUsers = new HashMap<>();

    private List<Book> allBooks = new ArrayList<>();

    Task task = new Task();

    public Library() {
        //Hårdkodade som tillfälligt test
        User testUser = new User("User", "testUser");
        Admin test = new Admin("admin", "test");
        allUsers.put(test.getUserName(), test);
        allUsers.put(testUser.getUserName(), testUser);
        bookList.add(new Book("Memoarer", "Marcus Aurelius"));
        bookList.add(new Book("Sagan om Ringen", "JRR Tolkien"));
        bookList.add(new Book("Sagan om de två tornen", "JRR Tolkien"));
        //-------------------------------

        currentUser = task.login(allUsers);
        start();
    }

    public void start() {
        String cont;
        do {
            if (currentUser instanceof Admin) {
                adminSwitch((Admin) currentUser);
            } else if (currentUser instanceof User) {
                userSwitch((User) currentUser);
            } else {
                System.out.println("Something went wrong..");
            }
            System.out.println();
            System.out.println("Type 'quit' to exit, hit enter to continue");
            cont = task.scanString();
        } while (!cont.equalsIgnoreCase("quit"));
    }

    public void adminSwitch(Admin currentUser) {
        AdminMenu adminMenu = task.showMenuAndGetChoice(AdminMenu.values());
        switch (adminMenu) {
            case ADD_NEW_BOOK:
                System.out.println("It works, dags att fylla på med metoder!");
                break;
            case REMOVE_BOOK:
                break;
            case DISPLAY_CURRENTLY_BORROWED:
                break;
            case DISPLAY_USERS_AND_BOOKS:
                break;
            case DISPLAY_USER_AND_BOOKS:
                findUser();
                break;
            case ADD_NEW_USER:
                break;
        }
    }

    public void userSwitch(User currentUser) {
        UserMenu userMenu = task.showMenuAndGetChoice(UserMenu.values());
        switch (userMenu) {
            case SHOW_ALL_BOOKS:
                break;
            case SHOW_AVAILABLE_BOOKS:
                break;
            case SEARCH_LIBRARY:
                findBook();
                break;
            case SHOW_ALL_BORROWED:
                //currentUser.showAllBorrowed();
                break;
            case BORROW_NEW_BOOK:
                break;
            case RETURN_BOOK:
                break;
        }
    }

    public String inputName() {
        //TODO Förbättra möget, tillåta fler ord?, Splitta upp i mindre delar? Nuvarande RegEx tillåter inte ÅÄÖ

        String searchFor = task.scanString();
        String searchPatternOne = "[a-zA-Z]+\\s[a-zA-Z]+";
        String searchPatternTwo = "[a-zA-Z]+";
        boolean found = false;
        do if (searchFor.matches(searchPatternOne)) {
            found = searchFor.matches(searchPatternOne);
        } else if (searchFor.matches(searchPatternTwo)) {
            found = searchFor.matches(searchPatternTwo);
        } else {
            System.out.println("Keep it simple, 1-2 words");
            searchFor = task.scanString();
        } while (!found);
        return searchFor;
    }

    public void findUser() {
        System.out.println("Please enter a username");
        Person user = allUsers.get(inputName());
        if (user != null) {
            System.out.println(user.toString());
        } else {
            System.out.println("Could not find user");
        }
    }

    public void findBook(){
        System.out.println("Please enter Title or Author");
        String userInput = inputName();
        for (Book b : bookList) {
            if (b.getAuthor().matches(userInput)) {
                System.out.println("matched author");
            } else if (b.getName().toLowerCase().contains(userInput.toLowerCase())) {
                System.out.println(b.toString() + "\nSummary: " + b.getDescription() + "\nAvalible: "); //TODO lägg till att hämta boolean från bok
            }
        }
    }

    public Person getCurrentUser() {
        return currentUser;
    }


    //////////Abbas Shit......................................

    //Låna en bok
    public void borrow(String title) {
        List<Book> collect = allBooks.stream().filter(x -> x.getName().matches(title) & x.isAvailable()).collect(Collectors.toList());
        allBooks.stream().filter(x -> x.getName().matches(title) & x.isAvailable()).forEach(book -> book.setAvailable(false));
        User user = (User) currentUser;
        user.addBook(collect);
    }

    //Tillgängliga böcker
    public void getAvailableBooks() {
        allBooks.stream().filter(x -> x.isAvailable()).forEach(System.out::println);
    }
    //////////Abbas Shit......................................


    //Lämna tillbaka en bok

    public void returnBook(String title) {
        List<Book> collect = allBooks.stream().filter(x -> x.getName().matches(title) & !x.isAvailable()).collect(Collectors.toList());
        allBooks.stream().filter(x -> x.getName().matches(title) & !x.isAvailable()).forEach(book -> book.setAvailable(true));

        User user = (User) currentUser;
        user.removeBook(collect);
    }

    //Lånade böcker(bibliotekare);
    public void getBorrowedBooks() {
        allBooks.stream().filter(x -> !x.isAvailable()).forEach(System.out::println);
    }


    //Ta bort bok, (bibliotekare)
    public void RemoveBook(String title) {
        allBooks.stream().filter(x -> x.getName().matches(title)).forEach(x -> allBooks.remove(x));
    }

    //Skriver ut alla Users och deras lånade böcker.
    public void printAllUsersAndTheirBooks() {
        for (String personName : allUsers.keySet()) {
            System.out.println(allUsers.get(personName));
            ((User) allUsers.get(personName)).printBookList();
        }
    }

//////////Abbas Shit......................................
}
