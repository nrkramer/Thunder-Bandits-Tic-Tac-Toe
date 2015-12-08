package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UserNames extends JPanel {

	private static final long serialVersionUID = 4074500268726614707L;
	private JPanel UserNames;
	private JTextField player1;
	private JTextField player2;
	private JLabel label1;
	private JLabel label2;
	private RoundButton confirm1;
	private RoundButton confirm2;
	public String name1;
	public String name2;

	public UserNames() {
	
		UserNames = new JPanel();
		UserNames.setBackground(Color.white);
		UserNames.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0; // fill frame vertically

		label1 = new JLabel("Player 1's name?", SwingConstants.CENTER);
		label1.setFont(new Font("Helvetica", Font.PLAIN, 20));
		label1.setForeground(new Color(255, 100, 100));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weighty = 1;
		UserNames.add(label1, c);

		label2 = new JLabel("Player 2's name?", SwingConstants.CENTER);
		label2.setFont(new Font("Helvetica", Font.PLAIN, 20));
		label2.setForeground(new Color(255, 100, 100));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weighty = 1;
		UserNames.add(label2, c);

		player1 = new JTextField(SwingConstants.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weighty = 1;
		UserNames.add(player1, c);

		player2 = new JTextField(SwingConstants.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weighty = 1;
		UserNames.add(player2, c);

		confirm1 = new RoundButton("Confirm");
		confirm1.setFont(new Font("Helvetica", Font.BOLD, 20));
		confirm1.setFocusable(false);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(0, 10, 0, 10);
		UserNames.add(confirm1, c);

		confirm2 = new RoundButton("Confirm");
		confirm2.setFont(new Font("Helvetica", Font.BOLD, 20));
		confirm2.setFocusable(false);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(0, 10, 0, 10);
		UserNames.add(confirm2, c);
		

		// when the first confirm gets clicked, things go away and new things
		// come onto the screen
		confirm1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				name1 = player1.getText();
				confirm1.setVisible(false);
				label1.setVisible(false);
				player1.setVisible(false);
				confirm2.setVisible(true);
				label2.setVisible(true);
				player2.setVisible(true);
			}

		});
		
		// when the second confirm gets clicked, everything goes away to make
		// way for the game
		confirm2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				name2 = player2.getText();
				confirm2.setVisible(false);
				label2.setVisible(false);
				player2.setVisible(false);
				
			}

		});
		makeThingsDisappear();
	}
	
	private void makeThingsDisappear(){
		confirm2.setVisible(false);
		label2.setVisible(false);
		player2.setVisible(false);
		confirm1.setVisible(true);
		label1.setVisible(true);
		player1.setVisible(true);
		UserNames.setVisible(true);
	}
}
