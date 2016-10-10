package sample.view;

import javafx.fxml.FXML;
import sample.Main;

/**
 * Created by Lektor on 10.10.2016.
 */
public class RootLayoutController {

    private Main main;

    public void setMain(Main main){
        this.main = main;
    }
    @FXML
    private void handleNew(){

    }
    @FXML
    private void handleOpen(){

    }
    @FXML
    private void handleSave(){

    }
    @FXML
    private void handleSaveAs(){

    }
    @FXML
    private void handleClose(){
        System.exit(0);
    }
    @FXML
    private void handleAbout(){

    }
    public RootLayoutController(){}
    @FXML
    public void initialize(){

    }
}
