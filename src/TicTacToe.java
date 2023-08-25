import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static char current = 'X';

    public static void main(String[] args) {
        initial();
        board();
        
        boolean gameOver = false;
        while (!gameOver) {
            play();
            board();
            if (win() || draw()) {
                gameOver = true;
            }
            current = (current == 'X') ? 'O' : 'X';
        }
    }

    private static void initial() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void board() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void play() {
        Scanner in = new Scanner(System.in);
        int row, col;

        do {
            System.out.println("Player " + current + ", enter row (0-2) and column (0-2) separated by space:");
            row = in.nextInt();
            col = in.nextInt();
        } while (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != '-');

        board[row][col] = current;
        in.close();
    }

    private static boolean win() {
        return rows() || columns() || diagonal();
    }

    private static boolean rows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == current && board[i][1] == current && board[i][2] == current) {
                System.out.println("Player " + current + " wins!");
                return true;
            }
        }
        return false;
    }

    private static boolean columns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == current && board[1][i] == current && board[2][i] == current) {
                System.out.println("Player " + current + " wins!");
                return true;
            }
        }
        return false;
    }

    private static boolean diagonal() {
        if ((board[0][0] == current && board[1][1] == current && board[2][2] == current) ||
            (board[0][2] == current && board[1][1] == current && board[2][0] == current)) {
            System.out.println("Player " + current + " wins!");
            return true;
        }
        return false;
    }

    private static boolean draw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        System.out.println("It's a draw!");
        return true;
    }
}
