package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.GameLogic.GameState;
import java.awt.BorderLayout;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = -5452925014639836146L;

	private Player p1 = new Player("Player 1");
	private Player p2 = new Player("Player 2");
	public JButton btnBack = new JButton("Back");
	private TicTacToeBoard board = new TicTacToeBoard();
	private JButton undo = new JButton("Undo");
	private JLabel lblStatus = new JLabel("Player 1's Move");
	private GameLogic logic = new GameLogic();
	private final JPanel panel = new JPanel();
	private final JButton btnNewGame = new JButton("New Game");
	private final JButton btnExit = new JButton("Exit");
	private final Component rigidArea_1 = Box.createRigidArea(new Dimension(10, 0));
	private final Component rigidArea_2 = Box.createRigidArea(new Dimension(0, 10));
	private final Component rigidArea_3 = Box.createRigidArea(new Dimension(0, 10));
	
	/**
	 * Create the panel.
	 */
	public GamePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		board.setBackground(Color.BLACK);
		add(board);
		
		add(rigidArea_3);
		
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(btnNewGame);
		
		panel.add(rigidArea_1);
		panel.add(btnExit);
		
		add(rigidArea_2);
		
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
	
	private void hideNewGameExit() {
		panel.setVisible(false);
		rigidArea_1.setVisible(false);
		rigidArea_2.setVisible(false);
		rigidArea_3.setVisible(false);
	}
	
	private void showNewGameExit() {
		panel.setVisible(true);
		rigidArea_1.setVisible(true);
		rigidArea_2.setVisible(true);
		rigidArea_3.setVisible(true);
	}
	
	private void Reset() {
		logic.Reset();
		Update();
		board.Update(new Point(-50, -50));
	}
	
	private void Update() {
		board.setBoardState(logic.boardState());
		board.setPlayerIndicator(logic.getPlayerCurrentMove());
		board.setStrikeThrough(logic.getWinCondition());
		lblStatus.setText(logic.getStatus());
		undo.setEnabled(logic.canUndo());
		if (logic.getState() == GameState.Player1Win) {
			p1.win();
			p2.lose();
			showNewGameExit();
		} else if (logic.getState() == GameState.Player2Win) {
			p2.win();
			p1.lose();
			showNewGameExit();
		} else if (logic.getState() == GameState.PlayerTie) {
			p1.tied();
			p2.tied();
			showNewGameExit();
		}
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
		
		btnNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Reset();
				hideNewGameExit();
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Exit the application
				if (Frame.getFrames().length != 0)
					Frame.getFrames()[0].dispose();
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
					board.Update(arg0.getPoint());
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
		
		
		hideNewGameExit();
		Reset();
	}

}
