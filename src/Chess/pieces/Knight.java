package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardGame.Board;
import boardGame.Position;

public class Knight extends ChessPiece { // move no esquema dois um

    public Knight(Board board, Color color) {
        super(board, color);
    }

    private boolean canMove(Position position){
        ChessPiece pAuxiliar = (ChessPiece)getBoard().piece(position);
        return pAuxiliar == null || pAuxiliar.getColor() != getColor();
    }


    @Override
    public boolean[][] possibleMoves() {
        boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position pos = new Position(0,0);

        // acima
        pos.setValues(position.getRow() - 1, position.getColumn() - 2);
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        // abaixo
        pos.setValues(position.getRow() - 2, position.getColumn() - 1);
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        // esquerda
        pos.setValues(position.getRow() - 2, position.getColumn() + 1);
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        // direita
        pos.setValues(position.getRow() - 1, position.getColumn() + 2);
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        //diagonal noroeste
        pos.setValues(position.getRow() + 1, position.getColumn() + 2);
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        //diagonal nordeste
        pos.setValues(position.getRow() + 2, position.getColumn() + 1);
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        //diagonal sudoeste
        pos.setValues(position.getRow() + 2, position.getColumn() - 1);
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        //diagonal sudeste
        pos.setValues(position.getRow() + 1, position.getColumn() - 2);
        if(getBoard().positionExists(pos) && canMove(pos)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        return mat;
    }

    @Override
    public String toString() {
        return "♘";
    }

    }


