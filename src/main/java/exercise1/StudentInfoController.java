package exercise1;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class StudentInfoController {

    // Row 1
    @FXML
    private Label nameLbl;
    @FXML
    private TextField nameTf;
    @FXML
    private RadioButton compSciRdo;
    @FXML
    private RadioButton busRdo;

    //Row 2
    @FXML
    private Label addressLbl;
    @FXML
    private TextField addressTf;
    @FXML
    private CheckBox studentCouncilCbx;
    @FXML
    private ComboBox<String> coursesCbx;
    @FXML
    private ListView<String> courseSelectListView;

    //Row 3
    @FXML
    private Label provinceLbl;
    @FXML
    private TextField provinceTf;

    //Row 4
    @FXML
    private Label cityLbl;
    @FXML
    private TextField cityTf;

    //Row 5
    @FXML
    private Label postalCodeLbl;
    @FXML
    private TextField postalCodeTf;

    //Row 6
    @FXML
    private Label phoneLbl;
    @FXML
    private TextField phoneTf;
    @FXML
    private CheckBox volunteerWorkCbx;

    //Row 7
    @FXML
    private Label emailLbl;
    @FXML
    private TextField emailTf;

    //Row 8
    @FXML
    private Button submitBtn;

    //Row 9
    @FXML
    private TextArea resultTxa;

    //initializer
    @FXML
    private void initialize() {

        //set radios into same group
        ToggleGroup majorGroup = new ToggleGroup();
        compSciRdo.setToggleGroup(majorGroup);
        busRdo.setToggleGroup(majorGroup);

        //add items into dropdown menu
        courseSetItems(0);
    }

    // A combo box will display the list of courses for each program whenever the user selects the desired program.
    public void courseSetItems(int course){
        coursesCbx.getItems().clear();
        courseSelectListView.getItems().clear();
        if (course == 0) {
                coursesCbx.setItems(FXCollections.observableArrayList("Python", "Java", "C#"));
        }else{
                coursesCbx.setItems(FXCollections.observableArrayList("Marketing", "Accounting", "Econ"));
        }
    }
    public void onCompSciRdoClicked() {
        courseSetItems(0);
    }
    public void onBusRdoClicked() {
        courseSetItems(1);
    }

    //A course will be added to a list box whenever the user selects a course from the corresponding combo box.
    public void onCoursesCbxChanged() {
        String selectedCourse = coursesCbx.getValue();
        if (isCourseUnique(selectedCourse)) { // Make sure that the user cannot add a course several times
            courseSelectListView.getItems().add(selectedCourse);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Cannot add repeated course");
            alert.showAndWait();
        }
    }

    // Check course exists (already added before) in the course list
    private boolean isCourseUnique(String course){
        for(String item: courseSelectListView.getItems()){
            if(item.equals(course)){
                return false;
            }
        }
        return true;
    }

    //postal code validation
    private boolean isValidPostalCode(String postalCode) {
        String regex = "^[A-Za-z]\\d[A-Za-z] ?\\d[A-Za-z]\\d$";
        return postalCode.matches(regex);
    }

    //Phone number validation
    private boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^(\\+1\\s?)?(\\(\\d{3}\\)|\\d{3})[\\s-]?\\d{3}[\\s-]?\\d{4}$";
        return phoneNumber.matches(regex);
    }

    //Email validation
    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    //All the information about the student will be displayed in a text area component.
    private void displayResult() {
        String result;
        result = nameTf.getText() + ", "
                + addressTf.getText() + ", "
                + cityTf.getText() + ", "
                + provinceTf.getText() + ", "
                + postalCodeTf.getText() + ", "
                + phoneTf.getText() + ", "
                + emailTf.getText() + "\n"
                + "Courses:";
        for(String item: courseSelectListView.getItems()){
            result += "\n" + item;
        }
        if(studentCouncilCbx.isSelected()){
            result += "\nhas Student Council";
        }
        if(volunteerWorkCbx.isSelected()){
            result += "\nhas Volunteer Work";
        }
        resultTxa.setText(result);
    }


    public void onDisplayButtonClick(){
        try{
            // Empty input check
            if (nameTf.getText().isEmpty() ||
                    addressTf.getText().isEmpty() ||
                    provinceTf.getText().isEmpty() ||
                    cityTf.getText().isEmpty() ||
                    postalCodeTf.getText().isEmpty() ||
                    phoneTf.getText().isEmpty() ||
                    emailTf.getText().isEmpty()  ||
                    courseSelectListView.getItems().isEmpty()) {
                throw new Exception("Please fill in all fields");
            }

            //Postal Code validation
            if(!isValidPostalCode(postalCodeTf.getText())){
                throw new Exception("Invalid postal code");
            }

            //Phone number validation
            if(!isValidPhoneNumber(phoneTf.getText())){
                throw new Exception("Invalid phone number");
            }

            //Email validation
            if(!isValidEmail(emailTf.getText())){
                throw new Exception("Invalid email");
            }

            //All the information about the student will be displayed in a text area component.
            displayResult();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
