import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    Scanner input = new Scanner(System.in);
    private String name;
    private ArrayList<Card> hand;
    private int money;
    private static int startAmount = 2000;


    public Player(String name, ArrayList<Card> hand) {

        this.name = name;
        this.money = startAmount;

        //might break -- may need to make a copy of the cards
        this.hand = new ArrayList<Card>();
        for (Card cards : hand) {
            hand.add(cards);
        }


    }


    public Player(String name) {
        this.name = name;
        this.money = startAmount;
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

    //repromts till valid input
    //returns true for draw
    //returns false for a stay
    public boolean willMove(){

        String move = "";

        while(true){

            System.out.println("Do you want to draw or stay (d/s): ");
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

}
