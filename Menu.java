
package cineplex;

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Menu extends Cart{
    JFrame frame;
    JPanel topPanel;
    JPanel movieList;
    Cart cart = new Cart();
    Receipt receipt = new Receipt();
    
    Menu(){
        frame = new JFrame();
        frame.setLayout(null);
        frame.setResizable(false); //set false later
        frame.setBounds(350, 100, 914, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 254, 224));
        
        //yellow header
        topPanel = new JPanel();
        topPanel.setBounds(0, 0, 900, 90);
        topPanel.setBackground(new Color(255, 234, 0));
        topPanel.setLayout(null);
        topPanel.setBorder(BorderFactory.createLineBorder(new Color(85, 107, 47), 3));
        
        //cineplex logo
        ImageIcon logo1 = new ImageIcon("cineplex.png");
        Image logoImg = logo1.getImage().getScaledInstance(180, 70, Image.SCALE_SMOOTH);
        logo1 = new ImageIcon(logoImg);
        
        JLabel logoLabel1 = new JLabel();
        logoLabel1.setIcon(logo1);
        logoLabel1.setBounds(30, 10, 180, 70);
        
        //cart logo
        ImageIcon logo2 = new ImageIcon("cart.jpg");
        Image logoImg2 = logo2.getImage().getScaledInstance(45, 45, 0);
        logo2 = new ImageIcon(logoImg2);
        
        JButton cartButton = new JButton();
        cartButton.setIcon(logo2);
        cartButton.setBounds(790, 21, 45, 45); 
        cartButton.setBorder(null);
        cartButton.setFocusable(false);
        cartButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        //open cart frame
        cartButton.addActionListener(e ->{
            cart.cartClick();
        });
        
        //a receipt button
        JButton recButton = new JButton();
        ImageIcon logo3 = new ImageIcon("reciept.png");
        Image logoImg3 = logo3.getImage().getScaledInstance(45, 45, 0);
        logo3 = new ImageIcon(logoImg3);
        recButton.setIcon(logo3);
        recButton.setBounds(750, 21, 45, 45);
        recButton.setBorder(null);
        recButton.setFocusable(false);
        recButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        //open receipt fram
        recButton.addActionListener(e ->{
            receipt.recClick();
        });
        
        topPanel.add(logoLabel1);
        topPanel.add(recButton);
        topPanel.add(cartButton);
        
         //different movie objects, as there are many different types
        Movies m1 = new Movies();
        m1.setTicketInfo("The Super Mario Bros. Movie", "09-05-26", "Animation, Comedy", "English", "Saturday", 13, "10:00 AM", "mario.jpg");
 
        Movies m2 = new Movies();
        m2.setTicketInfo("Dune: Part Two", "08-05-26", "Sci-Fi, Adventure", "English", "Friday", 1, "08:00 PM", "dune.jpg");
 
        Movies m3 = new Movies();
        m3.setTicketInfo("Kung Fu Panda 4", "18-05-26", "Animation, Comedy", "English", "Friday", 8, "02:00 PM", "kung.jpg");
 
        Movies m4 = new Movies();
        m4.setTicketInfo("Godzilla x Kong", "01-06-26", "Action, Sci-Fi", "English", "Friday", 29, "06:00 PM", "kong.jpg");
 
        Movies m5 = new Movies();
        m5.setTicketInfo("Inside Out 2", "14-05-26", "Animation, Drama", "English", "Friday", 14, "11:00 AM", "inside.jpg");
 
        Movies m6 = new Movies();
        m6.setTicketInfo("Deadpool & Wolverine", "16-05-26", "Action, Comedy", "English", "Friday", 26, "09:00 PM", "dead.jpg");
 
        Movies m7 = new Movies();
        m7.setTicketInfo("Moana 2", "08-05-26", "Animation, Adventure", "English", "Wednesday", 27, "01:00 PM", "moana.jpg");
 
        Movies m8 = new Movies();
        m8.setTicketInfo("Alien: Romulus", "16-05-26", "Horror, Sci-Fi", "English", "Friday", 16, "10:00 PM", "alien.jpg");
 
        Movies m9 = new Movies();
        m9.setTicketInfo("Joker: Folie a Deux", "12-05-26", "Crime, Drama", "English", "Friday", 4, "07:00 PM", "joker.jpg");
 
        Movies m10 = new Movies();
        m10.setTicketInfo("Wicked", "22-05-26", "Drama, Musical", "English", "Friday", 22, "05:00 PM", "wicked.jpg");
 
        Movies m11 = new Movies();
        m11.setTicketInfo("Gladiator II", "15-05-26", "Action, Drama", "English", "Friday", 15, "08:00 PM", "glad.jpg");
 
        Movies m12 = new Movies();
        m12.setTicketInfo("Mufasa: The Lion King", "20-05-26", "Animation, Drama", "English", "Friday", 20, "03:00 PM", "mufa.jpeg");
 
        //movie list panel
        movieList = new JPanel();
        movieList.setLayout(new BoxLayout(movieList, BoxLayout.Y_AXIS)); //a box layout..
        movieList.setBackground(new Color(255, 254, 224));
 
        //add each Movies object as a rectangular shaped card..
        movieList.add(createMovieCard(m1));
        movieList.add(createMovieCard(m2));
        movieList.add(createMovieCard(m3));
        movieList.add(createMovieCard(m4));
        movieList.add(createMovieCard(m5));
        movieList.add(createMovieCard(m6));
        movieList.add(createMovieCard(m7));
        movieList.add(createMovieCard(m8));
        movieList.add(createMovieCard(m9));
        movieList.add(createMovieCard(m10));
        movieList.add(createMovieCard(m11));
        movieList.add(createMovieCard(m12));
 
        JScrollPane scrollPane = new JScrollPane(movieList); //a scroll pane to help the user scroll through multiple of these movies
        scrollPane.setBounds(0, 90, 914, 670);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); //create vertical scroll bar not horizontal
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //no need horizontal! too much work
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
 
        frame.add(scrollPane);       
        frame.add(topPanel);
        frame.setVisible(true);
    }
    
    private JPanel createMovieCard(Movies movie) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(new Color(255, 245, 150));
        card.setPreferredSize(new Dimension(914, 110));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
 
        // movie image on the left
        ImageIcon movieIcon = new ImageIcon(movie.getImagePath());
        Image scaled = movieIcon.getImage().getScaledInstance(75, 90, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(scaled));
        imgLabel.setBounds(10, 10, 75, 90);
 
        JLabel titleLabel = new JLabel(movie.getName());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(100, 10, 380, 25);
 
        JLabel genreLabel = new JLabel("Genre: " + movie.getGenre());
        genreLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        genreLabel.setBounds(100, 40, 350, 18);
 
        JLabel langLabel = new JLabel("Language: " + movie.getLanguage());
        langLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        langLabel.setBounds(100, 62, 250, 18);
 
        JLabel releaseLabel = new JLabel("Date: " + movie.getRelease());
        releaseLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        releaseLabel.setBounds(560, 35, 200, 18);
 
        //we create that timing file we saw in the hall owner
        //hall owner can set time, for movies
        //if this movie's time has been chnaged, we will read it and display it..
        String timing = movie.getTime();
        String timingFile = movie.getName().replaceAll("[^a-zA-Z0-9]", "_") + "_timing.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(timingFile));
            String t = br.readLine();
            br.close();
            if (t != null && !t.trim().isEmpty()) timing = t.trim();
        } catch (IOException ex) { }
 
        JLabel timeLabel = new JLabel("Time: " + timing);   // uses the updated timing
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        timeLabel.setBounds(560, 58, 200, 18);

 
        JButton bookBtn = new JButton("Book Now");
        bookBtn.setBounds(730, 30, 110, 40);
        bookBtn.setBackground(new Color(85, 107, 47));
        bookBtn.setForeground(Color.WHITE);
        bookBtn.setFont(new Font("Arial", Font.BOLD, 13));
        bookBtn.setFocusable(false);
        bookBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bookBtn.addActionListener(e -> movie.visibility());  // opens the movie's detail frame
 
        card.add(imgLabel);
        card.add(titleLabel);
        card.add(genreLabel);
        card.add(langLabel);
        card.add(releaseLabel);
        card.add(timeLabel);
        card.add(bookBtn);
 
        return card;
    }

}
