import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private BorderPane root;
    private double amplitude;
    private double frequency;
    private double size;
    private LineChart<Number, Number> sChart;
    private NumberAxis xAxis;
    private XYChart.Series<Number, Number> dSeries;

    public void init() {
        root = new BorderPane();

        xAxis = new NumberAxis(0, 200, 1);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLowerBound(0);
        yAxis.setAutoRanging(true);

        dSeries = new XYChart.Series<>();
        dSeries.setName("y(t)");

        sChart = new LineChart<>(xAxis, yAxis);
        sChart.setAnimated(false);
        sChart.getData().add(dSeries);
        sChart.setTitle("Sine Wave");
        sChart.setCreateSymbols(false);
    }

    @Override
    public void start(Stage window) {

        init();

        firstScreen();

        window.setScene(new Scene(root, 500, 300));
        window.setTitle("Sine Graph Generator");
        window.show();
    }

    private void firstScreen() {

        FlowPane fPane1 = new FlowPane();
        fPane1.setAlignment(Pos.CENTER);
        Label ampLabel = new Label("Amplitude: ");
        TextField ampField = new TextField();
        ampField.setPromptText("Amplitude");
        fPane1.getChildren().addAll(ampLabel, ampField);

        FlowPane fPane2 = new FlowPane();
        fPane2.setAlignment(Pos.CENTER);
        Label freqLabel = new Label("Frequency: ");
        TextField freqField = new TextField();
        freqField.setPromptText("Frequency");
        fPane2.getChildren().addAll(freqLabel, freqField);

        FlowPane fPane3 = new FlowPane();
        fPane3.setAlignment(Pos.CENTER);
        Label sizeLabel = new Label("Sample Size: ");
        TextField sizeField = new TextField();
        freqField.setPromptText("Sample Size");
        fPane2.getChildren().addAll(sizeLabel, sizeField);

        Button sbmBtn = new Button("Plot Graph");

        VBox vBox1 = new VBox();
        vBox1.setSpacing(10);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.getChildren().addAll(fPane1, fPane2, fPane3, sbmBtn);

        root.setCenter(vBox1);

        sbmBtn.setOnAction((e) -> {
            if ((ampField.getText().toString() != null) && (freqField.getText().toString() != null) && (sizeField
                    .getText().toString() != null)) {
                amplitude = Double.parseDouble(ampField.getText().toString());
                frequency = Double.parseDouble(freqField.getText().toString());
                size = Double.parseDouble(sizeField.getText().toString());

                System.out.println(amplitude + " " + frequency);

                secondScreen();
            } else {
                Alert alert = new Alert(AlertType.ERROR, "Empty field entered!",
                        ButtonType.CLOSE, ButtonType.OK);
                alert.show();
            }
        });

    }

    private void secondScreen() {
        addDataToSeries();

        root.setCenter(sChart);

    }

    private void addDataToSeries() {
        double y;
        for (int i = 0; i < size; i++) {
            y = ((Math.sin((i * 2 * Math.PI) / frequency) * (amplitude)));
            System.out.println(y);
            dSeries.getData().add(new XYChart.Data<Number, Number>(i, y));
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

}
