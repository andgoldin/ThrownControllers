import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * The GUI component for the countdown timer used in the game.
 * @author Andrew Goldin
 */
public class TimerPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	
	/**
	 * The time for an easy trivia challenge, in seconds.
	 */
	public static final int TIME_EASY = 15;
	
	
	/**
	 * The time for an easy trivia challenge, in seconds.
	 */
	public static final int	TIME_MEDIUM = 30;
	
	
	/**
	 * The time for an easy trivia challenge, in seconds.
	 */
	public static final int	TIME_HARD = 45;

	
	private Timer timer;
	private JLabel timerDisplay;
	private JPanel controlPanel, settingsPanel;
	private JButton easyButton, mediumButton, hardButton, startButton;
	private int time;
	private String timeString, temp;

	
	/**
	 * Creates and sets up all of the components of the timer.
	 */
	public TimerPanel() {
		
		// set up the timer and the timer display
		timer = new Timer(10, this);
		time = 0;
		timeString = "00:00";
		timerDisplay = new JLabel(timeString);
		timerDisplay.setFont(new Font("Sans Serif", Font.BOLD, 32));
		timerDisplay.setForeground(Color.RED);
		
		// create the "easy" button
		easyButton = new JButton("EASY");
		easyButton.setFont(new Font("Sans Serif", Font.BOLD, 14));
		easyButton.setBackground(Color.GREEN);
		easyButton.setForeground(Color.BLACK);
		easyButton.addActionListener(this);
		
		// create the "medium" button
		mediumButton = new JButton("MEDIUM");
		mediumButton.setFont(new Font("Sans Serif", Font.BOLD, 14));
		mediumButton.setBackground(Color.YELLOW);
		mediumButton.setForeground(Color.BLACK);
		mediumButton.addActionListener(this);
		
		// create the "hard" button
		hardButton = new JButton("HARD");
		hardButton.setFont(new Font("Sans Serif", Font.BOLD, 14));
		hardButton.setBackground(Color.RED);
		hardButton.setForeground(Color.WHITE);
		hardButton.addActionListener(this);
		
		// create the "start/stop" toggle button
		startButton = new JButton("START");
		startButton.setFont(new Font("Sans Serif", Font.BOLD, 14));
		startButton.setBackground(Color.GREEN);
		startButton.setForeground(Color.BLACK);
		startButton.addActionListener(this);
		
		// add the display and the start/stop button to the control panel
		controlPanel = new JPanel(new FlowLayout());
		controlPanel.add(timerDisplay);
		JLabel sec = new JLabel("seconds   ");
		sec.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		controlPanel.add(sec);
		controlPanel.add(startButton);
		
		// add the three settings buttons to the settings panel
		settingsPanel = new JPanel(new FlowLayout());
		settingsPanel.add(easyButton);
		settingsPanel.add(mediumButton);
		settingsPanel.add(hardButton);
		
		// add the control and settings panels to the main panel
		this.setLayout(new GridLayout(2, 1, 1, 1));
		this.add(controlPanel);
		this.add(settingsPanel);
		
	}

	
	/**
	 * The actionPerformed method. Defines the various actions of each component of the timer.
	 * @param e The ActionEvent that gets passed to the method.
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		// if there is no action command, the action is from the swing timer
		if (cmd == null) {
			
			// while there is time left, decrement the timer and update the display
			if (time > 0) {
				time--;
				temp = "" + time;
				if (temp.length() == 1) {
					timeString = "00:0" + temp;
				}
				else if (temp.length() == 2) {
					timeString = "00:" + temp;
				}
				else if (temp.length() == 3) {
					timeString = "0" + temp.substring(0, 1) + ":" + temp.substring(1, 3);
				}
				else {
					timeString = temp.substring(0, 2) + ":" + temp.substring(2, 4);
				}
				timerDisplay.setText(timeString);
			}
			// otherwise, stop the timer and make the text red
			else {
				timerDisplay.setForeground(Color.RED);
				timer.stop();
				startButton.setText("START");
				startButton.setBackground(Color.GREEN);
				startButton.setForeground(Color.BLACK);
			}
		}
		
		// if there is an action command, a button was clicked
		else {
			
			// if EASY, MEDIUM, or HARD is pressed, set the time to the corresponding value
			if (cmd.equals("EASY")) {
				time = TIME_EASY * 100;
				timerDisplay.setText((time / 100) + ":00");
				timerDisplay.setForeground(Color.BLACK);
			}
			else if (cmd.equals("MEDIUM")) {
				time = TIME_MEDIUM * 100;
				timerDisplay.setText((time / 100) + ":00");
				timerDisplay.setForeground(Color.BLACK);
			}
			else if (cmd.equals("HARD")) {
				time = TIME_HARD * 100;
				timerDisplay.setText((time / 100) + ":00");
				timerDisplay.setForeground(Color.BLACK);
			}
			
			// if START/STOP is pressed, start or stop the timer, depending on whether it is running
			else if (cmd.equals("START") || cmd.equals("STOP!")) {
				if (time > 0 && !timer.isRunning()) {
					timer.start();
					startButton.setText("STOP!");
					startButton.setBackground(Color.RED);
					startButton.setForeground(Color.WHITE);
				}
				else {
					timer.stop();
					startButton.setText("START");
					startButton.setBackground(Color.GREEN);
					startButton.setForeground(Color.BLACK);
				}
			}
		}

	}

}