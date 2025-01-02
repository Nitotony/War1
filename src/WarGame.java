
import java.util.*;
public class WarGame {

    static Player player1=new Player();
    static Player player2=new Player();

    static List<Card> Stacks=new ArrayList<>(52);
    static Queue<Card> tiebreakercards1=new LinkedList<>();
    static Queue<Card> tiebreakercards2=new LinkedList<>();

    static String[] suits= {"spade","club","heart","dimond"};
    static String[] val= {"A","J","K","Q","10","9","8","7","6","5","4","3","2"};

    private static int findval(String value) {
        // to find the position of value of card in the val.
        for(int i=0;i<val.length;i++) {
            if(value.equals(val[i]) ){
                return i;
            }
        }
        return -1;

    }



    public static void  deal() {

        int i=0;
        for(;i<26;i++) {
            player1.holdingcards.add(Stacks.get(i));//first 26 given to player 1

        }
        for(;i<52;i++) {
            player2.holdingcards.add(Stacks.get(i)); // second 26 given to player 2
        }
    }

    public static int tiebreak() {
        int i = 0;
        int retry=0;
        int maxre=10000;
        while(retry<maxre) {
            System.out.println("Tie Breaker");
            // Draw up to 4 cards from each player for the tiebreaker
            while ((!player1.holdingcards.isEmpty() && !player2.holdingcards.isEmpty()) && i < 4) {
                tiebreakercards1.add(player1.holdingcards.poll());
                tiebreakercards2.add(player2.holdingcards.poll());
                i++;
            }

            // Check if any player runs out of cards during the tiebreaker
            if (player1.holdingcards.isEmpty()) {
                return -2; // Player 2 wins
            }
            if (player2.holdingcards.isEmpty()) {
                return 2; // Player 1 wins
            }

            // Compare the top cards of the tiebreaker
            Card card1 = tiebreakercards1.peek();
            Card card2 = tiebreakercards2.peek();

            System.out.println("Player 1:"+card1.getSuit()+" "+card1.getVal());
            System.out.println("Player 2:"+card2.getSuit()+" "+card2.getVal());

            int result = validate(card1, card2);

            if (result == 1) {
                // Player 1 wins the tiebreaker
                player1.holdingcards.addAll(tiebreakercards1);
                player1.holdingcards.addAll(tiebreakercards2);
                tiebreakercards1.clear();
                tiebreakercards2.clear();
                return result;
            } else if (result == -1) {
                // Player 2 wins the tiebreaker
                player2.holdingcards.addAll(tiebreakercards1);
                player2.holdingcards.addAll(tiebreakercards2);
                tiebreakercards1.clear();
                tiebreakercards2.clear();
                return result;
            }

            else if(result==0) {
                retry++;


            }

        }

        System.out.println(" in reconsilable tie");
        return 0;




    }





    public static void insert() {

        int k=0;
        for(int i=0;i<suits.length&& k<52;i++) {
            for(int j=0;j<val.length&& k<52;j++) {
                Card card=new Card(suits[i],val[j]);
                Stacks.add(card);
                k++;

            }
        }


    }

    public static void shuffle() {

        Collections.shuffle(Stacks);// shuffles the  cards

    }

    public static int validate(Card a,Card b) {

        if(findval(a.getVal())<findval(b.getVal())) {
            return 1; //player 1 wins
        }
        else if(findval(a.getVal())>findval(b.getVal())) {
            return -1; //player 2 wins
        }
        else {

            return 0;// initiate tiebreaker
        }


    }





    public static void main(String[] args) {



        insert();




        shuffle();
        deal();
        int j=1;
        int deals=1;
        int maxdeal=10000;
        System.out.println("mine");




        while(!player1.holdingcards.isEmpty()&& !player2.holdingcards.isEmpty()) {

            if(deals>maxdeal) {

                System.out.println("Maximum deals exeded DRAW");
                return;
            }


            Card p1=player1.holdingcards.poll();
            Card p2=player2.holdingcards.poll();
            if (p1 == null) {
                System.out.println("Player 1 has run out of cards! Player 2 wins the game!");
                return;
            }
            if (p2 == null) {
                System.out.println("Player 2 has run out of cards! Player 1 wins the game!");
                return;
            }
            System.out.println("Player 1:"+p1.getSuit()+" "+p1.getVal());
            System.out.println("Player 2:"+p2.getSuit()+" "+p2.getVal());

            if(validate(p1,p2)==1) {
                player1.holdingcards.add(p1);
                player1.holdingcards.add(p2);

                System.out.println("player 1 won round"+j);

            }

            else if(validate(p1,p2)==-1) {
                player2.holdingcards.add(p1);
                player2.holdingcards.add(p2);
                System.out.println("player 2 won round"+j);
            }

            else {

                switch( tiebreak()) {

                    case 1:
                        System.out.println("Player 1 wins round "+j);

                        break;

                    case -1:
                        System.out.println("Player 2 wins round "+j);

                        break;

                    case 2:
                        System.out.println("Player 2 wins");


                        return;
                    case -2:
                        System.out.println("Player 1 wins");

                        return;
                    default:
                        return;

                }
            }

            j++;

            System.out.println();

            System.out.println();
            System.out.println();
            deals++;


        }









    }

}