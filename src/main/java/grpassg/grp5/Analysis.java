package grpassg.grp5;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Analysis extends Stage {
    private Pane analysisPane;

    public Analysis() {
        this.setTitle("Contestant Entry Form");
        Label labName = new Label("Hi");
        labName.setLayoutX(50);
        labName.setLayoutY(25);

        analysisPane = new Pane();
        analysisPane.getChildren().add(labName);
        this.setScene(new Scene(analysisPane, 500, 300));
        this.show();
    }

}
