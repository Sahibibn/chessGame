package main;

import pieces.Piece;

public class Move {
    int oldCol;
    int oldRow;
    int newCol;
    int newRow;

    Piece piece;
    Piece capture;

    public Move(Board board, Piece piece, int newCol, int newRow) {
        this.oldCol = piece.col;  // Fix: Use the piece's current column
        this.oldRow = piece.row;  // Fix: Use the piece's current row
        this.newCol = newCol;
        this.newRow = newRow;

        this.piece = piece;
        this.capture = board.getPiece(newCol, newRow);
    }
}
