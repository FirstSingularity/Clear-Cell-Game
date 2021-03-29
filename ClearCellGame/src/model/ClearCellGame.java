package model;

import java.util.Random;

/* This class must extend Game */
public class ClearCellGame extends Game {

	private Random random = new Random();
	private int strategy;
	private int score = 0;

	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
	}

	public boolean isGameOver() {
		for (int i = 0; i < this.board[0].length; i++) {
			if (!this.board[this.board.length - 1][i].name().equals(BoardCell.EMPTY.name())) {
				return true;
			}
		}
		return false;
	}

	public int getScore() {
		int privacyLeakBlock = this.score;
		return privacyLeakBlock;
	}

	public void nextAnimationStep() {
		if (!isGameOver()) {
			BoardCell[][] temp = new BoardCell[this.board.length][this.board[0].length];

			for (int rows = 0; rows < this.board.length - 1; rows++) {
				for (int cols = 0; cols < this.board[0].length; cols++) {
					temp[rows + 1][cols] = this.board[rows][cols];
				}
			}

			for (int i = 0; i < temp[0].length; i++) {
				temp[0][i] = BoardCell.getNonEmptyRandomBoardCell(this.random);
			}

			this.board = temp;
		}
	}

	public void processCell(int rowIndex, int colIndex) {
		cellClear(rowIndex, colIndex, 0, getBoardCell(rowIndex, colIndex).getName());
		collapseEmpty();
	}

	private void cellClear(int row, int col, int code, String color) {
		this.board[row][col] = BoardCell.EMPTY;
		this.score += 1;

		if (code == 0) {
			if (checkCell(row - 1, col - 1, color)) {
				cellClear(row - 1, col - 1, 1, color);
			}

			if (checkCell(row - 1, col, color)) {
				cellClear(row - 1, col, 2, color);
			}

			if (checkCell(row - 1, col + 1, color)) {
				cellClear(row - 1, col + 1, 3, color);
			}

			if (checkCell(row, col - 1, color)) {
				cellClear(row, col - 1, 4, color);
			}

			if (checkCell(row, col + 1, color)) {
				cellClear(row, col + 1, 5, color);
			}

			if (checkCell(row + 1, col - 1, color)) {
				cellClear(row + 1, col - 1, 6, color);
			}

			if (checkCell(row + 1, col, color)) {
				cellClear(row + 1, col, 7, color);
			}

			if (checkCell(row + 1, col + 1, color)) {
				cellClear(row + 1, col + 1, 8, color);
			}
		}

		switch (code) {
		case 1:
			if (checkCell(row - 1, col - 1, color)) {
				cellClear(row - 1, col - 1, 1, color);
			}
			
			break;
		case 2:
			if (checkCell(row - 1, col, color)) {
				cellClear(row - 1, col, 2, color);
			}
			
			break;
		case 3:
			if (checkCell(row - 1, col + 1, color)) {
				cellClear(row - 1, col + 1, 3, color);
			}
			
			break;
		case 4:
			if (checkCell(row, col - 1, color)) {
				cellClear(row, col - 1, 4, color);
			}
			
			break;
		case 5:
			if (checkCell(row, col + 1, color)) {
				cellClear(row, col + 1, 5, color);
			}
			
			break;
		case 6:
			if (checkCell(row + 1, col - 1, color)) {
				cellClear(row + 1, col - 1, 6, color);
			}
			
			break;
		case 7:
			if (checkCell(row + 1, col, color)) {
				cellClear(row + 1, col, 7, color);
			}
			
			break;
		case 8:
			if (checkCell(row + 1, col + 1, color)) {
				cellClear(row + 1, col + 1, 8, color);
			}
			
			break;
		}
	}

	private boolean checkCell(int r, int c, String color) {
		if (r < this.board.length && r >= 0 && c < this.board[0].length && c >= 0) {
			if (color.equals(getBoardCell(r, c).getName())) {
				return true;
			}
		}
		return false;
	}

	private boolean isEmptyRow(int row) {
		for (int i = 0; i < this.board[0].length; i++) {
			if (!this.board[row][i].name().equals(BoardCell.EMPTY.name())) {
				return false;
			}
		}
		return true;
	}

	private void collapseEmpty() {
		BoardCell[][] temp = new BoardCell[this.board.length][this.board[0].length];
		int emptyLag = 0;

		for (int row = 0; row < temp.length; row++) {
			if (!isEmptyRow(row)) {
				for (int col = 0; col < temp[0].length; col++) {
					temp[row - emptyLag][col] = this.board[row][col];
				}
			} else {
				emptyLag++;
			}
		}

		this.board = temp;

		for (int rows = 0; rows < temp.length; rows++) {
			for (int cols = 0; cols < temp[0].length; cols++) {
				if (getBoardCell(rows, cols) == null) {
					this.board[rows][cols] = BoardCell.EMPTY;
				}
			}
		}
	}
}