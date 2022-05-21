module java_btl {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;

    opens java_btl to javafx.fxml;
    exports java_btl;
}
