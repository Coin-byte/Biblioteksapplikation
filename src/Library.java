import java.util.HashMap;

public class Library {
    private Person currentUser;
    private HashMap<String, Person> allUsers = new HashMap<>();
    Task task = new Task();

    public Library() {
        //Hårdkodade som tillfälligt test
        User testUser = new User("User", "testUser");
        Admin test = new Admin("admin", "test");
        allUsers.put(test.getUserName(), test);
        allUsers.put(testUser.getUserName(), testUser);
        //-------------------------------

        currentUser = task.login(allUsers);
        start();
    }

    public void start() {
        String cont;
        do {
            if (currentUser instanceof Admin) {
                adminSwitch();
            } else if (currentUser instanceof User) {
                userSwitch();
            } else {
                System.out.println("Something went wrong..");
            }
            System.out.println();
            System.out.println("Type 'quit' to exit, hit enter to continue");
            cont = task.scanString();
        } while (!cont.equalsIgnoreCase("quit"));
    }

    public void adminSwitch() {
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
            case DISPLAY_BOOKS_USER_HAS:
                break;
            case ADD_NEW_USER:
                break;
        }
    }

    public void userSwitch() {
        UserMenu userMenu = task.showMenuAndGetChoice(UserMenu.values());
        switch (userMenu) {
            case SHOW_ALL_BOOKS:
                break;
            case SHOW_AVAILABLE_BOOKS:
                break;
            case SHOW_DESCRIPTION:
                break;
            case SEARCH_LIBRARY:
                break;
            case SHOW_ALL_BORROWED:
                break;
            case BORROW_NEW_BOOK:
                break;
            case RETURN_BOOK:
                break;
        }
    }

    public Person getCurrentUser() {
        return currentUser;
    }
}
