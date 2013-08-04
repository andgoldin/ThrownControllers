import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Represents the board of panels in the main window.
 * @author Andrew Goldin
 */
public class Board extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	// the many instance fields
	private JPanel boardPanel, utilsPanel, messagePanel, switchPanel;
	private Panel[] board;
	private Trivia trivia;
	private JLabel challengeText;
	private boolean marioTurn;
	
	private int numRows, numCols;

	public Board() {
		
		// give the panel a border layout
		this.setLayout(new BorderLayout());
		
		// load all of the trivia in
		trivia = new Trivia();

		// create the array of board panels
		numRows = 2;
		numCols = 4;
		boardPanel = new JPanel(new GridLayout(numRows, numCols, 2, 2));
		board = new Panel[numRows * numCols];
		for (int i = 0; i < board.length; i++) {
			// create each panel, add a random challenge, label it and add it to the GUI
			board[i] = new Panel();
			board[i].setChallenge(trivia.popRandomChallenge());
			board[i].setText("" + (i + 1));
			boardPanel.add(board[i]);
		}

		// create the component that displays the text of each challenge
		challengeText = new JLabel("Welcome to THROWN CONTROLLERS!");
		challengeText.setFont(new Font("Helvetica LT Std", Font.PLAIN, 24));
		messagePanel = new JPanel();
		messagePanel.add(challengeText);
		messagePanel.setBackground(Color.WHITE);
		
		// create the button to change turns
		JButton toggleTurn = new JButton("CHANGE TURN");
		toggleTurn.setFont(new Font("Sans Serif", Font.BOLD, 16));
		toggleTurn.addActionListener(this);

		// create the button to reset the board
		JButton resetBoard = new JButton("RESET BOARD");
		resetBoard.setFont(new Font("Sans Serif", Font.BOLD, 16));
		resetBoard.addActionListener(this);

		// add the two buttons to the switch panel
		switchPanel = new JPanel(new GridLayout(1, 2, 2, 2));
		switchPanel.add(toggleTurn);
		switchPanel.add(resetBoard);

		// add the challenge display and buttons to the utility panel
		utilsPanel = new JPanel(new BorderLayout());
		utilsPanel.add(messagePanel, BorderLayout.CENTER);
		utilsPanel.add(switchPanel, BorderLayout.SOUTH);
		
		// add the board and utilities to the main component
		this.add(boardPanel, BorderLayout.CENTER);
		this.add(utilsPanel, BorderLayout.SOUTH);
		
		// start with team mario
		marioTurn = true;
	}

	
	/**
	 * The actionPerformed method for the change turn and reset buttons.
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		// toggle the board color between red and blue on change turn
		if (cmd.equals("CHANGE TURN")) {
			if (!marioTurn) {
				for (int i = 0; i < board.length; i++) {
					if (board[i].isEnabled()) {
						board[i].setBackground(Color.RED);
					}
				}
				marioTurn = true;
			}
			else {
				for (int i = 0; i < board.length; i++) {
					if (board[i].isEnabled()) {
						board[i].setBackground(Color.BLUE);
					}
				}
				marioTurn = false;
			}
		}
		
		// create a brand new board with new challenges on reset board
		else if (cmd.equals("RESET BOARD")) {
			this.remove(boardPanel);
			boardPanel = new JPanel(new GridLayout(numRows, numCols, 2, 2));
			board = new Panel[numRows * numCols];
			for (int i = 0; i < board.length; i++) {
				board[i] = new Panel();
				board[i].setChallenge(trivia.popRandomChallenge());
				board[i].setText("" + (i + 1));
				boardPanel.add(board[i]);
			}
			this.add(boardPanel, BorderLayout.CENTER);
			this.validate();
		}
	}

	
	/**
	 * A class to represent each panel within the board. Declared within the Board class
	 * because it needs access to the challenge display.
	 * @author Andrew Goldin
	 */
	class Panel extends JButton {

		private static final long serialVersionUID = 1L;

		// the instance fields
		private Color bgcolor, fgcolor;
		private int pointValue;
		private Challenge challenge;
		private int timesClicked;

		
		/**
		 * Sets up the panel, with all its many properties.
		 */
		public Panel() {
			
			// set the background and foreground colors
			bgcolor = Color.RED;
			fgcolor = Color.WHITE;
			
			// challenge will be created on Board creation
			challenge = null;
			
			// set the visual properties of the panel
			this.setText("" + pointValue);
			this.setFont(new Font("Sans Serif", Font.BOLD, 72));
			this.setBackground(bgcolor);
			this.setForeground(fgcolor);
			
			// keep track of the number of times clicked to determine what is displayed
			timesClicked = 0;
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// set the properties on first click
					setFont(new Font("Sans Serif", Font.BOLD, 24));
					setText(challenge.getDescription());
					setBackground(Color.DARK_GRAY);
					timesClicked++;
					
					// 10% chance of a physical challenge
					double x = Math.random() * 100;
					if (timesClicked == 1 && x < 10) {
						setBackground(Color.BLACK);
						setText("<html><center>PHYSICAL<br>CHALLENGE!<br>4 POINTS</center></html>");
						setEnabled(false);
					}
					// otherwise, get the challenge and display it
					else {
						if (timesClicked == 2) {
							setBackground(Color.LIGHT_GRAY);
							setForeground(Color.BLACK);
							challengeText.setText(challenge.getQuestion());
						}
						if (timesClicked == 3) {
							setBackground(Color.WHITE);
							challengeText.setText(challenge.getAnswer());
							setEnabled(false);
						}
					}


				}
			});
		}

		/**
		 * Sets the challenge for the given panel.
		 * @param c The challenge for the panel.
		 */
		public void setChallenge(Challenge c) {
			challenge = c;
		}

	}

}