
package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.UIManager;

public class Driver {

	public static String version = "1.0.0";
	public static HashMap<String, ArrayList<Integer>> scores = new HashMap<String, ArrayList<Integer>>();
	
	public static void main(String[] args) {
		readScores();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Error getting the system theme.");
		}
		
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
	
	public static void readScores() {
		// attempt to read score file. If it doesn't exist, create a blank one
		File f = new File("scores.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = br.readLine();
			while(line != null) {
				String[] pcs = line.split(" ");
				ArrayList<Integer> records = new ArrayList<Integer>();
				records.add(Integer.valueOf(pcs[1]));
				records.add(Integer.valueOf(pcs[2]));
				records.add(Integer.valueOf(pcs[3]));
				scores.put(pcs[0], records);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			writeScores();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeScores() {
		File f = new File("scores.txt");
		try {
			if (!f.exists())
				f.createNewFile();
			
			PrintWriter writer = new PrintWriter(f);
			Iterator it = scores.entrySet().iterator();
			while(it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				ArrayList<Integer> value = (ArrayList<Integer>)pair.getValue();
				writer.println(pair.getKey() + " " + value.get(0) + " " + value.get(1) + " " + value.get(2));
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void ticTacToeInterface() {
		MainMenu gui = new MainMenu();
		gui.addWindowListener(new WindowListener() {
			@Override
			public void windowClosed(WindowEvent e) {
				// record scores here
				writeScores();
				Iterator it = scores.entrySet().iterator();
				while(it.hasNext()) {
					Map.Entry pair = (Map.Entry)it.next();
					System.out.println(pair.getKey() + " " + pair.getValue());
				}
			}
			@Override
			public void windowActivated(WindowEvent e) {}
			@Override
			public void windowClosing(WindowEvent e) {}
			@Override
			public void windowDeactivated(WindowEvent e) {}
			@Override
			public void windowDeiconified(WindowEvent e) {}
			@Override
			public void windowIconified(WindowEvent e) {}
			@Override
			public void windowOpened(WindowEvent e) {}
		});
	}
}
