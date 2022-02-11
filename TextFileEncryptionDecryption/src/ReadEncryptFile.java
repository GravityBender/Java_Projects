import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ReadEncryptFile implements ReadFromFile {
    private String filePath;
    private Scanner scanner = new Scanner(System.in);

    public void getFilePath() {
        filePath = scanner.nextLine();
    }

    @Override
    public void readFile() {
        Path path = FileSystems.getDefault().getPath(filePath);

        try (BufferedReader fileReader = Files.newBufferedReader(path)) {
            String lines = null;
            while ((lines = fileReader.readLine()) != null) {
                System.out.println(lines);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception occured!");
        }
    }
}
