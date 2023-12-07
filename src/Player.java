import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    Scanner input = new Scanner(System.in);
    private String name;
    private ArrayList<Card> hand;
    private int money;
    private int startAmount;


    public Player(String name, ArrayList<Card> hand, int startAmount) {

        this.name = name;
        this.money = startAmount;

        //might break -- may need to make a copy of the cards
        this.hand = new ArrayList<Card>();
        for (Card cards : hand) {
            hand.add(cards);
        }


    }

    public Player(String name, int startAmount) {
        this.name = name;
        this.money = startAmount;
        this.hand = new ArrayList<Card>();
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

    public void changeMoney(int amount){
        money += amount;
    }

    public void addCard(Card card){
        hand.add(card);
    }

    public Card getLastCard(){
        return hand.get(hand.size() - 1);
    }

    public int getSum() {

        int sum = 0;
        int numAces = 0;

        for(Card card: hand){
            sum += card.getPoints();

            if(card.getRank().equals(("Ace"))){
                numAces++;
            }

        }
        //controls for aces
        //if the hand will bust turn an ace from 11 to 1
        //repeat if there are aces to turn
        while(sum > 21 && numAces > 0){
            sum -= 10;
        }

        return sum;
    }


    public String toString(){

        //untested
        return this.name + " has " + this.money + " points\n" + this.name + "’s cards: " + this.hand;

    }

    public void printHand(){

        System.out.println("\n" + this.name + "'s current hand is: ");

        for(Card card: hand){
            System.out.println(card);
        }
    }

    //repromts till valid input
    //returns true for draw
    //returns false for a stay
    public boolean willMove(){

        String move = "";

        while(true){
            System.out.print("Do you want to draw or stay (d/s): ");
            move = input.nextLine();

            //draw a card
            if(move.equals("d")){
                return true;
            }
            //stay
            if(move.equals("s")){
                return false;
            }

        }

    }

    public void clearHand(){
        hand.clear();
    }

}
