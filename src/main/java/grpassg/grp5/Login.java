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

public class Login extends Stage { //Login application window
    private String name = "";
    private String country = "";
    private PasswordField passwordField;
    private Pane constEntryPane, imgPane, flagPane;
    private ComboBox<String> comboBoxCountry = new ComboBox();
    private ComboBox<String> comboBoxName = new ComboBox();
    private Label labelMsg;
    private ArrayList<String[]> contestantArrayList = new ArrayList<String[]>();
    private ImageView contCountryFlagImg = new ImageView();
    private ImageView contImg1 = new ImageView();
    private ImageView contImg2 = new ImageView();
    private ImageView contImg3 = new ImageView();
    private File contestantFile = new File("src/data/contestant", "contestant.txt");

    public Login() {
        this.setTitle("Contestant Entry Form");
        readFromContestantFile();

        Label labName = new Label("Select your name");
        labName.setLayoutX(50);
        labName.setLayoutY(25);

        comboBoxName.setLayoutX(200);
        comboBoxName.setLayoutY(25);
        comboBoxName.valueProperty().addListener((observable) -> {
            int i = comboBoxName.getSelectionModel().getSelectedIndex();
            File imgFile1 = new File("src/data/contestant/pic/" + contestantArrayList.get(i)[2]);
            Image img1 = new Image(imgFile1.toURI().toString());
            contImg1.setImage(img1);

            File imgFile2 = new File("src/data/contestant/pic/" + contestantArrayList.get(i)[3]);
            Image img2 = new Image(imgFile2.toURI().toString());
            contImg2.setImage(img2);

            File imgFile3 = new File("src/data/contestant/pic/" + contestantArrayList.get(i)[4]);
            Image img3 = new Image(imgFile3.toURI().toString());
            contImg3.setImage(img3);

            setName(contestantArrayList.get(i)[0]);
        });

        contImg1.setFitHeight(240);
        contImg1.setFitWidth(240);
        contImg2.setFitHeight(240);
        contImg2.setFitWidth(240);
        contImg2.setLayoutX(270);
        contImg3.setFitHeight(240);
        contImg3.setFitWidth(240);
        contImg3.setLayoutX(540);

        Label labCountry = new Label("Select your country");
        labCountry.setLayoutX(50);
        labCountry.setLayoutY(60);

        comboBoxCountry.setLayoutX(200);
        comboBoxCountry.setLayoutY(60);
        comboBoxCountry.valueProperty().addListener((observable) -> {
            int j = comboBoxCountry.getSelectionModel().getSelectedIndex();
            File imgFile = new File("src/data/contestant/flag/" + contestantArrayList.get(j)[1] + ".png");
            Image img = new Image(imgFile.toURI().toString());
            contCountryFlagImg.setImage(img);
            setCountry(contestantArrayList.get(j)[1]);
        });

        Label labPassword = new Label("Enter your password");
        labPassword.setLayoutX(50);
        labPassword.setLayoutY(95);

        passwordField = new PasswordField();
        passwordField.setLayoutX(200);
        passwordField.setLayoutY(95);

        labelMsg = new Label(" ");
        labelMsg.setLayoutX(50);
        labelMsg.setLayoutY(125);

        Button btnDone = new Button("Done");
        btnDone.setLayoutX(800);
        btnDone.setLayoutY(430);
        btnDone.setOnAction(e -> {
            if(checkForm()) {
                this.hide();
                new Test(getName(), getCountry());
            }
            else {
                System.out.println("Error. Enable to login!");
            }
        });

        imgPane = new Pane();
        imgPane.setLayoutX(50);
        imgPane.setLayoutY(170);
        imgPane.getChildren().add(contImg1);
        imgPane.getChildren().add(contImg2);
        imgPane.getChildren().add(contImg3);

        flagPane = new Pane();
        flagPane.setLayoutX(600);
        flagPane.setLayoutY(20);
        flagPane.getChildren().add(contCountryFlagImg);

        constEntryPane = new Pane();
        constEntryPane.getChildren().add(labName);
        constEntryPane.getChildren().add(comboBoxName);
        constEntryPane.getChildren().add(btnDone);
        constEntryPane.getChildren().add(labCountry);
        constEntryPane.getChildren().add(comboBoxCountry);
        constEntryPane.getChildren().add(labPassword);
        constEntryPane.getChildren().add(passwordField);
        constEntryPane.getChildren().add(labelMsg);
        constEntryPane.getChildren().add(imgPane);
        constEntryPane.getChildren().add(flagPane);
        this.setScene(new Scene(constEntryPane, 900, 500));
        this.show();
    }

    public void readFromContestantFile() { //Read contents from Contestant file
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
                    comboBoxName.getItems().add(contestant[0]);
                    contestantArrayList.add(contestant);
                }
                sline.close();
            }
        } catch (FileNotFoundException e) {
            labelMsg.setText("File to read " + contestantFile + " not found!");
        }
    }

    public boolean checkForm() { //Function to check user's input

        boolean isValidated = false;
        int i = comboBoxName.getSelectionModel().getSelectedIndex();

        //If user skipped the password section, print warning
        if (comboBoxName.getValue() == null || comboBoxCountry.getValue() == null || passwordField.getText().trim().isEmpty()) {
            labelMsg.setText("ALL FIELD IS MANDATORY");
            labelMsg.setTextFill(Color.web("red"));
            System.out.println(passwordField.getText());
        }
        //If user key in the wrong password, print warning
        else if(!passwordField.getText().equals(contestantArrayList.get(i)[5])) {
            labelMsg.setText("PASSWORD NOT MATCHED");
            labelMsg.setTextFill(Color.web("red"));
        }
        else { //If it doesn't match either of the if statement then allow validation
            isValidated = true;
        }
        return isValidated;
    }

    public String getName() { return name; } //Function to get name

    public void setName(String n) { name = n; } //Function to set name

    public String getCountry() { return country; } //Function to get contestant's country name

    public void setCountry(String c) { country = c; } //Function to set contestant's country name
}

