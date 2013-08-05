import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The GUI component for the scorekeeping panel used in the game.
 * @author Andrew Goldin
 */
public class ScorePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// instance fields
	private int marioScore, sonicScore;
	private JLabel marioScoreLabel, sonicScoreLabel;
	private JButton marioUp, marioDown, sonicUp, sonicDown;


	/**
	 * Sets up all parts of the ScorePanel component, including images, buttons, and text.
	 */
	public ScorePanel() {
		// create a grid layout for the component
		this.setLayout(new GridLayout(2, 4, 2, 2));

		// set up the scoring components for team mario
		marioScore = 0;
		
		// create the text label that displays the score
		marioScoreLabel = new JLabel("" + marioScore, SwingConstants.LEFT);
		marioScoreLabel.setFont(new Font("Sans Serif", Font.BOLD, 34));
		
		// create the up and down buttons along with the ActionListener
		ActionListener marioListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("+")) {
					marioScore++;
				}
				else {
					marioScore--;
				}
				marioScoreLabel.setText("" + marioScore);
			}
		};
		marioUp = new JButton("+");
		marioUp.setBackground(Color.LIGHT_GRAY);
		marioUp.setFont(new Font("Sans Serif", Font.BOLD, 16));
		marioUp.addActionListener(marioListener);
		marioDown = new JButton("- ");
		marioDown.setBackground(Color.LIGHT_GRAY);
		marioDown.setFont(new Font("Sans Serif", Font.BOLD, 16));
		marioDown.addActionListener(marioListener);
		
		// add the score label and buttons
		JPanel marioButtons = new JPanel(new FlowLayout());
		marioButtons.add(marioScoreLabel);
		marioButtons.add(marioUp);
		marioButtons.add(marioDown);
		
		// add the rest of the components to the team mario panel
		JLabel teamMario = new JLabel("TEAM MARIO    ", SwingConstants.RIGHT);
		teamMario.setFont(new Font("Sans Serif", Font.BOLD, 18));
		this.add(teamMario);
		this.add(new JLabel(new ImageIcon("img/mario.png"), SwingConstants.LEFT));
		this.add(marioButtons);


		// set up the scoring components for team sonic

		sonicScore = 0;
		
		// create the text label that displays the score
		sonicScoreLabel = new JLabel("" + sonicScore, SwingConstants.LEFT);
		sonicScoreLabel.setFont(new Font("Sans Serif", Font.BOLD, 34));

		// create the up and down buttons along with the ActionListener
		ActionListener sonicListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("+")) {
					sonicScore++;
				}
				else {
					sonicScore--;
				}
				sonicScoreLabel.setText("" + sonicScore);
			}
		};
		sonicUp = new JButton("+");
		sonicUp.setBackground(Color.LIGHT_GRAY);
		sonicUp.setFont(new Font("Sans Serif", Font.BOLD, 16));
		sonicUp.addActionListener(sonicListener);
		sonicDown = new JButton("- ");
		sonicDown.setBackground(Color.LIGHT_GRAY);
		sonicDown.setFont(new Font("Sans Serif", Font.BOLD, 16));
		sonicDown.addActionListener(sonicListener);

		// add the score label and buttons
		JPanel sonicButtons = new JPanel(new FlowLayout());
		sonicButtons.add(sonicScoreLabel);
		sonicButtons.add(sonicUp, BorderLayout.NORTH);
		sonicButtons.add(sonicDown, BorderLayout.SOUTH);

		// add the rest of the components to the team sonic panel
		JLabel teamSonic = new JLabel("TEAM SONIC    ", SwingConstants.RIGHT);
		teamSonic.setFont(new Font("Sans Serif", Font.BOLD, 18));
		this.add(teamSonic);
		this.add(new JLabel(new ImageIcon("img/sonic.png"), SwingConstants.LEFT));
		this.add(sonicButtons);
	}

	/**
	 * Returns Team Mario's score.
	 * @return Team Mario's current score.
	 */
	public int getMarioScore() {
		return marioScore;
	}

	/**
	 * Returns Team Sonic's score.
	 * @return Team Sonic's current score.
	 */
	public int getSonicScore() {
		return sonicScore;
	}

}