package game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.GameLogic.GameState;
import main.Driver;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = -5452925014639836146L;

	private Player p1;
	private Player p2;
	public JButton btnBack = new JButton("Back");
	private TicTacToeBoard board = new TicTacToeBoard();
	private JButton undo = new JButton("Undo Last Move");
	private JLabel lblStatus = new JLabel("Player 1's Move");
	private GameLogic logic = new GameLogic();
	private final JPanel panel = new JPanel();
	private final JButton btnNewGame = new JButton("New Game");
	private final JButton btnExit = new JButton("Exit");
	private final Component rigidArea_1 = Box.createRigidArea(new Dimension(10, 0));
	private final Component rigidArea_2 = Box.createRigidArea(new Dimension(0, 10));
	private final Component rigidArea_3 = Box.createRigidArea(new Dimension(0, 10));	
	private boolean gameEnded = false;
	private final JButton btnToggleNumbers = new JButton("Toggle Numbers");
	
	/**
	 * Create the panel.
	 */
	public GamePanel() {
		setPlayer1("Player 1");
		setPlayer2("Player 2");
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
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
		
		bottomPanel.add(btnToggleNumbers);
		
		bottomPanel.add(undo);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		bottomPanel.add(horizontalGlue);
		
		lblStatus.setAlignmentX(Component.RIGHT_ALIGNMENT);
		bottomPanel.add(lblStatus);
		
		Component rigidArea = Box.createRigidArea(new Dimension(10, 0));
		bottomPanel.add(rigidArea);
		
		setupGameLogic();
	}
	
	public void setPlayer1(String s) {
		p1 = new Player(s, 0, 0, 0);
		if (Driver.scores.get(s) != null) {
			p1 = Driver.scores.get(s);
		}
	}
	
	public void setPlayer2(String s) {
		p2 = new Player(s, 0, 0, 0);
		if (Driver.scores.get(s) != null) {
			p2 = Driver.scores.get(s);
		}
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
			gameEnded = true;
		} else if (logic.getState() == GameState.Player2Win) {
			p2.win();
			p1.lose();
			gameEnded = true;
		} else if (logic.getState() == GameState.PlayerTie) {
			p1.tied();
			p2.tied();
			gameEnded = true;
		}
		
		if (gameEnded)
			showNewGameExit();
	}
	
	private void setupGameLogic() {
		btnToggleNumbers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board.toggleNumbersVisible();
				grabFocus();
			}
		});
		
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (logic.canUndo())
					logic.undo();
				Update();
				grabFocus();
			}
		});
		
		btnNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Reset();
				hideNewGameExit();
				gameEnded = false;
				grabFocus();
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
					if (!gameEnded)
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
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {
				switch(e.getKeyChar()) {
				case '1':
					logic.selectedCell(6);
					if (!gameEnded)
						Update();
					break;
				case '2':
					logic.selectedCell(7);
					if (!gameEnded)
						Update();
					break;
				case '3':
					logic.selectedCell(8);
					if (!gameEnded)
						Update();
					break;
				case '4':
					logic.selectedCell(3);
					if (!gameEnded)
						Update();
					break;
				case '5':
					logic.selectedCell(4);
					if (!gameEnded)
						Update();
					break;
				case '6':
					logic.selectedCell(5);
					if (!gameEnded)
						Update();
					break;
				case '7':
					logic.selectedCell(0);
					if (!gameEnded)
						Update();
					break;
				case '8':
					logic.selectedCell(1);
					if (!gameEnded)
						Update();
					break;
				case '9':
					logic.selectedCell(2);
					if (!gameEnded)
						Update();
					break;
				default:
				}
			}
		});
		
		
		hideNewGameExit();
		Reset();
	}

}
