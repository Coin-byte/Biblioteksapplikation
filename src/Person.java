package src;

public class Person {

    private String userName, passWord;
   // private Library library ;
    private boolean isAdmin;

    public Person() {
    }

    public Person(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
