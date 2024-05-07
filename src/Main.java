import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;
import boardGame.Position;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        ChessMatch chessMatch = new ChessMatch();



       while(true) {
           // fazer função para imprimir peças do tabuleiro
           UI.printBoard(chessMatch.getPieces());
           System.out.println();
           System.out.print("Source: ");
           ChessPosition source = UI.readChessPosition(sc);

           System.out.println();
           System.out.print("Target: ");
           ChessPosition target = UI.readChessPosition(sc);

           ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

       }
        
    }
}