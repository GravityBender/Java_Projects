public class HardCpu extends Board implements Move {

    private char sign;
    private char oppSign;

    @Override
    public void move(char sign) {
        this.oppSign = sign == 'O' ? 'X' : 'O';
        this.sign = sign;

        System.out.println("Making move level \"hard\"");

        Move nextMove = findBestMove();
        arr[nextMove.row][nextMove.column] = sign;
        depth++;
    }

    static class Move {

        int row;
        int column;

        Move() {
            row = -1;
            column = -1;
        }
    }

    int evaluateGrid() {

        if ((arr[0][0] == sign && arr[0][1] == sign && arr[0][2] == sign) ||
                (arr[1][0] == sign && arr[1][1] == sign && arr[1][2] == sign) ||
                (arr[2][0] == sign && arr[2][1] == sign && arr[2][2] == sign) ||
                (arr[0][0] == sign && arr[1][0] == sign && arr[2][0] == sign) ||
                (arr[0][1] == sign && arr[1][1] == sign && arr[2][1] == sign) ||
                (arr[0][2] == sign && arr[1][2] == sign && arr[2][2] == sign) ||
                (arr[0][0] == sign && arr[1][1] == sign && arr[2][2] == sign) ||
                (arr[0][2] == sign && arr[1][1] == sign && arr[2][0] == sign)) {
            return 10;
        } else if ((arr[0][0] == oppSign && arr[0][1] == oppSign && arr[0][2] == oppSign) ||
                (arr[1][0] == oppSign && arr[1][1] == oppSign && arr[1][2] == oppSign) ||
                (arr[2][0] == oppSign && arr[2][1] == oppSign && arr[2][2] == oppSign) ||
                (arr[0][0] == oppSign && arr[1][0] == oppSign && arr[2][0] == oppSign) ||
                (arr[0][1] == oppSign && arr[1][1] == oppSign && arr[2][1] == oppSign) ||
                (arr[0][2] == oppSign && arr[1][2] == oppSign && arr[2][2] == oppSign) ||
                (arr[0][0] == oppSign && arr[1][1] == oppSign && arr[2][2] == oppSign) ||
                (arr[0][2] == oppSign && arr[1][1] == oppSign && arr[2][0] == oppSign)) {
            return -10;
        }
        return 0;
    }

    int minimax(boolean isMax) {

        int score = evaluateGrid();

        if (score == 10 || score == -10) {
            return score;
        }

        if (!isMoveLeft()) {
            return 0;
        }

        int best = -1;

        if (isMax) {

            best = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (arr[i][j] == ' ') {

                        arr[i][j] = sign;

                        best = Math.max(best, minimax(false));

                        arr[i][j] = ' ';
                    }
                }
            }
        }

        if (!isMax) {

            best = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (arr[i][j] == ' ') {

                        arr[i][j] = this.oppSign;

                        best = Math.min(best, minimax(true));

                        arr[i][j] = ' ';
                    }
                }
            }
        }
        return best;
    }

    Move findBestMove() {

        int bestValue = Integer.MIN_VALUE;
        Move bestMove = new Move();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == ' ') {

                    arr[i][j] = this.sign;

                    int moveValue = minimax(false);

                    arr[i][j] = ' ';

                    if (moveValue > bestValue) {
                        bestMove.row = i;
                        bestMove.column = j;
                        bestValue = moveValue;
                    }
                }
            }
        }
        return bestMove;
    }


    boolean isMoveLeft() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == ' ') {
                    return true;
                }
            }
        }
        return false;
    }
}