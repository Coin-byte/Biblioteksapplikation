import java.io.Serializable;

public class Person implements Serializable{

    private String userName, passWord;

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

}
