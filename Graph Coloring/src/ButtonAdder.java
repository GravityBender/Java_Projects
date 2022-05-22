import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

public class ButtonAdder {

    private Button btn;
    private List<Button> btnList;

    public ButtonAdder() {
        this.btnList = new ArrayList<>();
    }

    public void addBtn(int count, double x, double y) {
        btn = new Button(Integer.toString(count));
        btn.setId(Integer.toString(count));
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        customizeBtn();
        btnList.add(btn);
    }

    private void customizeBtn() {
        btn.setShape(new Circle(50));
        btn.setPrefSize(40, 40);
    }

    public List<Button> getBtnList() {
        return this.btnList;
    }

    public Button getBtn() {
        return this.btn;
    }

}
