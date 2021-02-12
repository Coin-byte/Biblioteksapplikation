

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class Library {
    private Person currentUser;
    private HashMap<String, Person> allUsers;
    private List<Book> allBooks;
    private Pattern namePattern = Pattern.compile("[a-zA-Z]+\\s[a-zA-Z]+|[a-zA-Z]+");
    LoginPage login = new LoginPage();

    Task task = new Task();
    DocHandler docHandler = new DocHandler();

    public Library() {
        allBooks = (List<Book>) docHandler.readObject("AllBooks.ser");
        allUsers = (HashMap<String, Person>) docHandler.readObject("AllUsers.ser");

        //-------------------------------
        boolean running = true;
       do {
            try {
            currentUser = login.login(allUsers);
            if(currentUser instanceof User){
                reminder((User) currentUser);
            }
            if(currentUser != null){
                start();
            }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       } while (running);

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
            System.out.println("Type 'quit' to log out, hit enter to continue");
            cont = task.scanString();
        } while (!cont.equalsIgnoreCase("quit"));
    }

    public void adminSwitch(Admin currentUser) {
        AdminMenu adminMenu = task.showMenuAndGetChoice(AdminMenu.values());
        switch (adminMenu) {
            case SHOW_ALL_BOOKS:
                printBookList();
                break;
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
            case DELETE_A_USER:
                deleteUser();
                break;

        }
        saveAllBooks();
        saveAllUsers();
    }

    public void userSwitch(User currentUser) {
        UserMenu userMenu = task.showMenuAndGetChoice(UserMenu.values());
        switch (userMenu) {
            case SHOW_ALL_BOOKS:
                printBookList();
                break;
            case SORT_BY_AUTHOR:
                sortByAuthor();
                break;
            case SORT_BY_TITLE:
                sortByName();
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
                returnBook(task.scanString(), currentUser);
                break;
        }
        saveAllBooks();
        saveAllUsers();
    }

    public String inputName() {
        String search;
        boolean found;
        do {
            search = task.scanString();
            Matcher m = namePattern.matcher(search);
            found = m.find();
        } while (!found);
        return search;
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


    //////////Abbas Shit......................................

    //Låna en bok
    public void borrow(String title) {
        List<Book> collect = allBooks.stream().filter(x -> x.getName().matches(title) & x.isAvailable()).collect(Collectors.toList());
        allBooks.stream().filter(x -> x.getName().matches(title) & x.isAvailable()).forEach(book -> {
            book.setAvailable(false);
            book.setReturnDate(generateReturnDate());
        });
        User user = (User) currentUser;
        if (!collect.isEmpty()){
            user.addBook(collect);
        }else{
            System.out.println("\n Title not found!\n");
        }

    }

    //Tillgängliga böcker
    public void getAvailableBooks() {
        allBooks.stream().filter(x -> x.isAvailable()).forEach(System.out::println);
    }
    //////////Abbas Shit......................................


    //Lämna tillbaka en bok

    public void returnBook(String title, User user) {

        List<Book> collect = user.getBorrowedBooks().stream().filter(x -> x.getName().matches(title)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            setAvalibleTrue(title);
            System.out.println("Returned " + title + " to the library.\n");
        }else {
            System.out.println("Nothing to return");
        }
        collect.stream().forEach(x -> user.getBorrowedBooks().removeIf(y -> y.getName().matches(title)));
    }

    public void setAvalibleTrue(String title){
        allBooks.stream().filter(x -> x.getName().matches(title) & !x.isAvailable()).forEach(book -> book.setAvailable(true));
    }

    //Lånade böcker(bibliotekare);
    public void getBorrowedBooks() {
        allBooks.stream().filter(x -> !x.isAvailable()).forEach(System.out::println);
    }


    //Ta bort bok, (bibliotekare)
    public void RemoveBook(String title) {

        if (allBooks.removeIf(b -> b.getName().matches(title))){
            allBooks.removeIf(b -> b.getName().matches(title));
            System.out.println("Deleted "+ title );
        }else{
            System.out.println("Not found!");
        }


    }

    //Skriver ut alla Users och deras lånade böcker.
    public void printAllUsersAndTheirBooks() {
        for (String personName : allUsers.keySet()) {
            if (allUsers.get(personName) instanceof User) {

                System.out.println("\n"+allUsers.get(personName));


            }
        }
    }

    public void printBookList() {

        allBooks.forEach(book -> System.out.println("Title: " + book.getName()+"\nAuthor: "+book.getAuthor()+
                "\nDescription: "+book.getDescription()+"\nAvaible: "+book.isAvailable()+"\n"));


    }

    // Denna metod sorteras efter titel
    public void sortByName() {

        allBooks.stream().sorted(Comparator.comparing(Book::getName)).forEach(System.out::println);

    }

    //Denna metod sorteras efter Arthor
    public void sortByAuthor() {
        allBooks.stream().sorted(Comparator.comparing(Book::getAuthor)).forEach(System.out::println);
    }

    public void printMyBorrowedBooks() {
        ((User) currentUser).printAllBorrowedBooks();
    }


//////////Abbas Shit......................................


    public void addNewBook() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Enter Book title ");
        String userName = myObj.nextLine();  // Read book title input

        System.out.println("Enter Author's name ");
        String authorsname = myObj.nextLine();  // Read book title input

        System.out.println("Enter ISBN");
        String bookId = myObj.nextLine();  // Read book title input

        System.out.println("Enter book description: ");
        String description = myObj.nextLine();  // Read book title input

        Book newBook = new Book(userName, authorsname, bookId, description);
        allBooks.add(newBook);


        ////this docHandler must be already in the contructor
        ///  FileReader doc = new FileReader();
        ///  doc.writeToBooks(newBook.toString());
    }


    public void addNewUser() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Enter the user name:  ");
        String userName = myObj.nextLine();  // Read book title input
        if (allUsers.containsKey(userName)) {
            System.out.println("Sorry! the user already exist");
        } else {
            System.out.println("Enter the password:  ");
            String password = myObj.nextLine();  // Read book title input
            allUsers.put(userName, new User(userName, password));
            System.out.println("the new user were added successfully !!!! ");

        }
    }

    /////
    public void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the user name : ");
        String username = scanner.nextLine();

        if (allUsers.containsKey(username)) {
            User person = (User)allUsers.get(username);
            person.getBorrowedBooks().stream().forEach(x-> setAvalibleTrue(x.getName()));
            allUsers.remove(username);
            System.out.println("Successfully deleted: " + username);
        } else {
            System.out.println(username + " does not exsist");
        }
    }

    public void saveAllBooks() {
        docHandler.writeToBooksFile(allBooks);
    }

    public void saveAllUsers() {
        docHandler.writeToUsersFile(allUsers);
    }

    public void reminder(User currentUser) {
        if (!currentUser.getBorrowedBooks().isEmpty()){
            checkReturnDate(currentUser);
            System.out.println("Hit anything to continue..");
            task.scanString();
        }
    }

    public LocalDate generateReturnDate() {
        LocalDate today = LocalDate.now();
        return today.minusDays(1); //Tillfälligt för debug, ändra till plusWeeks(2) för 2 veckor
    }

    public void checkReturnDate(User currentUser) {
        List<Book> returnDates = currentUser.getBorrowedBooks();
        LocalDate today = LocalDate.now();
        if (returnDates != null) {
            returnDates.stream().filter(b -> b.getReturnDate().isBefore(today)).forEach(x -> System.out.println("Return " + x.getName() + " to the library"));
            //ska skrivas ut om boken är försenad
        }
    }

    public HashMap<String, Person> getAllUsers() {
        return allUsers;
    }

    public void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }
}
