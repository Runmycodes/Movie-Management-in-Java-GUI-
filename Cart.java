package cineplex;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Cart extends JFrame {

    public static ArrayList<String> items = new ArrayList<>();

    private final JPanel ticketList = new JPanel();
    private final JLabel totalLabel = new JLabel("Total: $0.00", SwingConstants.RIGHT);

    Cart() {
        this.setLayout(null);
        this.setBounds(400, 200, 520, 560);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Cart");
        this.getContentPane().setBackground(new Color(255, 254, 224));

        ticketList.setLayout(new BoxLayout(ticketList, BoxLayout.Y_AXIS));
        ticketList.setBackground(new Color(255, 254, 224));

        JScrollPane scroll = new JScrollPane(ticketList);
        scroll.setBounds(10, 10, 480, 430);
        scroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        this.add(scroll);

        totalLabel.setBounds(10, 450, 480, 30);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(totalLabel);

        JButton payBtn = new JButton("Proceed to Payment");
        payBtn.setBounds(130, 490, 240, 40);
        payBtn.setBackground(new Color(85, 107, 47));
        payBtn.setForeground(Color.WHITE);
        payBtn.setFont(new Font("Arial", Font.BOLD, 14));
        payBtn.setFocusable(false);
        payBtn.setBorderPainted(false);
        payBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        payBtn.addActionListener(e -> new Payment(this).setVisible(true));
        this.add(payBtn);
    }

    public static void addItem(String movieName, String seatName, double price) {
        items.add(movieName + "  |  Seat: " + seatName + "  |  $" + String.format("%.2f", price));
    }

    public void cartClick() {
        ticketList.removeAll();

        double total = 0;

        for (String item : items) {
            JLabel label = new JLabel("  " + item);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            label.setOpaque(true);
            label.setBackground(new Color(255, 245, 150));
            label.setPreferredSize(new Dimension(460, 45));
            label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
            label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
            ticketList.add(label);

            total += Double.parseDouble(item.substring(item.lastIndexOf("$") + 1));
        }

        totalLabel.setText("Total: $" + String.format("%.2f", total));

        ticketList.revalidate();
        ticketList.repaint();
        this.setVisible(true);
    }
    
    public void disposeCart(){
        this.setVisible(false);
    }
    
}