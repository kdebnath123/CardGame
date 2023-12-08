// Kirin Debnath
// Last updated: December 7th 2023
import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    Scanner input = new Scanner(System.in);
    // Instance Variables
    private String name;
    private ArrayList<Card> hand;
    private int money;

    // Constructs player
    public Player(String name, ArrayList<Card> hand, int startAmount) {

        this.name = name;
        this.money = startAmount;

        //might break -- may need to make a copy of the cards
        this.hand = new ArrayList<Card>();
        for (Card cards : hand) {
            hand.add(cards);
        }


    }

    // Constructs player
    public Player(String name, int startAmount) {
        this.name = name;
        this.money = startAmount;
        this.hand = new ArrayList<Card>();
    }


    // Gets Player's name
    public String getName() {
        return name;
    }

    // Gets players hand
    public ArrayList<Card> getHand() {
        return hand;
    }

    // Gets players money amount
    public int getMoney() {
        return money;
    }

    // Changes money of player
    public void changeMoney(int amount){
        money += amount;
    }

    // Adds card to players hand
    public void addCard(Card card){
        hand.add(card);
    }

    // Returns the last card drawn
    public Card getLastCard(){
        return hand.get(hand.size() - 1);
    }

    // Adds up the players hand and returns total
    // Controls for aces if they were to bust the hand
    public int getSum() {

        int sum = 0;
        int numAces = 0;

        // add each card's value in players hand to sum
        for(Card card: hand){
            sum += card.getPoints();

            // If the card is an ace, increase ace count
            if(card.getRank().equals(("Ace"))){
                numAces++;
            }

        }

        // Controls for aces
        // If the hand will bust turn an ace from 11 to 1
        // Repeat if there are aces to turn
        while(numAces > 0 && sum > 21){
            sum -= 10;
        }

        return sum;
    }


    // Player's two string
    public String toString(){

        // Adds player's information to the return string
        String output = this.name + " has " + this.money + " points\n" + this.name + "’s cards: \n";

        // Adds each card in players hand to the return string on a new line
        for (Card cards: hand) {
            output += cards + "\n";
        }

        return output;

    }


    // Prints players hand
    public void printHand(){

        System.out.println("\n" + this.name + "'s current hand is: ");

        // Prints each card in hand
        for(Card card: hand){
            System.out.println(card);
        }
    }

    // Prompts for the move of player
    // Returns true if they want to move and false if they want to stay
    // Precondition: should have the ability to move (hand is not busted)
    public boolean willMove(){

        String move;

        // Repromts until either d or s is inputed
        while(true){
            System.out.print("Do you want to draw or stay (d/s): ");
            move = input.nextLine();

            // If they want to move return true
            if(move.equals("d")){
                return true;
            }
            // If they want to stay return false
            if(move.equals("s")){
                return false;
            }

        }

    }

    // Clears the player's hand, 'puts' the cards back in deck for further use
    public void clearHand(){
        hand.clear();
    }

}
