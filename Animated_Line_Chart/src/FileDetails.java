import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileDetails {
    private String path;

    public FileDetails() {

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean checkIfPresent() {
        File file = new File(path);

        return file.exists();
    }

    public List<Double> fileReader() {
        List<Double> signal = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(this.path))) {
            while (scanner.hasNext()) {
                signal.add(Double.parseDouble(scanner.next()));
            }
        } catch (NumberFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return signal;
    }
}
