import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
// import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class App extends Application {

    @Override
    public void start(Stage window) {

        BorderPane pane = new BorderPane();

        Menu filMenu = new Menu("File");
        Menu ediMenu = new Menu("Edit");
        Menu formMenu = new Menu("Format");
        Menu viMenu = new Menu("View");
        Menu hMenu = new Menu("Help");

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(filMenu, ediMenu, formMenu, viMenu, hMenu);
        pane.setTop(menuBar);

        MenuItem filMenuItem1 = new MenuItem("New");
        // MenuItem filMenuItem2 = new MenuItem("New Window");
        MenuItem filMenuItem3 = new MenuItem("Open");
        MenuItem filMenuItem4 = new MenuItem("Save As");

        MenuItem ediMenuItem1 = new MenuItem("Undo");
        MenuItem ediMenuItem2 = new MenuItem("Cut");
        MenuItem ediMenuItem3 = new MenuItem("Copy");
        MenuItem ediMenuItem4 = new MenuItem("Paste");

        CheckMenuItem forMenuItem1 = new CheckMenuItem("Word Wrap");
        CheckMenuItem forMenuItem2 = new CheckMenuItem("Dark Mode");

        MenuItem viMenuItem1 = new MenuItem("Zoom in");
        MenuItem viMenuItem2 = new MenuItem("Zoom out");

        filMenu.getItems().addAll(filMenuItem1, filMenuItem3, filMenuItem4);
        ediMenu.getItems().addAll(ediMenuItem1, ediMenuItem2, ediMenuItem3, ediMenuItem4);
        formMenu.getItems().addAll(forMenuItem1, forMenuItem2);
        viMenu.getItems().addAll(viMenuItem1, viMenuItem2);

        TextArea input_area = new TextArea();

        pane.setCenter(input_area);

        HBox statusbar = new HBox();
        statusbar.setSpacing(20);

        Label no_letters = new Label();
        Label no_words = new Label();
        // Label longest_word = new Label();
        Label no_Col = new Label();
        Label no_Row = new Label();
        // Label w_wrap = new Label();
        Label curr_Size = new Label();

        statusbar.getChildren().addAll(no_letters, no_words, no_Col, no_Row, curr_Size);
        pane.setBottom(statusbar);

        // Code for the functionality of the status bar
        input_area.textProperty().addListener((change, oldValue, newValue) -> {
            int characters = newValue.length();
            // int col = characters;
            String[] parts = newValue.split("\\s|\n");
            int words = parts.length;
            // String longest = Arrays.stream(parts)
            // .sorted((s1, s2) -> s2.length() - s1.length())
            // .findFirst()
            // .get();

            no_letters.setText("Number of letter: " + characters);
            no_words.setText("Number of words: " + words);
            // longest_word.setText("Longest word: " + longest);
            // no_Col.setText("Col: " + col);
            String rowCount = input_area.getText();
            String[] lineArray = rowCount.split("\n");
            int row = lineArray.length;
            int col = lineArray[row - 1].length();
            no_Col.setText("Col: " + col);
            no_Row.setText("Row: " + row);
        });

        // Code for 'New' menu item in the File Menu.
        // Clears the text area and sets the title of the window to 'Untitled-1'.
        filMenuItem1.setOnAction((e) -> {
            input_area.clear();
            window.setTitle("Untitled-1");
        });

        // Code to open a text file using FileChooser.
        filMenuItem3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open text file");

                File file = fileChooser.showOpenDialog(window);
                if (file != null) {
                    input_area.setText(openTextFile(file));
                }
                window.setTitle(file.getName());
            }
        });

        // Code to save the text as a text file using FileChooser.
        filMenuItem4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save As");
                fileChooser.getExtensionFilters().addAll(new ExtensionFilter("TXT files (*.txt)", "*.txt"));
                // Opening a dialog box
                File file = fileChooser.showSaveDialog(window);
                if (file != null) {
                    saveTextFile(input_area.getText(), file);
                }
                window.setTitle(file.getName());
            }
        });

        // Code for the 'Undo' menu item in the Edit menu.
        input_area.textProperty().addListener((change, oldValue, newValue) -> {
            String[] words = newValue.split("\\s");
            Stack<String> undoWords = new Stack<>();
            undoWords.removeAllElements();
            for (int i = 0; i < words.length; i++) {
                undoWords.push(words[i]);
            }
            ediMenuItem1.setOnAction((e) -> {
                undoWords.remove(undoWords.size() - 1);
                input_area.clear();
                for (int i = 0; i < undoWords.size(); i++) {
                    input_area.appendText(undoWords.elementAt(i) + " ");
                }
                input_area.end();
            });

        });

        // Code for implementing the 'Word Wrap' option in the Format menu.
        forMenuItem1.setOnAction((e) -> {
            if (input_area.isWrapText() && forMenuItem1.isSelected() == false) {
                input_area.setWrapText(false);
                // w_wrap.setText("Word Wrap disabled");
            } else {
                input_area.setWrapText(true);
                // w_wrap.setText("Word Wrap enabled");
            }
        });

        // Code for the changing the text area to Dark Mode.
        forMenuItem2.setOnAction((e) -> {
            // Region region = (Region) input_area.lookup(".content");
            // region.setBackground(new Background(new BackgroundFill(Color.BLACK,
            // CornerRadii.EMPTY, Insets.EMPTY)));
            if (forMenuItem2.isSelected() == true) {
                BorderPane blackpane = new BorderPane(pane);

                Scene blackScene = new Scene(blackpane);
                blackScene.getStylesheets().add("style.css");

                // menuBar.getStyleClass().add("menuBar");

                input_area.getStyleClass().add("text-area");

                statusbar.getStyleClass().add("statusbar");
                no_letters.getStyleClass().add("common_label");
                no_words.getStyleClass().add("common_label");
                // w_wrap.getStyleClass().add("common_label");
                no_Col.getStyleClass().add("common_label");
                no_Row.getStyleClass().add("common_label");
                curr_Size.getStyleClass().add("common_label");

                window.setScene(blackScene, 600, 400);
            } else {
                BorderPane whitepane = new BorderPane(pane);
                Scene normal = new Scene(whitepane, 600, 400);
                window.setScene(normal);
            }
        });

        // Code for changing the font size of the text in the text area and displaying
        // current font size in the statusbar.
        viMenuItem1.setOnAction((e) -> {
            Font font = input_area.fontProperty().get();
            Double size = font.getSize() + 10;
            input_area.setFont(Font.font(size));
            curr_Size.setText("Curr. Size: " + size);
        });
        viMenuItem2.setOnAction((e) -> {
            Font font = input_area.fontProperty().get();
            Double size = font.getSize() - 10;
            input_area.setFont(Font.font(size));
            curr_Size.setText("Curr. Size: " + size);
        });

        Scene scene = new Scene(pane, 600, 400);

        window.setTitle("Text Editior");
        window.setScene(scene);
        window.show();
    }

    private void saveTextFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String openTextFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuilder.append(text);
            }
        } catch (FileNotFoundException exception) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, exception);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        launch(App.class);
    }
}
