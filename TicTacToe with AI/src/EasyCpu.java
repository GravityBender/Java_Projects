import java.util.Random;

public class EasyCpu extends Board implements Move {

    @Override
    public void move(char sign) {
        Random random = new Random();

        while (true) {
            int row = random.nextInt(3);
            int column = random.nextInt(3);
            if (arr[row][column] != ' ') {
                continue;
            } else {
                arr[row][column] = sign;
                System.out.println("Making move level \"easy\"");
                return;
            }
        }
    }
}
