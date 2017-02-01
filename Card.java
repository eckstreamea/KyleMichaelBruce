import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.*;
import java.awt.event.*;

public class Card extends JPanel {
    private String number; //A1234-jqk
    private String suit;
    private boolean facedown;
    private BufferedImage image;

    /**
     * Constructor for objects of class Card
     */
    public Card(String num, String suit2) {
        facedown = false;
        number = num;
        suit = suit2;
        try {
            image = ImageIO.read(new File(getImageName()));
        } catch (Exception e) {
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getImageName() {
        String name = "";
        name = name + number;
        name += "_";
        name += suit;
        name += ".jpg";
        return name;
    }

    public String getNum() {
        return number;
    }

    public void changeFaceDown() {
        facedown = true;
    }

    public boolean getFD() {
        return facedown;
    }

    public int getValue() {
        try {
            int output = Integer.parseInt(number);
            return output;
        } catch (Exception e) {
            if (number.equals("A"))
                return 1;
            return 10;
        }
    }
}
