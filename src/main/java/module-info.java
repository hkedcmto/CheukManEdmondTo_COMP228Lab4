module exercise1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens exercise1 to javafx.fxml;
    exports exercise1; // Exports the package containing the StudentInfo class
}