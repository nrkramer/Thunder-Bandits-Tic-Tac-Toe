package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.GamePanel;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 4074500268726614700L;
	
	private JPanel mainMenuPanel;
	private GamePanel gamePanel;
	private RoundButton startButton;
	private RoundButton rulesButton;
	private RoundButton exitButton;
	private RoundButton leaderBoard;
	private UserNames userNames;
	private Rules rules;
	private LeaderBoard lb;
	
	public MainMenu() {
		super("Tic-Tac-Toe");
		setMinimumSize(new Dimension(600, 600));
		createGUI();
	}
	
	private void createGUI() {
		// maybe change to prompt user to confirm
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		mainMenuPanel = new JPanel();
		mainMenuPanel.setBackground(Color.white);
		mainMenuPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0; // fill frame vertically
		
		JLabel titleText = new JLabel("Tic-Tac-Toe", SwingConstants.CENTER);
		titleText.setFont(new Font("Helvetica", Font.BOLD, 30));
		titleText.setForeground(new Color(100, 100, 255));
		c.fill = GridBagConstraints.HORIZONTAL; // max width
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.weighty = 0.5;
		mainMenuPanel.add(titleText, c); // add to pane with these constraints
		
		JLabel titleText2 = new JLabel("By Thunder Bandits", SwingConstants.CENTER);
		titleText2.setFont(new Font("Helvetica", Font.BOLD, 20));
		titleText2.setForeground(new Color(255, 100, 100));
		c.fill = GridBagConstraints.HORIZONTAL; // max width
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.weighty = 0.5;
		mainMenuPanel.add(titleText2, c); // add to pane with these constraints
		
		startButton = new RoundButton("Start");
		startButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		startButton.setFocusable(false);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weighty = 1.0;
		c.insets = new Insets(0,10,0,10);
		mainMenuPanel.add(startButton, c);
		
		rulesButton = new RoundButton("Rules");
		rulesButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		rulesButton.setFocusable(false);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(0,10,0,10);
		mainMenuPanel.add(rulesButton, c);
		
		exitButton = new RoundButton("Exit");
		exitButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		exitButton.setFocusable(false);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 4;
		c.insets = new Insets(0,10,0,10);
		mainMenuPanel.add(exitButton, c);
		
		leaderBoard = new RoundButton("Leaders");
		leaderBoard.setFocusable(false);
		leaderBoard.setFont(new Font("Helvetica", Font.BOLD, 20));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weighty = 0.2;
		c.insets = new Insets(0,10,0,10);
		mainMenuPanel.add(leaderBoard, c);
		
		JLabel versionText = new JLabel("Version " + Driver.version, SwingConstants.RIGHT);
		versionText.setForeground(Color.white);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 5;
		c.weighty = 0.0;
		c.insets = new Insets(0,0,0,0);
		mainMenuPanel.add(versionText, c);
		
		gamePanel = new GamePanel();
		userNames = new UserNames();
		
		gamePanel.btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setContentPane(mainMenuPanel);
				validate();
			}
		});
		
		rules = new Rules(this);
		rules.setModal(true);
		
		lb = new LeaderBoard(this);
		lb.setModal(true);
		
		setContentPane(mainMenuPanel);
		
		// start clicked 
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setContentPane(gamePanel); // change this to change where start goes
				validate();
				gamePanel.setFocusable(true);
				gamePanel.grabFocus();
			}
		});
		
		// exit clicked
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Frame.getFrames().length != 0)
					Frame.getFrames()[0].dispose();
			}
			
		});
		
		//rules clicked
		rulesButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
				rules.setVisible(true);
			}
		});
		
		leaderBoard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				lb.updateLeaders();
				lb.setVisible(true);
			}		
		});
		
		pack();
		setVisible(true);
	}
}