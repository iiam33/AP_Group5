package grpassg.grp5;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Analysis extends Stage {
    private Pane analysisPane;
    private String name, country, correct;
    private File answerFile = new File("src/data/result", "answers.txt");
    private ArrayList<String[]> resultList = new ArrayList<String[]>();
    private ArrayList<String[]> contestantResultList = new ArrayList<String[]>();
    private ListView<String> leaderboardList = new ListView<String>();
    private Label labAverage, labHighest, labWinner;
    private DecimalFormat df = new DecimalFormat("#.0");

    public Analysis() {
        this.setTitle("Analysis Form");
        readFromAnswerFile();
        rearrangeList();

        Label labLeaderboard = new Label("Leaderboard");
        labLeaderboard.setLayoutX(50);
        labLeaderboard.setLayoutY(25);
        labLeaderboard.setStyle("-fx-font-weight:bold;");

        leaderboardList.setLayoutX(50);
        leaderboardList.setLayoutY(70);
        leaderboardList.setPrefWidth(470);
        leaderboardList.setPrefHeight(210);
        leaderboardList.getItems().add("No" + ".\t\t\t" + "Contestant Name" + "\t\t\t" + "Country" + "\t\t\t\t\t" + "Score (%)" + "\n");
        for(int i = 0; i < contestantResultList.size(); i++) {
            leaderboardList.getItems().add((i + 1) + ".\t\t\t" + contestantResultList.get(i)[0] + " \t\t\t\t\t" + contestantResultList.get(i)[1] + "  \t\t\t\t\t" + calAccuracy(contestantResultList.get(i)[2]) + "\n");
        }

        labAverage = new Label("Average Score: " + calAverage() + " %");
        labAverage.setLayoutX(50);
        labAverage.setLayoutY(300);
        labAverage.setStyle("-fx-font-weight:bold;");

        labHighest = new Label("Highest Score: " + Double.valueOf(calAccuracy(contestantResultList.get(0)[2])) + " %");
        labHighest.setLayoutX(250);
        labHighest.setLayoutY(300);
        labHighest.setStyle("-fx-font-weight:bold;");

        labWinner = new Label("Winner: " + contestantResultList.get(0)[0]);
        labWinner.setLayoutX(420);
        labWinner.setLayoutY(300);
        labWinner.setStyle("-fx-font-weight:bold;");

        Button btnClose = new Button("CLOSE");
        btnClose.setLayoutX(500);
        btnClose.setLayoutY(340);
        btnClose.setOnAction(e -> {
            this.close();
        });

        analysisPane = new Pane();
        analysisPane.getChildren().add(labLeaderboard);
        analysisPane.getChildren().add(leaderboardList);
        analysisPane.getChildren().add(labAverage);
        analysisPane.getChildren().add(labHighest);
        analysisPane.getChildren().add(labWinner);
        analysisPane.getChildren().add(btnClose);
        this.setScene(new Scene(analysisPane, 570, 380));
        this.show();
    }

    public void readFromAnswerFile() {
        Scanner readFile;
        try {
            readFile = new Scanner(answerFile);
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
                    resultList.add(result);
                    contestantResultList.add(new String[]{name, country, correct});
                }
                sline.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File to read " + answerFile + " not found!");
        }
    }

    public void rearrangeList() {
        int n = contestantResultList.size();
        String tmpArr[];

        for (int i = 0; i < contestantResultList.size(); i++) {
            for(int j = 0; j < contestantResultList.size()-i-1; j++) {
                if(Integer.parseInt(contestantResultList.get(j)[2]) < Integer.parseInt(contestantResultList.get(j+1)[2])) {
                    tmpArr = contestantResultList.get(j);
                    contestantResultList.set(j, contestantResultList.get(j+1));
                    contestantResultList.set(j+1, tmpArr);
                }
            }
        }
    }

    public Double calAccuracy(String scoreString) {
        Double score = Double.parseDouble(scoreString);
        Double accuracy = Math.floor((Double.valueOf(score) / 25) * 100);
        return Double.valueOf(df.format(accuracy));
    }

    public Double calAverage() {
        Double totalScore = 0.0;
        Double averageScore = 0.0;
        for(int i = 0; i < contestantResultList.size(); i++) {
            totalScore += calAccuracy(contestantResultList.get(i)[2]);
        }
        averageScore = totalScore / contestantResultList.size();

        return Double.valueOf(df.format(averageScore));
    }
}
