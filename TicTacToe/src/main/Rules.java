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

public class Rules extends JFrame{
	private static final long serialVersionUID = 4074500268726614700L;
	private JLabel rule1;
	private JLabel rule1_1;
	private JLabel rule1_2;
	private JLabel rule2;
	private JLabel rule3;
	private JLabel rule3_1;
	private JLabel rule4;
	
	public Rules(){
		super("Memorize to get your prize!");
		RulesList();
	}
	
	private void RulesList(){
		//sets up the rules list GUI that is separate from the main game so that players can look at them any time that they wish
		Container pane2 = this.getContentPane();
		pane2.setBackground(Color.black); 
		pane2.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0; 
		
		rule1 = new JLabel("1.) Use the number pad on the keyboard to indicate where you want to make your move,", SwingConstants.LEFT);
		rule1.setFont(new Font("Helvetica", Font.PLAIN, 30));
		rule1.setForeground(new Color(255, 255, 255)); 
		c.fill = GridBagConstraints.HORIZONTAL; 
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.weighty = 0.5;
		pane2.add(rule1, c); 
		
		rule1_1 = new JLabel(" with each number on the keypad corresponding with the area on the board you wish to move.", SwingConstants.LEFT);
		rule1_1.setFont(new Font("Helvetica", Font.PLAIN, 30));
		rule1_1.setForeground(new Color(255, 255, 255)); 
		c.fill = GridBagConstraints.HORIZONTAL; 
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.weighty = 0.5;
		pane2.add(rule1_1, c); 
		
		rule1_2 = new JLabel("(i.e. 1 on the number pad would be the bottom left on the board, 3 bottom right, etc.)", SwingConstants.LEFT);
		rule1_2.setFont(new Font("Helvetica", Font.PLAIN, 30));
		rule1_2.setForeground(new Color(255, 255, 255)); 
		c.fill = GridBagConstraints.HORIZONTAL; 
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.weighty = 0.5;
		pane2.add(rule1_2, c); 
		
		rule2 = new JLabel("2.) Each player is allowed to 'redo' one move, as long as it is not 'game-winning'.", SwingConstants.LEFT);
		rule2.setFont(new Font("Helvetica", Font.PLAIN, 30));
		rule2.setForeground(new Color(255, 255, 255)); 
		c.fill = GridBagConstraints.HORIZONTAL; 
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		c.weighty = 0.5;
		pane2.add(rule2, c); 
		
		rule3 = new JLabel("3.) Once one player has three of his/her markings in a row, they win the game and the game can be reset.", SwingConstants.LEFT);
		rule3.setFont(new Font("Helvetica", Font.PLAIN, 30));
		rule3.setForeground(new Color(255, 255, 255)); 
		c.fill = GridBagConstraints.HORIZONTAL; 
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 3;
		c.weighty = 0.5;
		pane2.add(rule3, c);
		
		rule3_1 = new JLabel("However, if neither player can achieve this then it is a 'cat's game' and neither player wins." , SwingConstants.LEFT);
		rule3_1.setFont(new Font("Helvetica", Font.PLAIN, 30));
		rule3_1.setForeground(new Color(255, 255, 255)); 
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 3;
		c.weighty = 0.5;
		pane2.add(rule3_1, c);
		
		rule4 = new JLabel("*Most importantly, remember to have fun and play fair!*", SwingConstants.LEFT);
		rule4.setFont(new Font("Helvetica", Font.PLAIN, 30));
		rule4.setForeground(new Color(255, 255, 255));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 3;
		c.weighty = 0.5;
		pane2.add(rule4, c);
		
        pack();
	}
}
