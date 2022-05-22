import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Line;

public class Algo {
    private LineAdder lineAdder;
    private ButtonAdder bAdder;
    private int colors;
    private String[] colorArr;
    private int V;
    private int[] color;

    public Algo(LineAdder lineAdder, ButtonAdder bAdder, int colors) {
        this.lineAdder = lineAdder;
        this.bAdder = bAdder;
        this.colors = colors;
        this.V = bAdder.getBtnList().size();
    }

    boolean isSafe(int v, int graph[][], int c) {
        for (int i = 0; i < V; i++)
            if (graph[v][i] == 1 && c == color[i])
                return false;
        return true;
    }

    boolean graphColoringUtil(int graph[][], int v) {

        if (v == V)
            return true;

        for (int c = 1; c <= colors; c++) {
            if (isSafe(v, graph, c)) {
                color[v] = c;

                if (graphColoringUtil(graph, v + 1))
                    return true;

                color[v] = 0;
            }
        }

        return false;
    }

    private void colorGraph(int[][] graph) {
        color = new int[V];
        for (int i = 0; i < V; i++)
            color[i] = 0;

        if (!graphColoringUtil(graph, 0)) {
            // System.out.println("Solution does not exist"); // Debugging purposes
        }
        assignColor();
    }

    private void assignColor() {
        try {
            RandomColorGenerator generator = new RandomColorGenerator();
            colorArr = generator.getRandomColors();
            for (int i = 0; i < color.length; i++) {
                // System.out.println(color[i]); // Debugging purposes
                bAdder.getBtnList().get(i).setStyle("-fx-background-color: " + colorArr[color[i] - 1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Alert alert = new Alert(AlertType.WARNING, "Solution does not exist!",
                    ButtonType.CLOSE, ButtonType.OK);
            alert.show();
        }
    }

    public void start() {
        int[][] graph = new int[V][V];

        for (Line line : lineAdder.getLineList()) {
            String id = line.getId();
            String[] temp = id.split("&");
            int i = Integer.parseInt(temp[0]);
            int j = Integer.parseInt(temp[1]);

            graph[i][j] = 1;
            graph[j][i] = 1;
        }

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (graph[i][j] != 1) {
                    graph[i][j] = 0;
                }
                // System.out.print(graph[i][j] + " "); // Debugging purposes
            }
            // System.out.println(); // Debugging purposes
        }
        colorGraph(graph);
    }

}
