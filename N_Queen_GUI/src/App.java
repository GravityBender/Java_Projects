import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Coded by Garvit Verma
 */
public class App extends Application {

    private static int ROWS = 8;
    private static int COLS = 8;
    private static VBox vBox;
    private static GridPane chessGrid;
    private static Button startButton;
    private static Queens queen;

    private static Pane createSol() {
        chessGrid = new GridPane();
        chessGrid.getStylesheets().addAll("sol.css");
        for (int i = 0; i < ROWS; i++) {
            Node[] nodeList = new Node[COLS];
            for (int j = 0; j < COLS; j++) {
                Button btnNode = new Button();
                btnNode.setPrefSize(90, 90);
                nodeList[j] = btnNode;

                if (queen.getBoard()[i][j] == 1) {
                    btnNode.setText("Q");
                }

                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        btnNode.getStyleClass().add("white-btn");
                    } else {
                        btnNode.getStyleClass().add("black-btn");
                    }
                } else {
                    if (j % 2 == 0) {
                        btnNode.getStyleClass().add("black-btn");
                    } else {
                        btnNode.getStyleClass().add("white-btn");
                    }
                }
            }
            chessGrid.addRow(i, nodeList);
        }
        return chessGrid;
    }

    private static VBox setNView() {
        VBox nVBox = new VBox(8);
        nVBox.setPadding(new Insets(10));
        nVBox.setAlignment(Pos.CENTER);
        nVBox.getChildren().addAll(createSol());

        return nVBox;
    }

    private static void generateAlert() {
        Alert alertBox = new Alert(AlertType.INFORMATION);
        alertBox.setTitle("Alert!");
        alertBox.setHeaderText("ALERT!");
        alertBox.setContentText("No solution present!");
        alertBox.showAndWait();
    }

    private static Pane makeGrid() {

        chessGrid = new GridPane();
        chessGrid.getStylesheets().add("style.css");
        for (int i = 0; i < ROWS; i++) {
            Node[] nodeList = new Node[COLS];
            for (int j = 0; j < COLS; j++) {
                Button btnNode = new Button();
                btnNode.setPrefSize(90, 90);
                nodeList[j] = btnNode;
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        btnNode.getStyleClass().add("white-btn");
                    } else {
                        btnNode.getStyleClass().add("black-btn");
                    }
                } else {
                    if (j % 2 == 0) {
                        btnNode.getStyleClass().add("black-btn");
                    } else {
                        btnNode.getStyleClass().add("white-btn");
                    }
                }
            }
            chessGrid.addRow(i, nodeList);
        }

        return chessGrid;
    }

    private static VBox setVView() {
        vBox = new VBox(8);
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);

        startButton = new Button("Click to Start");
        startButton.setPrefWidth(400);
        startButton.setStyle("-fx-font-size: 2em");
        vBox.getChildren().addAll(makeGrid(), startButton);

        queen = new Queens(ROWS, COLS);

        return vBox;
    }

    @Override
    public void start(Stage window) {

        HBox hBox = new HBox();
        Text ques = new Text("Enter a number");
        TextField textField = new TextField();
        Button sbBtn = new Button("Enter");
        hBox.getChildren().addAll(ques, textField, sbBtn);
        hBox.setAlignment(Pos.CENTER);

        sbBtn.setOnAction((e) -> {
            String nString = textField.getText();
            int n = Integer.parseInt(nString);

            ROWS = n;
            COLS = n;

            vBox = setVView();

            startButton.setOnAction((em) -> {
                if (queen.solveQueen() == false) {
                    generateAlert();
                } else {
                    VBox nVBox = setNView();
                    Scene answer = new Scene(nVBox);
                    window.setScene(answer);
                    window.setTitle("8-Queens Problem");
                    window.show();
                }
            });

            window.setScene(new Scene(vBox));
            window.setTitle("8-Queens Problem");
            window.show();
        });

        window.setScene(new Scene(hBox));
        window.setTitle("8-Queens Problem");
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
