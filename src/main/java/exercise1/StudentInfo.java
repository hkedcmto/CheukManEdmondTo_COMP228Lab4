package exercise1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashSet;

public class StudentInfo extends Application {
    private TextField nameField, addressField, cityField, provinceField, postalField, phoneField, emailField;
    private RadioButton csRadio, businessRadio;
    private ComboBox<String> courseComboBox;
    private ListView<String> courseListView;
    private CheckBox studentCouncilCheckBox, volunteerWorkCheckBox;
    private TextArea displayArea;
    private HashSet<String> selectedCourses = new HashSet<>(); // To prevent duplicate courses

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Information");

        // Create input fields
        nameField = new TextField();
        addressField = new TextField();
        cityField = new TextField();
        provinceField = new TextField();
        postalField = new TextField();
        phoneField = new TextField();
        emailField = new TextField();

        // Create radio buttons for major
        csRadio = new RadioButton("Computer Science");
        businessRadio = new RadioButton("Business");
        ToggleGroup majorGroup = new ToggleGroup();
        csRadio.setToggleGroup(majorGroup);
        businessRadio.setToggleGroup(majorGroup);

        // Course selection ComboBox and ListView
        courseComboBox = new ComboBox<>();
        courseListView = new ListView<>();

        // Checkboxes for additional info
        studentCouncilCheckBox = new CheckBox("Student Council");
        volunteerWorkCheckBox = new CheckBox("Volunteer Work");

        // Display area
        displayArea = new TextArea();
        displayArea.setEditable(false);

        // Layout the components using GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Address:"), 0, 1);
        grid.add(addressField, 1, 1);
        grid.add(new Label("City:"), 0, 2);
        grid.add(cityField, 1, 2);
        grid.add(new Label("Province:"), 0, 3);
        grid.add(provinceField, 1, 3);
        grid.add(new Label("Postal Code:"), 0, 4);
        grid.add(postalField, 1, 4);
        grid.add(new Label("Phone Number:"), 0, 5);
        grid.add(phoneField, 1, 5);
        grid.add(new Label("Email:"), 0, 6);
        grid.add(emailField, 1, 6);

        // Major selection
        VBox majorBox = new VBox(10, csRadio, businessRadio);
        grid.add(new Label("Major:"), 0, 7);
        grid.add(majorBox, 1, 7);

        // Course selection
        grid.add(new Label("Courses:"), 0, 8);
        grid.add(courseComboBox, 1, 8);
        Button addCourseButton = new Button("Add Course");
        addCourseButton.setOnAction(e -> addCourse());
        grid.add(addCourseButton, 2, 8);
        grid.add(courseListView, 1, 9);

        // Additional info
        HBox extraActivities = new HBox(10, studentCouncilCheckBox, volunteerWorkCheckBox);
        grid.add(extraActivities, 1, 10);

        // Display button and text area
        Button displayButton = new Button("Display");
        displayButton.setOnAction(e -> displayInfo());
        grid.add(displayButton, 1, 11);
        grid.add(displayArea, 1, 12, 2, 1);

        // Set initial course list based on major selection
        csRadio.setOnAction(e -> updateCourseList("Computer Science"));
        businessRadio.setOnAction(e -> updateCourseList("Business"));

        Scene scene = new Scene(grid, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Update courses based on selected major
    private void updateCourseList(String major) {
        courseComboBox.getItems().clear();
        if (major.equals("Computer Science")) {
            courseComboBox.getItems().addAll("Java", "Python", "C#");
        } else if (major.equals("Business")) {
            courseComboBox.getItems().addAll("Economics", "Marketing", "Accounting");
        }
    }

    // Add course to ListView if not already added
    private void addCourse() {
        String course = courseComboBox.getValue();
        if (course != null && selectedCourses.add(course)) {
            courseListView.getItems().add(course);
        }
    }

    // Display all student information in the TextArea
    private void displayInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Name: ").append(nameField.getText()).append("\n");
        info.append("Address: ").append(addressField.getText()).append("\n");
        info.append("City: ").append(cityField.getText()).append("\n");
        info.append("Province: ").append(provinceField.getText()).append("\n");
        info.append("Postal Code: ").append(postalField.getText()).append("\n");
        info.append("Phone Number: ").append(phoneField.getText()).append("\n");
        info.append("Email: ").append(emailField.getText()).append("\n");

        info.append("Major: ").append(csRadio.isSelected() ? "Computer Science" : "Business").append("\n");

        info.append("Courses: ");
        for (String course : courseListView.getItems()) {
            info.append(course).append(", ");
        }
        info.append("\n");

        if (studentCouncilCheckBox.isSelected()) info.append("Student Council\n");
        if (volunteerWorkCheckBox.isSelected()) info.append("Volunteer Work\n");

        displayArea.setText(info.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

