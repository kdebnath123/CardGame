// Blackjack by Kirin Debnath
import java.util.Scanner;


public class Game {

    Scanner input = new Scanner(System.in);

    // Instance variables for cards and deck
    private String[] rank = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private String[] suit = {"Diamonds", "Spades", "Clubs", "Hearts"};
    private int[] points = {11,2,3,4,5,6,7,8,9,10,10,10,10};
    private Deck deck;

    // Instance variables for player class
    //Sets the 'buy-in' for each round
    private final static int gambleAmount = 500;
    private final static int startAmount = 2000;
    private Player player1;
    private Player dealer;


    // Creates instance of game class
    // Calls method to handle game loop
    public static void main(String[] args) {

        Game blackjack = new Game();
        blackjack.play();

    }

    //
    public Game() {
        // Creates deck of 52 cards
        this.deck = new Deck(rank, suit, points);

        // Gets name from user
        // Creates user's instance of player class
        System.out.print("Enter name: ");
        this.player1 = new Player(input.nextLine(), startAmount);

        // Creates dealer
        // Start amount at any number b/c will never be changed
        this.dealer = new Player("Dealer", 0);
    }

    // Allows user to infinitly play rounds unless they lose all their money
    public void play(){

        printInstructions();

        // If the player has money to bet perform a round
        while(player1.getMoney() > 0){

            //shuffle the deck
            deck.shuffle();
            // play the round of blackjack
            round();

            // Clear all hands
            // Removes cards from players, allowing for further use
            player1.clearHand();
            dealer.clearHand();

        }

        System.out.println("You have no more money. :(");
        System.out.println("Thanks for playing!!");

    }

    public void round(){


        // Deals 2 cards to each player
        player1.addCard(deck.deal());
        dealer.addCard(deck.deal());
        player1.addCard(deck.deal());
        dealer.addCard(deck.deal());


        // Print one of the dealer's cards
        System.out.println("\nDealers current hand: \n" + "unkown");
        System.out.println(dealer.getLastCard());


        // Players turn
        while(player1.getSum() < 21){


            player1.printHand();


            if(player1.willMove()){
                player1.addCard(deck.deal());
            }

            else{
                break;
            }

            //trigger blackjack win
            if (player1.getSum() == 21) {
                player1.printHand();
                winLossScreen(true);
                return;
            }

        }

        //if player1 has bust, trigger loss
        if(player1.getSum() > 21){
            System.out.println("Your hand is a bust");
            winLossScreen(false);
            return;
        }

        //reveal dealers full hand
        dealer.printHand();


        //dealer must hit until they reach 17 or great
        while(dealer.getSum() < 17){

            dealer.addCard((deck.deal()));
            System.out.println(dealer.getLastCard());
        }

        //if dealer is bust trigger win
        if(dealer.getSum() > 21){
            System.out.println("The dealer busted");
            winLossScreen(true);
            return;
        }

        //triggers win loss screen
        //if either side has 'bust' handled earlier
        //pass in true if player cards are higher thus they won
        winLossScreen(player1.getSum() >= dealer.getSum());








    }

    //returns true if player has won, false if dealer has one
    private void winLossScreen(boolean PlayerIsWinner) {


        String endMessage = "";

        if(PlayerIsWinner){

            endMessage = "WON";
            player1.changeMoney(gambleAmount);

            //if its blackjack double the amount recieved
            if(player1.getSum() == 21){
                endMessage = "GOT BLACK JACK";
                player1.changeMoney(gambleAmount);
            }

        //loss statment
        }else{
            endMessage = "LOST";
            player1.changeMoney(gambleAmount * -1);

        }

        // prints end message and current amount of chips
        System.out.println("\nYOU " + endMessage + "!!!!");
        System.out.println("Your new total is " + player1.getMoney() + " dollars");


    }


    //ACE CONTROL
    // ACES ARE ALWAYS WORTH 11 unless they bust the hand then it should be one??



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

    public void printInstructions() {

        System.out.println("Welcome to Black Jack " + player1.getName());
        System.out.println("The goal: have a hand with a sum higher than the dealer’s without going over 21.");

        System.out.println("Face cards are worth 10.");
        System.out.println("Aces are special: they are worth 1 or 11, your choice!");
        System.out.println("The remaining cards are counted as normal.");

        System.out.println("Each round place a bet.");
        System.out.println("You will then be dealt two cards. The dealer is dealt one card face up and one face down.");

        System.out.println("If you have exactly 21, that's blackjack! You win!");
        System.out.println("For any other total, decide to ‘draw’ or ‘stay’.");
        System.out.println("Continue to draw cards until you are happy with your hand.");

        System.out.println("Then, the dealer will draw until they reach 17 or more.");

        System.out.println("Once all cards are drawn, the higher total without going over 21 win.");
        System.out.println("All bets are paid 1:1 but Blackjack get you paid 1:2.");
        System.out.println("Each hand is worth 500 dollars.");

        System.out.print("Enter to countinue:");
        input.nextLine();

    }


}
