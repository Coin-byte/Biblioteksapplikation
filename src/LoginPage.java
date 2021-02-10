import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class LoginPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("userID:");
    JLabel userPasswordLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel();
    HashMap<String, Person> logininfo;
    Person currentUser;

    public Person getCurrentUser() {
        return currentUser;
    }

    LoginPage() {


        userIDLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);

        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));

        userIDField.setBounds(125, 100, 200, 25);
        userPasswordField.setBounds(125, 150, 200, 25);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(225, 200, 100, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }

    public Person login(HashMap<String, Person> logininfoFromLib) {
        logininfo = logininfoFromLib;

        return currentUser;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
        }

        if (e.getSource() == loginButton) {

            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if (logininfo.containsKey(userID)) {
                if (logininfo.get(userID).getPassWord().equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login successful");
                    currentUser = logininfo.get(userID);
                    frame.dispose();
                    WelcomePage n = new WelcomePage();
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Wrong password");
                }

            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("User name not found");
            }
        }
    }


    //-----------------------------------------------------------------------------------------------------
    class WelcomePage implements ActionListener {

        JFrame frame = new JFrame();
        JLabel messageLabel = new JLabel();
        JLabel UsermessageLabel = new JLabel();


        public WelcomePage() {
            messageLabel.setBounds(85, 40, 250, 35);
            messageLabel.setFont(new Font(null, Font.ITALIC, 25));
            messageLabel.setText("Program is running!");
            messageLabel.setForeground(Color.green);

            UsermessageLabel.setBounds(85, 85, 250, 35);
            UsermessageLabel.setFont(new Font(null, Font.ITALIC, 25));
            UsermessageLabel.setText("Loged in as: " + currentUser.getUserName());
            UsermessageLabel.setForeground(Color.red);




            frame.add(messageLabel);
            frame.add(UsermessageLabel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(420, 200);
            frame.setLayout(null);
            frame.setVisible(true);

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        }

        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }
}


