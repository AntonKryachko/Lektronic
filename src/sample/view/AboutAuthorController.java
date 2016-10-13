package sample.view;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Created by Lektor on 10.10.2016.
 */
public class AboutAuthorController {

    private Stage authorStage;

    public void setAuthorStage(Stage authorStage) {
        this.authorStage = authorStage;
    }

    @FXML
    private ImageView image_s;
    @FXML
    private void handleClose(){
        authorStage.close();
    }

    @FXML
    public void initialize(){
        image_s.setImage(new Image("sample/res/images/pP3-cy54hvE.jpg"));
    }

}
