package cineplex;

import java.awt.*;
import javax.swing.*;

public class Movies extends JFrame {

    private String name;
    private String release;
    private String genre;
    private String language;
    private String day;
    private int date;
    private String time;
    private String imagePath;   

    public Movies() {
        this.setLayout(null);
        this.setBounds(350, 100, 914, 800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }

    public void visibility() {
        Booking b = new Booking(name);
        this.add(b);
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    public void setTicketInfo(String name, String release, String genre, String language, String day, int date, String time, String imagePath) {
        this.name = name;
        this.release = release;
        this.genre = genre;
        this.language = language;
        this.day = day;
        this.date = date;
        this.time = time;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getRelease() {
        return release;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public String getDay() {
        return day;
    }

    public int getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getImagePath() {
        return imagePath;
    }
}
