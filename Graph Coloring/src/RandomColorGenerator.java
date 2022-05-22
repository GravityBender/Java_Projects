import java.util.Arrays;
import java.util.Collections;

public class RandomColorGenerator {
    private String[] colorList;

    public RandomColorGenerator() {
        colorList = new String[] {
                "#b30047;",
                "#66ccff;",
                "#800000;",
                "#00cc44;",
                "#99ccff;",
                "#4700b3;",
                "#ffc266;",
                "#996633;",
                "#003300;",
                "#df9fbf;",
                "#b3b3b3;",
                "#a3c2c2;"
        };
    }

    public String[] getRandomColors() {
        // String[] arr = new String[colors];
        // Random random = new Random();
        // for (int i = 0; i < colors; i++) {
        // arr[i] = colorList[random.nextInt(colorList.length)];
        // System.out.println(arr[i]);
        // }

        // return arr;

        Collections.shuffle(Arrays.asList(colorList));
        return colorList;
    }
}
