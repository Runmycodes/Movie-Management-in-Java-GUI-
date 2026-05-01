package cineplex;

import java.awt.*;
import javax.swing.*;

public class Start extends JFrame {

    //this will create a starting frame, user will enter his/her name and pass, admin or hall owner can enter
    Start() {
        this.setLayout(null);
        this.setBounds(350, 150, 500, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Cineplex");
        this.getContentPane().setBackground(new Color(40, 40, 40));

        ImageIcon logo = new ImageIcon("cineplex.png");
        Image logoImg = logo.getImage().getScaledInstance(250, 90, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        logoLabel.setBounds(120, 30, 250, 90);
        this.add(logoLabel);

        JLabel subtitle = new JLabel("Welcome — Who are you?", SwingConstants.CENTER);
        subtitle.setBounds(0, 130, 500, 30);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setForeground(Color.LIGHT_GRAY);
        this.add(subtitle);

        JButton customerBtn = new JButton("Customer");
        customerBtn.setBounds(150, 190, 200, 50);
        customerBtn.setBackground(new Color(85, 107, 47));
        customerBtn.setForeground(Color.WHITE);
        customerBtn.setFont(new Font("Arial", Font.BOLD, 16));
        customerBtn.setFocusable(false);
        customerBtn.setBorderPainted(false);
        customerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        customerBtn.addActionListener(e -> { //action button... creates login class
            Login login = new Login();
            login.setVisible(true);
        });
        this.add(customerBtn);

        JButton ownerBtn = new JButton("Hall Owner");
        ownerBtn.setBounds(150, 260, 200, 50);
        ownerBtn.setBackground(new Color(180, 130, 0));
        ownerBtn.setForeground(Color.WHITE);
        ownerBtn.setFont(new Font("Arial", Font.BOLD, 16));
        ownerBtn.setFocusable(false);
        ownerBtn.setBorderPainted(false);
        ownerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ownerBtn.addActionListener(e -> { //action button opens JOPTIONPANE dialog box..
            String pass = JOptionPane.showInputDialog(this, "Enter Hall Owner Password:");
            if (pass != null && pass.equals("owner123")) {
                HallOwnerPanel panel = new HallOwnerPanel(); //hall owner can enter by entering correct pass, will create a class
                panel.setVisible(true);
            } else if (pass != null) {
                JOptionPane.showMessageDialog(this, "Incorrect password.");
            }
        });
        this.add(ownerBtn);

        JButton adminBtn = new JButton("Admin");
        adminBtn.setBounds(150, 330, 200, 50);
        adminBtn.setBackground(new Color(140, 40, 40));
        adminBtn.setForeground(Color.WHITE);
        adminBtn.setFont(new Font("Arial", Font.BOLD, 16));
        adminBtn.setFocusable(false);
        adminBtn.setBorderPainted(false);
        adminBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        adminBtn.addActionListener(e -> { //same thing for admin as that of a hall owner
            String pass = JOptionPane.showInputDialog(this, "Enter Admin Password:");
            if (pass != null && pass.equals("admin123")) {
                AdminPanel panel = new AdminPanel();
                panel.setVisible(true);
            } else if (pass != null) {
                JOptionPane.showMessageDialog(this, "Incorrect password.");
            }
        });
        this.add(adminBtn);
        this.setVisible(true);
    }
}