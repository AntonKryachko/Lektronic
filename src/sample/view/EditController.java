package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Engineer;

/**
 * Created by Lektor on 10.10.2016.
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

   private boolean okClicked = false;
    private Stage editStage;
    private Engineer engineer;
    private Main main;
    public boolean isOkClicked() {
        return okClicked;
    }
    public TextField getIdField(){
        return idField;
    }
    public void setEditStage(Stage editStage) {
        this.editStage = editStage;
    }

    public void setEngineer(Engineer engineer){
        this.engineer = engineer;

        idField.setText(Integer.toString(engineer.getId()));
        nameField.setText(engineer.getName());
        ageField.setText(Integer.toString(engineer.getAge()));
        categoryField.setText(Integer.toString(engineer.getCategory()));
    }

    private boolean isInputValid(){
        String errorString = "";

        if (idField.getText() == null || idField.getText().length() == 0){
            errorString += "No valid ID\n";
        }else{
            try{
                Integer.parseInt(idField.getText());
            }catch (NullPointerException e){
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
                Integer.parseInt(ageField.getText());
            }catch (NullPointerException e){
                errorString += "No valid AGE (must be an integer)\n";
            }
        }
        if (categoryField.getText() == null || categoryField.getText().length() == 0){
            errorString += "No valid CATEGORY\n";
        }else{
            try{
                Integer.parseInt(categoryField.getText());
            }catch (NullPointerException e){
                errorString += "No valid CATEGORY (must be an integer)\n";
            }
        }
        if (errorString.length() != 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editStage);
            alert.setTitle("Invalid Fields");
            alert.setContentText(errorString);

            return false;
        }else {
            return true;
        }
    }

    @FXML
    private void handleOk(){
        if(isInputValid()){
            engineer.setId(Integer.parseInt(idField.getText()));
            engineer.setName(nameField.getText());
            engineer.setAge(Integer.parseInt(ageField.getText()));
            engineer.setCategory(Integer.parseInt(categoryField.getText()));
            editStage.close();
        }
    }
    @FXML
    private void handleCancel(){
        editStage.close();
    }

    public EditController(){}

    @FXML
    public void initialize(){

    }
}
