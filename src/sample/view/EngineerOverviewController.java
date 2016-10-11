package sample.view;

import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Main;
import sample.model.Engineer;

/**
 * Created by Lektor on 10.10.2016.
 */
public class EngineerOverviewController {

    private Main main;

    @FXML
    private TableView<Engineer> engineerTable;
    @FXML
    private TableColumn<Engineer, Integer> numberColumn;
    @FXML
    private TableColumn<Engineer, Integer> idColumn;
    @FXML
    private TableColumn<Engineer, String> nameColumn;
    @FXML
    private TableColumn<Engineer, Integer> ageColumn;
    @FXML
    private TableColumn<Engineer, Integer> categoryColumn;

    @FXML
    private RadioButton radioName;
    @FXML
    private RadioButton radioAge;
    @FXML
    private RadioButton radioCategory;
    private ToggleGroup g;
    private int number = 0;
    @FXML
    private void handleFilter(){

    }
    @FXML
    private void handleDeleteUnderAge(){

    }
    @FXML
    private void handleDeleteUnderCat(){

    }
    @FXML
    private void handleSpecial(){
        main.showSpecialData();
    }
    @FXML
    private void handleAdd(){
        Engineer engineer = new Engineer();
        boolean okID = main.showEngineerEdit(engineer);
        if (okID){
            main.getEngineers().add(engineer);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("ID repeated");
            alert.setHeaderText("ID repeated ");
            alert.setContentText("Please select another ID for an engineer");

            alert.showAndWait();
        }
    }
    @FXML
    private void handleEdit(){
        Engineer selectedEng = engineerTable.getSelectionModel().getSelectedItem();
        if (selectedEng != null){
            boolean okID =  main.showEngineerEdit(selectedEng);
            if (okID){
                System.out.println("all is OK");
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(main.getPrimaryStage());
                alert.setTitle("ID repeated");
                alert.setHeaderText("ID repeated ");
                alert.setContentText("Please select another ID for an engineer");

                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Engineer Selected");
            alert.setContentText("Please select a engineer in the table");

            alert.showAndWait();
        }
    }
    @FXML
    private void handleDeleteById(){
        int selectedEng = engineerTable.getSelectionModel().getSelectedIndex();
        if  (selectedEng >=0){
            engineerTable.getItems().remove(selectedEng);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Engineer Selected");
            alert.setContentText("Please select a engineer in the table");
            alert.showAndWait();
        }
    }

    public EngineerOverviewController(){}

    @FXML
    public void initialize(){
        g = new ToggleGroup();
        radioAge.setToggleGroup(g);
        radioName.setToggleGroup(g);
        radioCategory.setToggleGroup(g);

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty().asObject());
    }

    public void setMain(Main main){
        this.main = main;
        engineerTable.setItems(main.getEngineers());
    }

}
