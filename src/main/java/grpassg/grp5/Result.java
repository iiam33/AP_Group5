package grpassg.grp5;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Result extends Stage {
    private Label labName, labResult, labResultList, labAccuracy;
    private String name, country, correct;
    private File questFile = new File("src/data/question", "inputdata.txt");
    private File answerFile = new File("src/data/result", "answers.txt");
    private Pane resultPane;
    private ComboBox<String> comboBoxName = new ComboBox();
    private ArrayList<String[]> resultList = new ArrayList<String[]>();
    private ArrayList<String> correctList = new ArrayList<String>();
    private DecimalFormat df = new DecimalFormat("#.0");

    public Result() {//The result application window

        this.setTitle("Result Form");
        readFromAnswerFile();

        labName = new Label("Your name");
        labName.setLayoutX(50);
        labName.setLayoutY(25);

        comboBoxName.setLayoutX(200);
        comboBoxName.setLayoutY(25);
        comboBoxName.valueProperty().addListener((observable) -> {
            int i = comboBoxName.getSelectionModel().getSelectedIndex();
            displayResult(i);
        });

        labResult = new Label("Result");
        labResult.setLayoutX(50);
        labResult.setLayoutY(70);
        labResult.setStyle("-fx-font-weight:bold;");

        labResultList = new Label(" ");
        labResultList.setLayoutX(50);
        labResultList.setLayoutY(108);

        labAccuracy = new Label("Accuracy: ");
        labAccuracy.setLayoutX(50);
        labAccuracy.setLayoutY(290);
        labAccuracy.setStyle("-fx-font-weight:bold;");

        Button btnClose = new Button("CLOSE");
        btnClose.setLayoutX(470);
        btnClose.setLayoutY(290);
        btnClose.setOnAction(e -> {
            this.close();
        });

        resultPane = new Pane();
        resultPane.getChildren().add(labName);
        resultPane.getChildren().add(comboBoxName);
        resultPane.getChildren().add(labResult);
        resultPane.getChildren().add(labResultList);
        resultPane.getChildren().add(labAccuracy);
        resultPane.getChildren().add(btnClose);
        this.setScene(new Scene(resultPane, 570, 350));
        this.show();
    }

    public void readFromAnswerFile() { //Function to read content from external files
        Scanner readFile;
        try {
            readFile = new Scanner(answerFile);
            while (readFile.hasNextLine()) {
                String aLine = readFile.nextLine();
                Scanner sline = new Scanner(aLine);
                sline.useDelimiter(":"); //To set a delimiting pattern when the programs scan ":"
                while (sline.hasNext()) {
                    name = sline.next(); //Display participant's name
                    country = sline.next(); //Display participant's selected country
                    String result[] = { //Result array to print the results of each question
                            sline.next(), sline.next(), sline.next(), sline.next(), sline.next(),
                            sline.next(), sline.next(), sline.next(), sline.next(), sline.next(),
                            sline.next(), sline.next(), sline.next(), sline.next(), sline.next(),
                            sline.next(), sline.next(), sline.next(), sline.next(), sline.next(),
                            sline.next(), sline.next(), sline.next(), sline.next(), sline.next()
                    };
                    correct = sline.next(); //Display the total correct answers gotten
                    comboBoxName.getItems().add(name); //If there's a participants names in result file, add name to dropdown list
                    resultList.add(result); //Same with dropdown list (name), add result if there's results
                    correctList.add(correct); //Same with dropdown list (name), add total correct answer if there's total correct answer
                }
                sline.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File to read " + answerFile + " not found!");
        }
    }

    public void displayResult(int i) { //Function to display results

        labResultList.setText("  1.   " + resultList.get(i)[0] + "\t\t    " + "  2.   " + resultList.get(i)[1] + "\t\t    " + "  3.   " + resultList.get(i)[2] + "\t\t    "
                + "  4.   " + resultList.get(i)[3] + "\t\t    " + "  5.   " + resultList.get(i)[4] + "\n\n" + "  6.   " + resultList.get(i)[5] + "\t\t    "
                + "  7.   " + resultList.get(i)[6] + "\t\t    " + "  8.   " + resultList.get(i)[7] + "\t\t    " + "  9.   " + resultList.get(i)[8] + "\t\t    "
                + "10.   " + resultList.get(i)[9] + "\n\n" + "11.   " + resultList.get(i)[10] + "\t\t    " + "12.   " + resultList.get(i)[11] + "\t\t    "
                + "13.   " + resultList.get(i)[12] + "\t\t    " + "14.   " + resultList.get(i)[13] + "\t\t    " + "15.   " + resultList.get(i)[14] + "\n\n"
                + "16.   " + resultList.get(i)[15] + "\t\t    " + "17.   " + resultList.get(i)[16] + "\t\t    " + "18.   " + resultList.get(i)[17] + "\t\t    "
                + "19.   " + resultList.get(i)[18] + "\t\t    " + "20.   " + resultList.get(i)[19] + "\n\n" + "21.   " + resultList.get(i)[20] + "\t\t    "
                + "22.   " + resultList.get(i)[21] + "\t\t    " + "23.   " + resultList.get(i)[22] + "\t\t    " + "24.   " + resultList.get(i)[23] + "\t\t    "
                + "25.   " + resultList.get(i)[24] + "\t\t"
        );
        Double accuracy = calAccuracy(i);
        String s = Double.toString(accuracy);
        labAccuracy.setText("Accuracy: " + s + " %");
    }

    public double calAccuracy(int i) { //Function to calculate the percentage

        Double accuracy = Math.floor((Double.valueOf(correctList.get(i)) / 25) * 100);
        return Double.valueOf(df.format(accuracy)); //returns the value in 1 decimal point format
    }
}

