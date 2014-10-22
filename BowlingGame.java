/*
 * The bowling game.
 * Rahul Yadav
 * rahulyadav@cmu.edu
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// Initializes the list of players
		final BowlingRules bowlingSystem = new BowlingRules();
		List<PlayerDetails> playerList = new ArrayList<PlayerDetails>();

		String selection = "";
		String name;
		int choice;

		System.out.println("Please enter your name:");
		name = new BufferedReader(new InputStreamReader(System.in)).readLine();
		playerList.add(new PlayerDetails(name));

		// Processes menu choices
		while (!selection.equals("2")) {

			// Prints welcome message
			bowlingSystem.welcomeMessage(playerList.get(0).getName());

			// Prints menu
			bowlingSystem.mainMenu();

			// Gets menu selection number
			try {
				selection = new BufferedReader(new InputStreamReader(System.in))
						.readLine();
				choice = Integer.parseInt(selection);
				switch (choice) {
				case 1:
					// Adds new players
					System.out.println("Please enter the player's name:");
					name = new BufferedReader(new InputStreamReader(System.in))
							.readLine();
					playerList.add(new PlayerDetails(name));
					break;
				case 2:
					// Prints list of players and starts game
					bowlingSystem.printPlayerInfo(playerList);
					break;
				case 3:
					// Exits game
					System.out.println("See you around!");
					System.exit(0);
				default:
					// Error message for invalid selections
					System.out
							.println("You have entered an invalid choice. Choice number can be 1, 2 or 3. Please try again.");
					System.out.println("");
				}
			} catch (NumberFormatException e) {
				System.out
						.println("You have entered an invalid choice. Choice number can be 1, 2 or 3. Please try again.");
			}

		}

		// Play frames 1 to 9
		for (int i = 0; i < 9; i++) {
			System.out.println("\n***** Frame " + (i + 1) + "! *****");
			for (PlayerDetails player : playerList) {
				bowlingSystem.bowlFrame(player, i);
			}
		}

		// Play final frame
		System.out.println("\n***** Final Frame! *****");
		for (PlayerDetails player : playerList) {
			bowlingSystem.bowlLastFrame(player, 9);
		}

		// Prints the final score board
		final ScoreBoard scoreboard = new ScoreBoard();
		scoreboard.printScores(playerList);
	}

}