import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class App extends Application {

    @Override
    public void start(Stage window) {
        Image image = new Image(getClass().getResourceAsStream("labels.jpg"));

        Label label1 = new Label("Hi there!", new ImageView(image));
        label1.setTextFill(Color.GREENYELLOW);
        label1.setFont(new Font("Cambria", 35));
        label1.setOnMouseEntered((MouseEvent e) -> {
            label1.setScaleX(1.2);
            label1.setScaleY(1.2);
        });
        label1.setOnMouseExited((MouseEvent e) -> {
            label1.setScaleX(1);
            label1.setScaleY(1);
        });

        Label label2 = new Label();
        label2.setText("My first JavaFx GUI Application!");
        label2.setFont(Font.font("Cambria", 30));
        label2.setOnMouseEntered((MouseEvent e) -> {
            label2.setScaleX(1.2);
            label2.setScaleY(1.2);
        });
        label2.setOnMouseExited((MouseEvent e) -> {
            label2.setScaleX(1);
            label2.setScaleY(1);
        });

        FlowPane getComponent = new FlowPane();
        getComponent.getChildren().add(label1);
        getComponent.getChildren().add(label2);

        Scene scene = new Scene(getComponent);

        window.setTitle("Practise");
        window.setScene(scene);

        window.show();

    }

    public static void main(String[] args) {
        launch(App.class);
    }
}