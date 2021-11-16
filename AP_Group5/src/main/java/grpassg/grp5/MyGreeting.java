package grpassg.grp5;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class MyGreeting extends Stage {

    private TextField txtName, txtName2;

    public MyGreeting() {
        this.setTitle("Greeting");

        Label labName = new Label("Enter your name1 and name2");
        txtName = new TextField();
        txtName2 = new TextField();
        Button btnDone = new Button("Done");
        btnDone.setOnAction(e -> {
            MyParam.setName2(txtName2.getText());
            this.hide();
        });

        VBox myVBox = new VBox(labName, txtName, txtName2, btnDone);
        myVBox.setAlignment(Pos.CENTER);
        this.setScene(new Scene(myVBox, 300, 200));
        this.show();
    }

    public String getName() {
        return txtName.getText();
    }
}
