import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> hand;
    private int money;
    private static int startAmount = 1000;

    public Player(String name, ArrayList<Card> hand, int money) {

        this.name = name;
        this.money = money;

        //might break -- may need to make a copy of the cards
        this.hand = new ArrayList<Card>();
        for (Card cards : hand) {
            hand.add(cards);
        }


    }


    public Player(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getMoney() {
        return money;
    }

    public void addPoints(int amount){
        money += amount;
    }

    public void addCard(Card card){
        hand.add(card);
    }

    public int getSum() {
        int sum = 0;
        for(Card card: hand){
            sum += card.getPoints();
        }

        return sum;
    }

    public String toString(){

        //untested
        return this.name + " has " + this.money + " points\n" + this.name + "’s cards: " + hand;

    }

    public void printHand(){

        System.out.println("Your current hand is: ");

        for(Card card: hand){
            System.out.println(card);
        }
    }


}
