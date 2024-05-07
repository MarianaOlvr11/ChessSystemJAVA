package boardGame;

public class Piece {

    protected Position position;
    private Board board;

    public Piece (Board board){  // posição de uma peça recem criada é nula, por isso não se passa como argumento
        this.board = board;
        position = null; // enfatiza valor nulo
    }

    protected Board getBoard() { // não permitir que o tabuleiro seja alterado então sem metodo set e deixar ele como protegido, não é acessivel no pacote de Xadrez só no de Tabuleiro
        return board;  // somente sera acessado no pacote e subclasses, evita erros
    }
}
