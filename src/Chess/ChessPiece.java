package Chess;

import boardGame.Board;
import boardGame.Piece;

public class ChessPiece extends Piece { // peça de Xadrez é subclass de peça

    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() { // só get pois a cor não pode ser modificada
        return color;
    }
}
