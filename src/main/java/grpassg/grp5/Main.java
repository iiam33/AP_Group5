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
    public void start(Stage mainStage) {
        mainStage.setTitle("Welcome to MUKT");
        Label mainTitle = new Label("*Miss Universe Knowledge Test*" + "\n");
        mainTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 18));
        mainTitle.setLayoutX(60);
        mainTitle.setLayoutY(90);

        Label groupName = new Label("- GROUP 5 -");
        groupName.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));
        groupName.setLayoutX(160);
        groupName.setLayoutY(120);

        btnLogin = new Button("Login");
        btnLogin.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btnLogin.setLayoutX(170);
        btnLogin.setLayoutY(160);
        btnLogin.setOnAction(e -> {
            test.start(mainStage);
        });

        btnResult = new Button("Result");
        btnResult.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btnResult.setLayoutX(90);
        btnResult.setLayoutY(160);

        btnResult.setOnAction(e -> {
            new Result();
        });

        btnAnalysis = new Button("Analysis");
        btnAnalysis.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btnAnalysis.setLayoutX(240);
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
        btnResult.setStyle("" +
                "-fx-text-fill: #006464;" +
                "    -fx-background-color: #DFB951;" +
                "    -fx-border-radius: 15;" +
                "    -fx-background-radius: 15;");
        btnAnalysis.setStyle("" +
                "-fx-text-fill: #006464;" +
                "    -fx-background-color: #DFB951;" +
                "    -fx-border-radius: 15;" +
                "    -fx-background-radius: 15;");
        btnLogin.setStyle("" +
                "-fx-text-fill: #006464;" +
                "    -fx-background-color: #DFB951;" +
                "    -fx-border-radius: 15;" +
                "    -fx-background-radius: 15;");
        mainPane.setStyle("" +
                "-fx-background-image:" +
                "url('https://www.freepik.com/download-file/15599884');" +
                " -fx-background-repeat: no-repeat; -fx-background-size: 1000 1000; -fx-background-position: center center;");
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
