/**
 * Write a description of class Blackjack here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class Blackjack {
    private ArrayList<Hand> players;
    private int turnnumber;
    private Deck deck;
    private Hand dealer;
    private Hand player1;
    private Hand player2;
    private Hand player3;
    private Hand player4;

    /**
     * Constructor for objects of class Blackjack
     */
    public Blackjack() {
        turnnumber = 0;
        players = new ArrayList<Hand>();
        deck = new Deck();
        deck.fill();
        deck.shuffle();
        player1 = new Hand();
        players.add(player1);
        dealer = new Hand();
        players.add(dealer);
        Beground();
    }

    public int getTurnNumber() {
        return turnnumber;
    }

    public void updateTurn() {
        turnnumber++;
    }

    public void addPlayer(Hand h, int splitcount) {
        players.add(splitcount, h);
    }

    public ArrayList<Hand> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public void Beground() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).hit(deck);
            players.get(i).hit(deck);
        }
        getPlayers().get(players.size() - 1).getHand().get(0).changeFaceDown();
    }

    public void endRound() {
        int killme = 0;
        turnnumber = 0;
        while (players.size() > 2) {
            players.remove(killme);
            killme++;
        }
        for (int i = 0; i < players.size(); i++) {
            players.get(i).clear();
        }
        deck.fill();
        deck.shuffle();
        for (int i = 0; i < players.size(); i++) {
            players.get(i).clearCount();
        }
    }

    public void other() {
        while (players.get(getPlayers().size() - 1).calculateValue() < 16) {
            players.get(getPlayers().size() - 1).hit(deck);

        }
    }

    public int compare() {
        int a = 0;
        int max = 0;
        int winner = 0;
        for (int i = 0; i < players.size(); i++) {
            a = players.get(i).calculateValue();
            if (a == max) {
                return 100;
            }
            if (a <= 21 && a > max) {
                winner = i;
                max = a;
            }
        }
        return winner;
    }

    public int compareString() {
        int a = compare();
        if (turnnumber > this.getPlayers().size() - 2) {
            if (a != players.size() - 1)
                return 0;
            else if (this.compare() == 100)
                return 1;
            else
                return 2;
        } else return -1; // added this because it won't compile without default return
    }

    public void printElse() {
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Other ppl have " + players.get(i).showAll());
        }
    }
}
