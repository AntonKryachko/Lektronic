package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Engineer;

/**
 * Created by Lektor on 10.10.2016.
 */
public class SpecialDataController {
    @FXML
    private Label sum1cat;
    @FXML
    private Label sum2cat;
    @FXML
    private Label sum3cat;
    @FXML
    private Label maxAge;
    @FXML
    private Label minAge;

    private Stage specialStage;
    public void setSpecialStage(Stage specialStage){
        this.specialStage = specialStage;
    }
    public void setMain(Main main){this.main = main;}
    @FXML
    private void handleClose(){
        specialStage.close();
    }
    private Main main;

    @FXML
    public void initialize(){
        sum1cat.setText(Integer.toString(sumCat1()));
        sum2cat.setText(Integer.toString(sumCat2()));
        sum3cat.setText(Integer.toString(sumCat3()));
        maxAge.setText(Integer.toString(findMaxAge()));
        minAge.setText(Integer.toString(findMinAge()));
    }

    private int sumCat1(){
        int i = 0;
        for(Engineer eng: this.main.getEngineers()){
            if (eng.getCategory() == 1){
                i++;
            }
        }
        return i;
    }
    private int sumCat2(){
        int i = 0;
        for(Engineer eng: main.getEngineers()){
            if (eng.getCategory() == 2){
                i++;
            }
        }
        return i;
    }
    private int sumCat3(){
        int i = 0;
        for(Engineer eng: main.getEngineers()){
            if (eng.getCategory() == 3){
                i++;
            }
        }
        return i;
    }

    private int findMaxAge(){
        int max = 0;
        Engineer eng;
        for (Engineer e: main.getEngineers()){
            if (e.getAge() > max) {
                max = e.getAge();
            }
        }
        return max;
    }
    private int findMinAge(){
        int min = 200;
        Engineer eng;
        for (Engineer e: main.getEngineers()){
            if (e.getAge() < min) {
                min = e.getAge();
            }
        }
        return min;
    }
}
