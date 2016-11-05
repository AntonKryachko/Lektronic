package sample.controller;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * @author Lektor
 */

public class AboutAuthorController {
    @FXML
    private ImageView image_s;
    private Stage authorStage;

    public void setAuthorStage(Stage authorStage){
        this.authorStage = authorStage;
    }
    @FXML
    private void handleClose(){
        authorStage.close();
    }
    @FXML
    public void initialize(){
        image_s.setImage(new Image("sample\\model\\res\\images\\pP3-cy54hvE.jpg"));
    }
}
