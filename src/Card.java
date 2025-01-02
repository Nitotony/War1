public class Card {
    private String suit;
    private String val;


    public Card(String suit, String val) {
        this.suit=suit;
        this.val=val;

    }


    public String getSuit() {
        return suit;
    }


    public void setSuit(String suit) {
        this.suit = suit;
    }


    public String getVal() {
        return val;
    }


    public void setVal(String val) {
        this.val = val;
    }




}