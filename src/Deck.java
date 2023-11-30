
import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> deck;
    private int cardsLeft;


    public Deck(String[] rank, String[] suit, int[] points) {

        this.deck = new ArrayList<Card>();
        createDeck(rank, suit, points);
        this.cardsLeft = deck.size();
    }

    public void createDeck(String[] rank, String[] suits, int[] points){
        // Adds new card object to deck
        // Creates deck with every rank of every color
        for(String suit : suits) {
            for (int i = 0; i < rank.length; i++) {
                deck.add(new Card(rank[i], suit, points[i]));
            }
        }

    }

    // Returns true iff 0 cards left in deck
    public boolean isEmpty(){
        return cardsLeft == 0;
    }

    public int getCardsLeft(){
        return cardsLeft;
    }

    public Card deal(){
        if(isEmpty()){
            return null;
        }

        return deck.get(cardsLeft--);

    }

    //Tested and working
    public void shuffle(){

        /* For i = last index of the deck down to 0
            Generate a random integer r (using Math.random) between 0
            and i, inclusive;
            Exchange cards[i] and cards[r]

         */

        int randomNum;
        Card temp;

        for (int i = deck.size() - 1; i > 0; i--){

            randomNum = (int)(Math.random() * (i + 1));
            // Swaps cards and random num and i
            //Since set method returns previosuly stored elem e, no temp var needed
            /*
            temp = deck.get(i);
            System.out.println(temp);
            deck.set(i, deck.get(randomNum));
            System.out.println(temp);
*/

            deck.set(randomNum, deck.set(i, deck.get(randomNum)));

        }

        cardsLeft = deck.size();

    }

    //Tested and working
    public void printDeck(){

        System.out.println("\n\n\nNUMBER OF CARDS " + cardsLeft);

        for(Card cards : deck){
            System.out.println(cards);
        }
    }

}
