package grpassg.grp5;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Test {
    private File questFile = new File("src/data/question", "inputdata.txt");
    private int totQues = 0;
    private int activeQ = 1; //first question
    private Label labQuesNo, labQues, labName;
    private ImageView imgQues, imglabA, imglabB, imglabC, imglabD;
    private Label labA, labB, labC, labD;
    private RadioButton radChoice1, radChoice2, radChoice3, radChoice4;
    private ToggleGroup grpChoices;
    private Button btnPrev, btnNext, btnSubmit;
    private Pane testPane;
    private Pane paneC;
    private Scene testScene;
    private Label labTimer = new Label();
    private Timer timer;
    private Double interval = 10.0;
    private ContestantForm constForm;
    private Analysis winFarewell;
    private LinkedList<Question> quesList = new LinkedList<Question>();

    public void start(Stage testStage) {
        testStage.setTitle("Miss Universe Knowledge Test");
        Label labNameDesc = new Label("Name");
        labNameDesc.setLayoutX(25);
        labNameDesc.setLayoutY(25);
        labName = new Label("");
        labName.setLayoutX(75);
        labName.setLayoutY(25);
        labName.setStyle("-fx-pref-width: 100px;-fx-border-color:black;");

        labQuesNo = new Label("");
        labQuesNo.setLayoutX(25);
        labQuesNo.setLayoutY(75);
        labQuesNo.setStyle("-fx-font-family:serif;-fx-text-fill:#0000ff;");

        labQues = new Label("");
        labQues.setLayoutX(25);
        labQues.setLayoutY(100);
        labQues.setStyle("-fx-font-size: 10pt;-fx-font-weight:bold;");

        labTimer = new Label();
        labTimer.setLayoutX(25);
        labTimer.setLayoutY(678);
        labTimer.setText("Time left: " + interval);
        labTimer.setTextFill(Color.RED);
        labTimer.setStyle("-fx-font-size: 10pt;-fx-font-weight:bold;");

        imgQues = new ImageView();
        imgQues.setLayoutX(25);
        imgQues.setLayoutY(75);
        imgQues.setFitHeight(280);
        imgQues.setFitWidth(275);

        imglabA = new ImageView();
        imglabA.setLayoutX(75);
        imglabA.setLayoutY(25);
        imglabA.setFitHeight(100);
        imglabA.setFitWidth(100);

        imglabB = new ImageView();
        imglabB.setLayoutX(75);
        imglabB.setLayoutY(100);
        imglabB.setFitHeight(100);
        imglabB.setFitWidth(100);

        imglabC = new ImageView();
        imglabC.setLayoutX(75);
        imglabC.setLayoutY(175);
        imglabC.setFitHeight(100);
        imglabC.setFitWidth(100);

        imglabD = new ImageView();
        imglabD.setLayoutX(75);
        imglabD.setLayoutY(175);
        imglabD.setFitHeight(100);
        imglabD.setFitWidth(100);

        labA = new Label("A");
        labA.setLayoutX(25);
        radChoice1 = new RadioButton("");
        radChoice1.setLayoutX(50);

        labB = new Label("B");
        labB.setLayoutX(25);
        radChoice2 = new RadioButton("");
        radChoice2.setLayoutX(50);

        labC = new Label("C");
        labC.setLayoutX(25);
        radChoice3 = new RadioButton("");
        radChoice3.setLayoutX(50);

        labD = new Label("D");
        labD.setLayoutX(25);
        radChoice4 = new RadioButton("");
        radChoice4.setLayoutX(50);

        grpChoices = new ToggleGroup();

        radChoice1.setToggleGroup(grpChoices);
        radChoice2.setToggleGroup(grpChoices);
        radChoice3.setToggleGroup(grpChoices);
        radChoice4.setToggleGroup(grpChoices);
        paneC = new Pane();
        paneC.setLayoutX(25);
        paneC.setLayoutY(75); //75
        paneC.getChildren().add(imgQues);

        paneC.getChildren().add(labA);
        paneC.getChildren().add(radChoice1);
        paneC.getChildren().add(imglabA);
        paneC.getChildren().add(labB);
        paneC.getChildren().add(radChoice2);
        paneC.getChildren().add(imglabB);
        paneC.getChildren().add(labC);
        paneC.getChildren().add(radChoice3);
        paneC.getChildren().add(imglabC);
        paneC.getChildren().add(labD);
        paneC.getChildren().add(radChoice4);
        paneC.getChildren().add(imglabD);

        btnPrev = new Button("Previous");
        btnPrev.setLayoutX(25);
        btnPrev.setLayoutY(725);
        btnPrev.setStyle("-fx-pref-width: 75px;");
        btnPrev.setDisable(true);
        btnNext = new Button("Next");
        btnNext.setLayoutX(125);
        btnNext.setLayoutY(725);
        btnNext.setStyle("-fx-pref-width: 75px;");
        btnSubmit = new Button("End");
        btnSubmit.setLayoutX(300);
        btnSubmit.setLayoutY(725);
        btnSubmit.setStyle("-fx-pref-width: 75px;");

        readFromFile();
        radChoice1.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, true);
            quesList.get(activeQ-1).setSelected(1, false);
            quesList.get(activeQ-1).setSelected(2, false);
            quesList.get(activeQ-1).setSelected(3, false);
        });
        radChoice2.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, false);
            quesList.get(activeQ-1).setSelected(1, true);
            quesList.get(activeQ-1).setSelected(2, false);
            quesList.get(activeQ-1).setSelected(3, false);
        });
        radChoice3.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, false);
            quesList.get(activeQ-1).setSelected(1, false);
            quesList.get(activeQ-1).setSelected(2, true);
            quesList.get(activeQ-1).setSelected(3, false);
        });
        radChoice4.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, false);
            quesList.get(activeQ-1).setSelected(1, false);
            quesList.get(activeQ-1).setSelected(2, false);
            quesList.get(activeQ-1).setSelected(3, true);
        });

        if (totQues == 1)
            btnNext.setDisable(true);
        btnNext.setOnAction(e -> {
            activeQ++;
            btnPrev.setDisable(false);
            if (activeQ == totQues)
                btnNext.setDisable(true);
            reloadQues();
        });
        btnPrev.setOnAction(e -> {
            activeQ--;
            btnNext.setDisable(false);
            if (activeQ == 1)
                btnPrev.setDisable(true);
            reloadQues();
        });
        btnSubmit.setOnAction(e -> {
            winFarewell.setName(labName.getText());
            testStage.hide();
            winFarewell.showStage();
        });
        testPane = new Pane();
        testPane.getChildren().add(labNameDesc);
        testPane.getChildren().add(labName);
        testPane.getChildren().add(labQuesNo);
        testPane.getChildren().add(labQues);
        testPane.getChildren().add(paneC);
        testPane.getChildren().add(btnNext);
        testPane.getChildren().add(btnPrev);
        testPane.getChildren().add(btnSubmit);
        testPane.getChildren().add(labTimer);

        testScene = new Scene(testPane, 650, 775);
        reloadQues();
        testStage.hide();
        constForm = new ContestantForm();
        constForm.setOnHiding(e -> {
            labName.setText(constForm.getName());
            testStage.setScene(testScene);
            testStage.show();
        });
        winFarewell = new Analysis();
    }

    public void reloadQues() {
        labQuesNo.setText("Question " + Integer.toString(activeQ) + " of " + quesList.size());
        labQues.setText(quesList.get(activeQ-1).getTheQues());
        radChoice1.setText(quesList.get(activeQ-1).getChoice(0));
        radChoice2.setText(quesList.get(activeQ-1).getChoice(1));
        radChoice3.setText(quesList.get(activeQ-1).getChoice(2));
        radChoice4.setText(quesList.get(activeQ-1).getChoice(3));
        imgQues.setImage(null);
        imglabA.setImage(null);
        imglabB.setImage(null);
        imglabC.setImage(null);
        imglabD.setImage(null);

        //type A
        if (quesList.get(activeQ-1).getType() == 1) {
            labA.setLayoutY(75); //for the label A,B,C,D
            radChoice1.setLayoutY(75);
            labB.setLayoutY(125);
            radChoice2.setLayoutY(125);
            labC.setLayoutY(175);
            radChoice3.setLayoutY(175);
            labD.setLayoutY(225);
            radChoice4.setLayoutY(225);
        }
        //type B
        if (quesList.get(activeQ-1).getType() == 2) {
            File pFile = new File("src/data/question/img/" + quesList.get(activeQ-1).getQuesPic());
            Image img = new Image(pFile.toURI().toString());
            imgQues.setImage(img);
            labA.setLayoutY(375);
            radChoice1.setLayoutY(375);
            labB.setLayoutY(425);
            radChoice2.setLayoutY(425);
            labC.setLayoutY(475);
            radChoice3.setLayoutY(475);
            labD.setLayoutY(525);
            radChoice4.setLayoutY(525); //where u change the x and y placement
        }
        //type C
        if (quesList.get(activeQ - 1).getType() == 3) {
            radChoice1.setText("");
            radChoice2.setText("");
            radChoice3.setText("");
            radChoice4.setText("");

            File pFile = new File("src/data/question/img/" + quesList.get(activeQ - 1).getChoice(0));
            Image img = new Image(pFile.toURI().toString());

            File pFile2 = new File("src/data/question/img/" + quesList.get(activeQ - 1).getChoice(1));
            Image img2 = new Image(pFile2.toURI().toString());

            File pFile3 = new File("src/data/question/img/" + quesList.get(activeQ - 1).getChoice(2));
            Image img3 = new Image(pFile3.toURI().toString());

            File pFile4 = new File("src/data/question/img/" + quesList.get(activeQ - 1).getChoice(3));
            Image img4 = new Image(pFile4.toURI().toString());

            imglabA.setImage(img);
            imglabA.setLayoutY(100);
            labA.setLayoutY(125);
            radChoice1.setLayoutY(125);

            imglabB.setImage(img2);
            imglabB.setLayoutY(225);
            labB.setLayoutY(250);
            radChoice2.setLayoutY(250);

            imglabC.setImage(img3);
            imglabC.setLayoutY(350);
            labC.setLayoutY(375);
            radChoice3.setLayoutY(375);

            imglabD.setImage(img4);
            imglabD.setLayoutY(475);
            labD.setLayoutY(500);
            radChoice4.setLayoutY(500);
        }

        radChoice1.setSelected(quesList.get(activeQ-1).getSelected(0));
        radChoice2.setSelected(quesList.get(activeQ-1).getSelected(1));
        radChoice3.setSelected(quesList.get(activeQ-1).getSelected(2));
        radChoice4.setSelected(quesList.get(activeQ-1).getSelected(3));
    }

    public void readFromFile() {
        Scanner sfile;
        int type;
        char answer;
        String theQues;
        String choices[] = new String[4];
        String quesPic;
        Question ques;
        try {
            sfile = new Scanner(questFile);
            String aLine = sfile.nextLine();
            Scanner sline = new Scanner(aLine);
            totQues = Integer.parseInt(sline.next());

            for (int k = 1; k <= totQues; k++) {
                aLine = sfile.nextLine();
                sline = new Scanner(aLine);
                sline.useDelimiter(":");
                type = Integer.parseInt(sline.next());
                answer = sline.next().charAt(0);
                theQues = sline.next();
                quesPic = "";

                if (type==1 | type == 3) {
                    choices[0] = sline.next();
                    choices[1] = sline.next();
                    choices[2] = sline.next();
                    choices[3] = sline.next();
                    sline.close();
                    ques = new Question(type, answer, theQues, choices, quesPic);
                    quesList.add(ques);
                }
                if (type == 2) {
                    quesPic = sline.next();
                    choices[0] = sline.next();
                    choices[1] = sline.next();
                    choices[2] = sline.next();
                    choices[3] = sline.next();
                    sline.close();
                    ques = new Question(type, answer, theQues, choices, quesPic);
                    quesList.add(ques);
                }
            }
            sfile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File to read " + questFile + " not found!");
        }
    }

    public void startCountdownTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(interval > 0)
                {
                    Platform.runLater(() -> labTimer.setText("Time left: " + interval));
                    System.out.println(interval);
                    interval--;
                }
                else
                    timer.cancel();
            }
        }, 1000,1000);
    }
}

