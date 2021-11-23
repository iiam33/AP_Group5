package grpassg.grp5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
    private Scene mainScene;
    private Button btnLogin, btnResult, btnAnalysis;
    private Pane mainPane;
    private Test test = new Test();

    @Override
    public void start(Stage mainStage) { //Main application window

        mainStage.setTitle("Welcome");
        Label mainTitle = new Label("Miss Universe Knowledge Test (MUKT)");
        mainTitle.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 18));
        mainTitle.setLayoutX(50);
        mainTitle.setLayoutY(90);

        Label groupName = new Label("- Group 5 -");
        groupName.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        groupName.setLayoutX(170);
        groupName.setLayoutY(120);

        btnLogin = new Button("Login");
        btnLogin.setLayoutX(100);
        btnLogin.setLayoutY(160);

        btnLogin.setOnAction(e -> {
            test.start(mainStage);
        });

        btnResult = new Button("Result");
        btnResult.setLayoutX(180);
        btnResult.setLayoutY(160);

        btnResult.setOnAction(e -> {
            new Result();
        });

        btnAnalysis = new Button("Analysis");
        btnAnalysis.setLayoutX(255);
        btnAnalysis.setLayoutY(160);

        btnAnalysis.setOnAction(e -> {
            new Analysis();
        });

        mainPane = new Pane();
        mainPane.getChildren().add(mainTitle);
        mainPane.getChildren().add(groupName);
        mainPane.getChildren().add(btnLogin);
        mainPane.getChildren().add(btnResult);
        mainPane.getChildren().add(btnAnalysis);
        mainScene = new Scene(mainPane, 400, 300);
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
