import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.control.*;

public class App extends Application {

    @Override
    public void start(Stage window) {
        BorderPane layout = new BorderPane();

        HBox initial = new HBox();
        initial.setSpacing(10);

        Label label1 = new Label();
        label1.setText("Enter your height.(in feet)");
        label1.setFont(Font.font("Cambria", 20));

        TextField enterHeight = new TextField();
        enterHeight.getText();

        Button enterButton = new Button("Enter");

        initial.getChildren().addAll(label1, enterHeight, enterButton);
        layout.setTop(initial);

        VBox output = new VBox();
        output.setSpacing(10);

        FlowPane outputH = new FlowPane();
        Label label2 = new Label();
        label2.setText("Your height in feet is: ");
        label2.setFont(Font.font("Cambria", 20));

        Label label3 = new Label();

        outputH.getChildren().addAll(label2, label3);
        output.getChildren().add(outputH);
        layout.setLeft(output);

        enterButton.setOnAction((event) -> {
            label3.setText(enterHeight.getText() + " feet");
            label3.setFont(Font.font("Cambria", 20));
        });

        Scene scene = new Scene(layout);

        window.setTitle("Height Calculator");
        window.setScene(scene);
        window.show();

    }

    public static void main(String[] args) {
        launch(App.class);
    }
}