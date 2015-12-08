package main;

import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextPane;

public class Rules extends JDialog {

	private static final long serialVersionUID = 4074500268726614701L;
	private JTextPane textPane;

	public Rules(JFrame frame) {
		super(frame, "Rules");
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setHighlighter(null);
		try {
			File f = new File("rules.html");
			URL url = null;
			if (f.exists()) {
				url = f.toURI().toURL();
				textPane.setPage(url);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Container pane = getContentPane();
		pane.add(textPane);
		Dimension size = new Dimension(600, 350);
		pane.setSize(size);
		pane.setPreferredSize(size);
		pane.setMaximumSize(size);
		pane.setMinimumSize(size);
		pack();
	}
}
