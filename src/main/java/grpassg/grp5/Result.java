package grpassg.grp5;

import java.io.File;
import java.io.FileNotFoundException;
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
    private File resultFile = new File("src/data/result", "answers.txt");
    private Pane resultPane;
    private ComboBox<String> comboBoxName = new ComboBox();
    private ArrayList<String[]> resultList = new ArrayList<String[]>();
    private ArrayList<String> correctList = new ArrayList<String>();

    public Result() {
        this.setTitle("Result Form");
        readFromResultFile();

        labName = new Label("Your name");
        labName.setLayoutX(50);
        labName.setLayoutY(25);

        comboBoxName.setLayoutX(200);
        comboBoxName.setLayoutY(25);
        comboBoxName.valueProperty().addListener((observable) -> {
            int i = comboBoxName.getSelectionModel().getSelectedIndex();
            displayResult(i);
            labResult.setText("Result");
        });

        labResult = new Label(" ");
        labResult.setLayoutX(50);
        labResult.setLayoutY(70);

        labResultList = new Label(" ");
        labResultList.setLayoutX(50);
        labResultList.setLayoutY(108);

        labAccuracy = new Label(" ");
        labAccuracy.setLayoutX(50);
        labAccuracy.setLayoutY(290);
        labAccuracy.setStyle("-fx-font-weight:bold;");

        resultPane = new Pane();
        resultPane.getChildren().add(labName);
        resultPane.getChildren().add(comboBoxName);
        resultPane.getChildren().add(labResult);
        resultPane.getChildren().add(labResultList);
        resultPane.getChildren().add(labAccuracy);
        this.setScene(new Scene(resultPane, 570, 350));
        this.show();
    }

    public void readFromResultFile() {
        Scanner readFile;
        try {
            readFile = new Scanner(resultFile);
            while (readFile.hasNextLine()) {
                String aLine = readFile.nextLine();
                Scanner sline = new Scanner(aLine);
                sline.useDelimiter(":");
                while (sline.hasNext()) {
                    name = sline.next();
                    country = sline.next();
                    String result[] = {
                            sline.next(), sline.next(), sline.next(), sline.next(), sline.next(),
                            sline.next(), sline.next(), sline.next(), sline.next(), sline.next(),
                            sline.next(), sline.next(), sline.next(), sline.next(), sline.next(),
                            sline.next(), sline.next(), sline.next(), sline.next(), sline.next(),
                            sline.next(), sline.next(), sline.next(), sline.next(), sline.next()
                    };
                    correct = sline.next();
                    comboBoxName.getItems().add(name);
                    resultList.add(result);
                    correctList.add(correct);
                }
                sline.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File to read " + questFile + " not found!");
        }
    }

    public void displayResult(int i) {
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

    public double calAccuracy(int i) {
        Double accuracy = Math.floor((Double.valueOf(correctList.get(i)) / 25) * 100);
        return accuracy;
    }
}

