package sample.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.EngineersSingleton;
import sample.Main;
import sample.model.AlertData;
import sample.model.Engineer;
import sample.model.Sort.CompCatAscAgeDescNameAsc;

/**
 * @author Lektor
 */
public class EngineerOverviewController {
    private Main main;
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private EngineersSingleton engineers = EngineersSingleton.getInstance();
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
    @FXML
    private TextField deleteUnderAgeField;
    @FXML
    private TextField deleteUnderCatField;
    @FXML
    private TextField filterField;

    public void setMain(Main main) {
        this.main = main;
    }
    @FXML
    private void handleSortCat(){
        engineers.getEngineers().sort(new CompCatAscAgeDescNameAsc());
        engineerTable.setItems(engineers.getEngineers());
    }
    private boolean isValidFilterField() {
        String filter = filterField.getText(),
                errorMsg = "";
        if (radioName.isSelected()) {
            if (filter == null || filter.length() == 0) {
                errorMsg += "No valid, please enter NAME\n";
            }
        } else if (radioAge.isSelected()) {
            if (filter == null || filter.length() == 0) {
                errorMsg += "No valid, please enter AGE\n";
            } else {
                try {
                    int age = Integer.parseInt(filter);
                    if (!(age > 0)) {
                        errorMsg += "No valid (AGE must be positive)\n";
                    }
                } catch (NumberFormatException e) {
                    errorMsg += "No valid (AGE must be integer)\n";
                }
            }
        } else if (radioCategory.isSelected()) {
            if (filter == null || filter.length() == 0) {
                errorMsg += "No valid, please enter CATEGORY\n";
            } else {
                try {
                    int category = Integer.parseInt(filter);
                    if (!((category > 0) && (category < 4))) {
                        errorMsg += "No valid (CATEGORY must be from 1 to 3)\n";
                    }
                } catch (NumberFormatException e) {
                    errorMsg += "No valid (CATEGORY must be integer)\n";
                }
            }
        }
        if (errorMsg.length() == 0) {
            return true;
        } else {
            new AlertData(
                    main.getPrimaryStage(),
                    "Invalid Filter!",
                    "Entered Filter invalid",
                    errorMsg,
                    "ERROR"
            );
            return false;
        }
    }
    @FXML
    private void handleFilter() {
        if(isValidFilterField()){
            String filter = filterField.getText();
            ObservableList<Engineer> list = engineers.getEngineers();
            ObservableList<Engineer> ol = FXCollections.observableArrayList();
            if (radioName.isSelected()) {
                for (Engineer eng : list) {
                    if (filter.equals(eng.getName())) {
                        ol.add(eng);
                    }
                }
            } else if (radioAge.isSelected()) {
                for (Engineer eng : list) {
                    if (filter.equals(Integer.toString(eng.getAge()))) {
                        ol.add(eng);
                    }
                }
            } else if (radioCategory.isSelected()) {
                for (Engineer eng : list) {
                    if (filter.equals(Integer.toString(eng.getCategory()))) {
                        ol.add(eng);
                    }
                }
            }
            engineerTable.setItems(ol);
            filterField.setText("");
        }
    }
    @FXML
    private void handleReset(){
        engineerTable.setItems(engineers.getEngineers());
    }
    @FXML
    private void handleDeleteUnderAge() {
        String removeUnderAge = deleteUnderAgeField.getText(),
                errorMSG = "";
        if (removeUnderAge == null || removeUnderAge.length() == 0) {
            errorMSG += "No valid, please enter text in remove age field\n";
        } else {
            try {
                int age = Integer.parseInt(removeUnderAge);
                if (age < 0) {
                    errorMSG += "No valid (age must be positive)\n";
                }
            } catch (NumberFormatException e) {
                errorMSG += "No valid (remove age must be integer)\n";
            }
        }
        if (errorMSG.length() == 0) {
            engineers.removeUnderAge(Integer.parseInt(removeUnderAge));
            engineerTable.setItems(engineers.getEngineers());
        } else {
            new AlertData(
                    main.getPrimaryStage(),
                    "Invalid Age!",
                    "Entered Age invalid",
                    errorMSG,
                    "ERROR"
            );
        }
        deleteUnderAgeField.setText("");
    }
    @FXML
    private void handleDeleteUnderCat() {
        String removeUnderCategory = deleteUnderCatField.getText(),
                errorMSG = "";
        if (removeUnderCategory == null || removeUnderCategory.length() == 0) {
            errorMSG += "No valid, please enter text in remove category field\n";
        } else {
            try {
                int category = Integer.parseInt(removeUnderCategory);
                if (!(category > 0 && category < 4)) {
                    errorMSG += "No valid (category must be from 1 to 3)\n";
                }
            } catch (NumberFormatException e) {
                errorMSG += "No valid (remove category must be integer)\n";
            }
        }
        if (errorMSG.length() == 0) {
            engineers.removeUnderCategory(Integer.parseInt(removeUnderCategory));
            engineerTable.setItems(engineers.getEngineers());
        } else {
            new AlertData(
                    main.getPrimaryStage(),
                    "Invalid Category!",
                    "Entered Category invalid",
                    errorMSG,
                    "ERROR"
            );
        }
        deleteUnderCatField.setText("");
    }
    @FXML
    private void handleSpecial() {
        main.showSpecial();
    }
    @FXML
    private void handleAdd() {
        Engineer engineer = new Engineer();
        boolean okID = main.showEditEngineers(engineer);
        if (okID){
            engineers.add(engineer);
        }
    }
    @FXML
    private void handleEdit() {
        Engineer selectedEngineer = engineerTable.getSelectionModel().getSelectedItem();
        if (selectedEngineer != null){
            main.showEditEngineers(selectedEngineer);
        } else {
            new AlertData(
                    main.getPrimaryStage(),
                    "No Selection",
                    "No Engineer Selected",
                    "Please select a engineer in the table",
                    "WARNING"
            );
        }
    }
    @FXML
    private void handleDeleteById() {
        int selectedIndex = engineerTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            engineerTable.getItems().remove(selectedIndex);
        } else {
            new AlertData(
                    main.getPrimaryStage(),
                    "No Selection",
                    "No Engineer Selected",
                    "Please select a engineer in the table",
                    "WARNING"
            );
        }
    }
    public EngineerOverviewController() {}
    @FXML
    public void initialize() {
        FXCollections.sort(engineers.getEngineers());
        engineerTable.setItems(engineers.getEngineers());

        radioAge.setToggleGroup(toggleGroup);
        radioName.setToggleGroup(toggleGroup);
        radioCategory.setToggleGroup(toggleGroup);
        radioName.setSelected(true);

//        numberColumn.setCellValueFactory(cellData -> cellData.getTableColumn());
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty().asObject());
    }
}