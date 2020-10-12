package tictactoe;

public class MediumAIPlayer extends AIPlayer {

    private final char enemy;

    public MediumAIPlayer(char identifier) {
        super(identifier, "medium");
        enemy = identifier == 'X' ? 'O' : 'X';
    }

    @Override
    public int getTurn(char[][] gameboard) {
        int move = -1;
        int len = gameboard.length;
        for (int i = 0; i < len; i++) {
            int prevRow = i - 1 < 0 ? len - 1 : i - 1;
            int nextRow = i + 1 >= len ? 0 : i + 1;
            for (int j = 0; j < len; j++) {
                char current = gameboard[i][j];
                int prevCol = j - 1 < 0 ? len - 1 : j - 1;
                int nextCol = j + 1 >= len ? 0 : j + 1;
                if (current == ' ' || current == '_') {
                    if (gameboard[i][nextCol] == gameboard[i][prevCol]) {
                        if (gameboard[i][nextCol] == identifier || (gameboard[i][nextCol] == enemy && move == -1)) {
                            move = translateToPos(len, i, j);
                        }
                    }

                    if (gameboard[nextRow][j] == gameboard[prevRow][j]) {
                        if (gameboard[nextRow][j] == identifier || (gameboard[nextRow][j] == enemy && move == -1)) {
                            move = translateToPos(len, i, j);
                        }
                    }

                    if (j - i == 0 && gameboard[nextRow][nextCol] == gameboard[prevRow][prevCol]) {
                        if (gameboard[nextRow][nextCol] == identifier ||
                                (gameboard[nextRow][nextCol] == enemy && move == -1)) {
                            move = translateToPos(len, i, j);
                        }
                    }

                    if (i + j + 1 == len && gameboard[prevRow][nextCol] == gameboard[nextRow][prevCol]) {
                        if (gameboard[prevRow][nextCol] == identifier ||
                                (gameboard[prevRow][nextCol] == enemy && move == -1)) {
                            move = translateToPos(len, i, j);
                        }
                    }
                }
            }
        }
        if (move == -1) {
            return getRandomMove(gameboard);
        } else {
            return move;
        }
    }
}
