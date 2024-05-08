package Chess;

import Chess.pieces.King;
import Chess.pieces.Rook;
import boardGame.Board;
import boardGame.BoardException;
import boardGame.Piece;
import boardGame.Position;


public class ChessMatch {  // aqui terá as regras e a lógica do sistema de Xadrez

    private Board board; // tabuleiro da partida de xadrez.


    // Construtor que inicializa a partida de xadrez com um tabuleiro de 8x8.
    public ChessMatch() {
        board = new Board(8, 8); // a dimensão do tabuleiro cabe a classe ChessMatch
        initialSetup(); // instancia o tabuleiro com peças
    }

    // Método que retorna uma matriz com as peças de xadrez da partida.
    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; // programa Main deve só enxergar peça do tipo ChessPiece, variavel matriz temporaria.
        // percorrer a matriz de peças do tabuleiro Board e para cada peça realizar um downcasting para ChessPiece

        for (int i = 0; i < board.getRows(); i++) {
            for(int c = 0; c < board.getColumns(); c++) {
                mat[i][c] = (ChessPiece) board.piece(i, c); // para cada posição i,c do tabuleiro a matriz mat i,c recebe o downcasting para peça de xadrez (Converte as peças do tipo genérico 'Piece' para 'ChessPiece'.)
            }
        }
        return mat; // retorna a matriz de peças da partida de xadrez
    }

    // Método que executa um movimento de xadrez, movendo uma peça de uma posição de origem para uma de destino.
    public ChessPiece performChessMove (ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source); // valida a posição de origem existe
        validateTargetPosition(source, target);

        Piece capturePiece = makeMove(source, target); // realiza o movimento e captura uma peça, se houver.

        return (ChessPiece) capturePiece; // retorna a peça capturada, se houver.
    }

    // método privado que realiza o movimento de uma peça no tabuleiro.
    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source); // remove a peça da posição de origem
        Piece capturePiece = board.removePiece(target); // remove possivel peça da posição de destino
        board.placePiece(p, target); // coloca a peça na posição de destino

        return capturePiece; // // retorna a peça capturada.
    }

    // método privado que valida se a posição de origem contém uma peça que pode se mover.
    private void validateSourcePosition (Position position){
        if (!board.thereIsAPiece(position)){ // se não existir peça nessa posição dar exceção
            throw new ChessException("There is no piece on source position"); // lança exceção
        }
        if (!board.piece(position).isThereAnyPossibleMove()){ // se não tiver nenhum movimento possivel
            throw new ChessException("There is no possible moves for the chosen pieces."); // lança exceção
        }
    }

    private void validateTargetPosition(Position source, Position target){ // testa se a posição de destino é um movimento possivel em relação a peça que estiver na posição de origem
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("The chosen piece can't move to target position.");
        }

    }


    // método privado que coloca uma nova peça no tabuleiro nas coordenadas de xadrez (ex: a1).
    private void placeNewPieceModelA1(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition()); // passa a posição nas cordenadas do xadrez a1
    }

    private void initialSetup(){  // configura o tabuleiro com as peças iniciais de uma partida de xadrez.


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

    public boolean [][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }


}
