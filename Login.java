package cineplex;

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Login extends JFrame {

    //stores the name of the user
    public static String loggedInUser = null;

    //this is where the customer can login or register...
    Login() {
        this.setLayout(null);
        this.setBounds(380, 200, 400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Customer Login");
        this.getContentPane().setBackground(new Color(255, 254, 224));

        JLabel title = new JLabel("Login / Register", SwingConstants.CENTER);
        title.setBounds(0, 0, 400, 50);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBackground(new Color(255, 234, 0));
        title.setOpaque(true);
        this.add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 80, 100, 30);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(userLabel);

        //here's a text field... user can enter data for name
        JTextField userField = new JTextField();
        userField.setBounds(160, 80, 180, 30);
        this.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 130, 100, 30);
        passLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(passLabel);

        //enter password into this JPasswordField util, basically i get the *** when typing.. yay..
        JPasswordField passField = new JPasswordField();
        passField.setBounds(160, 130, 180, 30);
        this.add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(50, 200, 120, 40);
        loginBtn.setBackground(new Color(85, 107, 47));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setFocusable(false);
        loginBtn.setBorderPainted(false);
        loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginBtn.addActionListener(e -> {
            String user = userField.getText().trim(); //get the text from the text field and password field
            String pass = new String(passField.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) { //ask again if the input is empty...
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            if (checkLogin(user, pass)) { //here i will check if the user loggin into that account exists or not
                loggedInUser = user;
                this.dispose();
                Menu menu = new Menu(); //open the menu.. where there are movieees
                menu.frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Wrong username or password.");
            }
        });
        this.add(loginBtn);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(220, 200, 120, 40);
        registerBtn.setBackground(new Color(180, 130, 0));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
        registerBtn.setFocusable(false);
        registerBtn.setBorderPainted(false);
        registerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerBtn.addActionListener(e -> {
            String user = userField.getText().trim();
            String pass = new String(passField.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            if (userExists(user)) { //now let's check if the user already registered before
                JOptionPane.showMessageDialog(this, "Username already taken.");
                return;
            }

            saveUser(user, pass); //if register succesful, save the name and pass!
            loggedInUser = user;
            this.dispose();
            Menu menu = new Menu();
            menu.frame.setVisible(true);
        });
        this.add(registerBtn);
    }

    private boolean checkLogin(String user, String pass) {
        try {
            //this file will have basically "username:password" format in which it will be saved.. so we just read it that way
            BufferedReader br = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":"); //split at the part of : because left of it is name and right of it is password
                if (parts[0].equals(user) && parts[1].equals(pass)) { //could've just done next(), haha...
                    br.close();
                    return true;
                }
            }
            br.close();
        } catch (IOException e) { }
        return false;
    }

    private boolean userExists(String user) { //same stuff as checkLogin
        try {
            BufferedReader br = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.split(":")[0].equals(user)) { //I dont need to check the pass, just need the username
                    //could've done String[] parts again, but this is just direct so yeah..
                    br.close();
                    return true;
                }
            }
            br.close();
        } catch (IOException e) { }
        return false;
    }

    private void saveUser(String user, String pass) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true));
            bw.write(user + ":" + pass);
            bw.newLine(); // '\n'
            bw.close();
        } catch (IOException e) { }
    }
}