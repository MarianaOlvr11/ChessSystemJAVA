package Chess;

import boardGame.Position;

public class ChessPosition {
    private char column;
    private int row;

    public ChessPosition(char column, int row) {

        if (column < 'a' || column > 'h' || row < 1 || row > 8){ // Coluna no tabuleiro de xadrez, de 'a' a 'h' e  linha no tabuleiro de xadrez, de 1 a 8 excederem lança exceção
            throw new ChessException("Error instantianting ChessPosition. Valid values are from a1 to h8. ");
        }

        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    protected Position toPosition(){ // Método que converte ChessPosition para Position (uma classe que usa coordenadas de matriz).
        return new Position(8 - row, column -'a' ); // a lógica de conversão leva em conta a inversão da linha e a conversão da letra da coluna para um índice numérico.
    }

    protected static ChessPosition fromPosition(Position position){ // Método estático que converte Position para ChessPosition.
        return new ChessPosition((char)('a'+ position.getColumn()), 8 - position.getRow()); // a lógica de conversão leva em conta a inversão da linha e a conversão do índice numérico da coluna para uma letra.
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}



