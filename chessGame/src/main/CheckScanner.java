package main;

import pieces.Piece;

public class CheckScanner {

    Board board;

    public CheckScanner(Board board) {
        this.board = board;
    }

    public boolean isKingChecked(Move move) {
        Piece King = board.findKing(move.piece.isWhite);
        assert King != null;

        int kingCol = King.col;
        int kingRow = King.row;

        // If the moving piece is the King, update its position
        if (board.selectedPiece != null && board.selectedPiece.name.equals("King")) {
            kingCol = move.newCol;
            kingRow = move.newRow;
        }

        return hitByRook(King, kingCol, kingRow, 0, 1) || // Up
                hitByRook(King, kingCol, kingRow, 1, 0) || // Right
                hitByRook(King, kingCol, kingRow, 0, -1) || // Down
                hitByRook(King, kingCol, kingRow, -1, 0) || // Left

                hitByBishop(King, kingCol, kingRow, -1, -1) || // Up left
                hitByBishop(King, kingCol, kingRow, 1, -1) || // Up right
                hitByBishop(King, kingCol, kingRow, 1, 1) || // Down right
                hitByBishop(King, kingCol, kingRow, -1, 1) || // Down left

                hitByKnight(King, kingCol, kingRow) ||
                hitByPawn(King, kingCol, kingRow) ||
                hitByKing(King, kingCol, kingRow);
    }

    private boolean hitByRook(Piece King, int KingCol, int KingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) {
            Piece piece = board.getPiece(KingCol + (i * colVal), KingRow + (i * rowVal));
            if (piece != null && piece != board.selectedPiece) {
                return !board.sameTeam(piece, King) && (piece.name.equals("Queen") || piece.name.equals("Rook"));
            }
        }
        return false;
    }

    private boolean hitByBishop(Piece King, int KingCol, int KingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) {
            Piece piece = board.getPiece(KingCol + (i * colVal), KingRow + (i * rowVal));
            if (piece != null && piece != board.selectedPiece) {
                return !board.sameTeam(piece, King) && (piece.name.equals("Queen") || piece.name.equals("Bishop"));
            }
        }
        return false;
    }

    private boolean hitByKnight(Piece King, int KingCol, int KingRow) {
        return checkKnight(board.getPiece(KingCol - 1, KingRow - 2), King) ||
                checkKnight(board.getPiece(KingCol + 1, KingRow - 2), King) ||
                checkKnight(board.getPiece(KingCol + 2, KingRow - 1), King) ||
                checkKnight(board.getPiece(KingCol + 2, KingRow + 1), King) ||
                checkKnight(board.getPiece(KingCol + 1, KingRow + 2), King) ||
                checkKnight(board.getPiece(KingCol - 1, KingRow + 2), King) ||
                checkKnight(board.getPiece(KingCol - 2, KingRow + 1), King) ||
                checkKnight(board.getPiece(KingCol - 2, KingRow - 1), King);
    }

    private boolean checkKnight(Piece p, Piece K) {
        return p != null && !board.sameTeam(p, K) && p.name.equals("Knight");
    }

    private boolean hitByKing(Piece King, int KingCol, int KingRow) {
        return checkKing(board.getPiece(KingCol - 1, KingRow - 1), King) ||
                checkKing(board.getPiece(KingCol + 1, KingRow - 1), King) ||
                checkKing(board.getPiece(KingCol, KingRow - 1), King) ||
                checkKing(board.getPiece(KingCol - 1, KingRow), King) ||
                checkKing(board.getPiece(KingCol + 1, KingRow), King) ||
                checkKing(board.getPiece(KingCol - 1, KingRow + 1), King) ||
                checkKing(board.getPiece(KingCol + 1, KingRow + 1), King) ||
                checkKing(board.getPiece(KingCol, KingRow + 1), King);
    }

    private boolean checkKing(Piece p, Piece K) {
        return p != null && !board.sameTeam(p, K) && p.name.equals("King");
    }

    private boolean hitByPawn(Piece King, int KingCol, int KingRow) {
        int colorVal = King.isWhite ? -1 : 1;
        return checkPawn(board.getPiece(KingCol + 1, KingRow + colorVal), King) ||
                checkPawn(board.getPiece(KingCol - 1, KingRow + colorVal), King);
    }

    private boolean checkPawn(Piece p, Piece K) {
        return p != null && !board.sameTeam(p, K) && p.name.equals("Pawn");
    }

    public boolean isGameOver(Piece King){
        for(Piece piece : board.pieceList){
            if(board.sameTeam(piece, King)){
                board.selectedPiece = piece == King ? King : null;
                for(int row =0; row<board.rows; row++){
                    for(int col = 0; col<board.cols; col++){
                        Move move = new Move(board, piece,col, row);
                        if(board.isValidMove(move)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
