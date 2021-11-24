package grpassg.grp5;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
        labName.setLayoutY(35);
        labName.setFont(Font.font("Serif", FontWeight.NORMAL, FontPosture.REGULAR, 15));

        comboBoxName.setLayoutX(200);
        comboBoxName.setLayoutY(25);
        comboBoxName.setStyle(
                "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;" +
                        "    -fx-background-insets: 0 0 -1 0, 0, 1, 2;" +
                        "    -fx-pref-width: 200;" +
                        "    -fx-padding: 0.333333em 0.333em 0.333333em 0.33em; /* 4 8 4 8 */" +
                        "    -fx-text-fill: -fx-text-base-color;" +
                        "    -fx-alignment: CENTER;" +
                        "    -fx-content-display: LEFT;");
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
        contImg1.setLayoutX(20);
        contImg1.setLayoutY(20);
        contImg2.setFitHeight(240);
        contImg2.setFitWidth(240);
        contImg2.setLayoutX(285);
        contImg2.setLayoutY(20);
        contImg3.setFitHeight(240);
        contImg3.setFitWidth(240);
        contImg3.setLayoutX(550);
        contImg3.setLayoutY(20);

        Label labCountry = new Label("Select your country");
        labCountry.setLayoutX(50);
        labCountry.setLayoutY(85);
        labCountry.setFont(Font.font("Serif", FontWeight.NORMAL, FontPosture.REGULAR, 15));

        comboBoxCountry.setLayoutX(200);
        comboBoxCountry.setLayoutY(75);
        comboBoxCountry.setStyle(
                "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;" +
                        "    -fx-background-insets: 0 0 -1 0, 0, 1, 2;" +
                        "    -fx-pref-width: 200;" +
                        "    -fx-padding: 0.333333em 0.333em 0.333333em 0.33em; /* 4 8 4 8 */" +
                        "    -fx-text-fill: -fx-text-base-color;" +
                        "    -fx-alignment: CENTER;" +
                        "    -fx-content-display: LEFT;");
        comboBoxCountry.valueProperty().addListener((observable) -> {
            int j = comboBoxCountry.getSelectionModel().getSelectedIndex();
            File imgFile = new File("src/data/contestant/flag/" + contestantArrayList.get(j)[1] + ".png");
            Image img = new Image(imgFile.toURI().toString());
            contCountryFlagImg.setImage(img);
            setCountry(contestantArrayList.get(j)[1]);
        });

        Label labPassword = new Label("Enter your password");
        labPassword.setLayoutX(50);
        labPassword.setLayoutY(125);
        labPassword.setStyle( "    -fx-pref-width: 200;" );
        labPassword.setFont(Font.font("Serif", FontWeight.NORMAL, FontPosture.REGULAR, 15));


        passwordField = new PasswordField();
        passwordField.setLayoutX(200);
        passwordField.setLayoutY(120);

        labelMsg = new Label(" ");
        labelMsg.setLayoutX(50);
        labelMsg.setLayoutY(145);

        Button btnDone = new Button("Done");
        btnDone.setLayoutX(800);
        btnDone.setLayoutY(470);
        btnDone.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btnDone.setStyle("" +
                "-fx-text-fill: #006464;" +
                "    -fx-background-color: #DFB951;" +
                "    -fx-border-radius: 15;" +
                "    -fx-background-radius: 15;");
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
        imgPane.setStyle("" +
                "    -fx-background-color: #fff8e3;" +
                "    -fx-padding: 15;" +
                "    -fx-spacing: 10;" +
                "    -fx-background-radius: 18 18 18 18;"  +
                "    -fx-border-radius: 18 18 18 18;");
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
        constEntryPane.setStyle("" +
                "-fx-background-image:" +
                "url('https://www.freepik.com/download-file/15599884');" +
                " -fx-background-repeat: no-repeat; -fx-background-size: 1000 1000; -fx-background-position: center center;");
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

