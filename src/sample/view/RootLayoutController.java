package sample.view;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import sample.Main;


import java.io.File;

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
        main.getEngineers().clear();
        main.setEngineerFilePath(null);
    }
    @FXML
    private void handleOpen(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml",
                "All files", "*.*"
        );
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());
        if (file != null){
            main.loadEngineerDataFromFile(file);
        }
    }
    @FXML
    private void handleSave(){
        File engFile = main.getEngineerFilePath();
        if (engFile != null){
            main.saveEngineersDataToFile(engFile);
        }else {
            handleSaveAs();
        }
    }
    @FXML
    private void handleSaveAs(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml",
                "All files", "*.*"
        );
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(main.getPrimaryStage());
        if(file != null){
            if (!file.getPath().endsWith(".xml")){
                file = new File(file.getPath() + ".xml");
            }
            main.saveEngineersDataToFile(file);
        }
    }
    @FXML
    private void handleClose(){
        System.exit(0);
    }
    @FXML
    private void handleAbout(){
        main.showAboutAuthor();
    }

    public RootLayoutController(){}
    @FXML
    public void initialize(){}
}
