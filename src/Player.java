import java.util.*;


public class Player {
    Queue<Card> holdingcards=new LinkedList<>();




    public void tiebreak() {

    }

    public Card deal() {

        return holdingcards.poll();

    }

    public void winadd(Card c) {
        holdingcards.add(c);
    }



}