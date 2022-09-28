import java.util.Random;

/*
 * Coded by Garvit Verma
 */

public class GenerateError {
    private Random rand;
    private int r1;
    private int r2;
    private final int bound;
    private StringBuilder errorString;

    public GenerateError(String encodedString) {
        rand = new Random();
        bound = encodedString.length();

        this.errorString = new StringBuilder(encodedString);
        setR1R2();
    }

    private void setR1R2() {
        r1 = rand.nextInt(2);
        r2 = rand.nextInt(bound);
    }

    public String createError() {
        if (r1 % 2 != 0) {
            if (Character.toString(errorString.charAt(r2)).equals("1")) {
                errorString.replace(r2, r2 + 1, "0");
            } else {
                errorString.replace(r2, r2 + 1, "1");
            }
            return errorString.toString();
        } else {
            return errorString.toString();
        }
    }
}
