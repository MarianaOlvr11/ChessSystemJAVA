package boardGame;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces; // matriz de peças

    public Board (int rows, int columns) {
        if (rows < 1 || columns < 1){  // criar exceção caso não tenha linhas ou colunas
            throw new BoardException ("Error creating board: there must be at least one row and one column.");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece piece(int row, int column){

        if (!positionExists(row, column)){  // testa se a posição da peça existe
            throw new BoardException("Position not on the board");
        }

        return pieces[row][column];
    }

    public Piece piece (Position position){

        if (!positionExists(position)){ // // testa se a posição da peça existe
            throw new BoardException("Position not on the board");
        }

        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position){

        if (thereIsAPiece(position)){
            throw new BoardException("There is alredy a piece on position " + position); // // testa se a posição da peça já não esta ocupada
        }
        pieces[position.getRow()][position.getColumn()] = piece; // metodo pegar peça da matriz e atribuila a uma posição
        piece.position = position;
    }

    public Piece removePiece (Position position){
        if (!positionExists(position)){
            throw new BoardException("Position not on the board.");
        }

        if (piece(position) == null){
            return null;
        }

        Piece auxiliar = piece(position);
        auxiliar.position = null; // retira a peça do tabuleiro deixando ela nula
        pieces[position.getRow()][position.getColumn()] = null; // a posição vai receber nulo ficando vazia

        return auxiliar;
    }

    public boolean positionExists (int row, int column){
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean positionExists (Position position ){
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece (Position position){

        if (!positionExists(position)){ // testa se a posição da peça existe
            throw new BoardException("Position not on the board");
        }

       return piece(position) != null; // se a peça for diferente de nulo significa que tem uma peça nessa posição
    }


}
