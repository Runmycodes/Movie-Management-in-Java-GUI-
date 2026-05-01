package cineplex;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Booking extends JPanel {
    
    //i make different price by default
    private double[] currentPrices = {45.29, 42.19, 38.79, 35.39, 31.29, 24.99, 21.99, 19.99};

    // price per row... 
    private double getPrice(String row) {
        switch (row) {
            case "A": return currentPrices[0];
            case "B": return currentPrices[1];
            case "C": return currentPrices[2];
            case "D": return currentPrices[3];
            case "E": return currentPrices[4];
            case "F": return currentPrices[5];
            case "G": return currentPrices[6];
            case "H": return currentPrices[7];
            default: return 10.00;
        }
    }
    Booking(String movieName) {
        this.setLayout(null);
        this.setBounds(0, 0, 914, 800);
        this.setBackground(new Color(40, 40, 40));
        
        //here i will change my current prices for the certain movie in case hall owner changed the prices, which we had stored
        //we saw this in the hall owner class
        String priceFile = movieName.replaceAll("[^a-zA-Z0-9]", "_") + "_prices.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(priceFile))) {
            String[] parts = br.readLine().trim().split(",");
            for (int i = 0; i < 8 && i < parts.length; i++) {
                currentPrices[i] = Double.parseDouble(parts[i]);
            }
        }catch (Exception e) {
            
        }
        
        String fileName = movieName.replaceAll("[^a-zA-Z0-9]", "_") + "_seats.txt";

        //load already occupied seats froma file into a set
        Set<String> takenSeats = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                takenSeats.add(line.trim());
            }
        }catch (IOException e) { }

        // ---- SCREEN ----
        JLabel screenLabel = new JLabel("S C R E E N", SwingConstants.CENTER);
        screenLabel.setBounds(100, 20, 680, 50);
        screenLabel.setBackground(new Color(200, 200, 200));
        screenLabel.setForeground(new Color(40, 40, 40));
        screenLabel.setFont(new Font("Arial", Font.BOLD, 22));
        screenLabel.setOpaque(true);
        screenLabel.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
        this.add(screenLabel);

        
        String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H"};
        int startX = 120;
        int startY = 100;
        int seatW  = 55;
        int seatH  = 45;
        int gapX   = 10;
        int gapY   = 12;

        for (int row = 0; row < rows.length; row++) {
            for (int col = 1; col <= 10; col++) {
                String seatName = rows[row] + col;
                boolean isTaken = takenSeats.contains(seatName);

                JButton seat = new JButton(seatName);
                seat.setBounds(
                    //some intense math...
                        
                        
                    startX + (col - 1) * (seatW + gapX),
                    startY + row * (seatH + gapY),
                    seatW, seatH
                );
                seat.setForeground(Color.WHITE);
                seat.setFont(new Font("Arial", Font.PLAIN, 11));
                seat.setFocusable(false);
                seat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                if (isTaken) {
                    //grey out taken seats
                    seat.setBackground(new Color(100, 100, 100));
                    seat.setEnabled(false); //cannot click on the seat
                    seat.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 1));
                } else {
                    seat.setBackground(new Color(85, 107, 47));
                    seat.setBorder(BorderFactory.createLineBorder(new Color(60, 80, 30), 1));

                    double price = getPrice(rows[row]);

                    seat.addActionListener(e -> {
                        //ok so user just clicked on a seat..
                        Runnable onPurchase = () -> { //a runnable function that will run when we call it using .run
                            saveSeat(fileName, seatName); //save this seat number, so it get's greyed out, and so hall owner can see it, it appears on receipt..
                            Cart.addItem(movieName, seatName, price); //goes to cart frame  
                            seat.setBackground(new Color(100, 100, 100));
                            seat.setEnabled(false);
                        };
                        Seat s = new Seat(price, seatName, onPurchase); //this just opens that ticket price frame
                        s.setVisible();
                    });
                }

                this.add(seat);
            }
        }

        JLabel availLabel = new JLabel("  Available");
        availLabel.setBounds(250, 700, 180, 30);
        availLabel.setBackground(new Color(85, 107, 47));
        availLabel.setForeground(Color.WHITE);
        availLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        availLabel.setOpaque(true);
        this.add(availLabel);

        JLabel takenLabel = new JLabel("  Taken");
        takenLabel.setBounds(450, 700, 180, 30);
        takenLabel.setBackground(Color.GRAY);
        takenLabel.setForeground(Color.WHITE);
        takenLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        takenLabel.setOpaque(true);
        this.add(takenLabel);
        
    }

    // appends the seat name to the movie's file
    private void saveSeat(String fileName, String seatName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(seatName);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}