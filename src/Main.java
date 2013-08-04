import java.awt.*;
import javax.swing.*;


/**
 * The main class. Sets up the game and runs it.
 * @author Andrew Goldin
 */
public class Main {

	// the main window
	private JFrame window;
	
	// the two main panels, the board and tools
	private JPanel boardPanel, toolPanel;
	
	
	/**
	 * Creates the game board and tools, then adds them to the window.
	 */
	public Main() {
		
		// create the frame
		window = new JFrame("THROWN CONTROLLERS");
		
		// create the board and the tool panel
		System.out.println("loading board and tools...");
		boardPanel = new Board();
		toolPanel = new JPanel(new GridLayout(1, 2, 2, 2));
		toolPanel.add(new ScorePanel());
		toolPanel.add(new TimerPanel());

		// add the components to the frame and display it
		window.getContentPane().add(boardPanel, BorderLayout.CENTER);
		window.getContentPane().add(toolPanel, BorderLayout.SOUTH);
		window.setPreferredSize(new Dimension(960, 600));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}
	
	
	/**
	 * Confirms the start of the game.
	 */
	public void run() {
		System.out.println("> board and tools loaded\nbegin!");
	}
	
	
	/**
	 * Main method, sets up a new instance of Main.
	 */
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		new Main().run();
	}
	
}