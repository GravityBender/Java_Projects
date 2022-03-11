import java.util.Random;

public class MediumCpu extends Board implements Move {
    @Override
    public void move(char sign) {
        Random random = new Random();
        char oppSign = sign == 'O' ? 'X' : 'O';
        System.out.println("Making move level \"medium\"");
        while (true) {
            int row = random.nextInt(3);
            int column = random.nextInt(3);
            if (arr[row][column] != ' ') {
                continue;
            }
            if ((arr[0][0] == ' ') && ((arr[0][1] == sign && arr[0][2] == sign) || (arr[1][0] == sign && arr[2][0] == sign) || ((arr[1][1] == sign && arr[2][2] == sign)))) {
                arr[0][0] = sign;
            } else if ((arr[0][1] == ' ') && ((arr[0][0] == sign && arr[0][2] == sign) || (arr[1][1] == sign && arr[2][1] == sign))) {
                arr[0][1] = sign;
                return;
            } else if ((arr[0][2] == ' ') && ((arr[0][0] == sign && arr[0][1] == sign) || (arr[1][2] == sign && arr[2][2] == sign) || (arr[2][0] == sign && arr[1][1] == sign))) {
                arr[0][2] = sign;
                return;
            } else if ((arr[1][0] == ' ') && ((arr[0][0] == sign && arr[2][0] == sign) || (arr[1][1] == sign && arr[1][2] == sign))) {
                arr[1][0] = sign;
                return;
            } else if ((arr[1][1] == ' ') && ((arr[1][0] == sign && arr[1][2] == sign) || (arr[0][1] == sign && arr[2][1] == sign) || ((arr[0][0] == sign && arr[2][2] == sign)) || ((arr[0][2] == sign && arr[2][0] == sign)))) {
                arr[1][1] = sign;
                return;
            } else if ((arr[1][2] == ' ') && ((arr[1][0] == sign && arr[1][1] == sign) || (arr[0][2] == sign && arr[2][2] == sign))) {
                arr[1][2] = sign;
                return;
            } else if ((arr[2][0] == ' ') && ((arr[0][0] == sign && arr[1][0] == sign) || (arr[2][1] == sign && arr[2][2] == sign) || (arr[0][2] == sign && arr[1][1] == sign))) {
                arr[2][0] = sign;
                return;
            } else if ((arr[2][1] == ' ') && ((arr[2][0] == sign && arr[2][2] == sign) || (arr[0][1] == sign && arr[1][1] == sign))) {
                arr[2][1] = sign;
                return;
            } else if ((arr[2][2] == ' ') && ((arr[2][0] == sign && arr[2][1] == sign) || (arr[0][2] == sign && arr[1][2] == sign) || (arr[0][0] == sign && arr[1][1] == sign))) {
                arr[2][2] = sign;
                return;
            } else if ((arr[0][0] == ' ') && ((arr[0][1] == oppSign && arr[0][2] == oppSign) || (arr[1][0] == oppSign && arr[2][0] == oppSign) || ((arr[1][1] == oppSign && arr[2][2] == oppSign)))) {
                arr[0][0] = sign;
                return;
            } else if ((arr[0][1] == ' ') && ((arr[0][0] == oppSign && arr[0][2] == oppSign) || (arr[1][1] == oppSign && arr[2][1] == oppSign))) {
                arr[0][1] = sign;
                return;
            } else if ((arr[0][2] == ' ') && ((arr[0][0] == oppSign && arr[0][1] == oppSign) || (arr[1][2] == oppSign && arr[2][2] == oppSign) || (arr[2][0] == oppSign && arr[1][1] == oppSign))) {
                arr[0][2] = sign;
                return;
            } else if ((arr[1][0] == ' ') && ((arr[0][0] == oppSign && arr[2][0] == oppSign) || (arr[1][1] == oppSign && arr[1][2] == oppSign))) {
                arr[1][0] = sign;
                return;
            } else if ((arr[1][1] == ' ') && ((arr[1][0] == oppSign && arr[1][2] == oppSign) || (arr[0][1] == oppSign && arr[2][1] == oppSign) || ((arr[0][0] == oppSign && arr[2][2] == oppSign)) || ((arr[0][2] == oppSign && arr[2][0] == oppSign)))) {
                arr[1][1] = sign;
                return;
            } else if ((arr[1][2] == ' ') && ((arr[1][0] == oppSign && arr[1][1] == oppSign) || (arr[0][2] == oppSign && arr[2][2] == oppSign))) {
                arr[1][2] = sign;
                return;
            } else if ((arr[2][0] == ' ') && ((arr[0][0] == oppSign && arr[1][0] == oppSign) || (arr[2][1] == oppSign && arr[2][2] == oppSign) || (arr[0][2] == oppSign && arr[1][1] == oppSign))) {
                arr[2][0] = sign;
                return;
            } else if ((arr[2][1] == ' ') && ((arr[2][0] == oppSign && arr[2][2] == oppSign) || (arr[0][1] == oppSign && arr[1][1] == oppSign))) {
                arr[2][1] = sign;
                return;
            } else if ((arr[2][2] == ' ') && ((arr[2][0] == oppSign && arr[2][1] == oppSign) || (arr[0][2] == oppSign && arr[1][2] == oppSign) || (arr[0][0] == oppSign && arr[1][1] == oppSign))) {
                arr[2][2] = sign;
                return;
            } else {
                arr[row][column] = sign;
                return;
            }
        }
    }
}
