package cineplex;

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class AdminPanel extends JFrame {

    static final double ADMIN_CUT = 0.60; //get 60%

    //admin gets to see his money
    AdminPanel() {
        this.setLayout(null);
        this.setBounds(350, 150, 500, 350);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Admin Panel");
        this.getContentPane().setBackground(new Color(30, 30, 30));

        JLabel header = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        header.setBounds(0, 0, 500, 60);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        header.setBackground(new Color(140, 40, 40));
        header.setForeground(Color.WHITE);
        header.setOpaque(true);
        this.add(header);

        JLabel cutLabel = new JLabel("Admin Cut: 60% per ticket");
        cutLabel.setBounds(60, 100, 380, 30);
        cutLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        cutLabel.setForeground(Color.LIGHT_GRAY);
        this.add(cutLabel);

        double revenue = loadRevenue(); 

        JLabel revenueLabel = new JLabel("Total Revenue:  $" + String.format("%.2f", revenue));
        revenueLabel.setBounds(60, 150, 380, 40);
        revenueLabel.setFont(new Font("Arial", Font.BOLD, 22));
        revenueLabel.setForeground(new Color(255, 234, 0));
        this.add(revenueLabel);
    }

    //used in payment class
    public static void addRevenue(double ticketPrice) {
        double current = loadRevenue();
        saveRevenue(current + ticketPrice * ADMIN_CUT);
    }

    //linked to addRevenue
    static void saveRevenue(double amount) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("admin_revenue.txt"));
            bw.write(String.format("%.2f", amount));
            bw.close();
        } catch (IOException e) { }
    }
    
    //previously set some amount, after every time user buy ticket, the amount will change
    static double loadRevenue() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("admin_revenue.txt"));
            double val = Double.parseDouble(br.readLine().trim());
            br.close();
            return val;
        } catch (Exception e) {
            return 1500.00; //amount for previously booked seats
        }
    }
}