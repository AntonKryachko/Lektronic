package sample.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.EngineersSingleton;

/**
 * Created by Lektor on 30.10.2016.
 */
public class SpecialController {
    public int CATEGORY_1 = 1;
    public int CATEGORY_2 = 2;
    public int CATEGORY_3 = 3;
    private Stage specialStage;
    private EngineersSingleton engineers = EngineersSingleton.getInstance();
    @FXML
    private Label cat1;
    @FXML
    private Label cat2;
    @FXML
    private Label cat3;
    @FXML
    private Label maxAge;
    @FXML
    private Label minAge;
    @FXML
    private void handleClose(){specialStage.close();}
    public void setSpecialStage(Stage specialStage){
        this.specialStage = specialStage;
    }
    public SpecialController(){}
    @FXML
    public void initialize(){
        cat1.setText(Integer.toString(engineers.sumCategory(CATEGORY_1)));
        cat2.setText(Integer.toString(engineers.sumCategory(CATEGORY_2)));
        cat3.setText(Integer.toString(engineers.sumCategory(CATEGORY_3)));
        maxAge.setText("Инженер: " + ((engineers.maxAge() == null) ? "Unknown" : engineers.maxAge()));
        minAge.setText("Инженер: " + ((engineers.minAge() == null) ? "Unknown" : engineers.minAge()));
    }
}
