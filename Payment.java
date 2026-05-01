package cineplex;

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Payment extends JFrame {

    Payment(Cart c) {
        this.setLayout(null);
        this.setBounds(420, 200, 400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Payment");
        this.getContentPane().setBackground(new Color(255, 254, 224));

        String[] labels = {"Full Name", "Card Number", "Expiry (MM/YY)", "CVV", "Email"};
        int y = 20;

        JTextField[] fields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i] + ":");
            label.setBounds(30, y, 130, 30);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            this.add(label);

            fields[i] = new JTextField();
            fields[i].setBounds(170, y, 180, 30);
            fields[i].setFont(new Font("Arial", Font.PLAIN, 14));
            this.add(fields[i]);

            y += 50;
        }

        JButton confirmBtn = new JButton("Confirm Payment");
        confirmBtn.setBounds(80, y + 10, 220, 40);
        confirmBtn.setBackground(new Color(85, 107, 47));
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.setFont(new Font("Arial", Font.BOLD, 14));
        confirmBtn.setFocusable(false);
        confirmBtn.setBorderPainted(false);
        confirmBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        confirmBtn.addActionListener(e -> {
            for (JTextField f : fields) {
                if (f.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                    return;
                }
            }
 
            // write each cart item to this user's receipt file
            String receiptFile = Login.loggedInUser + "_receipt.txt";
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(receiptFile, true));
                for (String item : Cart.items) {
                    bw.write(item);
                    bw.newLine();
                }
                bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
 
            // update revenue for admin and hall owner
            for (String item : Cart.items) {
                double price = Double.parseDouble(item.substring(item.lastIndexOf("$") + 1));
                AdminPanel.addRevenue(price);
                HallOwnerPanel.addRevenue(price);
            }
 
            JOptionPane.showMessageDialog(this, "Payment Successful! Enjoy your movie.");
            Cart.items.clear();
            c.disposeCart();
            this.dispose();
        });

        this.add(confirmBtn);
    }
}