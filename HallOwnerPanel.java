package cineplex;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class HallOwnerPanel extends JFrame {

    static final double OWNER_CUT = 0.40; //hall owner get 40%

    private String[] movies = {
        "The Super Mario Bros. Movie", "Dune: Part Two", "Kung Fu Panda 4",
        "Godzilla x Kong", "Inside Out 2", "Deadpool & Wolverine",
        "Moana 2", "Alien: Romulus", "Joker: Folie a Deux",
        "Wicked", "Gladiator II", "Mufasa: The Lion King"
    }; 

    HallOwnerPanel() {
        this.setLayout(null);
        this.setBounds(200, 100, 700, 560);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Hall Owner Panel");
        this.getContentPane().setBackground(new Color(255, 254, 224));

        JLabel header = new JLabel("Hall Owner Panel", SwingConstants.CENTER);
        header.setBounds(0, 0, 700, 55);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setBackground(new Color(180, 130, 0));
        header.setForeground(Color.WHITE);
        header.setOpaque(true);
        this.add(header);

        //will show revenue.. using loadRevenue()
        JLabel revenueLabel = new JLabel("Revenue (40%): $" + String.format("%.2f", loadRevenue())); 
        revenueLabel.setBounds(400, 65, 270, 25);
        revenueLabel.setFont(new Font("Arial", Font.BOLD, 14));
        revenueLabel.setForeground(new Color(85, 107, 47));
        this.add(revenueLabel);

        JLabel selectLabel = new JLabel("Movie:");
        selectLabel.setBounds(30, 70, 80, 25);
        selectLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(selectLabel);

        //a dropdown cool gui thing for selecting movies
        JComboBox<String> dropdown = new JComboBox<>(movies);
        dropdown.setBounds(100, 70, 280, 28);
        this.add(dropdown);

        // booked seats
        JLabel bookedLabel = new JLabel("Booked Seats:");
        bookedLabel.setBounds(30, 120, 120, 25);
        bookedLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(bookedLabel);

        //text area is basically a bigger version of text label.. 
        JTextArea bookedArea = new JTextArea();
        bookedArea.setEditable(false);
        bookedArea.setBackground(new Color(255, 245, 150));
        bookedArea.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(bookedArea); //u can scroll from left to right
        scroll.setBounds(30, 150, 480, 100);
        this.add(scroll);

        JButton viewBtn = new JButton("View Booked Seats");
        viewBtn.setBounds(530, 150, 140, 40);
        viewBtn.setBackground(new Color(85, 107, 47));
        viewBtn.setForeground(Color.WHITE);
        viewBtn.setFont(new Font("Arial", Font.BOLD, 12));
        viewBtn.setFocusable(false);
        viewBtn.setBorderPainted(false);
        viewBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewBtn.addActionListener(e -> { //ok so when i click this button it should load the seat file for that certain movie..
            String movie = (String) dropdown.getSelectedItem(); /// why do i have to typecast????! 
            String fileName = movie.replaceAll("[^a-zA-Z0-9]", "_") + "_seats.txt"; //a template for removing symbols.. and putting _ instead
            // i have to get it in the form, super_mario_bros.seat, that just matches the name basically..
            bookedArea.setText("");
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName)); 
                ArrayList<String> seats = new ArrayList<>();
                String line;
                //adding the seat numbers to the array list
                while ((line = br.readLine()) != null) seats.add(line.trim()); //always trim, it's good, I often leave spaces after writing the seat number
                br.close();
                if (seats.isEmpty()) { //if there were no seats booked
                    bookedArea.setText("No seats booked yet.");
                } else {
                    bookedArea.setText("Booked (" + seats.size() + "): ");
                    for(var a: seats){ //just print the seat numbers
                        System.out.print(a + " ");
                    }
                }
            } catch (IOException ex) {
                bookedArea.setText("No bookings found for this movie.");
            }
        });
        this.add(viewBtn);

        
        JLabel timingLabel = new JLabel("Timing:");
        timingLabel.setBounds(30, 275, 80, 25);
        timingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(timingLabel);

        //the hall owner can set the time the movie will be played
        JTextField timingField = new JTextField();
        timingField.setBounds(110, 275, 200, 30);
        this.add(timingField);

        JButton saveTimingBtn = new JButton("Save");
        saveTimingBtn.setBounds(325, 275, 80, 30);
        saveTimingBtn.setBackground(new Color(85, 107, 47));
        saveTimingBtn.setForeground(Color.WHITE);
        saveTimingBtn.setFocusable(false);
        saveTimingBtn.setBorderPainted(false);
        saveTimingBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveTimingBtn.addActionListener(e -> {
            String movie = (String) dropdown.getSelectedItem();
            String fileName = movie.replaceAll("[^a-zA-Z0-9]", "_") + "_timing.txt";
            try {
                //so i'm writing the time down in a file that has suffix "timing"
                BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
                bw.write(timingField.getText().trim());// trimming good, user can leave a space at the end..
                bw.close();
                JOptionPane.showMessageDialog(this, "Timing saved! Restart Menu to see update.");
            } catch (IOException ex) { }
        });
        this.add(saveTimingBtn);

        //set prices
        JLabel priceLabel = new JLabel("Row Prices A-H (comma separated):");
        priceLabel.setBounds(30, 330, 300, 25);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(priceLabel);

        // lot of prices.. that have been previously set..
        JTextField priceField = new JTextField("45.29,42.19,38.79,35.39,31.29,24.99,21.99,19.99");
        priceField.setBounds(30, 360, 460, 30);
        this.add(priceField);

        JButton savePriceBtn = new JButton("Save");
        savePriceBtn.setBounds(505, 360, 80, 30);
        savePriceBtn.setBackground(new Color(85, 107, 47));
        savePriceBtn.setForeground(Color.WHITE);
        savePriceBtn.setFocusable(false);
        savePriceBtn.setBorderPainted(false);
        savePriceBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        savePriceBtn.addActionListener(e -> { //just save the prices the way they are arranged into each file
            String movie = (String) dropdown.getSelectedItem();
            String[] parts = priceField.getText().split(",");
            if (parts.length != 8) {
                JOptionPane.showMessageDialog(this, "Enter exactly 8 prices.");
                return;
            }
            String fileName = movie.replaceAll("[^a-zA-Z0-9]", "_") + "_prices.txt";
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
                bw.write(priceField.getText().trim());
                bw.close();
                JOptionPane.showMessageDialog(this, "Prices saved! Will apply next time Booking opens.");
            } catch (IOException ex) { }
        });
        this.add(savePriceBtn);

        //load timing and prices when movie changes in dropdown.. 
        dropdown.addActionListener(e -> {
            String movie = (String) dropdown.getSelectedItem();

            String timingFile = movie.replaceAll("[^a-zA-Z0-9]", "_") + "_timing.txt";
            try {
                BufferedReader br = new BufferedReader(new FileReader(timingFile));
                timingField.setText(br.readLine());
                br.close();
            } catch (IOException ex) { timingField.setText(""); }

            String pricesFile = movie.replaceAll("[^a-zA-Z0-9]", "_") + "_prices.txt";
            try {
                BufferedReader br = new BufferedReader(new FileReader(pricesFile));
                priceField.setText(br.readLine());
                br.close();
                //if there's an error set the prices as they were..
            } catch (IOException ex) { priceField.setText("45.29,42.19,38.79,35.39,31.29,24.99,21.99,19.99"); }
        });
    }
    
    
    
    public static void addRevenue(double ticketPrice) {
        double current = loadRevenue();
        saveRevenue(current + ticketPrice * OWNER_CUT);
    }

    static double loadRevenue() { 
        try {
            //I will basically just store a certain value in the owner file, then i will add value to it later when user buys tickets
            //for now I just read the value there right now
            BufferedReader br = new BufferedReader(new FileReader("owner_revenue.txt"));
            double val = Double.parseDouble(br.readLine().trim());
            br.close();
            return val;
        } catch (Exception e) {
            return 1000.00;
        }
    }

    //save this man's earnings in a file
    static void saveRevenue(double amount) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("owner_revenue.txt"));
            bw.write(String.format("%.2f", amount));
            bw.close();
        } catch (Exception e) { }
    }
}