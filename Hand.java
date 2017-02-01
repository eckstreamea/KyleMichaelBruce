/**
 * Write a description of class Hand here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class Hand {
    int count;
    private ArrayList<Card> player;

    public Hand() {
        count = 0;
        player = new ArrayList<Card>();
    }

    public void hit(Deck deck) {
        player.add(deck.removeAndReturnCard());
    }

    public int getHandSize() {
        return player.size();
    }

    public String getNum2(int i) {
        return player.get(i).getNum();
    }

    public ArrayList<Card> getHand() {
        return player;
    }

    public void clearCount() {
        count = 0;
    }

    public Hand split() {
        count = 0;
        Hand lefthand = new Hand();
        lefthand.add(player.get(0));
        player.remove(0);
        count++;
        return lefthand;
    }

    public int returnCount() {
        return count;
    }

    public void doubleDown() {

    }

    public int calculateValue() {
        int value = 0;
        boolean isThereAce = false;
        for (int i = 0; i < player.size(); i++) {
            value += player.get(i).getValue();
            if (player.get(i).getNum().equals("A")) {
                isThereAce = true;
            }
        }
        if (value < 12 && isThereAce == true) {
            return value + 10;
        }
        return value;
    }

    public void clear() {
        player.clear();
    }

    public String showAll() {
        int value = 0;
        String y = "";
        boolean isThereAce = false;
        for (int i = 0; i < player.size(); i++) {
            value += player.get(i).getValue();
            y += player.get(i).getNum() + ":" + player.get(i).getValue() + " ";
            if (player.get(i).getNum().equals("A")) {
                isThereAce = true;
            }
        }
        if (value < 12 && isThereAce == true) {
            value = value + 10;
        }
        y += "total:" + value;
        return y;
    }

    public String showDouble() {
        int value = 0;
        String y = "";
        boolean isThereAce = false;
        for (int i = 0; i < player.size(); i++) {
            value += player.get(i).getValue();
            if (i == 2 && player.get(i).getFD() == true) {
                y += player.get(i).getNum() + ":" + player.get(i).getValue() + "*";
            } else
                y += player.get(i).getNum() + ":" + player.get(i).getValue() + " ";
            if (player.get(i).getNum().equals("A")) {
                isThereAce = true;
            }

        }
        if (value < 12 && isThereAce == true) {
            value = value + 10;
        }
        y += "total:" + value;
        return y;
    }

    public void add(Card a) {
        player.add(a);
    }
}
