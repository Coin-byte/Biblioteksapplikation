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
        allBooks.add(new Book("Memoarer", "Marcus Aurelius", "test", "test"));
        allBooks.add(new Book("Sagan om Ringen", "JRR Tolkien", "test", "test"));
        allBooks.add(new Book("Sagan om de två tornen", "JRR Tolkien", "test", "test"));
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
                addNewBook();
                break;
            case REMOVE_BOOK:
                System.out.println("Which title to remove");
                RemoveBook(inputName());
                break;
            case DISPLAY_CURRENTLY_BORROWED:
                getBorrowedBooks();
                break;
            case DISPLAY_USERS_AND_BOOKS:
                printAllUsersAndTheirBooks();
                break;
            case DISPLAY_USER_AND_BOOKS:
                findUser();
                break;
            case ADD_NEW_USER:
                addNewUser();
                break;
        }
    }

    public void userSwitch(User currentUser) {
        UserMenu userMenu = task.showMenuAndGetChoice(UserMenu.values());
        switch (userMenu) {
            case SHOW_ALL_BOOKS:
                break;
            case SHOW_AVAILABLE_BOOKS:
                getAvailableBooks();
                break;
            case SEARCH_LIBRARY:
                findBook();
                break;
            case SHOW_ALL_BORROWED:
                currentUser.printBookList();
                break;
            case BORROW_NEW_BOOK:
                System.out.println("Which book do you want to borrow?");
                borrow(task.scanString());
                break;
            case RETURN_BOOK:
                System.out.println("Which book do you want to return");
                returnBook(task.scanString());
                break;
        }
    }

    //TODO Förbättra möget tills imorgon
    public String inputName() {

        String search = task.scanString();
        String regexOne = "[a-zA-Z]+\\s[a-zA-Z]+";
        String regexTwo = "[a-zA-Z]+";

        boolean found = false;
        do if (stringMatch(search, regexOne) || stringMatch(search, regexTwo)) {
            found = true;
        } else {
            System.out.println("Keep it simple, 1-2 words");
            search = task.scanString();
        } while (!found);
        return search;
    }

    public boolean stringMatch(String searchFor, String regEx) {
        if (searchFor.matches(regEx)) {
            return true;
        } else return false;
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

    public void findBook() {
        System.out.println("Please enter Title or Author");
        String userInput = inputName();
        for (Book b : allBooks) {
            if (b.getAuthor().toLowerCase().contains(userInput.toLowerCase()) ||
                    b.getName().toLowerCase().contains(userInput.toLowerCase())) {
                System.out.println(b.toString() + "\nSummary: " + b.getDescription() + "\nAvalible: " + b.isAvailable());
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
            if (allUsers.get(personName) instanceof User) {
                System.out.println(allUsers.get(personName));
                ((User) allUsers.get(personName)).toString();
            }
        }
    }

//////////Abbas Shit......................................






    public void addNewBook(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Enter Book title ");
        String userName = myObj.nextLine();  // Read book title input

        System.out.println("Enter Author's name ");
        String authorsname  = myObj.nextLine();  // Read book title input

        System.out.println("Enter ISBN");
        String bookId  = myObj.nextLine();  // Read book title input

        System.out.println("Enter book description: ");
        String description = myObj.nextLine();  // Read book title input

        Book newBook =  new Book(userName , authorsname , bookId, description);
        allBooks.add(newBook);


        ////this docHandler must be already in the contructor
        ///  FileReader doc = new FileReader();
        ///  doc.writeToBooks(newBook.toString());
    }



    public void addNewUser(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Enter the new user name  ");
        String userName = myObj.nextLine();  // Read book title input
        if(allUsers.containsKey(userName)){
            System.out.println("Srry! the user already exsist");
        }
        else{
            System.out.println("Enter the new user name  ");
            String password = myObj.nextLine();  // Read book title input
            allUsers.put(userName, new User(UserName , password));'
            System.out.println("the new user were added successfully !!!! ");

        }
    }


}
