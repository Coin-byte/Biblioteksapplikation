

public enum AdminMenu implements HasDescription{
    SHOW_ALL_BOOKS("Show all books"),
    ADD_NEW_BOOK("Add new book"),
    REMOVE_BOOK("Remove book from library"),
    DISPLAY_CURRENTLY_BORROWED("Show all borrowed books"),
    DISPLAY_USERS_AND_BOOKS("Display all users"),
    DISPLAY_USER_AND_BOOKS("Search for user"), //and display books user has
    ADD_NEW_USER("Add new user"),
    DELETE_A_USER("Delete user");

    private String description;

    AdminMenu(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
