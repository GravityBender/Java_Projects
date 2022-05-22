import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ClickablePane {

    private Pane clickablePane;

    public ClickablePane() {
        clickablePane = new Pane();
    }

    public Pane getPane() {
        return clickablePane;
    }

    public void addToPane(Node... nodes) {
        clickablePane.getChildren().addAll(nodes);
    }

    public void addToPane(Node node) {
        clickablePane.getChildren().add(node);
    }

}
