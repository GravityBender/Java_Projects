import java.util.Scanner;

public class Human extends Board implements Move{

    Scanner scanner = new Scanner(System.in);

    @Override
    public void move(char sign) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            try {
                while (true) {
                    int row = Integer.parseInt(scanner.next());
                    int column = Integer.parseInt(scanner.next());
                    if (arr[row - 1][column - 1] != ' ') {
                        System.out.println("Coordinates already taken!");
                        System.out.print("Enter the coordinates: ");
                    } else {
                        arr[row - 1][column - 1] = sign;
                        return;
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Only numbers allowed!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Enter numbers between 1 and 3!");
            }
        }
    }
}
