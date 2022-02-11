import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class DecryptShiftCipher implements ReadFromFile, WriteToFile {

    File fileName;

    public DecryptShiftCipher(String fname) {
        this.fileName = new File(fname + ".txt");
    }

    public void startDecryption() {
        readFile();
    }

    @Override
    public void readFile() {
        Path path = FileSystems.getDefault().getPath(fileName.getPath());

        try (BufferedReader fileReader = Files.newBufferedReader(path)) {
            String lines = null;
            while ((lines = fileReader.readLine()) != null) {
                // System.out.println(lines);
                decrypt(lines);
                writeFile("\\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception occured!");
        }
    }

    private void decrypt(String lines) {
        String[] words = lines.split(" ");
        for (String word : words) {
            // System.out.println(word);
            StringBuffer b = new StringBuffer();
            char[] chars = word.toCharArray();
            for (char c : chars) {
                if (c != ' ' || c != '.' || c != '!' || c != '@') {
                    c = (char) (c - 1);
                    b.append(c);
                }
            }
            word = b.toString();
            writeFile(word);
            // System.out.println(word);
        }
    }

    @Override
    public void writeFile(String word) {
        Path filePath = FileSystems.getDefault().getPath("DecryptedShiftCipher.txt");

        if (filePath.toFile().exists()) {
            try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.WRITE,
                    StandardOpenOption.APPEND)) {
                if (word.equals("\\n")) {
                    // System.out.println();
                    writer.newLine();
                } else {
                    writer.write(word + " ");
                    // System.out.print(word + " ");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error writing to file!");
            }
        } else {
            try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE)) {
                if (word.equals("\\n")) {
                    // System.out.println();
                    writer.newLine();
                } else {
                    writer.write(word + " ");
                    // System.out.print(word + " ");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error writing to file!");
            }
        }
    }
}
