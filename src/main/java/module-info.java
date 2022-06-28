module java_btl {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    //requires javafx.controls;
    requires javafx.fxml;

    opens java_btl to javafx.fxml;
    exports java_btl;
}
