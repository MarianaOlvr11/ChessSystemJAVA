import Chess.ChessException;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;
import boardGame.BoardException;
import boardGame.Position;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();



       while(true) {

          try {
              // fazer função para imprimir peças do tabuleiro
              UI.clearScreen(); // limpa a tela toda vez que o while volta
              UI.printMatch(chessMatch, captured);
              System.out.println();
              System.out.print("Source: ");
              ChessPosition source = UI.readChessPosition(sc);

              boolean[][] possibleMoves = chessMatch.possibleMoves(source);
              UI.clearScreen();
              UI.printBoard(chessMatch.getPieces(), possibleMoves); // imprime movimentos possiveis


              System.out.println();
              System.out.print("Target: ");
              ChessPosition target = UI.readChessPosition(sc);

              ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

              if (capturedPiece != null){ // se executar um movimento e uma peça for capturada ela entra na lista de peças capturadas
                  captured.add(capturedPiece);
              }
          }
          catch (ChessException e){
              System.out.println(e.getMessage());
              sc.nextLine();
          }
          catch (InputMismatchException e){
              System.out.println(e.getMessage());
              sc.nextLine();
          }
          catch (BoardException e){
              System.out.println(e.getMessage());
              sc.nextLine();
          }
       }
        
    }
}