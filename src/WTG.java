
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
//import sparseMatrix.Commander;
//import sparseMatrix.Card;
//import sparseMatrix.sparseMatrix;
import java.io.*;

public class WTG

{

	public static sparseMatrix<Card> sm = new sparseMatrix<Card>(5, 11);
	public static ArrayList<Card> deck0 = new ArrayList();
	public static ArrayList<Card> deck1 = new ArrayList();
	public static ArrayList<Card> hand0 = new ArrayList();
	public static ArrayList<Card> hand1 = new ArrayList();
	public static ArrayList<Card> discard0 = new ArrayList();
	public static ArrayList<Card> discard1 = new ArrayList();
	public static ArrayList<Card> exile0 = new ArrayList();
	public static ArrayList<Card> exile1 = new ArrayList();

	// exile cards
	// discarding and re adding cards from discard
	//

	public static Scanner input = new Scanner(System.in);

	public static Card findCommander(byte playerNum) {
		for (int r = 0; r < sm.numRows(); r++) {
			for (int c = 0; c < sm.numColumns(); c++) {
				Card temp = sm.get(r, c);
				if (temp != null && temp instanceof Commander && temp.getPlayer() == playerNum)
					return temp;
			}
		}
		return null;
	}

	public static void main(String[] vidyagame) throws IOException {

		sm.add(2, 1, new Commander((byte) 0));
		sm.add(2, 9, new Commander((byte) 1));

		Card c0 = findCommander((byte) 0);
		Card c1 = findCommander((byte) 1);

		Scanner anime = new Scanner(new FileReader("cards.txt"));
		while (anime.hasNextLine()) {

			deck0.add(new Card(anime.nextLine()));
		}
		anime.close();

		Scanner season2 = new Scanner(new FileReader("cards2.txt"));
		while (season2.hasNextLine()) {

			deck1.add(new Card(season2.nextLine()));
		}
		season2.close();

		/* while */
		while (c0.getHealth() > 0 && c1.getHealth() > 0) {

			showToScreen();

			System.out.println(deck0);
			System.out.println(deck1);

			drawCard((byte) 0);
			drawCard((byte) 0);
			drawCard((byte) 0);

			drawCard((byte) 1);
			drawCard((byte) 1);
			drawCard((byte) 1);
			drawCard((byte) 1);

			// drawCardFromDiscard(byte playerNum, ArrayList<Card> discard)

			System.out.println(hand0);
			System.out.println(hand1);

			c0 = findCommander((byte) 0);
			c1 = findCommander((byte) 1);

			//discard((byte) 0, hand0, discard0);

			//drawCardFromDiscard((byte) 0, discard0);

			playCard((byte) 0, hand0);
			playCard((byte) 1, hand1);
			dealDamage((byte) 3, (byte) 2, (byte) 5);
			
			
			System.out.println("health of commander " + c0.getHealth());
			System.out.println("health of commander " + c1.getHealth());

			c0.setHealth((byte) -123);

			// putting cards onto the board and adjacent to friendly cards
			// taking cards from the array list and removing them from the hand
			// traversing and printing from the new arrayList from the hand size

		}
	}

	public static byte playCard(byte playerNum, ArrayList<Card> hand) {

		System.out.println("number card in hand");

		for (int r = 0; r < hand.size(); r++) {

			{
				System.out.print(hand.get(r) + " ");
				System.out.print(r + " ");
			}

		}

		System.out.println();

		System.out.println("choose the card you want to play ");
		byte value2 = (byte) (input.nextInt());
		System.out.println("x coord");
		byte xCoord = (byte) (input.nextInt());
		System.out.println("y coord");
		byte yCoord = (byte) (input.nextInt());
		while (validInputToPlay(xCoord, yCoord, value2, hand, playerNum) == false) {
			System.out.println("the selected input is bad");

			for (int r = 0; r < hand.size(); r++) {

				{
					System.out.print(hand.get(r) + " ");
					System.out.print(r + " ");
				}

			}

			System.out.println("");
			showToScreen();

			System.out.println("choose the card you want to play ");
			value2 = (byte) (input.nextInt());
			System.out.println("x coord");
			xCoord = (byte) (input.nextInt());
			System.out.println("y coord");
			yCoord = (byte) (input.nextInt());
		}

		Card card = hand.get(value2);
		hand.remove(card);

		sm.add(xCoord, yCoord, card);
		System.out.println("(" + xCoord + "," + yCoord + ")");
		showToScreen();

		return playerNum;
	}

	// pre: x and y are valid indices
	// post: returns if the move can be made
	// questions...
	// done if pick card returns null it breaks,
	// done printing out the coord for if it is broken or still works
	// player can only play pieces adjacent to each other
	// move method
	// battle method

	public static byte playCardFromDiscard(byte playerNum, ArrayList<Card> discard) {

		System.out.println("number card in discardPile");

		for (int r = 0; r < discard.size(); r++) {

			{
				System.out.print(discard.get(r) + " ");
				System.out.print(r + " ");
			}

		}

		System.out.println();

		System.out.println("choose the card you want to play from the discard pile ");
		byte value2 = (byte) (input.nextInt());
		System.out.println("x coord");
		byte xCoord = (byte) (input.nextInt());
		System.out.println("y coord");
		byte yCoord = (byte) (input.nextInt());
		while (validInputToPlay(xCoord, yCoord, value2, discard, playerNum) == false) {
			System.out.println("the selected input is bad");

			for (int r = 0; r < discard.size(); r++) {

				{
					System.out.print(discard.get(r) + " ");
					System.out.print(r + " ");
				}

			}

			System.out.println("");
			showToScreen();

			System.out.println("choose the card you want to play ");
			value2 = (byte) (input.nextInt());
			System.out.println("x coord");
			xCoord = (byte) (input.nextInt());
			System.out.println("y coord");
			yCoord = (byte) (input.nextInt());
		}

		Card card = discard.get(value2);
		discard.remove(card);

		sm.add(xCoord, yCoord, card);
		System.out.println("(" + xCoord + "," + yCoord + ")");
		showToScreen();

		return playerNum;
	}

	public static byte drawCardFromDiscard(byte playerNum, ArrayList<Card> discard) {
		System.out.println("number card in discardPile");

		for (int r = 0; r < discard.size(); r++) {

			{
				System.out.print(discard.get(r) + " ");
				System.out.print(r + " ");
			}

		}

		System.out.println();

		System.out.println("choose the card you want to play from the discard pile ");
		byte pickingCardFromDiscard = (byte) (input.nextInt());
		Card card = discard.get(pickingCardFromDiscard);
		discard.add(discard.remove(pickingCardFromDiscard));

		return playerNum;
	}

	public static byte drawCard(byte pNum) {

		if (pNum == 0) {

			hand0.add(deck0.remove(0));

		}

		else {

			hand1.add(deck1.remove(0));

		}

		return 0;

	}

	public static byte discard(byte playerNum, ArrayList<Card> hand, ArrayList<Card> discard) {
		System.out.println("cards in your hand");

		for (int r = 0; r < hand.size(); r++) {

			{
				System.out.print(hand.get(r) + " ");
				System.out.print(r + " ");
			}

		}

		System.out.println();

		System.out.println("choose the card you want to discard");
		byte cardToDiscard = (byte) (input.nextInt());
		Card card = hand.get(cardToDiscard);
		discard.add(hand.remove(cardToDiscard));

		return playerNum;
	}

	public static byte dealDamage(byte xCoord, byte yCoord, byte amtDamage) {

		Card attacker = sm.get(xCoord, yCoord);
		
		System.out.println("choose a card on the field to target");

		
		
		for (int r = 0; r < sm.numRows(); r++) {
			for (int c = 0; c < sm.numColumns(); c++) {
				if(r == xCoord && c == yCoord)
					continue;
				Card temp = sm.get(r, c);
				if (temp != null)
				{
					
				System.out.println(temp);
									
				}
				
				System.out.println("choose the card you want to deal damage to ");
				byte value2 = (byte) (input.nextInt());
				System.out.println("x coord");
				byte xCoord2 = (byte) (input.nextInt());
				System.out.println("y coord");
				byte yCoord2 = (byte) (input.nextInt());
				
				Card victim sm.get(xCoord2, yCoord2);
				
				while (validInput(xCoord2, yCoord2, value2, hand, playerNum) == false) {
					System.out.println("the selected input is bad");

					for (int r = 0; r < hand.size(); r++) {

						{
							System.out.print(hand.get(r) + " ");
							System.out.print(r + " ");
						}

					}

					System.out.println("");
					showToScreen();

					System.out.println("choose the card you want to play ");
					value2 = (byte) (input.nextInt());
					System.out.println("x coord");
					xCoord = (byte) (input.nextInt());
					System.out.println("y coord");
					yCoord = (byte) (input.nextInt());
				}

				Card card = hand.get(value2);
				hand.remove(card);

				sm.add(xCoord, yCoord, card);
				System.out.println("(" + xCoord + "," + yCoord + ")");
				showToScreen();

				
			}

				
			}

			showToScreen();
		}
		return amtDamage;

	}

	public static void showToScreen() {

		System.out.println(sm.toString());

	}

	public static boolean validInputToPlay(byte r, byte c, byte cardNum, ArrayList<Card> hand, byte playerNum) {
		if (r < 0 || r >= sm.numRows() || c < 0 || c >= sm.numColumns()) {

			return false;

		}

		if (cardNum < 0 || cardNum >= hand.size()) {
			return false;
		}

		for (int row = r - 1; row <= r + 1; row++)
			for (int col = c - 1; col <= c + 1; col++) {
				if (row == r && col == c)
					continue;
				Card temp = sm.get(row, col);
				if (temp != null && temp.getPlayer() == playerNum)
					return true;

			}
		return false;

	}
	public static boolean validInputToAttack(byte r, byte c, byte cardNum, ArrayList<Card> hand, byte playerNum) {
		if (r < 0 || r >= sm.numRows() || c < 0 || c >= sm.numColumns()) {

			return false;

		}

		if (cardNum < 0 || cardNum >= hand.size()) {
			return false;
		}

		for (int row = r - 1; row <= r + 1; row++)
			for (int col = c - 1; col <= c + 1; col++) {
				if (row == r && col == c)
					continue;
				Card temp = sm.get(row, col);
				if (temp != null && temp.getPlayer() == playerNum)
					return true;

			}
		return false;

	}
	
}
