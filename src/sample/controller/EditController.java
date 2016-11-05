package sample.controller;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.EngineersSingleton;
import sample.model.AlertData;
import sample.model.Engineer;

/**
 * @author  Lektor
 */
public class EditController {
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField categoryField;
    private boolean okClicked;
    private Stage editStage;
    private EngineersSingleton engineers = EngineersSingleton.getInstance();
    private Engineer engineer;

    public void setEditStage(Stage editStage){
        this.editStage = editStage;
    }
    public void setEngineer(Engineer engineer){
        this.engineer = engineer;

        idField.setText(Integer.toString(engineer.getId()));
        nameField.setText(engineer.getName());
        ageField.setText(Integer.toString(engineer.getAge()));
        categoryField.setText(Integer.toString(engineer.getCategory()));
    }
    public boolean isOkClicked(){
        return okClicked;
    }

    private boolean isInputValid(){
        String errorString = "";

        if (idField.getText() == null || idField.getText().length() == 0){
            errorString += "No valid ID\n";
        }else{
            try{
                if (engineers.isIDRepeat(Integer.parseInt(idField.getText()))){
                    errorString += "No valid ID (not to be repeated)\n";
                }
            }catch (NumberFormatException e){
                errorString += "No valid ID (must be an integer)\n";
            }
        }
        if (nameField.getText() == null || nameField.getText().length() == 0){
            errorString += "No valid NAME\n";
        }
        if (ageField.getText() == null || ageField.getText().length() == 0){
            errorString += "No valid AGE\n";
        }else{
            try{
                int age = Integer.parseInt(ageField.getText());
                if (age < 0) {
                    errorString += "No valid AGE(must be positive)\n";
                }
            }catch (NumberFormatException e){
                errorString += "No valid AGE (must be an integer)\n";
            }
        }
        if (categoryField.getText() == null || categoryField.getText().length() == 0){
            errorString += "No valid CATEGORY\n";
        }else{
            try{
                int category = Integer.parseInt(categoryField.getText());
                if (!(category > 0 && category < 4)) {
                    errorString += "No valid CATEGORY(must be from 1 to 3)\n";
                }
            }catch (NumberFormatException e){
                errorString += "No valid CATEGORY (must be an integer)\n";
            }
        }
        if (errorString.length() == 0){
            return true;
        }else{
            new AlertData(
                    editStage,
                    "Invalid Fields",
                    "Please correct invalid fields",
                    errorString,
                    "ERROR"
            );
            return false;
        }
    }
    @FXML
    private void handleOk(){
        if(isInputValid()){
            engineer.setId(Integer.parseInt(idField.getText()));
            engineer.setName(nameField.getText());
            engineer.setAge(Integer.parseInt(ageField.getText()));
            engineer.setCategory(Integer.parseInt(categoryField.getText()));
            okClicked = true;
            editStage.close();
        }
    }
    @FXML
    private void handleCancel(){
        editStage.close();
    }
    public EditController(){}
    @FXML
    public void initialize(){}
}
