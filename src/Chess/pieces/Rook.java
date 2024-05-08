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
    public boolean[][] possibleMoves() {  // cria uma matriz de booleanos com o mesmo tamanho do tabuleiro.
        boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position pAuxiliar = new Position(0,0);    // cria uma posição auxiliar para verificar os movimentos possíveis.

        // acima da peça(verifica os movimentos possíveis acima da Torre)
        pAuxiliar.setValues(position.getRow() -1 , position.getColumn()); // está acima então é menos 1
        while(getBoard().positionExists(pAuxiliar) && !getBoard().thereIsAPiece(pAuxiliar)){ // enquanto a posição estiver vaga marca a posição como verdadeira
            mat[pAuxiliar.getRow()][pAuxiliar.getColumn()] = true; // // marca a posição como possível.
            pAuxiliar.setRow(pAuxiliar.getRow()-1); // move para a próxima posição acima.

            if (getBoard().positionExists(pAuxiliar) && isThereOpponentPiece(pAuxiliar)){ // // se houver uma peça adversária, marca a posição e para o loop.
                mat[pAuxiliar.getRow()][pAuxiliar.getColumn()] = true;
            }
        }

        // esquerda da peça
        pAuxiliar.setValues(position.getRow() , position.getColumn() - 1); // está acima então é menos 1
        while(getBoard().positionExists(pAuxiliar) && !getBoard().thereIsAPiece(pAuxiliar)){ // enquanto a posição estiver vaga marca a posição como verdadeira
            mat[pAuxiliar.getRow()][pAuxiliar.getColumn()] = true;
            pAuxiliar.setColumn(pAuxiliar.getColumn()-1);

            if (getBoard().positionExists(pAuxiliar) && isThereOpponentPiece(pAuxiliar)){
                mat[pAuxiliar.getRow()][pAuxiliar.getColumn()] = true;
            }
        }

        // direita da peça

        pAuxiliar.setValues(position.getRow() , position.getColumn() + 1); // está acima então é menos 1
        while(getBoard().positionExists(pAuxiliar) && !getBoard().thereIsAPiece(pAuxiliar)){ // enquanto a posição estiver vaga marca a posição como verdadeira
            mat[pAuxiliar.getRow()][pAuxiliar.getColumn()] = true;
            pAuxiliar.setColumn(pAuxiliar.getColumn()+1);

            if (getBoard().positionExists(pAuxiliar) && isThereOpponentPiece(pAuxiliar)){
                mat[pAuxiliar.getRow()][pAuxiliar.getColumn()] = true;
            }
        }

        // baixo da peça
        pAuxiliar.setValues(position.getRow() + 1 , position.getColumn()); // está acima então é menos 1
        while(getBoard().positionExists(pAuxiliar) && !getBoard().thereIsAPiece(pAuxiliar)){ // enquanto a posição estiver vaga marca a posição como verdadeira
            mat[pAuxiliar.getRow()][pAuxiliar.getColumn()] = true;
            pAuxiliar.setRow(pAuxiliar.getRow()+1);

            if (getBoard().positionExists(pAuxiliar) && isThereOpponentPiece(pAuxiliar)){
                mat[pAuxiliar.getRow()][pAuxiliar.getColumn()] = true;
            }
        }


        return mat;
    }
}
