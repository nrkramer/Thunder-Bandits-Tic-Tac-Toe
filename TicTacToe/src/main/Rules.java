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

public class Rules extends JFrame {

	private static final long serialVersionUID = 4074500268726614701L;
	private JLabel rules;
	private JLabel rule1;
	private JLabel rule1_1;
	private JLabel rule1_2;
	private JLabel rule2;
	private JLabel rule3;
	private JLabel rule3_1;
	private JLabel rule4;

	public Rules() {
		super("Memorize these and you will be pleased!");
		createRules();
	}

	private void createRules() {

		Container pane2 = this.getContentPane();
		pane2.setBackground(Color.white);
		pane2.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0; // fill frame vertically

		rules = new JLabel("Rules", SwingConstants.LEFT);
		rules.setFont(new Font("Helvetica", Font.PLAIN, 30));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.weighty = 1;
		pane2.add(rules, c);

		rule1 = new JLabel(
				"1.) Use the number pad on the keyboard or use your mouse to make your move,",
				SwingConstants.LEFT);
		rule1.setFont(new Font("Helvetica", Font.PLAIN, 30));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weighty = 1;
		pane2.add(rule1, c);

		rule1_1 = new JLabel(
				"with each place on the board corresponding to the layout of the numberpad.",
				SwingConstants.LEFT);
		rule1_1.setFont(new Font("Helvetica", Font.PLAIN, 30));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weighty = 1;
		pane2.add(rule1_1, c);

		rule1_2 = new JLabel(
				"(i.e. the number 1 would mark the bottom left corner of the board) ",
				SwingConstants.LEFT);
		rule1_2.setFont(new Font("Helvetica", Font.PLAIN, 30));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.weighty = 1;
		pane2.add(rule1_2, c);

		rule2 = new JLabel(
				"2.) Each player will be allowed to redo any move, as long as it's not 'game winning.'",
				SwingConstants.LEFT);
		rule2.setFont(new Font("Helvetica", Font.PLAIN, 30));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.weighty = 1;
		pane2.add(rule2, c);

		rule3 = new JLabel(
				"3.) Once one player has three of their marks in a row he/she wins the game, ",
				SwingConstants.LEFT);
		rule3.setFont(new Font("Helvetica", Font.PLAIN, 30));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.weighty = 1;
		pane2.add(rule3, c);

		rule3_1 = new JLabel(
				"however if there are no more moves to make and nobody has won then the game is a draw.",
				SwingConstants.LEFT);
		rule3_1.setFont(new Font("Helvetica", Font.PLAIN, 30));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		c.weighty = 1;
		pane2.add(rule3_1, c);

		rule4 = new JLabel("*Make sure to have fun and play fair!*",
				SwingConstants.LEFT);
		rule4.setFont(new Font("Helvetica", Font.PLAIN, 30));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 2;
		c.weighty = 1;
		pane2.add(rule4, c);

		pack();
	}
}
