package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardGame.Board;
import boardGame.Position;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color) { //construtor da classe 'Rook', recebe o tabuleiro e a cor da peça como parâmetros
        super(board, color);
    }

    // método que retorna a representação textual da Torre.
    @Override
    public String toString() {
        return "♖";
    }

    // método que determina os movimentos possíveis da Torre.
    @Override

    public boolean[][] possibleMoves() { // cria uma matriz de booleanos com o mesmo tamanho do tabuleiro.
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0); // cria uma posição auxiliar para verificar os movimentos possíveis.

        // acima da peça(verifica os movimentos possíveis acima da Torre)
        p.setValues(position.getRow() - 1, position.getColumn()); // está acima então é menos 1
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posição estiver vaga marca a posição como verdadeira
            mat[p.getRow()][p.getColumn()] = true;  // marca a posição como possível.
            p.setRow(p.getRow() - 1); // move para a próxima posição acima.
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // se houver uma peça adversária, marca a posição e para o loop.
            mat[p.getRow()][p.getColumn()] = true;
        }

        // esquerda da peça
        p.setValues(position.getRow(), position.getColumn() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // direita da peça
        p.setValues(position.getRow(), position.getColumn() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // abaixo da peça
        p.setValues(position.getRow() + 1, position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }

}
