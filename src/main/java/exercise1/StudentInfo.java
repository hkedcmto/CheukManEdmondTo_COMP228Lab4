package exercise1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class StudentInfo extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(StudentInfo.class.getResource("studentInfo_view.fxml")); //call from view
        Scene scene = new Scene(fxmlLoader.load(), 900, 300); //size of the window
        stage.setTitle("Student Info"); //title of the window
        stage.setScene(scene);
        stage.show(); //display window
    }

    public static void main(String[] args) {
        launch();
    }
}