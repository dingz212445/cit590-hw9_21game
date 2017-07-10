package Simple21;

import java.util.Scanner;
import java.util.Random;

/**
 * This is a simplified version of a common card game, "21".<p>
 * In this game, the dealer deals two "cards" to each player,
 * one hidden, so that only the player who gets the card knows
 * what it is, and one face up, so that everyone can see it.
 * (Actually, what the other players see is the <i>total</i>
 * of each other player's visible cards, not the individual cards.)<p>
 *
 * The players then take turns requesting cards, trying to get
 * as close to 21 as possible, but not going over 21. These cards
 * will be visible to all players.  A player may pass (ask for no
 * more cards). Once a player has passed, he or she cannot later
 * ask for another card. When all players have passed, the
 * game ends.<p>
 *
 * The winner is the player who has come closest to 21 without
 * exceeding it. In the case of a tie, or if everyone goes over
 * 21, no one wins.<p>
 *
 * In this program, there are some number of computer players
 * and one human player. The game is only played once. 
 * 
 * @author David Matuszek
 * @author Ding Zhang
 * @version 0
 */
public class GameControl {
    
    /** All the Player objects, including the Human player.
     * The number of players is set here; other places in the
     * program should use <code>players.length</code>. */
    Player[] players = new Player[4];
    
    /** passed[i] == true indicates that players[i] has passed. */
    boolean[] passed = new boolean[] {false, false, false, false};
    
    private int numPassed = 0;
    
    /** Used for getting input from the user. */
    Scanner scanner = new Scanner(System.in);
    
    /** A random number generator. */
    Random random = new Random();
      
    /**
     * The main method just creates a GameControl object and calls
     * its <code>run</code> method.
     * 
     * @param args Not used.
     */
    public static void main(String args[]) {    
        // Students: your code goes here.
    	GameControl game21 = new GameControl();
    	game21.run();
    	
    }
    
    /**
     * Prints a welcome method, then calls methods to perform each
     * of the following actions:
     * <ol>
     *   <li>Create the Players (one of them a Human),</li>
     *   <li>Deal the initial two cards to each player,</li>
     *   <li>Control the play of the game, and</li>
     *   <li>Print the final results.</li>
     * </ol>
     */
    void run() { 
    	// Students: your code goes here.
    	System.out.println("running");
    	this.createPlayers();
    	this.deal();
    	this.controlPlay();
    	this.printResults();
    	System.out.println("Games over!");
    }
    
    /**
     * Asks the human player for a name, and then creates a Human
     * object and all the other Player objects, saving them in the
     * <code>players</code> array. (Names of the other players may
     * be hardwrited; don't ask the user for them.)
     */
    void createPlayers() {
        System.out.print("What is your name?  ");
        String humansName = scanner.next();
        // Students: your code goes here.
        Human humanPlayer = new Human(humansName);
        this.players[0] = humanPlayer;
        this.players[1] = new Player("Manny");
        this.players[2] = new Player("Moe");
        this.players[3] = new Player("Jack");
    }
    
    /**
     * Deals the initial two cards (one hidden, one not hidden)
     * to each player.
     */
    void deal() { 
        // Students: your code goes here.
    	for (int i = 0; i < 4; i++) {
    		this.players[i].takeHiddenCard(nextCard());
    		this.players[i].takeVisibleCard(nextCard());
    	}
    }
    
    /**
     * Returns a random "card", represented by a integer between
     * 1 and 10, inclusive. The odds of returning a 10 are four
     * times as likely as any other value (because in an actual
     * deck of cards, 10, Jack, Queen, and King all count as 10).<br />
     * <b>Note:</b> The java.util package contains a <code>Random</code>
     * class, which is perfect for generating random numbers.
     *
     * @return a random integer in the range 1..10.
     */
    int nextCard() { 
        // Students: your code goes here.
    	Random rd = new Random();
    	return rd.nextInt(11);
    }

    /**
     * Gives each player in turn a chance to take a card, until all
     * players have passed. Prints a message when a player passes.
     * Once a player has passed, that player is not given another
     * chance to take a card.<br />
     * <b>Note:</b> The global array <code>passed</code> is used to
     * keep track of which players have passed.
     */
    void controlPlay() { 
        // Students: your code goes here.
    	while (numPassed < 4) {
    		for (int i = 0; i < 4; i++) {
    			if (this.passed[i] == false) {
    				if (this.players[i].offerCard(this.players)) {
    					this.players[i].takeVisibleCard(nextCard());
    				} else {
    					System.out.println(this.players[i].name + "passed!");
    					this.passed[i] = true;
    					this.numPassed++;
    				}
    			}
    		}
    	}
    }
    
    /**
     * Prints a summary at the end of the game, saying how many
     * points each player had, and who won.
     */
    void printResults() { 
        // Students: your code goes here.
    	for (int i = 0; i < 4; i++) {
    		System.out.println(this.players[i].name + " has " + this.players[i].getScore() + " points.");
    	}
    	
    	int winner = this.findWinningPlayer();
    	if (winner != -1) {
    		System.out.println(this.players[winner].name + "wins.");
    	} else {
    		System.out.println("Nobody wins.");
    	}
    }

    /**
     * Determines who won the game, and returns it as an index into
     * the players array.
     * 
     * @return The index of the winner, or -1 if nobody won.
     */
    int findWinningPlayer() { 
        // Students: your code goes here.
    	int index = -1;
    	int maxScore = -1;
    	for (int i = 0; i < 4; i++) {
    		if (this.players[i].getScore() < 22) {
    			if (this.players[i].getScore() > maxScore) {
	    			index = i;
	    			maxScore = this.players[i].getScore();
    			} else if (this.players[i].getScore() == maxScore) {
    				index = -1;
    			}
    		}
    	}
    	return index;
    }
}
