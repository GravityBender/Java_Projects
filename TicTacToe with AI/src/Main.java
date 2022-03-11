import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printMenu();
    }

    private static void printMenu() {
        String param1 = "";
        String param2 = "";
        while (true) {
            System.out.print("Enter command: > ");
            String cmd = scanner.nextLine();

            if (cmd.equals("exit")) {
                return;
            } else {
                String[] cmds = cmd.split(" ");
                if (!cmds[0].equals("start") || cmds.length != 3) {
                    System.out.println("Bad parameters!");
                    continue;
                }
                if ((cmds[1].equals("easy") || cmds[1].equals("medium") || cmds[1].equals("hard")) && (cmds[2].equals("easy") || cmds[2].equals("medium") || cmds[2].equals("hard"))) {
                    param1 = cmds[1];
                    param2 = cmds[2];
                } else if (cmds[1].equals("user") && (cmds[2].equals("easy") || cmds[2].equals("medium") || cmds[2].equals("hard"))) {
                    param1 = cmds[1];
                    param2 = cmds[2];
                } else if ((cmds[1].equals("easy") || cmds[1].equals("medium") || cmds[1].equals("hard")) && cmds[2].equals("user")) {
                    param1 = cmds[1];
                    param2 = cmds[2];
                } else if (cmds[1].equals("user") && cmds[2].equals("user")) {
                    param1 = cmds[1];
                    param2 = cmds[2];
                } else {
                    System.out.println("Bad parameters!");
                }
            }
            Board newBoard = new Board(param1, param2);
            newBoard.start();
        }
    }
}
