package grpassg.grp5;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ContestantEntry extends Stage {

    private TextField txtName, txtName2;

    public ContestantEntry() {
        this.setTitle("Greeting");

        Label labName = new Label("Enter your name");
        txtName = new TextField();
        Button btnDone = new Button("Done");
        btnDone.setOnAction(e -> {
            Contestant.setName(txtName.getText());
            this.hide();
        });

        VBox myVBox = new VBox(labName, txtName, btnDone);
        myVBox.setAlignment(Pos.CENTER);
        this.setScene(new Scene(myVBox, 300, 200));
        this.show();
    }

//    public String getName() {
//        return txtName.getText();
//    }
}
