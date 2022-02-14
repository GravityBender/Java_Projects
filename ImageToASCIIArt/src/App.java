import java.util.Scanner;

/**
 * App
 */
public class App {

    public static void main(String[] args) {
        ImgToArt obj = new ImgToArt();
        System.out.println("Enter the absolute path of the file");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        obj.convertToAscii(filePath);

        scanner.close();
    }
}