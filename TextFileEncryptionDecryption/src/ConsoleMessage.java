import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleMessage implements WriteToFile {
    private String message;
    private Scanner scanner;
    private List<String> lines;

    public ConsoleMessage() {
        this.lines = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void readFromConsole() {
        while (true) {
            message = scanner.nextLine();
            lines.add(message);
            if (message.equals("")) {
                message = scanner.nextLine();
                if (message.equals("")) {
                    break;
                } else {
                    lines.add(message);
                    continue;
                }
            } else {
                continue;
            }
        }
        for (String word : lines) {
            writeFile(word);
        }
    }

    // @Override
    // public void writeFile() {
    // Path path = FileSystems.getDefault().getPath("MessageToEncrypt.txt");
    // try (BufferedWriter writer = Files.newBufferedWriter(path)) {
    // for (String line : lines) {
    // writer.write(line);
    // writer.newLine();
    // writer.flush();
    // }
    // } catch (IOException e) {
    // e.printStackTrace();
    // System.out.println("Error occurred in writing file.");
    // }
    // }

    @Override
    public void writeFile(String word) {
        Path filePath = FileSystems.getDefault().getPath("MessageToEncrypt.txt");

        if (filePath.toFile().exists()) {
            try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.WRITE,
                    StandardOpenOption.APPEND)) {
                writer.write(word);
                writer.newLine();
                System.out.println(word + " ");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error writing to file!");
            }
        } else {
            try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE)) {
                writer.write(word + " ");
                writer.newLine();
                System.out.println(word + " ");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error writing to file!");
            }
        }
    }

    // For Debugging Processes
    // public void printArrayList() {
    // for (String line : lines) {
    // String[] words = line.split(" ");
    // for (String word : words) {
    // System.out.println(word);
    // }
    // }
    // }
}
