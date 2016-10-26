package sample.model;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * @author Lektor on 26.10.2016.
 */
public class AlertData {
    public AlertData(Stage stage, String title, String headerText, String contestText, Alert.AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.initOwner(stage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contestText);

        alert.showAndWait();
    }
}
