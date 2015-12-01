package game;

public class GameLogic {
	
	public static enum GameState {
		Player1,
		Player2,
		Player1Win,
		Player2Win
	}
	
	private GameState state = GameState.Player1;
	private boolean[] player1Cells = new boolean[9];
	private boolean[] player2Cells = new boolean[9];
	private int lastCell = -1;
	private String status = "";
	private boolean undoAvailable = false;
	
	public GameLogic() {}
	
	public void selectedCell(int c) {
		if ((state != GameState.Player1Win) && (state != GameState.Player2Win)) {
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
		}
	}
	
	private boolean isWin(boolean[] cells) {
		// 8 win conditions
		if (cells[0] && cells[1] && cells[2]) // first row
			return true;
		else if (cells[0] && cells[3] && cells[6]) // first col
			return true;
		else if (cells[0] && cells[4] && cells[8]) // diagonal top left to bottom right
			return true;
		else if (cells[2] && cells[4] && cells[6]) // diagonal top right to bottom left
			return true;
		else if (cells[1] && cells[4] && cells[7]) // second col
			return true;
		else if (cells[2] && cells[5] && cells[8]) // third col
			return true;
		else if (cells[3] && cells[4] && cells[5]) // second row
			return true;
		else if (cells[6] && cells[7] && cells[8]) // third row
			return true;
		else return false;
	}
	
	public String getPlayerCurrentMove() {
		if (state == GameState.Player1)
			return "P1";
		else if (state == GameState.Player2) {
			return "P2";
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
}
