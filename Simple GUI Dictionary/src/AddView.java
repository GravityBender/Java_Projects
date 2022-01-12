import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class AddView {

    private Dictionary dictionary;

    public AddView(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Parent getView() {
        AnchorPane layout = new AnchorPane();

        TextField searchField = new TextField();

        TextArea resultArea = new TextArea();
        resultArea.setPrefWidth(300.0);
        resultArea.setPrefHeight(120.0);
        resultArea.setWrapText(true);

        AnchorPane.setTopAnchor(searchField, 30.0);
        AnchorPane.setLeftAnchor(searchField, 190.0);
        AnchorPane.setTopAnchor(resultArea, 100.0);
        AnchorPane.setLeftAnchor(resultArea, 120.0);

        layout.getChildren().addAll(searchField, resultArea);

        resultArea.setOnKeyPressed((e) -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                dictionary.add(searchField.getText().toLowerCase(), resultArea.getText());
                resultArea.setCursor(Cursor.DISAPPEAR);
                resultArea.clear();
                searchField.clear();
            }
        });

        return layout;
    }
}
