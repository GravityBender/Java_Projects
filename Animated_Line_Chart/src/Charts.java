import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.util.Duration;

public class Charts {
    private LineChart<Number, Number> mainLineChart;
    private XYChart.Series<Number, Number> mainDataSeries;
    private FileDetails fDetails;
    private int i;

    public Charts() {
        mainDataSeries = new XYChart.Series<>();
        i = -1;
    }

    public LineChart<Number, Number> getMainLineChart() {
        return mainLineChart;
    }

    public void setMainLineChart(LineChart<Number, Number> mainLineChart) {
        this.mainLineChart = mainLineChart;
    }

    public FileDetails getfDetails() {
        return fDetails;
    }

    public void setfDetails(FileDetails fDetails) {
        this.fDetails = fDetails;
    }

    public void animateChart() {

        this.mainLineChart.getData().add(this.mainDataSeries);

//         Timeline chartUpdater = new Timeline();
//         KeyFrame kf = new KeyFrame(Duration.millis(10), event -> {

//             if (getValue() >= fDetails.fileReader().size()) {
//                 chartUpdater.stop();
//             } else {
//                 mainDataSeries.getData().add(new XYChart.Data<>(getValue(), fDetails.fileReader().get(getValue())));
//             }
//         });
//         chartUpdater.getKeyFrames().addAll(kf);
//         chartUpdater.setCycleCount(Timeline.INDEFINITE);
//         chartUpdater.play();
        
        Timeline chartUpdater = new Timeline();

        KeyFrame kf = new KeyFrame(Duration.millis(80), event -> {

            if (geti() >= fDetails.fileReader().size() - 1) {
                chartUpdater.stop();
            } else {
                seti();
                mainDataSeries.getData().add(new XYChart.Data<>(geti(), fDetails.fileReader().get(geti())));
                if (geti() > 1000) {
                    mainDataSeries.getData().remove(0);
                }
                // System.out.println(xAxis.getLowerBound());
                xAxis.setLowerBound(i - 1000);
                xAxis.setUpperBound(i + 1);
            }
        });

        mainLineChart.setAnimated(false);
        chartUpdater.getKeyFrames().addAll(kf);
        chartUpdater.setCycleCount(Timeline.INDEFINITE);
        chartUpdater.play();
    }

    private int getValue() {
        i++;
        return i;
    }

}
