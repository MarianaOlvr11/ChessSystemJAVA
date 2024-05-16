package Chess.pieces;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.Color;
import boardGame.Board;
import boardGame.Position;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "♔";
    }

    private boolean canMove(Position position){
        ChessPiece pAuxiliar = (ChessPiece)getBoard().piece(position);
        return pAuxiliar == null || pAuxiliar.getColor() != getColor();
    }

    private boolean testRookCastling(Position position){ // nessa posiçao tem uma torre e ta apta a jogada roque
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position pos = new Position(0,0);

        // acima
        pos.setValues(position.getRow() - 1, position.getColumn());
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        // abaixo
        pos.setValues(position.getRow() + 1, position.getColumn());
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        // esquerda
        pos.setValues(position.getRow(), position.getColumn() - 1);
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        // direita
        pos.setValues(position.getRow(), position.getColumn() + 1);
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        //diagonal noroeste
        pos.setValues(position.getRow() -1, position.getColumn() - 1);
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        //diagonal nordeste
        pos.setValues(position.getRow() -1, position.getColumn() + 1);
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        //diagonal sudoeste
        pos.setValues(position.getRow() + 1, position.getColumn() - 1);
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        //diagonal sudeste
        pos.setValues(position.getRow() + 1, position.getColumn() + 1);
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        // #special move castiling
        if(getMoveCount() == 0 && !chessMatch.getCheck()){
            // Esta condição verifica se é o primeiro movimento da peça e se o rei não está em xeque.

            // roque pequeno do lado do rei
            Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
            // Define a posição da torre para o roque pequeno.
            if(testRookCastling(posT1)){
                // Verifica se a torre está na posição correta e se pode fazer o roque.
                Position p1 = new Position(position.getRow(), position.getColumn() + 1);
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);
                // Define as posições por onde o rei passará no roque pequeno.
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null){
                    // Verifica se as posições estão livres para que o roque pequeno possa ser realizado.
                    mat [position.getRow()][position.getColumn() + 2] = true;
                    // Marca a posição final do rei após o roque pequeno como possível.
                }
            }

            // roque grande do lado da rainha
            Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
            // Define a posição da torre para o roque grande.
            if(testRookCastling(posT2)){
                // Verifica se a torre está na posição correta e se pode fazer o roque.
                Position p1 = new Position(position.getRow(), position.getColumn() - 1);
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);
                // Define as posições por onde o rei passará no roque grande.
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){
                    // Verifica se as posições estão livres para que o roque grande possa ser realizado.
                    mat [position.getRow()][position.getColumn() - 2] = true;
                    // Marca a posição final do rei após o roque grande como possível.
                }
            }
        }

        return mat;
    }
}
