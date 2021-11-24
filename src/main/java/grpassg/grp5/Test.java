package grpassg.grp5;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Test extends Stage {
    private final int TIMELIMIT = 5; //Timer duration is set to 5 minutes
    private File questFile = new File("src/data/question", "inputdata.txt");
    private File resultFile = new File("src/data/question", "answers.txt");
    private int totQues = 0; //Total Question
    private int activeQ = 1; //First Question
    private Label labName = new Label("");
    private Label labQuesNo = new Label("");
    private Label labQues = new Label("");
    private ImageView imgQues, imglabA, imglabB, imglabC, imglabD, imgFlag;
    private Label labA, labB, labC, labD;
    private RadioButton radChoice1, radChoice2, radChoice3, radChoice4;
    private ToggleGroup grpChoices;
    private Button btnPrev, btnNext, btnSubmit;
    private Pane testPane, imgPane, paneC;
    private HBox timerPane;
    private Scene testScene;
    private Label labTimer = new Label();
    private Thread timerThread;
    private Login login;
    private Result result;
    private LinkedList<Question> quesList = new LinkedList<Question>();
    private int seconds = TIMELIMIT * 60;
    private int userAnsInt[] = new int[25]; //An array to store user's input
    private String userAnsString[] = new String[25]; //An array to store the user's input in String format
    private String countryName = "";

    public Test(String name, String country) { //The main application window
        this.setTitle("Miss Universe Knowledge Test");
        Label labNameDesc = new Label("Name");
        labNameDesc.setLayoutX(25);
        labNameDesc.setLayoutY(25);
        labNameDesc.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        labName = new Label("");
        labName.setLayoutX(75);
        labName.setLayoutY(25);
        labName.setStyle("-fx-pref-width: 100px;-fx-border-color:black;");

        labQuesNo = new Label("");
        labQuesNo.setLayoutX(25);
        labQuesNo.setLayoutY(75);
        labQuesNo.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        labQuesNo.setTextFill(Color.BLUE);

        labQues = new Label("");
        labQues.setLayoutX(25);
        labQues.setLayoutY(100);
        labQues.setStyle("-fx-font-size: 10pt;-fx-font-weight:bold;");

        labTimer.setText("Time left: " + "");
        labTimer.setTextFill(Color.RED);
        labTimer.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));;
        startCountdownTimer();
        labTimer.setStyle("-fx-font-size: 10pt;-fx-font-weight:bold;");

        imgFlag = new ImageView();
        imgFlag.setFitHeight(60);
        imgFlag.setFitWidth(90);

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

        //Settings for Radio Button A to C
        labA = new Label("A");
        labA.setLayoutX(25);
        labA.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        radChoice1 = new RadioButton("");
        radChoice1.setLayoutX(50);

        labB = new Label("B");
        labB.setLayoutX(25);
        labB.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        radChoice2 = new RadioButton("");
        radChoice2.setLayoutX(50);

        labC = new Label("C");
        labC.setLayoutX(25);
        labC.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        radChoice3 = new RadioButton("");
        radChoice3.setLayoutX(50);

        labD = new Label("D");
        labD.setLayoutX(25);
        labD.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        radChoice4 = new RadioButton("");
        radChoice4.setLayoutX(50);

        imgPane = new Pane();
        imgPane.setLayoutX(200);
        imgPane.setLayoutY(20);
        imgPane.getChildren().add(imgFlag);

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
        btnPrev.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btnPrev.setStyle("-fx-pref-width: 75px;" +
                        "-fx-text-fill: #006464;" +
                        "    -fx-background-color: #DFB951;" +
                        "    -fx-border-radius: 15;" +
                        "    -fx-background-radius: 15;");
        btnPrev.setDisable(true);

        btnNext = new Button("Next");
        btnNext.setLayoutX(125);
        btnNext.setLayoutY(725);
        btnNext.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btnNext.setStyle("-fx-pref-width: 75px;" +
                "-fx-text-fill: #006464;" +
                "    -fx-background-color: #DFB951;" +
                "    -fx-border-radius: 15;" +
                "    -fx-background-radius: 15;");

        btnSubmit = new Button("End");
        btnSubmit.setLayoutX(300);
        btnSubmit.setLayoutY(725);
        btnSubmit.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btnSubmit.setStyle("-fx-pref-width: 75px;" +
                "-fx-text-fill: #006464;" +
                "    -fx-background-color: #DFB951;" +
                "    -fx-border-radius: 15;" +
                "    -fx-background-radius: 15;");

        readFromFile();
        radChoice1.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, true);
            quesList.get(activeQ-1).setSelected(1, false);
            quesList.get(activeQ-1).setSelected(2, false);
            quesList.get(activeQ-1).setSelected(3, false);
            userAnsInt[activeQ-1] = 1;
        });
        radChoice2.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, false);
            quesList.get(activeQ-1).setSelected(1, true);
            quesList.get(activeQ-1).setSelected(2, false);
            quesList.get(activeQ-1).setSelected(3, false);
            userAnsInt[activeQ-1] = 2;
        });
        radChoice3.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, false);
            quesList.get(activeQ-1).setSelected(1, false);
            quesList.get(activeQ-1).setSelected(2, true);
            quesList.get(activeQ-1).setSelected(3, false);
            userAnsInt[activeQ-1] = 3;
        });
        radChoice4.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, false);
            quesList.get(activeQ-1).setSelected(1, false);
            quesList.get(activeQ-1).setSelected(2, false);
            quesList.get(activeQ-1).setSelected(3, true);
            userAnsInt[activeQ-1] = 4;
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
            submitAns(name, country);
            stopTimer();
            this.hide();
            result = new Result();
        });

        timerPane = new HBox();
        timerPane.setLayoutX(25);
        timerPane.setLayoutY(678);
        timerPane.getChildren().add(labTimer);

        testPane = new Pane();
        testPane.getChildren().add(labNameDesc);
        testPane.getChildren().add(labName);
        testPane.getChildren().add(labQuesNo);
        testPane.getChildren().add(labQues);
        testPane.getChildren().add(paneC);
        testPane.getChildren().add(btnNext);
        testPane.getChildren().add(btnPrev);
        testPane.getChildren().add(btnSubmit);
        testPane.getChildren().add(timerPane);
        testPane.getChildren().add(imgPane);
        testPane.setStyle("" +
                "-fx-background-image:" +
                "url('https://www.freepik.com/download-file/15599884');" +
                " -fx-background-repeat: no-repeat; -fx-background-size: 1000 1000; -fx-background-position: center center;");

        testScene = new Scene(testPane, 650, 775);
        reloadQues();
        labName.setText(name);
        countryName = country;
        getFlagImg(countryName);
        startCountdownTimer();
        playCountdownSound();
        this.setScene(testScene);
        this.show();
    }

    public void reloadQues() { //This functions refreshes the question when clicked 'Next' or 'Previous' button
        labQuesNo.setText("Question " + Integer.toString(activeQ) + " of " + quesList.size());
        labQues.setText(quesList.get(activeQ-1).getTheQues());
        labQues.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
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

    public void readFromFile() { //This function reads the content from the file
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
        catch (FileNotFoundException e) { //Print an error saying when result file does not exist
            System.out.println("File to read " + questFile + " not found!");
        }
    }

    public void getFlagImg(String cname) { //Get image from contestant file
        File imgFile = new File("src/data/contestant/flag/" + cname + ".png");
        Image img = new Image(imgFile.toURI().toString());
        imgFlag.setImage(img);
    }

    public void startCountdownTimer() { //Function to start the countdown timer
        timerThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    seconds--;
                    labTimer.setText("Time left: " + displayRemainTime()); //Display the remaining time
                    setSecond(seconds);
                    if (seconds == 60) {
                        playOneMinSound();
                    }
                    if (seconds == 0) {
                        System.exit(0);
                    }
                });
            }
        });   timerThread.start();
    }

    public String displayRemainTime() { //Function to display remaining time
        int min = seconds / 60;
        int minTosec = min * 60;
        int sec = seconds - minTosec;
        String time = String.format("0" + "%d:%02d", min, sec); //Display time in the format of xx minutes xx seconds
        return time;
    }

    public void setSecond(int s) { //Function to update timer so it change along the way
        seconds = s;
    }

    public void stopTimer() { //Function to stop timer
        timerThread.stop();
    }

    public void playOneMinSound() { //Function to play custom music when the time is approaching the limit
        File soundFile =  new File("src/data/question/audio/one_min_remaining.wav").getAbsoluteFile();
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            Clip myClip = AudioSystem.getClip();
            myClip.open(ais);
            myClip.start();
        }
        catch(Exception e) { //Print an error saying when result file does not exist
            System.out.println(e);
        }
    }

    public void playCountdownSound() { //Function to play custom music when the timer starts
        File soundFile =  new File("src/data/question/audio/five_min_remaining.wav").getAbsoluteFile();
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            Clip myClip = AudioSystem.getClip();
            myClip.open(ais);
            myClip.start();
        }
        catch(Exception e) { //Print an error saying when result file does not exist
            System.out.println(e);
        }
    }

    public void convertUserAnsToString() { //Function to convert user's input (int) to Strings (Alphabets)
        for(int i = 0; i < 25; i++) {
            if(userAnsInt[i] == 1)
                userAnsString[i] = "A";
            else if(userAnsInt[i] == 2)
                userAnsString[i] = "B";
            else if(userAnsInt[i] == 3)
                userAnsString[i] = "C";
            else if(userAnsInt[i] == 4)
                userAnsString[i] = "D";
            else
                userAnsString[i] = "null"; //If the user did not choose an answer, it will return null
        }
    }

    public void submitAns(String name, String country){ //Function to submit and save the answer into external files
        reloadQues();
        convertUserAnsToString();
        int correct = compareAns();
        try{
            //Write the user inputs into answers.txt
            PrintWriter fw = new PrintWriter(new FileWriter("src/data/result/answers.txt", true));
            PrintWriter pw = new PrintWriter(fw);
            pw.print(name + ":");
            pw.print(country + ":");

            for (int i = 0; i < userAnsString.length; i++){
                pw.print(userAnsString[i] + ":");
            }

            pw.print(correct);
            pw.println();
            pw.close();
        }
        catch(IOException e){ //Print an error saying when result file does not exist
            System.out.println("File to read " + resultFile + " not found!");
        }
    }

    public int compareAns() {//Function to compare user's input answer and pre-existing answer
        int count = 0; //Initiate count
        char tempAns;

        for (int i = 0; i < userAnsInt.length; i++) {
            tempAns = userAnsString[i].charAt(0); //Get the first character from userAnsString and store it in tempAns

            if (tempAns == (quesList.get(i).getAns())) { /* If the user answer matches the existing
            answer then execute if else statement */
                count++;
            }
        }
        return count;
    }
}

