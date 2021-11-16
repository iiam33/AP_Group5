package grpassg.grp5;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class MyTest extends Application {

    private File myf = new File("src/data", "questions.txt");
    private int totQues = 0;
    private int activeQ = 1; //first q
    private Label labQuesNo, labQues, labName;
    private ImageView imgQues;
    private Label labA, labB, labC, labD;
    private RadioButton radChoice1, radChoice2, radChoice3, radChoice4;
    private ToggleGroup grpChoices;
    private Button btnPrev, btnNext, btnSubmit;
    private Pane mainPane;
    private Pane paneC;
    private Scene mainScene;
    private MyGreeting winGreeting;
    private MyFarewell winFarewell;
    private LinkedList<Question> quesList = new LinkedList<Question>();

    public void start(Stage mainStage) {
        mainStage.setTitle("Knowledge Test 1");
        Label labNameDesc = new Label("Name");
        labNameDesc.setLayoutX(25);
        labNameDesc.setLayoutY(25);
        labName = new Label("");
        labName.setLayoutX(75);
        labName.setLayoutY(25);
        labName.setStyle("-fx-pref-width: 100px;-fx-border-color:red;");

        labQuesNo = new Label("");
        labQuesNo.setLayoutX(25);
        labQuesNo.setLayoutY(75);
        labQuesNo.setStyle("-fx-font-family:serif;-fx-text-fill:#0000ff;");

        labQues = new Label("");
        labQues.setLayoutX(25);
        labQues.setLayoutY(100);
        labQues.setStyle("-fx-font-size: 12pt;-fx-font-weight:bold;");

        imgQues = new ImageView();
        imgQues.setLayoutX(25);
        imgQues.setLayoutY(75);
        imgQues.setFitHeight(150); //150
        imgQues.setFitWidth(300); //150

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
        paneC.setLayoutY(75);
        paneC.getChildren().add(imgQues);

        paneC.getChildren().add(labA);
        paneC.getChildren().add(radChoice1);

        paneC.getChildren().add(labB);
        paneC.getChildren().add(radChoice2);

        paneC.getChildren().add(labC);
        paneC.getChildren().add(radChoice3);

        paneC.getChildren().add(labD);
        paneC.getChildren().add(radChoice4);

        btnPrev = new Button("Prev");
        btnPrev.setLayoutX(25);
        btnPrev.setLayoutY(550);
        btnPrev.setStyle("-fx-pref-width: 75px;");
        btnPrev.setDisable(true);
        btnNext = new Button("Next");
        btnNext.setLayoutX(125);
        btnNext.setLayoutY(550);
        btnNext.setStyle("-fx-pref-width: 75px;");
        btnSubmit = new Button("End");
        btnSubmit.setLayoutX(300);
        btnSubmit.setLayoutY(550);
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
            mainStage.hide();
            winFarewell.showStage();
        });
        mainPane = new Pane();
        mainPane.getChildren().add(labNameDesc);
        mainPane.getChildren().add(labName);
        mainPane.getChildren().add(labQuesNo);
        mainPane.getChildren().add(labQues);
        mainPane.getChildren().add(paneC);
        mainPane.getChildren().add(btnNext);
        mainPane.getChildren().add(btnPrev);
        mainPane.getChildren().add(btnSubmit);

        mainScene = new Scene(mainPane, 700, 800); //400 600
        mainStage.setScene(mainScene);
        reloadQues();
        winGreeting = new MyGreeting();
        winGreeting.setOnHiding(e -> {
            labName.setText(winGreeting.getName());
            mainStage.show();
        });
        winFarewell = new MyFarewell();
    }

    public void reloadQues() {
        labQuesNo.setText("Question " + Integer.toString(activeQ));
        labQues.setText(quesList.get(activeQ-1).getTheQues());
        radChoice1.setText(quesList.get(activeQ-1).getChoice(0));
        radChoice2.setText(quesList.get(activeQ-1).getChoice(1));
        radChoice3.setText(quesList.get(activeQ-1).getChoice(2));
        radChoice4.setText(quesList.get(activeQ-1).getChoice(3));
        imgQues.setImage(null);

        //type A
        if (quesList.get(activeQ-1).getType() == 1) {
            labA.setLayoutY(75);
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
            File pFile = new File("src/data/" + quesList.get(activeQ-1).getQuesPic());
            Image img = new Image(pFile.toURI().toString());
            imgQues.setImage(img);
            labA.setLayoutY(275);
            radChoice1.setLayoutY(275);
            labB.setLayoutY(325);
            radChoice2.setLayoutY(325);
            labC.setLayoutY(375);
            radChoice3.setLayoutY(375);
            labD.setLayoutY(425);
            radChoice4.setLayoutY(425);
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
            sfile = new Scanner(myf);
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
                if (type == 2)
                    quesPic = sline.next();
                choices[0] = sline.next();
                choices[1] = sline.next();
                choices[2] = sline.next();
                choices[3] = sline.next();

                sline.close();
                ques = new Question(type, answer, theQues, choices, quesPic);
                quesList.add(ques);
            }
            sfile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File to read " + myf + " not found!");
        }
    }

    public static void main(String args[]) {
        Application.launch(args);
    }
}

