package cineplex;

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Receipt extends JFrame {

    Receipt() {
        this.setLayout(null);
        this.setBounds(450, 200, 520, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Your Ticket Receipt");
        this.getContentPane().setBackground(new Color(255, 254, 224));

        JLabel header = new JLabel("Receipt — " + Login.loggedInUser, SwingConstants.CENTER);
        header.setBounds(0, 0, 520, 45);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setBackground(new Color(255, 234, 0));
        header.setOpaque(true);
        this.add(header);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        area.setBackground(new Color(255, 245, 150));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(10, 55, 480, 360);
        scroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.add(scroll);

        // read all past purchases from this user's receipt file
        double total = 0;
        String receiptFile = Login.loggedInUser + "_receipt.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(receiptFile));
            String line;
            while ((line = br.readLine()) != null) {
                area.append("  " + line + "\n\n");
                total += Double.parseDouble(line.substring(line.lastIndexOf("$") + 1));
            }
            br.close();
        } catch (IOException e) {
            area.setText("  No purchases yet.");
        }

        JLabel totalLabel = new JLabel("Total Paid: $" + String.format("%.2f", total), SwingConstants.RIGHT);
        totalLabel.setBounds(10, 425, 480, 30);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(totalLabel);
    }

    public void recClick() {
        this.setVisible(true);
    }
}