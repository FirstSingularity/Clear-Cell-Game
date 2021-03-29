package model;

/**
 * This class represents the logic of a game where a board is updated on each
 * step of the game animation. The board can also be updated by selecting a
 * board cell.
 * 
 * @author Dept of Computer Science, UMCP
 */

public abstract class Game {
	protected BoardCell[][] board;

	/**
	 * Defines a board with BoardCell.EMPTY cells.
	 * 
	 * @param maxRows
	 * @param maxCols
	 */
	public Game(int maxRows, int maxCols) {
		this.board = new BoardCell[maxRows][maxCols];

		for (int rows = 0; rows < maxRows; rows++) {
			for (int cols = 0; cols < maxCols; cols++) {
				this.board[rows][cols] = BoardCell.EMPTY;
			}
		}
	}

	public int getMaxRows() {
		int privacyLeakBlock = this.board.length;
		return privacyLeakBlock;
	}

	public int getMaxCols() {
		int privacyLeakBlock = this.board[0].length;
		return privacyLeakBlock;
	}

	public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell) {
		this.board[rowIndex][colIndex] = boardCell;
	}

	public BoardCell getBoardCell(int rowIndex, int colIndex) {
		return (this.board[rowIndex][colIndex]);
	}

	/**
	 * Initializes row with the specified color.
	 * 
	 * @param rowIndex
	 * @param cell
	 */
	public void setRowWithColor(int rowIndex, BoardCell cell) {
		for (int i = 0; i < this.board[rowIndex].length; i++) {
			this.board[rowIndex][i] = cell;
		}
	}

	/**
	 * Initializes column with the specified color.
	 * 
	 * @param colIndex
	 * @param cell
	 */
	public void setColWithColor(int colIndex, BoardCell cell) {
		for (int i = 0; i < this.board.length; i++) {
			this.board[i][colIndex] = cell;
		}
	}

	/**
	 * Initializes the board with the specified color.
	 * 
	 * @param cell
	 */
	public void setBoardWithColor(BoardCell cell) {
		for (int rows = 0; rows < this.board.length; rows++) {
			for (int cols = 0; cols < this.board[0].length; cols++) {
				this.board[rows][cols] = cell;
			}
		}
	}

	public abstract boolean isGameOver();

	public abstract int getScore();

	/**
	 * Advances the animation one step.
	 */
	public abstract void nextAnimationStep();

	/**
	 * Adjust the board state according to the current board state and the selected
	 * cell.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 */
	public abstract void processCell(int rowIndex, int colIndex);
}