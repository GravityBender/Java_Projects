import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GraphColoring extends Application {

    private ClickablePane clickablePane;
    private ButtonAdder bAdder;
    private int count;
    private ToggleBtns tBtns;
    private LineAdder lineAdder;
    private ToggleGroup toggleGroup = new ToggleGroup();
    private Button sButton;
    private TextField colorInput;
    private Button resetBtn;
    private final ObjectProperty<Button> rememberMe = new SimpleObjectProperty<>();

    public GraphColoring() {
        clickablePane = new ClickablePane();
        bAdder = new ButtonAdder();
        // btnList = new ArrayList<>();
        lineAdder = new LineAdder();
        count = 0;
        sButton = new Button("Start");
        colorInput = new TextField();
        resetBtn = new Button("Reset");
    }

    @Override
    public void start(Stage window) {
        BorderPane borderPane = new BorderPane();

        VBox optionsBox = initVBox();
        Separator vSeparator = new Separator(Orientation.VERTICAL);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(optionsBox, vSeparator);

        borderPane.setLeft(hBox);
        borderPane.setCenter(clickablePane.getPane());

        setToggle();

        sButton.setOnAction((e) -> {
            try {
                Algo algo = new Algo(lineAdder, bAdder, Integer.parseInt(colorInput.getText()));
                algo.start();
            } catch (NumberFormatException err) {
                Alert alert = new Alert(AlertType.ERROR, "Number of colors not specified!",
                        ButtonType.CLOSE, ButtonType.OK);
                alert.show();
            }
        });

        resetBtn.setOnAction((e) -> {
            lineAdder.getLineList().clear();
            bAdder.getBtnList().clear();
            colorInput.setText("");
            clickablePane.getPane().getChildren().clear();
            count = 0;
        });

        Scene scene = new Scene(borderPane, 800, 600);

        window.setTitle("Graph Coloring");
        window.setScene(scene);
        window.show();
    }

    /**
     * Function that acts according to the togglebutton that is active.
     */
    private void setToggle() {

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> arg0, Toggle oldToggle, Toggle newToggle) {

                if (newToggle == null) {
                    // Do nothing
                } else {
                    switch (toggleGroup.getSelectedToggle().getUserData().toString()) {
                        case "addbutton":
                            addBtns();
                            break;
                        case "joinbutton":
                            clickablePane.getPane().setOnMouseClicked(null);
                            joinBtns();
                            break;
                    }
                }

            }

        });
    }

    /**
     * Function to join the buttons in the clickablePane.
     * This works by storing a button in the ObjectProperty and then calling
     * the turn() function with this button and the next button instance
     */
    private void joinBtns() {

        for (Button button : bAdder.getBtnList()) {
            Button tempButton = button;
            tempButton.setOnAction((e) -> {
                if (rememberMe.get() != null) {
                    if (rememberMe.get().equals(button)) {
                        // Do Nothing
                    } else {
                        turn(rememberMe.get(), button);
                    }

                } else {
                    rememberMe.set(button);
                }
            });
        }

    }

    /**
     * Function that creates a Line and joins the buttons
     */
    private void turn(Button prev, Button curr) {
        rememberMe.set(null);
        lineAdder.addLine(prev.getLayoutX(), prev.getLayoutY(), curr.getLayoutX(), curr.getLayoutY(), prev.getId(),
                curr.getId());
        // System.out.println(lineAdder.getLine().getId()); //Debugging purposes
        clickablePane.addToPane(lineAdder.getLine());
    }

    /**
     * Function to add buttons onto the clickablePane.
     * These buttons are the nodes of the graph.
     */
    private void addBtns() {

        clickablePane.getPane().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                bAdder.addBtn(count++, me.getX(), me.getY());
                clickablePane.addToPane(bAdder.getBtn());
            }
        });

    }

    /**
     * Initialising the left view of the program that would contain the buttons to
     * add nodes and edges, also a textfield
     */
    private VBox initVBox() {
        VBox tempBox = new VBox();
        tempBox.setAlignment(Pos.CENTER);
        tempBox.setSpacing(20.0);
        tempBox.setPadding(new Insets(0, 0, 0, 20));

        resetBtn.setMaxWidth(50.0);

        tBtns = new ToggleBtns();
        toggleGroup = new ToggleGroup();

        tBtns.getAddButton().setToggleGroup(toggleGroup);
        tBtns.getJoinButton().setToggleGroup(toggleGroup);

        VBox vBox1 = new VBox();
        vBox1.setSpacing(5.0);
        vBox1.setAlignment(Pos.CENTER);
        Text text = new Text("Enter the number of colors: ");
        FlowPane flowPane = new FlowPane();
        flowPane.setMaxWidth(50.0);
        colorInput.setMaxWidth(50.0);
        flowPane.getChildren().addAll(colorInput, sButton);
        vBox1.getChildren().addAll(text, flowPane);

        tempBox.getChildren().addAll(tBtns.getAddButton(), tBtns.getJoinButton(), resetBtn, vBox1);

        return tempBox;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
