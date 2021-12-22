import java.util.Stack;
import javafx.application.*;
import javafx.geometry.Insets;
// import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.*;
// import javafx.scene.input.InputMethodEvent;

public class App extends Application {

    public static int defaultFontsize = 40;

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
        MenuItem filMenuItem2 = new MenuItem("New Window");
        MenuItem filMenuItem3 = new MenuItem("Open");
        MenuItem filMenuItem4 = new MenuItem("Save As");

        MenuItem ediMenuItem1 = new MenuItem("Undo");
        MenuItem ediMenuItem2 = new MenuItem("Cut");
        MenuItem ediMenuItem3 = new MenuItem("Copy");
        MenuItem ediMenuItem4 = new MenuItem("Paste");

        MenuItem forMenuItem1 = new MenuItem("Word Wrap");
        MenuItem forMenuItem2 = new MenuItem("Dark Mode");

        MenuItem viMenuItem1 = new MenuItem("Zoom in");
        MenuItem viMenuItem2 = new MenuItem("Zoom out");

        filMenu.getItems().addAll(filMenuItem1, filMenuItem2, filMenuItem3, filMenuItem4);
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
        Label w_wrap = new Label();

        statusbar.getChildren().addAll(no_letters, no_words, w_wrap, no_Col, no_Row);
        pane.setBottom(statusbar);

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

        // input_area.addEventHandler(InputMethodEvent.ANY, new
        // EventHandler<InputMethodEvent>() {
        // @Override
        // public void handle(InputMethodEvent event) {
        // int col = event.getCaretPosition();
        // no_Col.setText("Col: " + col);
        // }
        // });

        filMenuItem1.setOnAction((e) -> {
            input_area.clear();
        });
        
        input_area.textProperty().addListener((change, oldValue, newValue) -> {
            String[] words = newValue.split("\\s|\n");
            Stack<String> undoWords = new Stack<>();
            for (int i = 0; i < words.length; i++) {
                undoWords.push(words[i]);
            }
            ediMenuItem1.setOnAction((e) -> {
                undoWords.pop();
                input_area.clear();
                for (int i = 0; i < undoWords.size(); i++) {
                    input_area.setText(undoWords.remove(i));
                }
                input_area.end();
            });

        });

        forMenuItem1.setOnAction((e) -> {
            if (input_area.isWrapText()) {
                input_area.setWrapText(false);
            } else {
                input_area.setWrapText(true);
                w_wrap.setText("Word Wrap enabled");
            }
        });

        forMenuItem2.setOnAction((e) -> {
            // BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK,
            // CornerRadii.EMPTY, Insets.EMPTY);
            // Background background = new Background(backgroundFill);
            // input_area.setBackground(background);
            Region region = (Region) input_area.lookup(".content");
            region.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        });

        viMenuItem1.setOnAction((e) -> {
            input_area.setFont(Font.font(defaultFontsize + 10));
            defaultFontsize = defaultFontsize + 10;
        });
        viMenuItem2.setOnAction((e) -> {
            input_area.setFont(Font.font(defaultFontsize - 10));
            defaultFontsize = defaultFontsize - 10;
        });

        Scene scene = new Scene(pane);

        window.setTitle("Practise");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(App.class);
    }
}
