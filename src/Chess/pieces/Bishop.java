package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardGame.Board;
import boardGame.Position;

public class Bishop extends ChessPiece {

    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0); // cria uma posição auxiliar para verificar os movimentos possíveis.

        // noroeste
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posição estiver vaga marca a posição como verdadeira
            mat[p.getRow()][p.getColumn()] = true;  // marca a posição como possível.
            p.setValues(p.getRow() - 1, p.getColumn() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // se houver uma peça adversária, marca a posição e para o loop.
            mat[p.getRow()][p.getColumn()] = true;
        }

        // nordeste
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posição estiver vaga marca a posição como verdadeira
            mat[p.getRow()][p.getColumn()] = true;  // marca a posição como possível.
            p.setValues(p.getRow() - 1, p.getColumn() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // se houver uma peça adversária, marca a posição e para o loop.
            mat[p.getRow()][p.getColumn()] = true;
        }

        // sudeste
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posição estiver vaga marca a posição como verdadeira
            mat[p.getRow()][p.getColumn()] = true;  // marca a posição como possível.
            p.setValues(p.getRow() + 1, p.getColumn() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // se houver uma peça adversária, marca a posição e para o loop.
            mat[p.getRow()][p.getColumn()] = true;
        }

        // sudoeste
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posição estiver vaga marca a posição como verdadeira
            mat[p.getRow()][p.getColumn()] = true;  // marca a posição como possível.
            p.setValues(p.getRow() + 1, p.getColumn() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // se houver uma peça adversária, marca a posição e para o loop.
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
    // método que retorna a representação textual do bispo.
    @Override
    public String toString() {
        return "♗";
    }

    }

