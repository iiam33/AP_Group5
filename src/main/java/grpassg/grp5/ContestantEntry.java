package grpassg.grp5;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ContestantEntry extends Stage {

    private TextField txtName;
    private Pane constEntryPane;
    private ComboBox comboBoxCountry;
    private Label labelMsg;

    public ContestantEntry() {
        this.setTitle("Contestant Entry Form");

        Label labName = new Label("Enter your name");
        labName.setLayoutX(50);
        labName.setLayoutY(25);
        txtName = new TextField();
        txtName.setLayoutX(200);
        txtName.setLayoutY(25);

        Label labCountry = new Label("Select your country");
        labCountry.setLayoutX(50);
        labCountry.setLayoutY(50);

        comboBoxCountry = new ComboBox();
        comboBoxCountry.getItems().add("Laos");
        comboBoxCountry.getItems().add("Kuwait");
        comboBoxCountry.getItems().add("Kiribati");
        comboBoxCountry.getItems().add("Latvia");
        comboBoxCountry.getItems().add("Kyrgyzstan");
        comboBoxCountry.setLayoutX(200);
        comboBoxCountry.setLayoutY(50);
        comboBoxCountry.setOnAction((event) -> {
            int selectedIndex = comboBoxCountry.getSelectionModel().getSelectedIndex();
            Object selectedItem = comboBoxCountry.getSelectionModel().getSelectedItem();

            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("ComboBox.getValue(): " + comboBoxCountry.getValue());
        });

        labelMsg=new Label(" ");
        labelMsg.setLayoutX(50);
        labelMsg.setLayoutY(85);

        Button btnDone = new Button("Done");
        btnDone.setLayoutX(400);
        btnDone.setLayoutY(230);
        btnDone.setOnAction(e -> {
            if(checkForm()) {
                Contestant.setName(txtName.getText());
                this.hide();
            }
            else {
                System.out.println("All field is mandatory.");
            }
        });

        constEntryPane = new Pane();
        constEntryPane.getChildren().add(labName);
        constEntryPane.getChildren().add(txtName);
        constEntryPane.getChildren().add(btnDone);
        constEntryPane.getChildren().add(labCountry);
        constEntryPane.getChildren().add(comboBoxCountry);
        constEntryPane.getChildren().add(labelMsg);
        this.setScene(new Scene(constEntryPane, 500, 300));
        this.show();
    }

    public boolean checkForm() {
        boolean isFilled = false;
        if (txtName.getText() == null || txtName.getText().trim().isEmpty() || comboBoxCountry.getValue() == null) {
            labelMsg.setText("ALL FIELD IS MANDATORY");
            labelMsg.setTextFill(Color.web("red"));
        }
        else {
            isFilled = true;
        }
        return isFilled;
    }
}
