/*
 * Programmer: Maksim Shishkov
 * SFSU 
 * Created: 12/4/2013
 * Class: MyPokerGame
 * Assignment: PJ4
 * File Name: MyPokerGame.java
 * Description: solution for the PJ4
 * 
 */


package PJ4;
import java.util.*;

/*
 * Ref: http://en.wikipedia.org/wiki/Video_poker
 *      http://www.google.com/ig/directory?type=gadgets&url=www.labpixies.com/campaigns/videopoker/videopoker.xml
 *
 *
 * Short Description and Poker rules:
 *
 * Video poker is also known as draw poker. 
 * The dealer uses a 52-card deck, which is played fresh after each currentHand. 
 * The player is dealt one five-card poker currentHand. 
 * After the first draw, which is automatic, you may hold any of the cards and draw 
 * again to replace the cards that you haven't chosen to hold. 
 * Your cards are compared to a table of winning combinations. 
 * The object is to get the best possible combination so that you earn the highest 
 * payout on the bet you placed. 
 *
 * Winning Combinations
 *  
 * 1. Jacks or Better: a pair pays out only if the cards in the pair are Jacks, 
 * 	Queens, Kings, or Aces. Lower pairs do not pay out. 
 * 2. Two Pair: two sets of pairs of the same card denomination. 
 * 3. Three of a Kind: three cards of the same denomination. 
 * 4. Straight: five consecutive denomination cards of different suit. 
 * 5. Flush: five non-consecutive denomination cards of the same suit. 
 * 6. Full House: a set of three cards of the same denomination plus 
 * 	a set of two cards of the same denomination. 
 * 7. Four of a kind: four cards of the same denomination. 
 * 8. Straight Flush: five consecutive denomination cards of the same suit. 
 * 9. Royal Flush: five consecutive denomination cards of the same suit, 
 * 	starting from 10 and ending with an ace
 *
 */


/* This is the main poker game class.
 * It uses Decks and Card objects to implement poker game.
 * Please do not modify any data fields or defined methods
 * You may add new data fields and methods
 * Note: You must implement defined methods
 */



public class MyPokerGame {

    // default constant values
    private static final int startingBalance=100;
    private static final int numberOfCards=5;

    // default constant payout value and currentHand types
    private static final int[] multipliers={1,2,3,5,6,9,25,50,250};
    private static final String[] goodHandTypes={ 
	  "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush", 
	  "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

    // must use only one deck
    private static final Decks oneDeck = new Decks(1);

    // holding current poker 5-card hand, balance, bet    
    private List<Card> currentHand;
    private int balance;
    private int bet;
	
	private int[] cardArray = new int[5];

    /** default constructor, set balance = startingBalance */
    public MyPokerGame()
    {
	this(startingBalance);
    }

    /** constructor, set given balance */
    public MyPokerGame(int balance)
    {
	this.balance= balance;
    }

    /** This display the payout table based on multipliers and goodHandTypes arrays */
    private void showPayoutTable()
    { 
	System.out.println("\n\n");
	System.out.println("Payout Table   	      Multiplier   ");
	System.out.println("=======================================");
	int size = multipliers.length;
	for (int i=size-1; i >= 0; i--) {
		System.out.println(goodHandTypes[i]+"\t|\t"+multipliers[i]);
	}
	System.out.println("\n\n");
    }

    /** Check current currentHand using multipliers and goodHandTypes arrays
     *  Must print yourHandType (default is "Sorry, you lost") at the end of function.
     *  This can be checked by testCheckHands() and main() method.
     */
    private void checkHands()
	{
		
		int rank = 0;
		String ranks;
		
		
		for (int i = 0;i < 5; i++) 
		{
		    cardArray[i] = currentHand.get(i).getRank();
		}
		
		Arrays.sort(cardArray);
			
			
			
		if (isRoyalPair()) 
		{
			rank = 1;
		}
		if (isTwoPairs()) 
		{
			rank = 2;
		}
		if (isThreeOfAKind()) 
		{
			rank = 3;
		}
		
		if (isStraight()) 
		{
			rank = 4;
		}
		if (isFlush()) 
		{
			rank = 5;
		}
		if (isFullHouse()) 
		{
			rank = 6;
		}
		if (isFourOfAKind()) 
		{
			rank = 7;
		}
		if (isStraightFlush()) 
		{
			rank = 8;
		}
		if (isRoyalFlush()) 
		{
			rank = 9;
		}
		
		
		rank -= 1;
		if (rank < 0) {
			ranks = "Sorry, you lost!";
		} else {
			ranks = goodHandTypes[rank];
		}
		
		System.out.println(ranks);
		

		
		switch (ranks) {
		case "Royal Pair":
			this.balance += (this.bet * multipliers[0]);
			break;
		case "Two Pairs":
			this.balance += (this.bet * multipliers[1]);
			break;
		case "Three of a Kind":
			this.balance += (this.bet * multipliers[2]);
			break;
		case "Straight":
			this.balance += (this.bet * multipliers[3]);
			break;
		case "Flush":
			this.balance += (this.bet * multipliers[4]);
			break;
		case "Full House":
			this.balance += (this.bet * multipliers[5]);
			break;
		case "Four of a Kind":
			this.balance += (this.bet * multipliers[6]);
			break;
		case "Straight Flush":
			this.balance += (this.bet * multipliers[7]);
			break;
		case "Royal Flush":
			this.balance += (this.bet * multipliers[8]);
			break;
		default:
			break;
		}
	}


    /*************************************************
     *   add new private methods here ....
     *
     *************************************************/
	
	private boolean isPair() 
	{
		boolean pair = false;
		
		{
			if ((cardArray[0] == cardArray[1] || cardArray[1] == cardArray[2] ) ||
				(cardArray[2] == cardArray[3] || cardArray[3] == cardArray[4] ))
			{
				pair = true;
			}

		}
		return pair;	
	}
	
	
	private boolean isRoyalPair() 
	{
		boolean royalP = false;
		for (int i = 0; i < 4; i++) 
		{
		    if ((cardArray[i] > 10 || cardArray[i] == 1) && cardArray[i] == cardArray[i + 1])
			{
				royalP = true;
			}
		}
		
			
		
		return royalP;	
	}
	
	private boolean isTwoPairs() 
	{
		boolean TwoP = false;
		int pairCount = 0;
		
		if (cardArray[0] == cardArray[1]) {pairCount++;}
		if (cardArray[1] == cardArray[2]) {pairCount++;}
		if (cardArray[2] == cardArray[3]) {pairCount++;}
		if (cardArray[3] == cardArray[4]) {pairCount++;}
		
		if (pairCount == 2){TwoP = true;}
		
		return TwoP;
		
	}
	
	private boolean isThreeOfAKind() 
	{
		boolean threeOf = false;
		
		if (((cardArray[0] == cardArray[1]) && (cardArray[0] == cardArray[2]) ) ||
			((cardArray[1] == cardArray[2]) && (cardArray[1] == cardArray[3]) ) ||
			((cardArray[2] == cardArray[3] && cardArray[2] == cardArray[4])))
		{
		    threeOf = true;
		}
		
		return threeOf;
		
	}
	
	private boolean isStraight() 
	{
		boolean straight = false;
		
		if ((cardArray[4] - cardArray[0]) == 4 && (cardArray[2] == cardArray[1]+1))
		{
		    straight = true;
		}else if ((cardArray[4] - cardArray[1]) == 3 && cardArray[0] == 1 )
			{
			    straight = true;
			}
		
		return straight;
		
	}
	
	private boolean isFlush() 
	{
		boolean flush = true;
		for (int i = 0; i < numberOfCards - 1; i++) 
		{
            if (currentHand.get(i).getSuit() != currentHand.get(i + 1).getSuit()) 
			{
                flush = false;
            }
        }
		return flush;
		
	}
	
	private boolean isFullHouse() 
	{
		boolean threeOf = false;
		
		if ((cardArray[0] == cardArray[1] && cardArray[0] == cardArray[2] && cardArray[3] == cardArray[4]) ||
			(cardArray[2] == cardArray[3] && cardArray[2] == cardArray[4] && cardArray[0] == cardArray[1]))
		{
		    threeOf = true;
		}
		
		return threeOf;
		
	}
	
	private boolean isFourOfAKind() 
	{
		boolean fourOf = false;
		if ((cardArray[0] == cardArray[1] && cardArray[0] == cardArray[2] && cardArray[0] == cardArray[3] ) ||
			(cardArray[1] == cardArray[2] && cardArray[1] == cardArray[3] && cardArray[1] == cardArray[4] ))
		{
		    fourOf = true;
		}
		
		return fourOf;
		
	}
	
	private boolean isStraightFlush() 
	{
		return (isStraight() && isFlush());
		
	}
	
	private boolean isRoyalFlush() 
	{
		
		boolean royal = false;
		if (isStraightFlush() && cardArray[0] == 1)
		{
		    royal = true;
		}
		return royal;
		
	}




    public void play() 
    {
 //   /** The main algorithm for single player poker game 
 //    *
 //    * Steps:
 //    * 		showPayoutTable()
 //    *
 //    * 		++	
 //    * 		show balance, get bet 
 //    *		verify bet value, update balance
 //    *		reset deck, shuffle deck, 
 //    *		deal cards and display cards
 //    *		ask for position of cards to keep  
 //    *          get positions in one input line
 //    *		update cards
 //    *		check hands, display proper messages
 //    *		update balance if there is a payout
 //    *		if balance = O:
 //    *			end of program 
 //    *		else
 //    *			ask if the player wants to play a new game
 //    *			if the answer is "no" : end of program
 //    *			else : showPayoutTable() if user wants to see it
 //    *			goto ++
 //    */
	
	Scanner input;
	Scanner in;
	Scanner in2;
	List<Card> keepCard = new ArrayList<Card>();
	int counter;
	boolean newGame = true;
	boolean rightBet = false;
	String s;
	String answer;
	String answer2;
	
	showPayoutTable();
	
	while (newGame) {
		oneDeck.shuffle();
		input = new Scanner(System.in);
		counter = 0;
		
		
		
		System.out.println("Balance:" + balance + "\n");
		while (!rightBet) 
		{
			System.out.print("Enter bet: ");
			bet = Integer.parseInt(input.nextLine());
			if (bet > balance) 
			{
				System.out.println("insufficient funds!");
				rightBet = false;
			} else 
			{
				rightBet = true;
			}
			
		}
		
		balance = balance - bet;
		try 
		{
			currentHand = oneDeck.deal(5);
		} catch (PlayingCardException e) 
		{
			System.out.println("Error while dealing a new hand: " + e.getMessage());
		}
		System.out.println(currentHand.toString());
		System.out.println("Enter positions of cards you would like to keep:");
		
			
			s = input.nextLine();
			if (!(s.equals("0"))) 
			{
				input = new Scanner(s);
				input = input.useDelimiter("\\s*");
				while (input.hasNext()) 
				{
					keepCard.add(currentHand.get((input.nextInt()) - 1));
					counter++;
				}
			}
		
		currentHand = keepCard;
		try 
		{
			currentHand.addAll(oneDeck.deal(5 - counter));
		} 
		catch (PlayingCardException e) 
		{
			System.out.println("Exception while dealing the second hand: " + e.getMessage());
		}
		
		System.out.println(currentHand.toString());
		
		
		checkHands();
		System.out.println("Your balance: " + balance );
		if (balance > 0 )
		{
			System.out.println("Would you like to play another game y/n ?");
			in = new Scanner(System.in);
			answer = new String (in.next());
			
			if (answer.equals("n")) 
			{
				newGame = false;
				System.out.println("Bye");
			}
			else
			{
				System.out.println("Want to see payout table ? (y/n)");
				in2 = new Scanner(System.in);
				answer2 = new String (in2.next());
				if (answer2.equals("y")) 
				{
					showPayoutTable();
				}
			}
		}else
		{
			System.out.println("You spent all your money!!!!");
			newGame = false;
			
		}
			
			
			
			oneDeck.reset();
			currentHand.clear();
			rightBet = false;
		}
		
		
	}


    /** Do not modify this. It is used to test checkHands() method 
     *  checkHands() should print your current hand type
     */ 
    public void testCheckHands()
    {
      	try {
    		currentHand = new ArrayList<Card>();

		// set Royal Flush
		currentHand.add(new Card(1,3));
		currentHand.add(new Card(10,3));
		currentHand.add(new Card(12,3));
		currentHand.add(new Card(11,3));
		currentHand.add(new Card(13,3));
		System.out.println(currentHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight Flush
		currentHand.set(0,new Card(9,3));
		System.out.println(currentHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight
		currentHand.set(4, new Card(8,1));
		System.out.println(currentHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Flush 
		currentHand.set(4, new Card(5,3));
		System.out.println(currentHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	 	// "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

		// set Four of a Kind
		currentHand.clear();
		currentHand.add(new Card(8,3));
		currentHand.add(new Card(8,0));
		currentHand.add(new Card(12,3));
		currentHand.add(new Card(8,1));
		currentHand.add(new Card(8,2));
		System.out.println(currentHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Three of a Kind
		currentHand.set(4, new Card(11,3));
		System.out.println(currentHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Full House
		currentHand.set(2, new Card(11,1));
		System.out.println(currentHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Two Pairs
		currentHand.set(1, new Card(9,1));
		System.out.println(currentHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Royal Pair
		currentHand.set(0, new Card(3,1));
		System.out.println(currentHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// non Royal Pair
		currentHand.set(2, new Card(3,3));
		System.out.println(currentHand);
    		checkHands();
		System.out.println("-----------------------------------");
      	}
      	catch (Exception e)
      	{
		System.out.println(e.getMessage());
      	}
    }

    /* Quick testCheckHands() */
    public static void main(String args[]) 
    {
	MyPokerGame mypokergame = new MyPokerGame();
	mypokergame.testCheckHands();
    }
}
