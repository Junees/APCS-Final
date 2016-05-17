
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
	public static ArrayList<Card> deck1 = new ArrayList();
	public static ArrayList<Card> deck2 = new ArrayList();
	public static ArrayList<Card> hand1 = new ArrayList();
	public static ArrayList<Card> hand2 = new ArrayList();

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

			deck1.add(new Card(anime.nextLine()));
		}
		anime.close();

		Scanner season2 = new Scanner(new FileReader("cards2.txt"));
		while (season2.hasNextLine()) {

			deck2.add(new Card(season2.nextLine()));
		}
		season2.close();

		/* while */ if (c0.getHealth() > 0 && c1.getHealth() > 0) {

			showToScreen();

			System.out.println(deck1);
			System.out.println(deck2);

			drawCard((byte) 0);
			drawCard((byte) 0);
			drawCard((byte) 0);

			drawCard((byte) 1);
			drawCard((byte) 1);
			drawCard((byte) 1);
			drawCard((byte) 1);

			System.out.println(hand1);
			System.out.println(hand2);

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
		Scanner input = new Scanner(System.in);

		if (playerNum == 1) {

			for (int r = 0; r < hand1.size(); r++) {
				{
					System.out.print(hand1.get(r) + " ");
				}
			}

			System.out.println();

			System.out.println("choose the card you want to play");
			int value2 = Integer.parseInt(input.nextLine());
			if (value2 >= 0 && value2 < hand1.size()) {
				Card card = hand1.get(value2);
				hand1.remove(card);
				System.out.println("x coord");
				int xCoord = input.nextInt();
				System.out.println("y coord");
				int yCoord = input.nextInt();

				if (xCoord >= 0 && xCoord >= 0) {
					sm.add(xCoord, yCoord, card);
					showToScreen();

				}
			}

			else {

				for (int r = 0; r < hand2.size(); r++) {
					{
						System.out.print(hand2.get(r) + " ");
					}
				}

				System.out.println();
				System.out.println("choose the card you want to play");
				int value = Integer.parseInt(input.nextLine());
				if (value >= 0 && value < hand1.size()) {
					Card card = hand1.get(value);
					hand1.remove(card);
					System.out.println("x coord");
					int xCoord = input.nextInt();
					System.out.println("y coord");
					int yCoord = input.nextInt();

					if (xCoord >= 0 && xCoord >= 0) {
						sm.add(xCoord, yCoord, card);
						showToScreen();

					}
				}
			}

		}
		input.close();
		return playerNum;
	}

	public static byte drawCard(byte pNum) {

		if (pNum == 1) {

			hand1.add(deck1.remove(0));

		}

		else {

			hand2.add(deck2.remove(0));

		}

		return 0;

	}

	public static void showToScreen() {

		System.out.println(sm.toString());

	}

	public void detection() {

	}

}
