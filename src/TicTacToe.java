import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        boolean playAgain;
        do {
            clearBoard();
            String player = "X";
            boolean gameOver = false;
            while (!gameOver) {
                display();

                int row;
                int col;
                do {
                    row = SafeInput.getRangedInt(in, player + " Enter row (1-3)", 1, 3) - 1;

                    col = SafeInput.getRangedInt(in, player + " Enter column (1-3)", 1, 3) - 1;

                    if (!isValidMove(row, col)) {
                        System.out.println("That position is taken. Try again.");
                    }
                } while (!isValidMove(row, col));

                board[row][col] = player;
                if (isWin(player)) {
                    display();
                    System.out.println(player + " wins!");
                    gameOver = true;
                } else if (isTie()) {
                    display();
                    System.out.println("It's a tie!");
                    gameOver = true;
                } else {
                    if (player.equals("X")) {
                        player = "O";
                    } else {
                        player = "X";
                    }
                }
            }
            playAgain = SafeInput.getYNConfirm(in, "Would you like to play again?");
        } while (playAgain);
        System.out.println("Thanks for playing!");
    }

    private static void clearBoard() { // sets all the board elements to a space
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = " ";
            }
        }
    }

    private static void display() { // shows the Tic Tac Toe game used as part of the prompt for the users move choice
        System.out.println();
        for (int r = 0; r < ROWS; r++) {
            System.out.printf(" %s | %s | %s %n", board[r][0], board[r][1], board[r][2]);

            if (r < ROWS - 1) {
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) { // returns true if there is a space " "
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) { // checks to see if there is a win state on the current board
        return isRowWin(player)
                || isColWin(player)
                || isDiagWin(player);
    }

    private static boolean isRowWin(String player) { // checks if the specified player has won in any row
        for (int r = 0; r < ROWS; r++) {
            if (board[r][0].equals(player) &&
                    board[r][1].equals(player) &&
                    board[r][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) { // checks if the specified player has won in any column
        for (int c = 0; c < COLS; c++) {
            if (board[0][c].equals(player) &&
                    board[1][c].equals(player) &&
                    board[2][c].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagWin(String player) { // checks if the specified player has won in any diagonal
        if (board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    private static boolean isTie() { // checks if the game is a tie
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}