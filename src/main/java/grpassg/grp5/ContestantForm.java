package grpassg.grp5;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ContestantForm extends Stage {
    private TextField txtName;
    private Pane constEntryPane, imgPane;
    private ComboBox<String> comboBoxCountry = new ComboBox();
    private Label labelMsg;
    private ArrayList<String> contCountryArrayList = new ArrayList<String>();
    private ImageView contCountryFlagImg = new ImageView();
    private File contestantFile = new File("src/data/contestant", "contestant.txt");

    public ContestantForm() {
        this.setTitle("Contestant Entry Form");
        readFromContestantFile();

        Label labName = new Label("Enter your name");
        labName.setLayoutX(50);
        labName.setLayoutY(25);
        txtName = new TextField();
        txtName.setLayoutX(200);
        txtName.setLayoutY(25);

        Label labCountry = new Label("Select your country");
        labCountry.setLayoutX(50);
        labCountry.setLayoutY(60);

        comboBoxCountry.setLayoutX(200);
        comboBoxCountry.setLayoutY(60);
        comboBoxCountry.valueProperty().addListener((observable) -> {
            int i = comboBoxCountry.getSelectionModel().getSelectedIndex();
            File imgFile = new File("src/data/contestant/flag/" + contCountryArrayList.get(i) + ".png");
            Image img = new Image(imgFile.toURI().toString());
            contCountryFlagImg.setImage(img);
            System.out.println("src/data/contestant/flag/" + contCountryArrayList.get(i) + ".png");
            System.out.println("Selection made: [" + i + "] ");
        });

        labelMsg = new Label(" ");
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

        imgPane = new Pane();
        imgPane.setLayoutX(50);
        imgPane.setLayoutY(120);
        imgPane.getChildren().add(contCountryFlagImg);

        constEntryPane = new Pane();
        constEntryPane.getChildren().add(labName);
        constEntryPane.getChildren().add(txtName);
        constEntryPane.getChildren().add(btnDone);
        constEntryPane.getChildren().add(labCountry);
        constEntryPane.getChildren().add(comboBoxCountry);
        constEntryPane.getChildren().add(labelMsg);
        constEntryPane.getChildren().add(imgPane);
        this.setScene(new Scene(constEntryPane, 500, 300));
        this.show();
    }

    public void readFromContestantFile() {
        Scanner readFile;

        try {
            readFile = new Scanner(contestantFile);
            while (readFile.hasNextLine()) {
                String aLine = readFile.nextLine();
                Scanner sline = new Scanner(aLine);
                sline.useDelimiter(":");
                while (sline.hasNext()) {
                    String contestant[] = {sline.next(), sline.next(), sline.next(), sline.next(), sline.next(), sline.next()};
                    comboBoxCountry.getItems().add(contestant[1]);
                    contCountryArrayList.add(contestant[1]);
                }
                sline.close();
            }
        } catch (FileNotFoundException e) {
            labelMsg.setText("File to read " + contestantFile + " not found!");
        }
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

    public String getName() {
        return txtName.getText();
    }
}

