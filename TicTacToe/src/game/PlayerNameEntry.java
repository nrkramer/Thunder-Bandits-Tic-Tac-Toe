package game;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class PlayerNameEntry extends JDialog {
	private static final long serialVersionUID = 3282470610854777466L;

	/**
	 * Create the dialog.
	 */
	public PlayerNameEntry(JFrame parent) {
		super(parent, "Enter your names");
		
		setBounds(100, 100, 450, 300);
	}

}
