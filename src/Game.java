public class Game {

    private String[] rank = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private String[] suit = {"Diamonds", "Spades", "Clubs", "Hearts"};
    private int[] points = {1,2,3,4,5,6,7,8,9,10,10,10,10};
    private Deck deck;

    public Game() {
        this.deck = new Deck(rank, suit, points);
    }
    public void play(){

        // Prints deck, shuffles it, reprints

        deck.printDeck();

        deck.shuffle();

        deck.printDeck();
    }


}
