package Chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;

public abstract class ChessPiece extends Piece { // peça de Xadrez é subclass abstrata de peça

    private Color color;
    private int moveCount;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() { // só get pois a cor não pode ser modificada
        return color;
    }

    public int getMoveCount() { // Método para obter o número de movimentos da peça, que retorna o contador de movimentos.
        return moveCount;
    }

    public void increaseMoveCount(){ // Método para incrementar o contador de movimentos.
        moveCount++;
    }

    public void decreaseMoveCount(){  // Método para decrementar o contador de movimentos.
        moveCount--;
    }

    public ChessPosition getChessPosition(){ // Método para obter a posição da peça no formato de xadrez.
        return ChessPosition.fromPosition(position);
    }

    protected boolean isThereOpponentPiece(Position position) {// se existe uma peça adversária numa dada posição
        ChessPiece pieceP = (ChessPiece) getBoard().piece(position); // variavel que recebe a peça que ta na posição X, downcasting para chessPiece
        return pieceP != null && pieceP.getColor()!= color; // se a peçaP é diferente de nulo e se a peçaP é diferente da cor da minha peça
    }



}
