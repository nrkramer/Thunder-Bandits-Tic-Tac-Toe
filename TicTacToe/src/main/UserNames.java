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
	private JPanel panel = new JPanel();
	private JTextField player1;
	private JTextField player2;
	private JLabel label1;
	private JLabel label2;
	private RoundButton confirm1;
	private RoundButton confirm2;
	public String name1;
	public String name2;

	public UserNames() {
	
		final Container pane3 = this.getRootPane();
		pane3.setBackground(Color.black);
		pane3.setLayout(new GridBagLayout());
		final GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0; // fill frame vertically

		label1 = new JLabel("Player 1's name?", SwingConstants.CENTER);
		label1.setFont(new Font("Helvetica", Font.PLAIN, 20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weighty = 1;
		pane3.add(label1, c);

		label2 = new JLabel("Player 2's name?", SwingConstants.CENTER);
		label2.setFont(new Font("Helvetica", Font.PLAIN, 20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weighty = 1;

		player1 = new JTextField(SwingConstants.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weighty = 1;
		pane3.add(player1, c);

		player2 = new JTextField(SwingConstants.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weighty = 1;

		confirm1 = new RoundButton("Confirm");
		confirm1.setFont(new Font("Helvetica", Font.BOLD, 20));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(0, 10, 0, 10);
		pane3.add(confirm1, c);

		confirm2 = new RoundButton("Confirm");
		confirm2.setFont(new Font("Helvetica", Font.BOLD, 20));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(0, 10, 0, 10);

		// when the first confirm gets clicked, things go away and new things
		// come onto the screen
		confirm1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				name1 = player1.getText();
				pane3.add(confirm2, c);
				pane3.add(label2, c);
				pane3.add(player2, c);
				pane3.remove(confirm1);
				pane3.remove(label1);
				pane3.remove(player1);
			}

		});
		
		// when the second confirm gets clicked, everything goes away to make
		// way for the game
		confirm2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				name2 = player2.getText();
				pane3.remove(confirm2);
				pane3.remove(label2);
				pane3.remove(player2);
				
			}

		});

		
	}
}
