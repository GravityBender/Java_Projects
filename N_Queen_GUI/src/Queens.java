public class Queens {

    private int ROWS;
    private int COLS;
    private int[][] board;

    public Queens(int rows, int cols) {
        this.ROWS = rows;
        this.COLS = cols;
        board = new int[rows][cols];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = 0;
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    private boolean solve(int[][] board, int n) {

        if (n >= COLS) {
            return true;
        }

        for (int i = 0; i < COLS; i++) {

            if (isSafe(board, i, n)) {
                board[i][n] = 1;

                if (solve(board, n + 1) == true)
                    return true;

                board[i][n] = 0;
            }
        }

        return false;
    }

    private boolean isSafe(int[][] board, int row, int col) {
        int i, j;

        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (i = row, j = col; j >= 0 && i < ROWS; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    public boolean solveQueen() {
        if (solve(board, 0) == false) {
            return false;
        }
        return true;
    }
}
