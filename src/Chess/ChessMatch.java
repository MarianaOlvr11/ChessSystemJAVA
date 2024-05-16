package Chess;

import Chess.pieces.*;
import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ChessMatch {  // aqui terá as regras e a lógica do sistema de Xadrez

    private Board board; // tabuleiro da partida de xadrez.
    private int turn; // turno do jogo
    private Color currentPlayer; // jogador atual de acordo com a cor

    private List<Piece> piecesOnTheBoard = new ArrayList<>(); // lista que contem as peças que estão no tabuleiro

    private List<Piece> capturedPieces = new ArrayList<>(); // lista que contem as peças que não estão no tabuleiro

    private boolean check;

    private boolean checkMate;

    private ChessPiece enPassantVunerable;


    // metodo get do turno
    public int getTurn() {
        return turn;
    }

    // metodo get do jogador atual
    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck (){
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
    }

    public ChessPiece getEnPassantVunerable() {
        return enPassantVunerable;
    }

    // Construtor que inicializa a partida de xadrez com um tabuleiro de 8x8.
    public ChessMatch() {
        board = new Board(8, 8); // a dimensão do tabuleiro cabe a classe ChessMatch

        initialSetup(); // instancia o tabuleiro com peças

        turn = 1; // o turno no inicio da partida vale 1

        currentPlayer = Color.BRANCO; // primeiro jogador é o branco
    }

    // Método que retorna uma matriz com as peças de xadrez da partida.
    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; // programa Main deve só enxergar peça do tipo ChessPiece, variavel matriz temporaria.
        // percorrer a matriz de peças do tabuleiro Board e para cada peça realizar um downcasting para ChessPiece

        for (int i = 0; i < board.getRows(); i++) {
            for (int c = 0; c < board.getColumns(); c++) {
                mat[i][c] = (ChessPiece) board.piece(i, c); // para cada posição i,c do tabuleiro a matriz mat i,c recebe o downcasting para peça de xadrez (Converte as peças do tipo genérico 'Piece' para 'ChessPiece'.)
            }
        }
        return mat; // retorna a matriz de peças da partida de xadrez
    }

    // Método que executa um movimento de xadrez, movendo uma peça de uma posição de origem para uma de destino.
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source); // valida a posição de origem existe
        validateTargetPosition(source, target);

        Piece capturePiece = makeMove(source, target); // realiza o movimento e captura uma peça, se houver.

        if (testCheck(currentPlayer)){ // ve se o jogador se colocou em xeque
            undoMove(source, target, capturePiece);
            throw new ChessException("You can't put yourself in check");
        }

        ChessPiece movedPiece = (ChessPiece)board.piece(target);


        check = (testCheck(opponent(currentPlayer)))? true : false; // Atribui à variável 'check' o valor true se o rei do jogador atual estiver em xeque, caso contrário, false.

        if (testCheckMate(opponent(currentPlayer))) {
            checkMate = true;
        }
        else {
            nextTurn();
        }

        // #special move en passant
        if (movedPiece instanceof Pawn && (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2  )){
            enPassantVunerable = movedPiece;
        }
        else {
            enPassantVunerable = null;
        }

        return (ChessPiece)capturePiece;
    }


    // método privado que valida se a posição de origem contém uma peça que pode se mover.
    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) { // se não existir peça nessa posição dar exceção
            throw new ChessException("There is no piece on source position"); // lança exceção
        }
        if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) { // verifica se a cor inicial é branca
            throw new ChessException("The chosen piece is not yours");

        }
        if (!board.piece(position).isThereAnyPossibleMove()) { // se não tiver nenhum movimento possivel
            throw new ChessException("There is no possible moves for the chosen pieces."); // lança exceção
        }
    }

    private void validateTargetPosition(Position source, Position target) { // testa se a posição de destino é um movimento possivel em relação a peça que estiver na posição de origem
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("The chosen piece can't move to target position");
        }
    }


    private void nextTurn() { // metodo de troca de turno
        turn++;
        currentPlayer = (currentPlayer == Color.BRANCO) ? Color.PRETO : Color.BRANCO; // (condicional terniaria) se o jogador atual for igual a Color.White entao ele mudará para Color.Black e senao muda pra branco denovo
    }


    // método privado que coloca uma nova peça no tabuleiro nas coordenadas de xadrez (ex: a1).
    private void placeNewPieceModelA1(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition()); // passa a posição nas cordenadas do xadrez a1
        piecesOnTheBoard.add(piece); //coloca na lista de peças no tabuleiro
    }

    private void initialSetup() {  // configura o tabuleiro com as peças iniciais de uma partida de xadrez.


         // coloca as peças brancas
        placeNewPieceModelA1('a', 1, new Rook(board, Color.BRANCO));
        placeNewPieceModelA1('e', 1, new King(board, Color.BRANCO, this)); // autoreferencia da partuda de xadrez
        placeNewPieceModelA1('h', 1, new Rook(board, Color.BRANCO));
        placeNewPieceModelA1('a', 2, new Pawn(board, Color.BRANCO,this));
        placeNewPieceModelA1('b', 2, new Pawn(board, Color.BRANCO,this));
        placeNewPieceModelA1('c', 2, new Pawn(board, Color.BRANCO,this));
        placeNewPieceModelA1('d', 2, new Pawn(board, Color.BRANCO,this));
        placeNewPieceModelA1('e', 2, new Pawn(board, Color.BRANCO,this));
        placeNewPieceModelA1('f', 2, new Pawn(board, Color.BRANCO,this));
        placeNewPieceModelA1('g', 2, new Pawn(board, Color.BRANCO,this));
        placeNewPieceModelA1('h', 2, new Pawn(board, Color.BRANCO,this));
        placeNewPieceModelA1('c', 1, new Bishop(board, Color.BRANCO));
        placeNewPieceModelA1('f', 1, new Bishop(board, Color.BRANCO));
        placeNewPieceModelA1('b', 1, new Knight(board, Color.BRANCO));
        placeNewPieceModelA1('g', 1, new Knight(board, Color.BRANCO));
        placeNewPieceModelA1('d', 1, new Queen(board, Color.BRANCO));


        // coloca as peças pretas
        placeNewPieceModelA1('a', 8, new Rook(board, Color.PRETO));
        placeNewPieceModelA1('e', 8, new King(board, Color.PRETO, this)); // autoreferencia da partuda de xadrez
        placeNewPieceModelA1('h', 8, new Rook(board, Color.PRETO));
        placeNewPieceModelA1('a', 7, new Pawn(board, Color.PRETO,this));
        placeNewPieceModelA1('b', 7, new Pawn(board, Color.PRETO,this));
        placeNewPieceModelA1('c', 7, new Pawn(board, Color.PRETO,this));
        placeNewPieceModelA1('d', 7, new Pawn(board, Color.PRETO,this));
        placeNewPieceModelA1('e', 7, new Pawn(board, Color.PRETO,this));
        placeNewPieceModelA1('f', 7, new Pawn(board, Color.PRETO,this));
        placeNewPieceModelA1('g', 7, new Pawn(board, Color.PRETO,this));
        placeNewPieceModelA1('h', 7, new Pawn(board, Color.PRETO,this));
        placeNewPieceModelA1('c', 8, new Bishop(board, Color.PRETO));
        placeNewPieceModelA1('f', 8, new Bishop(board, Color.PRETO));
        placeNewPieceModelA1('b', 8, new Knight(board, Color.PRETO));
        placeNewPieceModelA1('g', 8, new Knight(board, Color.PRETO));
        placeNewPieceModelA1('d', 8, new Queen(board, Color.PRETO));
    }

    private Color opponent(Color color){ // Esta função determina a cor oposta da cor passada como argumento. Se a cor for BRANCA, a função retorna PRETA, e vice-versa.
        return (color == Color.BRANCO)? Color.PRETO : Color.BRANCO; // condicional ternária: se 'color' for igual a Color.WHITE, retorna Color.BLACK, senão retorna Color.WHITE.
    }

    private ChessPiece king(Color color) {
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board");
    }

    private boolean testCheck(Color color){ //  Esta função verifica se o rei da cor especificada está em xeque.
        Position kingPosition = king(color).getChessPosition().toPosition(); // pega a posição do rei

        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList()); // lista de peças oponentes

        for (Piece p : opponentPieces){
            boolean[][] mat = p.possibleMoves();  // Matriz de movimentos possíveis da peça oponente.
            if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {  // Se a posição do rei estiver dentro dos movimentos possíveis da peça oponente, retorna true.
                return true; // teste de xeque deu verdadeiro
            }
        }
        return false; // Se nenhum dos movimentos possíveis das peças oponentes puder capturar o rei retorna falso
    }

    private boolean testCheckMate(Color color){ // Verifica se o rei da cor especificada está em xeque-mate.
        // Primeiro, verifica se o rei da cor especificada está em xeque.
        if (!testCheck(color)){
            // Se o rei não estiver em xeque, então não pode estar em xeque-mate.
            return false;
        }

        // Obtém todas as peças do oponente.
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list){

            boolean[][] mat = p.possibleMoves();// Matriz de movimentos possíveis para cada peça oponente.

            for (int i = 0; i < board.getRows(); i++){ // Verifica todos os movimentos possíveis para cada peça oponente.
                for (int c = 0; c < board.getColumns(); c++){
                    // Se houver um movimento que possa tirar o rei do xeque.
                    if(mat[i][c]){
                        // Armazena a posição de origem e destino do movimento.
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i,c);
                        // Realiza o movimento e captura a peça no destino, se houver.
                        Piece capturedPiece = makeMove(source, target);
                        // Verifica se o rei ainda está em xeque após o movimento.
                        boolean testCheck = testCheck(color);
                        // Desfaz o movimento para restaurar o estado original do tabuleiro.
                        undoMove(source, target, capturedPiece);


                        if (!testCheck){ // Se o rei não estiver mais em xeque após o movimento, então não está em xeque-mate.
                            return false;
                        }
                    }
                }
            }
        }
        return true;// Se nenhum movimento puder tirar o rei do xeque, então está em xeque-mate.
    }



    public boolean[][] possibleMoves(ChessPosition sourcePosition) {  // Calcula os movimentos possíveis para uma peça na posição de origem fornecida.
        Position position = sourcePosition.toPosition(); // Converte a posição de xadrez para uma posição no tabuleiro.
        validateSourcePosition(position);   // Valida se há uma peça na posição de origem.
        return board.piece(position).possibleMoves();   // Retorna os movimentos possíveis para a peça na posição fornecida.
    }

    // método privado que realiza o movimento de uma peça no tabuleiro.
    private Piece makeMove(Position source, Position target) {
        ChessPiece p = (ChessPiece) board.removePiece(source); // remove a peça da posição de origem
        p.increaseMoveCount();
        Piece capturePiece = board.removePiece(target); // remove possivel peça da posição de destino
        board.placePiece(p, target); // coloca a peça na posição de destino

        if (capturePiece != null) { // logica que coloca as peças capturadas na lista de peças fora do tabuleiro

            piecesOnTheBoard.remove(capturePiece);
            capturedPieces.add(capturePiece);
        }
        // #special move castling kingside rook (roque pequeno)
        if(p instanceof King && target.getColumn() == source.getColumn() + 2){
            // Esta condição verifica se a peça é um rei e se o movimento é um roque pequeno (duas casas para a direita).
            Position sourceT = new Position(source.getRow(), source.getColumn() + 3);
            // Define a posição original da torre no roque pequeno.
            Position targetT = new Position(source.getRow(), source.getColumn() + 1);
            // Define a posição final da torre após o roque pequeno.
            ChessPiece rook = (ChessPiece)board.removePiece(sourceT); // retira a torre da onde ela está
            // Remove a torre da sua posição original.
            board.placePiece(rook, targetT);
            // Coloca a torre na posição final após o roque pequeno.
            rook.increaseMoveCount();
            // Incrementa o contador de movimentos da torre, pois ela foi movida.
        }

        // #special move castling queenside rook (roque grande)
        if(p instanceof King && target.getColumn() == source.getColumn() - 2){
            // Esta condição verifica se a peça é um rei e se o movimento é um roque grande (duas casas para a esquerda).
            Position sourceT = new Position(source.getRow(), source.getColumn() - 4);
            // Define a posição original da torre no roque grande.
            Position targetT = new Position(source.getRow(), source.getColumn() - 1);
            // Define a posição final da torre após o roque grande.
            ChessPiece rook = (ChessPiece)board.removePiece(sourceT); // retira a torre da onde ela está
            // Remove a torre da sua posição original.
            board.placePiece(rook, targetT);
            // Coloca a torre na posição final após o roque grande.
            rook.increaseMoveCount();
            // Incrementa o contador de movimentos da torre, pois ela foi movida.
        }
        // #specialmove en passant
        if(p instanceof Pawn){// verifica se a peça é um peão.
            if(source.getColumn() != target.getColumn() && capturePiece == null){ // verifica se o peão moveu-se na diagonal e não capturou uma peça no destino final, o que indica um movimento en passant.

                Position pawnPosition;
                if(p.getColor() == Color.BRANCO){ // se o peão for branco a posição do peão adversário capturado será uma linha abaixo da posição de destino
                    pawnPosition = new Position(target.getRow() + 1, target.getColumn());
                }
                else { // se o peão for preto a posição do peão adversário capturado será uma linha acima da posição de destino.

                    pawnPosition = new Position(target.getRow() - 1, target.getColumn());
                }
                capturePiece = board.removePiece(pawnPosition); // remove o peão adversário da posição
                capturedPieces.add(capturePiece); // adiciona o peão capturado a lista de peças capturadas
                piecesOnTheBoard.remove(capturePiece); // remove o peão capturado da lista de peças no tabuleiro

            }
        }


        return capturePiece; // // retorna a peça capturada.
    }

    // metodo que desfaz o movimento
    private void undoMove(Position source, Position target, Piece capturedPiece) {
        ChessPiece p = (ChessPiece) board.removePiece(target); // tira a peça da posição de destino
        p.decreaseMoveCount();
        board.placePiece(p, source);

        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }

        // desfazer roque pequeno
        if(p instanceof King && target.getColumn() == source.getColumn() + 2){
            // Verifica se a peça é um rei e se o movimento foi um roque pequeno (duas casas para a direita).
            Position sourceT = new Position(source.getRow(), source.getColumn() + 3);
            // Define a posição original da torre antes do roque pequeno.
            Position targetT = new Position(source.getRow(), source.getColumn() + 1);
            // Define a posição onde a torre terminou após o roque pequeno.
            ChessPiece rook = (ChessPiece)board.removePiece(targetT); // retira a torre da posição de destino
            // Remove a torre da posição final após o roque pequeno.
            board.placePiece(rook, sourceT);
            // Coloca a torre de volta à sua posição original.
            rook.decreaseMoveCount();
            // Decrementa o contador de movimentos da torre, pois o movimento de roque foi desfeito.
        }

        // desfaz o roque grande
        if(p instanceof King && target.getColumn() == source.getColumn() - 2){
            // Verifica se a peça é um rei e se o movimento foi um roque grande (duas casas para a esquerda).
            Position sourceT = new Position(source.getRow(), source.getColumn() - 4);
            // Define a posição original da torre antes do roque grande.
            Position targetT = new Position(source.getRow(), source.getColumn() - 1);
            // Define a posição onde a torre terminou após o roque grande.
            ChessPiece rook = (ChessPiece)board.removePiece(targetT); // retira a torre da posição de destino
            // Remove a torre da posição final após o roque grande.
            board.placePiece(rook, sourceT);
            // Coloca a torre de volta à sua posição original.
            rook.decreaseMoveCount();
            // Decrementa o contador de movimentos da torre, pois o movimento de roque foi desfeito.
        }
        // desfaz en passant
        if(p instanceof Pawn){ // verifica se a peça é um peão.

            if(source.getColumn() != target.getColumn() && capturedPiece == enPassantVunerable){  // Verifica se o peão moveu-se na diagonal e se a peça capturada foi marcada como vulnerável ao en passant.

                ChessPiece pawn = (ChessPiece)board.removePiece(target);// Remove o peão da posição de destino, que é onde ele estaria após um en passant.

                Position pawnPosition; // Inicializa a variável para a posição do peão.

                if(p.getColor() == Color.BRANCO){ // se o peão for branco a posição do peão adversário capturado será uma linha abaixo da posição de destino
                    pawnPosition = new Position(3, target.getColumn());// Se o peão for branco, a posição original do peão adversário antes do en passant é uma linha abaixo.
                }
                else { // se o peão for preto a posição do peão adversário capturado será uma linha acima da posição de destino.
                    pawnPosition = new Position(4, target.getColumn());// Se o peão for preto, a posição original do peão adversário antes do en passant é uma linha acima.
                }
                board.placePiece(pawn, pawnPosition);// Coloca o peão de volta à sua posição original antes do en passant.

            }
        }

    }







}
