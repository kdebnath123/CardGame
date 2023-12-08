// Blackjack by Kirin Debnath
// Last updated: December 7th 2023
import java.util.Scanner;


public class Game {

    Scanner input = new Scanner(System.in);

    // Instance variables for cards and deck
    private String[] rank = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private String[] suit = {"Diamonds", "Spades", "Clubs", "Hearts"};
    private int[] points = {11,2,3,4,5,6,7,8,9,10,10,10,10};
    private Deck deck;

    // Controls bet amount
    private final static int betAmount = 500;
    // Controls bankroll amount
    private final static int bankRollAmount = 2000;

    // Instance variables for player class
    private Player player1;
    private Player dealer;



    // Game class constructor
    public Game() {
        // Creates deck of 52 cards
        this.deck = new Deck(rank, suit, points);

        // Gets name from user
        // Creates user's instance of player class
        System.out.print("Enter name: ");
        this.player1 = new Player(input.nextLine(), bankRollAmount);

        // Creates dealer
        // Start amount doesn't matter --> will never be changed
        this.dealer = new Player("Dealer", 0);
    }

    // Allow user to play rounds until bankrupt
    public void play(){




        printInstructions();

        // If the player has money to bet perform a round
        while(player1.getMoney() > 0){

            deck.shuffle();

            // play the round of blackjack
            round();

            // Clear all hands
            // Removes cards from players, allowing for further use
            player1.clearHand();
            dealer.clearHand();

        }

        /*
        System.out.println("You have no more money. :(");
        System.out.println("Thanks for playing!!");

         */



    }

    // Prints the rules and instructions for BlackJack
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

    // Controls single round of Blackjack
    // Deals intial cards, allows player and dealer to have a turn
    public void round(){

        // Deals 2 cards to each player
        player1.addCard(deck.deal());
        dealer.addCard(deck.deal());
        player1.addCard(deck.deal());
        dealer.addCard(deck.deal());

        // Print one of the dealer's cards
        System.out.println("\nDealers current hand: \n" + "unkown");
        System.out.println(dealer.getLastCard());

        // Prints players hand
        player1.printHand();

        // Players turn
        // As long as they are under 21, ask to hit or stay
        while(player1.getSum() <= 21){

            // Check/trigger blackjack win
            if (player1.getSum() == 21) {
                winLossScreen(true);
                return;
            }


            // If the player wants to draw a card
            if(player1.willMove()){
                //Draw a Card
                player1.addCard(deck.deal());
            }
            // when they stay end the loop
            else{
                break;
            }

            player1.printHand();


        }

        // Check/trigger for player bust
        if(player1.getSum() > 21){
            // Print the last card
            System.out.println(player1.getLastCard());
            // Inform of bust
            System.out.println("Player busted");
            winLossScreen(false);
            return;
        }

        // Reveals dealer's full hand
        dealer.printHand();


        // Dealer must hit until they reach 17 or great
        while(dealer.getSum() < 17){
            // Draw card
            dealer.addCard((deck.deal()));
            // Show it
            System.out.println(dealer.getLastCard());
        }

        // Check/trigger for player bust
        if(dealer.getSum() > 21){
            System.out.println("Dealer busted");
            winLossScreen(true);
            return;
        }

        // True if player has tie/higher score
        winLossScreen(player1.getSum() >= dealer.getSum());


    }

    // Check type of win/loss, change money, print rounds result
    private void winLossScreen(boolean PlayerIsWinner) {

        String endMessage;

        if(PlayerIsWinner){

            // Normal win
            endMessage = "WON";
            player1.changeMoney(betAmount);

            // BlackJack Win
            // If blackjack amount received doubled
            if(player1.getSum() == 21){
                endMessage = "GOT BLACK JACK";
                player1.changeMoney(betAmount);
            }


        }
        // Loss control
        else{
            endMessage = "LOST";
            // Removes money from user's bank
            player1.changeMoney(betAmount * -1);

        }

        // Prints end message and current money
        System.out.println("\nYOU " + endMessage + "!!!!");
        System.out.println("Your new total is " + player1.getMoney() + " dollars");
    }


    // Main Function
    // Creates and plays game
    public static void main(String[] args) {

        // Creates instance of game class
        Game blackjack = new Game();
        // Handles game loop
        blackjack.play();

    }
}
