/**
 * Write a description of class Deck here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class Deck {
    public ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public void fill() {
        for (int i = 2; i < 11; i++) {
            for (int j = 1; j < 5; j++) {
                Card card = new Card(i + "", getSuit(j));
                deck.add(card);
            }
        }
        for (int i = 1; i < 5; i++) {
            Card card = new Card("J", getSuit(i));
            deck.add(card);
            Card card2 = new Card("Q", getSuit(i));
            deck.add(card2);
            Card card3 = new Card("K", getSuit(i));
            deck.add(card3);
            Card card4 = new Card("A", getSuit(i));
            deck.add(card4);
        }
    }

    public Card removeAndReturnCard() {
        Card c = deck.get(0);
        deck.remove(0);
        return c;
    }

    public String getSuit(int number) {
        if (number == 1) {
            return "SPADE";
        }
        if (number == 2) {
            return "CLUB";
        }
        if (number == 3) {
            return "DIAMOND";
        }
        if (number == 4) {
            return "HEART";
        }
        return "fuckl";
    }

    public void tester() {
        for (int i = 0; i < 52; i++) {
            System.out.println(deck.get(i).getNum());
        }
    }

    public static void main() {
        Deck d = new Deck();
        d.fill();
        ///d.shuffle();
        d.tester();

    }
}
