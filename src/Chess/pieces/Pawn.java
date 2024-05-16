package Chess.pieces;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.Color;
import boardGame.Board;
import boardGame.Position;

public class Pawn extends ChessPiece {

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public boolean[][] possibleMoves() {

        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0); // cria uma posição auxiliar para verificar os movimentos possíveis.

        if (getColor() == Color.BRANCO) { // se for branco peao se move para cima
            p.setValues(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() - 2, position.getColumn());
            Position p2 = new Position(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // come a peça na diagonal
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // come a peça na diagonal
                mat[p.getRow()][p.getColumn()] = true;
            }
            // en passant white
            if (position.getRow() == 3) { // na quinta linha do tabuleiro
                Position left = new Position(position.getRow(), position.getColumn() - 1); // esquerda
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVunerable()) { // testa se a posição da esquerda existe, se tem uma peça que é oponente e se essa peça está vulneravel a tomar en passant
                    mat[left.getRow() - 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1); // agora da direita
                if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVunerable()) { // testa se a posição da esquerda existe, se tem uma peça que é oponente e se essa peça está vulneravel a tomar en passant
                    mat[right.getRow() - 1][right.getColumn()] = true;
                }
            }
        } else { // no preto é pra baixo ent é linha + 1
            p.setValues(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() + 2, position.getColumn());
            Position p2 = new Position(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            // en passant black
            if (position.getRow() == 4) { // na quarta linha do tabuleiro
                Position left = new Position(position.getRow(), position.getColumn() - 1); // esquerda
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVunerable()) { // testa se a posição da esquerda existe, se tem uma peça que é oponente e se essa peça está vulneravel a tomar en passant
                    mat[left.getRow() + 1][left.getColumn()] = true; // peao preto anda pra baixo ent +1
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1); // agora da direita
                if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVunerable()) { // testa se a posição da esquerda existe, se tem uma peça que é oponente e se essa peça está vulneravel a tomar en passant
                    mat[right.getRow() + 1][right.getColumn()] = true;
                }
            }

        }
        return mat;
    }
    // método que retorna a representação textual do peao.
    @Override
    public String toString () {
        return "♙";
    }
}
