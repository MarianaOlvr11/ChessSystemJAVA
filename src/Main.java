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



       while(!chessMatch.getCheckMate()) { // enquanto a partida não estiver em xeque mate

          try {
              // fazer função para imprimir peças do tabuleiro
              UI.clearScreen(); // limpa a tela toda vez que o while volta
              UI.printMatch(chessMatch, captured);
              System.out.println();
              System.out.print("Que peça será movida: ");
              ChessPosition source = UI.readChessPosition(sc);

              boolean[][] possibleMoves = chessMatch.possibleMoves(source);
              UI.clearScreen();
              UI.printBoard(chessMatch.getPieces(), possibleMoves); // imprime movimentos possiveis


              System.out.println();
              System.out.print("Destino da peça: ");
              ChessPosition target = UI.readChessPosition(sc);

              ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

              if (capturedPiece != null){ // se executar um movimento e uma peça for capturada ela entra na lista de peças capturadas
                  captured.add(capturedPiece);
              }
              if(chessMatch.getPromoted() != null){ // usuario insere qual peça ele quer trocar na promoção
                  System.out.print("Insira o código da peça para promoção ➜ Cavalo(C), Torre(T), Bispo(B), Rainha(R): ");
                  String type = sc.nextLine().toUpperCase();
                  while (!type.equals("B") && !type.equals("C") && !type.equals("T") && !type.equals("R")){
                      System.out.print("VALOR INVÁLIDO\n Insira o código da peça para promoção ➜ Cavalo(C), Torre(T), Bispo(B), Rainha(R): ");
                       type = sc.nextLine().toUpperCase();
                  }
                  chessMatch.replacePromotedPiece(type);
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
       UI.clearScreen();
       UI.printMatch(chessMatch, captured);
        
    }
}