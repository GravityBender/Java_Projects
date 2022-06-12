import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FirstScreen {
    private Button guardButton;
    private TextField getAddress;

    public FirstScreen() {
        this.guardButton = new Button("Start");
        this.getAddress = new TextField();
    }

    public Button getGuardButton() {
        return guardButton;
    }

    public TextField getGetAddress() {
        return getAddress;
    }

    public Parent setFirstScreen() {
        VBox tempVBox = new VBox();

        tempVBox.getChildren().add(getAddress);
        tempVBox.getChildren().add(guardButton);
        tempVBox.setAlignment(Pos.CENTER);

        return tempVBox;
    }

}
