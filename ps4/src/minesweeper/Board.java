/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Specification
 */
public class Board {
    
	// Abstraction function:
	//	represents a sizeX by sizeY Minesweeper board
	//	initially all squares are untouched
	
	// Rep invariant:
	// 	None
	// Safety from rep exposure:
	// only mutable members are board and bombsMap and both are never exposed
	// Thread Safety:
	//	TODO
	
	private SquareState[][] board;
	private Map<String, Boolean> bombsMap;
	
	public Board(int sizeX, int sizeY) {
		board = new SquareState[sizeX][sizeY];
		bombsMap = new HashMap<>();
		for (int i=0; i < sizeX; i++) {
			for (int j=0; j < sizeY; j++) {
				board[i][j] = SquareState.UNTOUCHED;
				if (Math.random() < 0.25) {
					bombsMap.put(sizeX + "," + sizeY, true);
				} else {
					bombsMap.put(sizeX + "," + sizeY, false);
				}
			}
		}
	}
	
	public enum SquareState {
		UNTOUCHED, DUG, FLAGGED
	}
	
	/**
	 * Convert a minesweeper board to a String representation
	 * @return String representation of the board
	 */
	public String look() {
		return null;
	}
	
	/**
	 * Dig square (x, y)
	 * @param x x coordinate of the square, requires 0 <= x < sizeX
	 * @param y y coordinate of the square, requires 0 <= y < sizeY
	 */
	public void dig(int x, int y) {
		board[x][y] = SquareState.DUG;
	}
	
	/**
	 * Set square (x, y) to FLAGGED if it's UNTOUCHED otherwise no effect
	 * @param x x coordinate of the square, requires 0 <= x < sizeX
	 * @param y y coordinate of the square, requires 0 <= y < sizeY
	 */
	public void flag(int x, int y) {
		if (board[x][y] == SquareState.UNTOUCHED) {
			board[x][y] = SquareState.FLAGGED;
		}
	}
	
	/**
	 * Set square (x, y) to UNTOUCHED if it's FLAGGED otherwise no effect
	 * @param x x coordinate of the square, requires 0 <= x < sizeX
	 * @param y y coordinate of the square, requires 0 <= y < sizeY
	 */
	public void deflag(int x, int y) {
		if (board[x][y] == SquareState.FLAGGED) {
			board[x][y] = SquareState.UNTOUCHED;
		}
	}
	
	/**
	 * Get square state
	 * @param x x coordinate of the square, requires 0 <= x < sizeX
	 * @param y y coordinate of the square, requires 0 <= y < sizeY
	 */
	public SquareState getSquareState(int x, int y) {
		return board[x][y];
	}
	
	/**
	 * 
	 * @param x x coordinate of the square, requires 0 <= x < sizeX
	 * @param y y coordinate of the square, requires 0 <= y < sizeY
	 * @return true if there's a bomb at square x,y otherwise returns false
	 */
	public boolean containsBombAt(int x, int y) {
		return bombsMap.get(x + "," + y);
	}
    
}
