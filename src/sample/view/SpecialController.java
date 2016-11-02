package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Main;
import sample.model.AlertData;
import sample.model.Engineer;

import java.util.List;

/**
 * Created by Lektor on 30.10.2016.
 */
public class SpecialController {
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
    private void handleClose(){
        specialStage.close();
    }
    private Stage specialStage;
    private Main main;


    private ObservableList<Engineer> engineers = FXCollections.observableArrayList();
    public void setEngineers (List<Engineer> engineers){
        this.engineers = FXCollections.observableArrayList(engineers);
    }
    public void setSpecialStage(Stage specialStage){
        this.specialStage = specialStage;
    }
    public void setMain(Main main){
        this.main = main;
    }
    public SpecialController(){}
    @FXML
    public void initialize(){
        System.out.println(sum1cat());
        try{
            cat1.setText(Integer.toString(sum1cat()));
            cat2.setText(Integer.toString(sum2cat()));
            cat3.setText(Integer.toString(sum3cat()));
            maxAge.setText("Инженер: " + ((maxAge() == null) ? "Unknown" : maxAge()));
            minAge.setText("Инженер: " + ((minAge() == null) ? "Unknown" : minAge()));
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
    public int sum1cat(){
        int i = 0;
        for (Engineer engineer: engineers){
            if (engineer.getCategory() == 1){
                i++;
            }
        }
        return i;
    }
    private int sum2cat(){
        int i = 0;
        for (Engineer engineer: engineers){
            if (engineer.getCategory() == 2){
                i++;
            }
        }
        return i;
    }
    private int sum3cat(){
        int i = 0;
        for (Engineer engineer: engineers){
            if (engineer.getCategory() == 3){
                i++;
            }
        }
        return i;
    }
    private Engineer maxAge(){
        Engineer engineer = null;
        int max = 0;
        for (Engineer engineer1: engineers){
            if (engineer1.getAge() > max) {
                engineer = engineer1;
            }
        }
        return engineer;
    }
    private Engineer minAge(){
        Engineer engineer = null;
        int min = 200;
        for (Engineer engineer1: engineers){
            if (engineer1.getAge() < min){
                engineer = engineer1;
            }
        }
        return engineer;
    }
}
