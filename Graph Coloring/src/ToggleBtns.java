import javafx.scene.control.ToggleButton;

public class ToggleBtns {
    private ToggleButton addButton;
    private ToggleButton joinButton;

    public ToggleBtns() {
        this.addButton = new ToggleButton();
        this.joinButton = new ToggleButton();

        setBtnData();
    }

    private void setBtnData() {
        addButton.setText("Add Nodes");
        addButton.setUserData("addbutton");
        joinButton.setText("Join Edges");
        joinButton.setUserData("joinbutton");
    }

    public ToggleButton getAddButton() {
        return this.addButton;
    }

    public ToggleButton getJoinButton() {
        return this.joinButton;
    }
}
