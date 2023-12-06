import java.sql.SQLOutput;
import java.util.Scanner;

public class Game {

    Scanner input = new Scanner(System.in);

    // instance variables for deck and cards
    private String[] rank = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private String[] suit = {"Diamonds", "Spades", "Clubs", "Hearts"};
    private int[] points = {11,2,3,4,5,6,7,8,9,10,10,10,10};
    private Deck deck;


    //player instance variables
    private static int gambleAmount = 500;
    private Player player1;
    private Player dealer;

    public static void main(String[] args) {

        Game blackjack = new Game();
        blackjack.play();


        //Deck cards = new Deck(rank, suit, points);

        //cards.printDeck()

    }

    public Game() {
        // Creates deck of 52 cards
        this.deck = new Deck(rank, suit, points);

        // Gets name and creates player class
        System.out.print("Enter name: ");
        this.player1 = new Player(input.nextLine());

        //creates dealer
        this.dealer = new Player("dealer");
    }

    public void play(){

        round();
    }

    public void round(){

        // Clear screen -- from javapoint
        System.out.print("\033[H\033[2J");

        // Deals cards
        player1.addCard(deck.deal());
        dealer.addCard(deck.deal());
        player1.addCard(deck.deal());
        dealer.addCard(deck.deal());

        //print player hand
        System.out.println("Your current hand: ");
        player1.printHand();

        //print delaers first card
        System.out.println("\nDealers current hand: \n" + "unkown");
        System.out.println(dealer.getHand().get(0));


        //players turn
        while(player1.getSum() <= 21){

            System.out.print("\033[H\033[2J");
            player1.printHand();

            if(player1.willMove()){
                player1.addCard(deck.deal());
            }

            else{
                break;
            }
        }

        //dealer must hit until they reach 17 or great
        while(dealer.getSum() < 17){
            dealer.addCard((deck.deal()));
        }


        //WinControl();











    }

    //returns true if player has won, false if dealer has one
    private void playerHasWon(boolean PlayerIsWinner) {

        System.out.print("\033[H\033[2J");

        if(PlayerIsWinner){
            System.out.println("YOU WON!!!!!");

            if(player1.getSum() == 21){

                System.out.println("You got black jack and earned " + gambleAmount * 2);
                player1.changeMoney(gambleAmount * 2);
                return;
            }

            System.out.println("You earned " + gambleAmount);
            player1.changeMoney(gambleAmount);
            return;

        }
        else{
            //fail control
        }



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

        System.out.println("Enter to countinue:");
        input.nextLine();

    }


}
