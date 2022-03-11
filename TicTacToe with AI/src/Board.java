public class Board {
    protected static char[][] arr;
    private Move moveX;
    private Move moveY;
    static int depth = 0;

    Board() {

    }

    Board(String cmd1, String cmd2) {
        arr = new char[3][3];

        switch (cmd1) {
            case "easy":
                moveX = new EasyCpu();
                break;
            case "medium":
                moveX = new MediumCpu();
                break;
            case "hard":
                moveX = new HardCpu();
                break;
            case "user":
                moveX = new Human();
                break;
        }

        switch (cmd2) {
            case "easy":
                moveY = new EasyCpu();
                break;
            case "medium":
                moveY = new MediumCpu();
                break;
            case "hard":
                moveY = new HardCpu();
                break;
            case "user":
                moveY = new Human();
                break;
        }

    }

    public void start() {
        initialiseBoard();
        printBoard(arr);

        while (true) {
            moveX.move('X');
            printBoard(arr);
            if (checkWinner()) {
                return;
            }
            moveY.move('O');
            printBoard(arr);
            if (checkWinner()) {
                return;
            }
        }
    }

    private void initialiseBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = ' ';
            }
        }
    }

    private void printBoard(char[][] arr) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            System.out.print(" ");
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    protected boolean checkWinner() {

        boolean winner = false;

        if ((arr[0][0] == arr[0][1] && arr[0][1] == arr[0][2] && arr[0][0] == 'X') || (arr[1][0] == arr[1][1] && arr[1][1] == arr[1][2] && arr[1][0] == 'X') || (arr[2][0] == arr[2][1] && arr[2][1] == arr[2][2] && arr[2][0] == 'X')) {
            System.out.println("X wins");
            winner = true;
        } else if ((arr[0][0] == arr[0][1] && arr[0][1] == arr[0][2] && arr[0][0] == 'O') || (arr[1][0] == arr[1][1] && arr[1][1] == arr[1][2] && arr[1][0] == 'O') || (arr[2][0] == arr[2][1] && arr[2][1] == arr[2][2] && arr[2][0] == 'O')) {
            System.out.println("O wins");
            winner = true;
        } else if ((arr[0][0] == arr[1][0] && arr[1][0] == arr[2][0] && arr[0][0] == 'X') || (arr[0][1] == arr[1][1] && arr[1][1] == arr[2][1] && arr[0][1] == 'X') || (arr[0][2] == arr[1][2] && arr[1][2] == arr[2][2] && arr[0][2] == 'X')) {
            System.out.println("X wins");
            winner = true;
        } else if ((arr[0][0] == arr[1][0] && arr[1][0] == arr[2][0] && arr[0][0] == 'O') || (arr[0][1] == arr[1][1] && arr[1][1] == arr[2][1] && arr[0][1] == 'O') || (arr[0][2] == arr[1][2] && arr[1][2] == arr[2][2] && arr[0][2] == 'O')) {
            System.out.println("O wins");
            winner = true;
        } else if ((arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2] && arr[0][0] == 'X') || (arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0] && arr[0][2] == 'X')) {
            System.out.println("X wins");
            winner = true;
        } else if ((arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2] && arr[0][0] == 'O') || (arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0] && arr[0][2] == 'O')) {
            System.out.println("O wins");
            winner = true;
        } else {
            int full = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (arr[i][j] == 'X' || arr[i][j] == 'O') {
                        full++;
                    }
                }
            }
            if (full == 9) {
                System.out.println("Draw");
                winner = true;
            }
        }

        return winner;
    }

}
