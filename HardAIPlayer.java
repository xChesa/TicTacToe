package tictactoe;

import java.util.ArrayList;

public class HardAIPlayer extends AIPlayer {

    public HardAIPlayer(char identifier) {
        super(identifier, "hard");
    }

    @Override
    public int getTurn(char[][] gameboard) {
        TicTacToe ttt = new TicTacToe();
        ttt.createInitializedBoard(getBoardArr(gameboard));
        ArrayList<Integer> open = getOpenSpaces(getBoardArr(gameboard));
        char[] boardArr = getBoardArr(gameboard);
        int move = open.get(0);
        int score = Integer.MIN_VALUE;
        for (int i : open) {
            char[] tempArr = boardArr.clone();
            tempArr[i] = ttt.isXTurn() ? 'X' : 'O';
            int tempScore = getBestMove(tempArr);
            if (tempScore > score) {
                score = tempScore;
                move = i;
            }
        }

        move = translateToPos(gameboard.length, move / 3, move % 3);

        return move;
    }

    //@Override
    public int getBestMove(char[] boardArr) {
        TicTacToe ttt = new TicTacToe();
        ttt.createInitializedBoard(boardArr);
        ArrayList<Integer> open = getOpenSpaces(boardArr);
        ArrayList<Integer> scores = new ArrayList<>();

        if (ttt.isGameOver()) {
            String result = ttt.getResult();
            if (result.equals("X wins") ^ identifier == 'O') {
                return 10;
            } else if (result.equals("Draw")) {
                return 0;
            } else {
                return -10;
            }
        }

        for (int i : open) {
            char[] tempArr = boardArr.clone();
            tempArr[i] = ttt.isXTurn() ? 'X' : 'O';

            scores.add(getBestMove(tempArr));
        }

        int score = scores.get(0);

        for (int s : scores) {
            if (ttt.isXTurn() ^ identifier == 'O') {
                score = Math.max(s, score);
            } else {
                score = Math.min(s, score);
            }
        }

        return score;
    }

    private char[] getBoardArr(char[][] gameboard) {
        StringBuilder str = new StringBuilder();
        for (char[] row : gameboard) {
            for (char ch : row) {
                str.append(ch);
            }
        }
        return str.toString().toCharArray();
    }

    private ArrayList<Integer> getOpenSpaces(char[] boardArr) {
        ArrayList<Integer> open = new ArrayList<>();
        for (int i = 0; i < boardArr.length; i++) {
            if (boardArr[i] == '_' || boardArr[i] == ' ') {
                open.add(i);
            }
        }
        return open;
    }
}
