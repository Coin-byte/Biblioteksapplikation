public enum AdminMenu implements HasDescription{
    ADD_NEW_BOOK("Add new book"),
    REMOVE_BOOK("Remove book from library"),
    DISPLAY_CURRENTLY_BORROWED("Show all borrowed books"),
    DISPLAY_USERS_AND_BOOKS("Display all users"),
    DISPLAY_BOOKS_USER_HAS("Search for user"), //and display books user has
    ADD_NEW_USER("Add new user");

    private String description;

    AdminMenu(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}