import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private Dictionary dictionary;
    private static BorderPane layout = new BorderPane();
    private static SearchView sView;
    private static AddView aView;

    @Override
    public void init() {
        this.dictionary = new Dictionary();
    }

    @Override
    public void start(Stage window) {

        sView = new SearchView(dictionary);
        aView = new AddView(dictionary);

        // TextField searchField = new TextField();

        // TextArea resultArea = new TextArea();
        // resultArea.setEditable(false);
        // resultArea.setMouseTransparent(true);
        // resultArea.setFocusTraversable(false);
        // resultArea.setPrefWidth(300.0);
        // resultArea.setPrefHeight(120.0);

        // AnchorPane.setTopAnchor(searchField, 30.0);
        // AnchorPane.setLeftAnchor(searchField, 290.0);
        // AnchorPane.setTopAnchor(resultArea, 100.0);
        // AnchorPane.setLeftAnchor(resultArea, 220.0);

        layout.setLeft(setButtons());
        layout.setCenter(aView.getView());

        Scene mScene = new Scene(layout, 600, 300);

        window.setTitle("Dictionary");
        window.setScene(mScene);
        window.show();
    }

    private static Node setButtons() {
        VBox vBtn = new VBox();
        vBtn.setSpacing(20);
        vBtn.setPadding(new Insets(10, 10, 10, 10));

        Button addWord = new Button("Add Word");
        Button getDef = new Button("Search Word");

        vBtn.getChildren().addAll(addWord, getDef);

        addWord.setOnAction((e) -> {
            layout.setCenter(aView.getView());
        });

        getDef.setOnAction((e) -> {
            layout.setCenter(sView.getView());
        });

        return vBtn;
    }

    public static void main(String[] args) {
        launch(args);
    }

}