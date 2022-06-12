import java.io.File;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private BorderPane root;
    private FirstScreen firstScreen;
    private FileDetails fDetails1;

    public App() {
        root = new BorderPane();
        firstScreen = new FirstScreen();
        fDetails1 = new FileDetails();
    }

    @Override
    public void start(Stage window) {

        root.setCenter(firstScreen.setFirstScreen());

        firstScreenEvents();

        window.setScene(new Scene(root, 300, 400));
        window.setTitle("Application");
        window.show();

    }

    private void firstScreenEvents() {

        if (firstScreen.getGetAddress().getText().toString() != null) {

            firstScreen.getGuardButton().setOnAction((e) -> {

                fDetails1.setPath(firstScreen.getGetAddress().getText().toString());
                if (!fDetails1.checkIfPresent()) {
                    Alert alert = new Alert(AlertType.ERROR, "No file present!",
                            ButtonType.CLOSE, ButtonType.OK);
                    alert.show();
                } else {
                    secondScreenEvents();
                }

            });

        }
    }

    private void secondScreenEvents() {

        BorderPane tempBorderPane = new BorderPane();

        Charts chartEvents = new Charts();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        yAxis.setLabel("Frequency");

        LineChart<Number, Number> tempLineChart = new LineChart<>(xAxis, yAxis);
        tempLineChart.setTitle("Sine Graph");
        tempLineChart.setCreateSymbols(false);

        chartEvents.setMainLineChart(tempLineChart);
        chartEvents.setfDetails(fDetails1);

        root.setCenter(chartEvents.getMainLineChart());

        chartEvents.animateChart();

        HBox tempHBox = new HBox();
        Button startAgainButton = new Button("Start Again");
        Button stopButton = new Button("Stop");

        startAgainButton.setOnAction((e) -> {
            root.setCenter(firstScreen.setFirstScreen());
            firstScreenEvents();
        });

        stopButton.setOnAction((e) -> {
            root.setCenter(firstScreen.setFirstScreen());
            firstScreenEvents();
        });

        tempHBox.getChildren().addAll(startAgainButton, stopButton);
        tempHBox.setAlignment(Pos.CENTER);
        root.setBottom(tempHBox);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
