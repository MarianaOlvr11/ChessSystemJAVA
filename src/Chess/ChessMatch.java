package Chess;

import Chess.pieces.King;
import Chess.pieces.Rook;
import boardGame.Board;
import boardGame.Position;


public class ChessMatch {  // aqui terá as regras do sistema de Xadrez
    private Board board;

    public ChessMatch() {
        board = new Board(8, 8); // a dimensão do tabuleiro cabe a classe ChessMatch
        initialSetup(); // instancia o tabuleiro com peças
    }

    public ChessPiece[][] getPieces() { // retorna uma matriz de peças de xadrez correspondente a essa partida
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; // programa Main deve só enxergar peça do tipo ChessPiece, variavel matriz temporaria.
        // percorrer a matriz de peças do tabuleiro Board e para cada peça realizar um downcasting para ChessPiece

        for (int i = 0; i < board.getRows(); i++) {
            for(int c = 0; c < board.getColumns(); c++) {
                mat[i][c] = (ChessPiece) board.piece(i, c); // para cada posição i,c do tabuleiro a matriz mat i,c recebe o downcasting para peça de xadrez
            }
        }
        return mat; // retorna a matriz de peças da partida de xadrez
    }

    private void placeNewPieceModelA1(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition()); // passa a posição nas cordenadas do xadrez a1
    }

    private void initialSetup(){  // inicia a partida com as peças no lugar


        placeNewPieceModelA1('c', 2, new Rook(board, Color.WHITE)); // coloca a torre no lugar com a cor branca
        placeNewPieceModelA1('d', 2, new Rook(board, Color.WHITE));
        placeNewPieceModelA1('e', 2, new Rook(board, Color.WHITE));
        placeNewPieceModelA1('e', 1, new Rook(board, Color.WHITE));
        placeNewPieceModelA1('d', 1, new King(board, Color.WHITE));

        placeNewPieceModelA1('c', 7, new Rook(board, Color.BLACK));
        placeNewPieceModelA1('c', 8, new Rook(board, Color.BLACK));
        placeNewPieceModelA1('d', 7, new Rook(board, Color.BLACK));
        placeNewPieceModelA1('e', 7, new Rook(board, Color.BLACK));
        placeNewPieceModelA1('e', 8, new Rook(board, Color.BLACK));
        placeNewPieceModelA1('d', 8, new King(board, Color.BLACK));
    }
}
