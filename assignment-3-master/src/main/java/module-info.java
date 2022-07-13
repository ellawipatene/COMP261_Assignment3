module comp261.tut3 {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;

    opens comp261.assig3 to javafx.fxml;
    exports comp261.assig3;
}
