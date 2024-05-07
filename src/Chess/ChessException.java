package Chess;

import boardGame.BoardException;

public class ChessException extends BoardException { // é subclasse da exceção de tabuleiro
    private static final long serialVersionUID = 1L;

    public ChessException (String msg){
        super(msg);
    }


}
