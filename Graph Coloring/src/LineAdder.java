import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Line;

public class LineAdder {
    private Line line;
    private List<Line> lineList;
    private final int var;

    public LineAdder() {
        line = new Line();
        lineList = new ArrayList<>();
        var = 20;
    }

    public void addLine(double x1, double y1, double x2, double y2, String prevId, String currId) {
        line = new Line(x1 + var, y1 + var, x2 + var, y2 + var);
        line.setId(prevId + "&" + currId);
        lineList.add(line);
    }

    public Line getLine() {
        return this.line;
    }

    public List<Line> getLineList() {
        return this.lineList;
    }
}
