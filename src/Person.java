public class Person {

    protected String userName, passWord;
   // private Library library ;
    protected boolean isAdmin;

    public Person() {
    }

    public Person(String userName, String passWord, boolean isAdmin) {
        this.userName = userName;
        this.passWord = passWord;
        this.isAdmin = isAdmin;
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
