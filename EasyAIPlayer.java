package tictactoe;

public class EasyAIPlayer extends AIPlayer {

    public EasyAIPlayer(char identifier) {
        super(identifier, "easy");
    }

    @Override
    public int getTurn(char[][] gameboard) {
        return getRandomMove(gameboard);
    }
}
