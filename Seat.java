package cineplex;

import java.awt.*;
import javax.swing.*;

public class Seat extends JFrame {
    private double ticketPrice;

    public Seat(double ticketPrice, String seatName, Runnable onPurchase) {
        this.ticketPrice = ticketPrice;
        this.setBounds(380, 400, 900, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(false);
        this.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 900, 200);
        panel.setBackground(Color.PINK);

        JLabel label = new JLabel("Seat " + seatName + "  —  Ticket Price: $" + String.format("%.2f", ticketPrice));
        label.setFont(new Font("Arial", Font.BOLD, 28));
        label.setBounds(40, 0, 700, 200);

        JButton purchase = new JButton("Add to Cart");
        purchase.setBounds(650, 70, 200, 50);
        purchase.setFocusPainted(false);
        purchase.setFocusable(false);
        purchase.setBackground(new Color(255, 244, 242));
        purchase.setBorder(BorderFactory.createBevelBorder(20));
        purchase.setFont(new Font("Montserrat", Font.BOLD, 15));
        purchase.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        purchase.addActionListener(e -> {
            purchase.setText("Added!");
            purchase.setEnabled(false);
            onPurchase.run();   //save seat to the file and basically calling the purchase lamda expression in Booking
        });

        panel.add(purchase);
        panel.add(label);

        this.add(panel);
    }

    public void setVisible() {
        this.setVisible(true);
    }
}