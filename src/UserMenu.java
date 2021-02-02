public enum UserMenu implements HasDescription{

    //Descriptions är placeholders, behöver inte vara the final product
    SHOW_ALL_BOOKS("Show all books in library."),
    SHOW_AVAILABLE_BOOKS("Show available books"),
    SEARCH_LIBRARY("Search library"),
    SHOW_ALL_BORROWED("Show currently borrowed"), //And return date?
    BORROW_NEW_BOOK("Borrow"),
    RETURN_BOOK("Return");

    private String description;

    UserMenu(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
