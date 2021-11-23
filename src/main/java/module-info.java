module grpassg.grp5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;


    opens grpassg.grp5 to javafx.fxml;
    exports grpassg.grp5;
}