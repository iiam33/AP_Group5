module grpassg.grp5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens grpassg.grp5 to javafx.fxml;
    exports grpassg.grp5;
}