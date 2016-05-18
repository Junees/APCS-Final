
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
	public static Scanner input = new Scanner(System.in);

	public static Card findCommander(byte playerNum) {
		for (int r = 0; r < sm.numRows(); r++) {
			for (int c = 0; c < sm.numColumns(); c++) {
				Card temp = sm.get(r, c);
				if (temp instanceof Commander && temp.getPlayer() == playerNum)
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

			System.out.println(hand0);
			System.out.println(hand1);

			c0 = findCommander((byte) 0);
			c1 = findCommander((byte) 1);

			playCard((byte) 0);
			playCard((byte) 1);

			System.out.println("health of commander " + c0.getHealth());
			System.out.println("health of commander " + c1.getHealth());

			c0.setHealth((byte) -123);

			// putting cards onto the board and adjacent to friendly cards
			// taking cards from the array list and removing them from the hand
			// traversing and printing from the new arrayList from the hand size

		}
	}

	public static byte playCard(byte playerNum) {

		if (playerNum == 0) {

			int z = 0;
			System.out.println("number card in hand");

			for (int r = 0; r < hand0.size(); r++) {

				{
					System.out.print(hand0.get(r) + " ");
					System.out.print(z++ + " ");
				}

			}

			System.out.println();

			System.out.println("choose the card you want to play ");
			int value2;
			try {
							
				value2 = Integer.parseInt(input.nextLine());
			} catch (Exception e) {
				value2 = Integer.parseInt(input.nextLine());
			}
			if (value2 >= 0 && value2 < hand0.size()) {
				Card card = hand0.get(value2);
				hand0.remove(card);
				System.out.println("x coord");
				int xCoord = input.nextInt();
				System.out.println("y coord");
				int yCoord = input.nextInt();
				System.out.println("(" + xCoord + "," + yCoord + ")");
				while (canPlay((byte) xCoord, (byte) yCoord, (byte) 0) == false)
				{
					System.out.println("the selected input is bad");
					showToScreen();

					System.out.println("enter an x coord");
					 xCoord = input.nextInt();
					System.out.println("enter the y coord");
					 yCoord = input.nextInt();
				
					 
				}
					
					
				
						sm.add(xCoord, yCoord, card);
						showToScreen();
					

				}
			
						
		}

		else {

			int po = 0;
			System.out.println("number card in hand");

			for (int r = 0; r < hand1.size(); r++) {

				{
					System.out.print(hand1.get(r) + " ");
					System.out.print(po++ + " ");
				}

			}

			System.out.println();

			System.out.println("choose the card you want to play ");
			int value;
			try {
				value = Integer.parseInt(input.nextLine());
			} catch (Exception e) {
				value = Integer.parseInt(input.nextLine());
			}
			
			if (value >= 0 && value < hand1.size()) {
				Card card = hand0.get(value);
				hand1.remove(card);
				System.out.println("x coord");
				int xCoord = input.nextInt();
				System.out.println("y coord");
				int yCoord = input.nextInt();
				System.out.println("(" + xCoord + "," + yCoord + ")");
				while (canPlay((byte) xCoord, (byte) yCoord, (byte) 0) == false)
				{
					System.out.println("the selected input is bad, cards must be adjacent to each other ");
					showToScreen();

					
					System.out.println("enter an x coord");
					 xCoord = input.nextInt();
					System.out.println("enter the y coord");
					 yCoord = input.nextInt();
				
			
				}
				
					
				
						sm.add(xCoord, yCoord, card);
						 System.out.println("(" + xCoord + "," + yCoord + ")");
						showToScreen();
					

				}
				

					
						

					

				
			

		}
		return playerNum;
	}
	// pre: x and y are valid indices
	// post: returns if the move can be made

	public static boolean canPlay(byte r, byte c, byte p) {

		for (int row = r - 1; row <= r + 1; row++)
			for (int col = c - 1; col <= c + 1; col++) {
				if (row == r && col == c)
					continue;
				Card temp = sm.get(row, col);
				if (temp != null && temp.getPlayer() == p)
					return true;
			}

		return false;
	}

	public static byte drawCard(byte pNum) {

		if (pNum == 1) {

			hand0.add(deck0.remove(0));

		}

		else {

			hand1.add(deck1.remove(0));

		}

		return 0;

	}

	public static void showToScreen() {

		System.out.println(sm.toString());

	}

}
