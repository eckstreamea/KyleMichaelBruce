import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.event.*;

public class PlayGame extends JPanel {
    private BufferedImage image; //might not be necessary to have all these images. If we do, we should use an arraylist of BufferedImages
    public Blackjack blackjack;

    public PlayGame() {
        //dump all the images into arraylist with a loop?
        // for( etc.)
        blackjack = new Blackjack();
    }

    public Blackjack getBlackjack() {
        return blackjack;
    }

    public void copyBlackjack(Blackjack i) {
        blackjack = i;
    }

    public void paintComponent(Graphics g) {
        //g.clearRect(250, 500, 300 , 300 );
        Graphics2D g2 = (Graphics2D) g;
        //deck draws back for first image and then draws the rest of the deck
        try {
            image = ImageIO.read(new File("BACK.jpg"));
            g2.drawImage(image, 100, 100, null);
        } catch (Exception e) {
            System.out.print("back faliure");
        }

        int a = blackjack.compareString(); // never used


        // x = 0 y = 0
        //draws every players' hand in a double layered for loop
        for (int i = 0; i < blackjack.getPlayers().size() - 1; i++) {
            for (int j = 0; j < blackjack.getPlayers().get(i).getHand().size(); j++) {
                try {
                    image = blackjack.getPlayers().get(i).getHand().get(j).getImage();
                    g2.drawImage(image, 250 * i, 50 * j + 500, null);
                } catch (Exception e) {
                    System.out.print("i=" + (i) + "j=" + (j) + " faliure player loop");
                }
            }
        }
        for (int i = 0; i < blackjack.getPlayers().get(blackjack.getPlayers().size() - 1).getHand().size(); i++) {
            try {
                if (blackjack.getPlayers().get(blackjack.getPlayers().size() - 1).getHand().get(i).getFD() == true) {
                    image = ImageIO.read(new File("BACK.jpg"));
                    g2.drawImage(image, i * 50 + 100, 100, null);
                } else {
                    image = blackjack.getPlayers().get(blackjack.getPlayers().size() - 1).getHand().get(i).getImage();
                    g2.drawImage(image, i * 50 + 100, 100, null);
                }
            } catch (Exception e) {
                System.out.println("great faliures");
            }
        }
        if (blackjack.getTurnNumber() > blackjack.getPlayers().size() - 2) {
            blackjack.other();
            blackjack.printElse();
            if (blackjack.compare() != blackjack.getPlayers().size() - 1) {
                int fontSize = 12;
                g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
                g.drawString("YOU WIN", 10, 20);
            } else if (blackjack.compare() == 100) {
                int fontSize = 12;
                g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
                g.drawString("YOU Push", 10, 20);
            } else {
                int fontSize = 12;
                g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
                g.drawString("YOU sdfhjkllsoe", 10, 20);
            }
        }
    }

    public static void main(String[] args) {//you can create new JPanel and do frame.add if you want {
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        PlayGame playGame = new PlayGame();
        frame.add(playGame);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JButton hit = new JButton("HIT");
        playGame.add(hit);
        JButton stay = new JButton("STAY");
        playGame.add(stay);
        JButton split = new JButton("SPLIT");
        playGame.add(split);
        playGame.repaint();
        Scanner a = new Scanner(System.in);
        Blackjack blackjack = playGame.getBlackjack();
        class HitButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                blackjack.getPlayers().get(blackjack.getTurnNumber()).hit(blackjack.getDeck());
                if (blackjack.getPlayers().get(blackjack.getTurnNumber()).calculateValue() > 21) {

                    System.out.println(blackjack.getPlayers().get(blackjack.getTurnNumber()).showAll());
                    blackjack.updateTurn();
                    if (blackjack.getTurnNumber() > blackjack.getPlayers().size() - 2) {
                        playGame.copyBlackjack(blackjack);
                        playGame.repaint();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        blackjack.endRound();
                        blackjack.Beground();
                    }
                }

                //hit current player and stay in loop unless >21, then move to next player

            }

        }

        class StayButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                //JLabel jlabel = new JLabel("This is a label");
                //jlabel.setFont(new Font("Verdana",1,20));
                //playGame.add(jlabel);
                blackjack.updateTurn();
                if (blackjack.getTurnNumber() > blackjack.getPlayers().size() - 2) {
                    blackjack.other();
                    blackjack.printElse();
                    if (blackjack.compare() != blackjack.getPlayers().size() - 1) {
                    } else if (blackjack.compare() == 100) {
                    } else {
                    }
                    playGame.copyBlackjack(blackjack);
                    playGame.repaint();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    blackjack.endRound();
                    blackjack.Beground();
                }
                //calls stay for current player
            }
        }
        class SplitButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if (blackjack.getPlayers().get(blackjack.getTurnNumber()).getHandSize() == 2
                        && blackjack.getPlayers().get(blackjack.getTurnNumber()).getNum2(0)
                        .equals(blackjack.getPlayers().get(blackjack.getTurnNumber()).getNum2(1))) {
                    blackjack.addPlayer(blackjack.getPlayers().get(blackjack.getTurnNumber()).split(),
                            blackjack.getPlayers().get(blackjack.getTurnNumber()).returnCount());
                    playGame.copyBlackjack(blackjack);
                    playGame.repaint();
                }
                //calls split for current players
            }
        }
        class ResetButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {

            }
        }
        class TimerListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                playGame.repaint();
            }
        }
        ActionListener listener = new TimerListener();
        Timer t = new Timer(100, listener); //delay time of 1000 millisecond
        t.start();
        ActionListener hitListener = new HitButtonListener();
        hit.addActionListener(hitListener);
        ActionListener stayListener = new StayButtonListener();
        stay.addActionListener(stayListener);
        ActionListener splitListener = new SplitButtonListener();
        split.addActionListener(splitListener);
    }
    //need to keep track of which player we are on and which players have died
}
