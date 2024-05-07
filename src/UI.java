import Chess.ChessPiece;

public class UI {
    public static void printBoard(ChessPiece[][] pieces){ // metodo estatico recebe a matriz pieces
        for (int i = 0; i < pieces.length; i++){
            System.out.print((8 - i) + " ");
            for (int c = 0; c < pieces.length; c++){
                printPiece(pieces[i][c]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPiece(ChessPiece piece){
        if (piece == null){
            System.out.print("-"); // sem peça
        } else {
            System.out.print(piece);
        }
        System.out.print(" ");
    }
}
