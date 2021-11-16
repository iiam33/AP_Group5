package grpassg.grp5;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
public class MyFarewell extends Stage {

    private Label labName, labName2;
    private MediaPlayer mdPlayer;

    public MyFarewell() {
        this.setTitle("Sayonara");
        labName = new Label();
        labName2 = new Label();
        Button btnExit = new Button("EXIT");
        btnExit.setOnAction(e -> {
            this.close();
        });

        HBox myHBox = new HBox(labName, labName2, btnExit);
        myHBox.setAlignment(Pos.CENTER_LEFT);
        this.setScene(new Scene(myHBox, 400, 100));

        String musicFile = "src/data/sayonara.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mdPlayer = new MediaPlayer(sound);
    }

    public void showStage() {
        labName2.setText("RIP " + MyParam.getName2() + " ! ");
        mdPlayer.play();
        this.show();
    }

    public void setName(String name) {
        labName.setText(" Bye " + name + " and ");
    }
}

