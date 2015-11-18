
package main;

public class Driver {

	public static String version = "1.0.0";
	
	public static void main(String[] args) {
		// open interface (which will inherently for this
		// kind of project, take control of the logic flow
		// in this program)
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ticTacToeInterface();
			}
			
		}); // this makes sure that the GUI is run on a separate thread
		// not that it really matters...
	}
	
	public static void ticTacToeInterface() {
		new MainMenu();
	}
}
