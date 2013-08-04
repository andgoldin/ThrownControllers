import java.util.*;
import java.io.*;

/**
 * A class to load trivia and challenges from text files and select
 * random challenges for use in the game.
 * @author Andrew Goldin
 */
public class Trivia {

	// the challenge lists
	private ArrayList<Challenge> easyTrivia, mediumTrivia, hardTrivia, gameChallenges;
	
	// the random number generator
	private Random randy;

	/**
	 * Creates the Trivia object. Loads each text file and fills the challenge lists.
	 */
	public Trivia() {
		System.out.println("> loading challenges...");
		
		easyTrivia = new ArrayList<Challenge>();
		mediumTrivia = new ArrayList<Challenge>();
		hardTrivia = new ArrayList<Challenge>();
		gameChallenges = new ArrayList<Challenge>();
		randy = new Random();

		// read in each file, every odd line is a question, even lines are corresponding answers
		try {
			// easy trivia
			Scanner read = new Scanner(new File("chal/easy_trivia.txt"));
			while (read.hasNextLine()) {
				String q = read.nextLine();
				if (!read.hasNextLine()) break;
				String a = read.nextLine();
				easyTrivia.add(new Challenge(q, a,
						"<html><center>EASY TRIVIA<br>1 POINT</center></html>"));
			}
			System.out.println(">> " + easyTrivia.size() + " easy trivia loaded");
			
			// medium trivia
			read = new Scanner(new File("chal/medium_trivia.txt"));
			while (read.hasNextLine()) {
				String q = read.nextLine();
				if (!read.hasNextLine()) break;
				String a = read.nextLine();
				mediumTrivia.add(new Challenge(q, a,
						"<html><center>MEDIUM TRIVIA<br>2 POINTS</center></html>"));
			}
			System.out.println(">> " + mediumTrivia.size() + " medium trivia loaded");
			
			// hard trivia
			read = new Scanner(new File("chal/hard_trivia.txt"));
			while (read.hasNextLine()) {
				String q = read.nextLine();
				if (!read.hasNextLine()) break;
				String a = read.nextLine();
				hardTrivia.add(new Challenge(q, a,
						"<html><center>HARD TRIVIA<br>3 POINTS</center></html>"));
			}
			System.out.println(">> " + hardTrivia.size() + " hard trivia loaded");
			
			// game challenges
			read = new Scanner(new File("chal/game_challenges.txt"));
			while (read.hasNextLine()) {
				String q = read.nextLine();
				gameChallenges.add(new Challenge(q, "Game Challenge",
						"<html><center>GAME CHALLENGE<br>4 POINTS</center></html>"));
			}
			System.out.println(">> " + gameChallenges.size() + " game challenges loaded");
			
			read.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Pops a random challenge from a random list, weighted against game challenges.
	 * That challenge is then removed from the list so it cannot be reused.
	 * @return A random challenge.
	 */
	public Challenge popRandomChallenge() {
		int type = randy.nextInt(7);
		int choice;
		Challenge temp = null;
		
		// 2 in 7 chance of easy
		if (type < 2) {
			choice = randy.nextInt(easyTrivia.size());
			temp = easyTrivia.get(choice);
			easyTrivia.remove(choice);
			return temp;
		}
		// 2 in 7 chance of medium
		else if (type < 4) {
			choice = randy.nextInt(mediumTrivia.size());
			temp = mediumTrivia.get(choice);
			mediumTrivia.remove(choice);
			return temp;
		}
		// 2 in 7 chance of hard
		else if (type < 6) {
			choice = randy.nextInt(hardTrivia.size());
			temp = hardTrivia.get(choice);
			hardTrivia.remove(choice);
			return temp;
		}
		// 1 in 7 chance of game challenge
		else {
			choice = randy.nextInt(gameChallenges.size());
			temp = gameChallenges.get(choice);
			gameChallenges.remove(choice);
			return temp;
		}
	}

}