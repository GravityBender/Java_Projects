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
        
        Separator sep1 = new Separator();
        sep1.setOrientation(Orientation.HORIZONTAL);
        sep1.setMaxWidth(100);
        sep1.setHalignment(HPos.CENTER);
        
        Button getDef = new Button("Search Word");

        vBtn.getChildren().addAll(addWord, getDef);
        vBtn.getChildren().add(1, sep1);

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
