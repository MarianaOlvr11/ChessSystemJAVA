package boardGame;

public abstract class Piece { // Classe abstrata para representar uma peça genérica no jogo de tabuleiro.

    protected Position position;   // A posição atual da peça no tabuleiro.
    private Board board;  // O tabuleiro ao qual a peça pertence.


    // Construtor para inicializar uma peça com o tabuleiro associado.
    // A posição inicial de uma nova peça é nula, pois ela ainda não foi colocada no tabuleiro.
    public Piece (Board board){
        this.board = board;
        position = null; // enfatiza valor nulo
    }


    // Método GET protegido para acessar o tabuleiro da peça.
    // Não há método 'set' pois o tabuleiro não deve ser alterado após a criação da peça.
    // A visibilidade é protegida para evitar que seja acessado fora do pacote de tabuleiro e suas subclasses.
    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();// Método abstrato que deve ser implementado pelas subclasses para determinar os movimentos possíveis da peça.


    // Método que utiliza o método abstrato 'possibleMoves' para verificar se um movimento é possível para a posição dada.
    // Este método age como um 'gancho' para a implementação fornecida pela subclasse.
    public boolean possibleMove (Position position){
        return possibleMoves()[position.getRow()][position.getColumn()]; // chama possivel implementação da subclasse
    }


    // Verifica se há algum movimento possível para a peça.
    // Utiliza a matriz de movimentos possíveis para verificar se há pelo menos um movimento verdadeiro (permitido).
    public boolean isThereAnyPossibleMove(){
        boolean [][] matAuxiliar = possibleMoves();
        for (int i = 0; i < matAuxiliar.length; i++){ // Usa 'length' pois a matriz é quadrada (ex: 8x8).
            for (int c = 0; c < matAuxiliar.length; c++){
                if(matAuxiliar[i][c]){
                    return true; // Retorna verdadeiro se encontrar um movimento possível.
                }
            }
        }
        return false; // Retorna falso se nenhum movimento possível for encontrado.
    }
}
