package sample.view;

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
    @FXML
    private void handleFilter(){

    }
    @FXML
    private void handleDeleteUnderAge(){

    }
    @FXML
    private void handleDeleteUnderCat(){

    }
    private boolean checkID(int id){
        int i = 0;
        for (Engineer e: main.getEngineers()) {
            if(e.getId() == id) {
                i++;
            }
        }
        if (i != 0){
            return false;
        }
        else {
            return true;
        }
    }
    @FXML
    private void handleAdd(){
        Engineer engineer = new Engineer();
        main.showEngineerEdit(engineer);
        main.getEngineers().add(engineer);
    }
    @FXML
    private void handleEdit(){
        Engineer selectedEng = engineerTable.getSelectionModel().getSelectedItem();
        if (selectedEng != null){
            main.showEngineerEdit(selectedEng);
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
    private void handleSpecial(){

    }
    @FXML
    private void handleDeleteById(){
        int selectedEng = engineerTable.getSelectionModel().getSelectedIndex();
        if(selectedEng >=0){
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
