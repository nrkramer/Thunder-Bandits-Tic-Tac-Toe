package game;

public class GameLogic {
	
	public static enum GameState {
		Player1,
		Player2,
		Player1Win,
		Player2Win,
		PlayerTie
	}
	
	private GameState state = GameState.Player1;
	private boolean[] player1Cells = new boolean[9];
	private boolean[] player2Cells = new boolean[9];
	private int winCondition = -1;
	private int lastCell = -1;
	private String status = "";
	private boolean undoAvailable = false;
	
	public GameLogic() {
		status = "Player 1's move.";
	}
	
	public void selectedCell(int c) {
		if ((state != GameState.Player1Win) && (state != GameState.Player2Win) && (state != GameState.PlayerTie)) {
			if (!player1Cells[c] && !player2Cells[c]) {
				if (state == GameState.Player1) {
						player1Cells[c] = true;
						state = GameState.Player2;
						status = "Player 2's move.";
						lastCell = c;
						undoAvailable = true;
				} else if (state == GameState.Player2) {
						player2Cells[c] = true;
						state = GameState.Player1;
						status = "Player 1's move.";
						lastCell = c;
						undoAvailable = true;
				}
				calculateWin();
			} else {
				status = "Someone has already moved there!";
			}
		}
	}
	
	private void calculateWin() {
		if (isWin(player1Cells)) {
			state = GameState.Player1Win;
			status = "Player 1 Wins!";
			undoAvailable = false;
		} else if (isWin(player2Cells)) {
			state = GameState.Player2Win;
			status = "Player 2 Wins!";
			undoAvailable = false;
		} else {
			if (isTie(player1Cells, player2Cells)) {
				state = GameState.PlayerTie;
				status = "Tie game.";
				undoAvailable = false;
			}
		}
	}
	
	private boolean isTie(boolean[] p1Cells, boolean[] p2Cells) {
		boolean tie = true;
		for(int i = 0; i < p1Cells.length; i++) {
			if (!(p1Cells[i] || p2Cells[i])) {
				tie = false;
				i = p1Cells.length;
				undoAvailable = true;
			} else {
				undoAvailable = false;
			}
		}
		
		return tie;
	}
	
	private boolean isWin(boolean[] cells) {
		// 8 win conditions
		if (cells[0] && cells[1] && cells[2]) {// first row
			winCondition = 0;
			return true;
		} else if (cells[0] && cells[3] && cells[6]) { // first col
			winCondition = 3;
			return true;
		} else if (cells[0] && cells[4] && cells[8]) {// diagonal top left to bottom right
			winCondition = 6;
			return true;
		} else if (cells[2] && cells[4] && cells[6]) {// diagonal top right to bottom left
			winCondition = 7;
			return true;
		} else if (cells[1] && cells[4] && cells[7]) {// second col
			winCondition = 4;
			return true;
		} else if (cells[2] && cells[5] && cells[8]) {// third col
			winCondition = 5;
			return true;
		} else if (cells[3] && cells[4] && cells[5]) {// second row
			winCondition = 1;
			return true;
		} else if (cells[6] && cells[7] && cells[8]) {// third row
			winCondition = 2;
			return true;
		} else {
			winCondition = -1;
			return false;
		}
	}
	
	public int getWinCondition() {
		return winCondition;
	}
	
	public String getPlayerCurrentMove() {
		if (state == GameState.Player1)
			return "X";
		else if (state == GameState.Player2) {
			return "O";
		} else {
			return "";
		}
	}
	
	public void undo() {
		if (undoAvailable) {
			if (state == GameState.Player1) {
				player2Cells[lastCell] = false;
				state = GameState.Player2;
				status = "Player 2's move.";
				undoAvailable = false;
			} else {
				player1Cells[lastCell] = false;
				state = GameState.Player1;
				status = "Player 1's move.";
				undoAvailable = false;
			}
		}
	}
	
	public boolean canUndo() {
		return undoAvailable;
	}
	
	public String getStatus() {
		return status;
	}
	
	public GameState getState() {
		return state;
	}
	
	public int[] boardState() {
		int[] state = new int[9];
		for (int i = 0; i < 9; i++)
		{
			state[i] = 0;
			if (player1Cells[i])
				state[i] = 1;
			if (player2Cells[i])
				state[i] = 2;
		}
		
		return state;
	}
	
	public void Reset() {
		player1Cells = new boolean[9];
		player2Cells = new boolean[9];
		undoAvailable = false;
		status = "Player 1's move.";
		lastCell = -1;
		winCondition = -1;
		state = GameState.Player1;
	}
}
