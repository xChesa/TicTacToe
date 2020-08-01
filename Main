package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static TicTacToe ttt;
    private static AIPlayer player1AI;
    private static AIPlayer player2AI;
    private static boolean exit;

    public static void main(String[] args) {
        ttt = new TicTacToe();
        exit = false;

        try (Scanner scan = new Scanner(System.in)) {
            while (!exit) {
                player1AI = null;
                player2AI = null;

                String[] params;
                do {
                    System.out.print("Input command: ");
                    params = scan.nextLine().toLowerCase().split(" ");
                } while (!handleCommand(params));

                while (!ttt.isGameOver()) {
                    boolean turnTaken = false;
                    while (!turnTaken) {
                        AIPlayer currentAI = ttt.isXTurn() ? player1AI : player2AI;
                        int inputX;
                        int inputY;

                        if (currentAI != null) {
                            try {
                                Thread.sleep(250);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            int pos = currentAI.getTurn(ttt.getGameboard());
                            inputX = pos % 3 == 0 ? 3 : pos % 3;
                            inputY = inputX == 3 ? pos / 3 : pos / 3 + 1;
                            System.out.println(String.format("Making move level \"%s\"", currentAI.getDifficulty()));
                            turnTaken = ttt.move(inputX, inputY);

                        } else {
                            System.out.print("Enter the coordinates: ");
                            try {
                                inputX = scan.nextInt();
                                inputY = scan.nextInt();
                                turnTaken = ttt.move(inputX, inputY);
                                scan.nextLine();

                            } catch (InputMismatchException e) {
                                System.out.println("You should enter numbers!");
                                scan.next();
                            }
                        }
                    }
                    ttt.printGameboard();
                }
                System.out.println(ttt.getResult());
                System.out.println();
            }
        }
    }

    private static boolean handleCommand(String[] params) {
        if (params.length == 3) {
            if (params[0].equals("start")) {
                switch (params[1]) {
                    case "user":
                        break;
                    case "easy":
                        player1AI = new EasyAIPlayer('X');
                        break;
                    case "medium":
                        player1AI = new MediumAIPlayer('X');
                        break;
                    case "hard":
                        player1AI = new HardAIPlayer('X');
                        break;
                    default:
                        System.out.println("Bad parameters!");
                        return false;
                }

                switch (params[2]) {
                    case "user":
                        break;
                    case "easy":
                        player2AI = new EasyAIPlayer('O');
                        break;
                    case "medium":
                        player2AI = new MediumAIPlayer('O');
                        break;
                    case "hard":
                        player2AI = new HardAIPlayer('O');
                        break;
                    default:
                        System.out.println("Bad parameters!");
                        return false;
                }

                ttt.createBoard();
            }
        } else if (params[0].equals("exit")) {
            exit = true;
            ttt.setGameOver(true);
        } else {
            System.out.println("Bad parameters!");
            return false;
        }

        return true;
    }
}
