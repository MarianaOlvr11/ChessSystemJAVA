import Chess.ChessMatch;
import boardGame.Position;

public class Main {
    public static void main(String[] args) {


        ChessMatch chessMatch = new ChessMatch();


        // fazer função para imprimir peças do tabuleiro
        UI.printBoard(chessMatch.getPieces());

        
    }
}