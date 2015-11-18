package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = -5452925014639836146L;

	public JButton btnBack = new JButton("Back");
	
	/**
	 * Create the panel.
	 */
	public GamePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		TicTacToeBoard board = new TicTacToeBoard();
		board.setBackground(Color.BLACK);
		add(board);
		
		JPanel bottomPanel = new JPanel();
		add(bottomPanel);
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		
		bottomPanel.add(btnBack);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		bottomPanel.add(horizontalGlue_1);
		
		JButton btnUndo = new JButton("Undo");
		bottomPanel.add(btnUndo);
		
		JButton btnRedo = new JButton("Redo");
		bottomPanel.add(btnRedo);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		bottomPanel.add(horizontalGlue);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setAlignmentX(Component.RIGHT_ALIGNMENT);
		bottomPanel.add(lblStatus);
		
		Component rigidArea = Box.createRigidArea(new Dimension(10, 0));
		bottomPanel.add(rigidArea);

	}

}
