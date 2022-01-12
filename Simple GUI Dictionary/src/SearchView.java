import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class SearchView {

    private Dictionary dictionary;

    public SearchView(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Parent getView() {
        AnchorPane layout = new AnchorPane();

        TextField searchField = new TextField();

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setMouseTransparent(true);
        resultArea.setFocusTraversable(false);
        resultArea.setWrapText(true);
        resultArea.setPrefWidth(300.0);
        resultArea.setPrefHeight(120.0);

        AnchorPane.setTopAnchor(searchField, 30.0);
        AnchorPane.setLeftAnchor(searchField, 190.0);
        AnchorPane.setTopAnchor(resultArea, 100.0);
        AnchorPane.setLeftAnchor(resultArea, 120.0);

        layout.getChildren().addAll(searchField, resultArea);

        searchField.setOnKeyPressed((e) -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                String word = searchField.getText();
                if (dictionary.hasWord(word.toLowerCase())) {
                    resultArea.setText(dictionary.get(word.toLowerCase()));
                    ;
                } else {
                    resultArea.setText("No such word!");
                }
            }
        });

        return layout;
    }
}
