import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * App
 */
public class App extends Application {

    private int i = -1;

    @Override
    public void start(Stage window) throws FileNotFoundException {

        window.setScene(new Scene(createContent()));
        window.setTitle("Graph");
        window.show();
    }

    private LineChart<Number, Number> createLineChart() {

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        yAxis.setLabel("Value");

        LineChart<Number, Number> tempLineChart = new LineChart<>(xAxis, yAxis);
        tempLineChart.setTitle("Signal Graph");
        tempLineChart.setCreateSymbols(false);

        return tempLineChart;
    }

    private Parent createContent() throws FileNotFoundException {

        BorderPane root = new BorderPane();

        LineChart<Number, Number> mainLineChart = createLineChart();

        XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();
        List<Double> signal = signalReader();

        EventHandler<ActionEvent> updateChart = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dataSeries.getData().add(new XYChart.Data<>(getValue(), signal.get(getValue())));

                // remove the first point, because that's the left-most.
                // if (getValue() > 100) {
                // dataSeries.getData().remove(0);
                // }
            }
        };

        mainLineChart.getData().add(dataSeries);
        mainLineChart.setAnimated(false);

        // Timeline chartUpdater = new Timeline(new KeyFrame(Duration.millis(50),
        // updateChart));
        // chartUpdater.setCycleCount(Timeline.INDEFINITE);
        // chartUpdater.play();

        Timeline chartUpdater = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(100), event -> {

            if (getValue() >= signal.size()) {
                chartUpdater.stop();
            } else {
                dataSeries.getData().add(new XYChart.Data<>(getValue(), signal.get(getValue())));
            }
        });
        chartUpdater.getKeyFrames().addAll(kf);
        chartUpdater.setCycleCount(Timeline.INDEFINITE);
        chartUpdater.play();

        root.setCenter(mainLineChart);
        return root;
    }

    private int getValue() {
        i++;
        return i;
    }

    private List<Double> signalReader() throws FileNotFoundException {
        List<Double> signal = new ArrayList<>();
        Scanner scanner = new Scanner(new File("resources/input.txt"));
        int i = 0;
        while (scanner.hasNext()) {
            // double data = Double.parseDouble(scanner.next());
            signal.add(Double.parseDouble(scanner.next()));
            i++;
        }

        return signal;

    }

    public static void main(String[] args) throws InterruptedException {

        // Make the main thread sleep for 5 seconds to wait for Arduino connection to be
        // successful.
        // Thread.sleep(5000);

        File checkFile = new File("resources/input.txt");

        // Infinite loop that checks for the creation of the text file sent by arduino
        do {
            // Wait till creation of file.
        } while (!checkFile.exists());

        launch(args);

    }
}