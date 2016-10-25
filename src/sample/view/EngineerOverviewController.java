package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Main;
import sample.model.Engineer;
import sample.model.Sort.CompCatAscAgeDesc;
import sample.model.Sort.CompCatDescAgeAsc;
import sample.model.Sort.CompNameAscAgeDesc;

/**
 * Created by Lektor on 10.10.2016.
 */
public class EngineerOverviewController {
    private Main main;

    private int number = 0;
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

    private ToggleGroup g;

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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Invalid Filter!");
            alert.setHeaderText("Entered Filter invalid");
            alert.setContentText(errorMsg);
            alert.showAndWait();
            return false;
        }
    }
    @FXML
    private void handleFilter() {
        if (isValidFilterField()) {
            String filter = filterField.getText();
            ObservableList<Engineer> list = main.getEngineers();
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
    private void handleReset() {
        engineerTable.setItems(main.getEngineers());
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
            main.removeUnder(Integer.parseInt(removeUnderAge), true);
            engineerTable.setItems(main.getEngineers());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Invalid Age!");
            alert.setHeaderText("Entered Age invalid");
            alert.setContentText(errorMSG);
            alert.showAndWait();
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
            main.removeUnder(Integer.parseInt(removeUnderCategory), false);
            engineerTable.setItems(main.getEngineers());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Invalid Category!");
            alert.setHeaderText("Entered Category invalid");
            alert.setContentText(errorMSG);
            alert.showAndWait();
        }
        deleteUnderCatField.setText("");
    }
    @FXML
    private void handleSpecial() {
        main.showSpecialData();
    }
    @FXML
    private void handleAdd() {
        Engineer engineer = new Engineer();
        boolean okID = main.showEngineerEdit(engineer);
        if (okID) {
            main.getEngineers().add(engineer);
        }
    }
    @FXML
    private void handleEdit() {
        Engineer selectedEng = engineerTable.getSelectionModel().getSelectedItem();
        if (selectedEng != null) {
            main.showEngineerEdit(selectedEng);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Engineer Selected");
            alert.setContentText("Please select a engineer in the table");

            alert.showAndWait();
        }
    }
    @FXML
    private void handleDeleteById() {
        int selectedEng = engineerTable.getSelectionModel().getSelectedIndex();
        if (selectedEng >= 0) {
            engineerTable.getItems().remove(selectedEng);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Engineer Selected");
            alert.setContentText("Please select a engineer in the table");
            alert.showAndWait();
        }
    }
    public EngineerOverviewController() {}
    @FXML
    public void initialize() {
        g = new ToggleGroup();
        radioAge.setToggleGroup(g);
        radioName.setToggleGroup(g);
        radioCategory.setToggleGroup(g);
        radioName.setSelected(true);

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty().asObject());

    }
    public void setMain(Main main) {
        this.main = main;
        engineerTable.setItems(main.getEngineers());
    }
}