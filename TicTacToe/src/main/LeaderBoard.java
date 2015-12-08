package main;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import game.Player;

public class LeaderBoard extends JDialog {
	private static final long serialVersionUID = -7101951949827329526L;
	
	private final JTextPane textPane = new JTextPane();

	public LeaderBoard(JFrame parent) {
		super(parent, "Leaderboard");
		
		setBounds(100, 100, 500, 450);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		
		textPane.setContentType("text/html");
		textPane.setHighlighter(null);
	}
	
	public void updateLeaders() {
		StringBuilder sb = new StringBuilder();
		sb.append("<table style=\"width:100%\"> <tr> <td> <b>Name</b> </td> <td> <b>Wins</b> </td> <td> <b>Losses</b> </td> <td> <b>Ties</b> </td> </tr>\n");
		
		// sort hashmap based on wins
		List<Player> values = new ArrayList<Player>(Driver.scores.values());
		Collections.sort(values, new Comparator<Player>() {
			@Override
			public int compare(Player arg0, Player arg1) {
				return arg1.getWins() - arg0.getWins();
			}
		});
		
		// print scores
		for(Player p : values) {
			sb.append("<tr> <td> ");
			sb.append(p.getName());
			sb.append(" </td> <td> ");
			sb.append(p.getWins());
			sb.append(" </td> <td> ");
			sb.append(p.getLosses());
			sb.append(" </td> <td> ");
			sb.append(p.getTies());
			sb.append(" </td> </tr>\n");
		}
		
		textPane.setText(sb.toString());
	}

}
