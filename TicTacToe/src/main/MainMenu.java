package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 4074500268726614700L;

	private JLabel titleText; // could be changed to an image later
	private JLabel versionText;
	
	private RoundButton startButton;
	private RoundButton aboutButton;
	private RoundButton exitButton;
	
	public MainMenu() {
		createGUI();
	}
	
	private void createGUI() {
		// maybe change to prompt user to confirm
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Container pane = this.getContentPane();
		pane.setBackground(Color.black); // black background
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0; // fill frame vertically
		
		titleText = new JLabel("Tic-Tac-Toe", SwingConstants.CENTER);
		titleText.setFont(new Font("Helvetica", Font.BOLD, 30));
		titleText.setForeground(new Color(255, 255, 255)); // yellow
		c.fill = GridBagConstraints.HORIZONTAL; // max width
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.weighty = 0.5;
		pane.add(titleText, c); // add to pane with these constraints
		
		startButton = new RoundButton("Start");
		startButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weighty = 1.0;
		c.insets = new Insets(0,10,0,10);
		pane.add(startButton, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.weighty = 1.0;
		c.weightx = 1.0;
		pane.add(new PanelThatDrawsX(), c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 1;
		c.weighty = 1.0;
		c.weightx = 1.0;
		pane.add(new PanelThatDrawsX(), c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 1.0;
		c.weightx = 1.0;
		pane.add(new PanelThatDrawsX(), c);
		
		aboutButton = new RoundButton("About");
		aboutButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(0,10,0,10);
		pane.add(aboutButton, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 2;
		c.weighty = 1.0;
		c.weightx = 1.0;
		pane.add(new PanelThatDrawsX(), c);
		
		exitButton = new RoundButton("Exit");
		exitButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 3;
		c.insets = new Insets(0,10,0,10);
		pane.add(exitButton, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.weighty = 1.0;
		c.weightx = 1.0;
		pane.add(new PanelThatDrawsX(), c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.weighty = 1.0;
		c.weightx = 1.0;
		pane.add(new PanelThatDrawsX(), c);
		
		versionText = new JLabel("Version " + Driver.version, SwingConstants.RIGHT);
		versionText.setForeground(Color.white);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 4;
		c.weighty = 0.0;
		c.insets = new Insets(0,0,0,0);
		pane.add(versionText, c);
		
		// exit clicked
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Frame.getFrames().length != 0)
					Frame.getFrames()[0].dispose();
			}
			
		});
		
		pack();
		setVisible(true);
	}
}
