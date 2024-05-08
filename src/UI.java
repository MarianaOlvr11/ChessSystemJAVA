import Chess.ChessPiece;
import Chess.ChessPosition;
import Chess.Color;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    // cores do texto
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // cores do fundo
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


    public static void clearScreen() { // limpa a tela
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public static ChessPosition readChessPosition (Scanner sc){ // lê a posição do usuario
       try { // caso tiver exceção já trata
           String s = sc.nextLine();
           char column = s.charAt(0);  // pega o primeiro caractere de s
           int row = Integer.parseInt(s.substring(1)); // converte o string para inteiro a partir da primeira posição

           return new ChessPosition(column, row);
       }
       catch (RuntimeException e){
           throw new InputMismatchException("Error instantianting ChessPosition. Valid values are from a1 to h8.");
       }
    }



    public static void printBoard(ChessPiece[][] pieces){// metodo estatico recebe a matriz pieces

        for (int i = 0; i < pieces.length; i++){
            System.out.print((8 - i) + " ");
            for (int c = 0; c < pieces.length; c++){
                printPiece(pieces[i][c], false); // indica q nenhuma peça deve ter o fundo colorido
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean [][] possibleMoves){// metodo estatico recebe a matriz pieces

        for (int i = 0; i < pieces.length; i++){
            System.out.print((8 - i) + " ");
            for (int c = 0; c < pieces.length; c++){
                printPiece(pieces[i][c], possibleMoves[i][c]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPiece(ChessPiece piece, boolean background){
        if(background){
            System.out.print(ANSI_BLUE_BACKGROUND); // possibleMoves com fundo azul
        }
        if (piece == null) {
        System.out.print("-" + ANSI_RESET); // se não tiver peça imprima "-"
        }
        else { // se tiver imprima a peça
            if (piece.getColor() == Color.WHITE) { // caso for branca:
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else { // se for preta:
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
}
