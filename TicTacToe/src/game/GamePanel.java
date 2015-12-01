package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = -5452925014639836146L;

	public JButton btnBack = new JButton("Back");
	private TicTacToeBoard board = new TicTacToeBoard();
	private JButton undo = new JButton("Undo");
	private JLabel lblStatus = new JLabel("Player 1's Move");
	private GameLogic logic = new GameLogic();
	
	/**
	 * Create the panel.
	 */
	public GamePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		board.setBackground(Color.BLACK);
		add(board);
		
		JPanel bottomPanel = new JPanel();
		add(bottomPanel);
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		
		bottomPanel.add(btnBack);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		bottomPanel.add(horizontalGlue_1);
		
		bottomPanel.add(undo);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		bottomPanel.add(horizontalGlue);
		
		lblStatus.setAlignmentX(Component.RIGHT_ALIGNMENT);
		bottomPanel.add(lblStatus);
		
		Component rigidArea = Box.createRigidArea(new Dimension(10, 0));
		bottomPanel.add(rigidArea);
		
		setupGameLogic();
	}
	
	private void Update() {
		board.setBoardState(logic.boardState());
		board.setPlayerIndicator(logic.getPlayerCurrentMove());
		lblStatus.setText(logic.getStatus());
		undo.setEnabled(logic.canUndo());
	}
	
	private void setupGameLogic() {
		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (logic.canUndo())
					logic.undo();
				Update();
			}
			
		});
		
		board.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int cell = -1;
				cell = board.getCellFromCoords(arg0.getX(), arg0.getY());
				
				if (cell != -1) {
					// player clicked a cell
					logic.selectedCell(cell);
					Update();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
	}

}
