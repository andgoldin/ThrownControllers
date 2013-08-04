/**
 * Represents a trivia or game challenge in the game.
 * @author Andrew Goldin
 */
public class Challenge {
	
	// strings for each part of a challenge
	private String question, answer, text;
	
	/**
	 * Creates a new challenge.
	 * @param q The question for the challenge.
	 * @param a The answer for the challenge. Can be null if the challenge is a game challenge.
	 * @param description The description text of the challenge.
	 */
	public Challenge(String q, String a, String description) {
		question = q;
		answer = a;
		text = description;
	}
	
	/**
	 * Creates a new empty challenge.
	 */
	public Challenge() {
		question = null;
		answer = null;
		text = null;
	}
	
	/**
	 * Set the challenge's question.
	 * @param q The question for the challenge.
	 */
	public void setQuestion(String q) {
		question = q;
	}
	
	/**
	 * Set the challenge's answer.
	 * @param a The answer for the challenge.
	 */
	public void setAnswer(String a) {
		answer = a;
	}
	
	/**
	 * Set the challenge's description.
	 * @param description The description for the challenge.
	 */
	public void setDescription(String description) {
		text = description;
	}
	
	/**
	 * Retrieve the challenge's question.
	 * @return The question for the challenge.
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * Retrieve the challenge's answer.
	 * @return The answer for the challenge.
	 */
	public String getAnswer() {
		return answer;
	}
	
	/**
	 * Retrieve the challenge's description.
	 * @return The description for the challenge.
	 */
	public String getDescription() {
		return text;
	}
	
}