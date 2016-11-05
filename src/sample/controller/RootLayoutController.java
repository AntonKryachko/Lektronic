package sample.controller;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import sample.EngineersSingleton;
import sample.Main;

import java.io.File;

/**
 * @author Lektor
 */
public class RootLayoutController {

    private Main main;

    private EngineersSingleton engineers = EngineersSingleton.getInstance();

    public void setMain(Main main){
        this.main = main;
    }
    @FXML
    private void handleNew(){
        engineers.clear();
        main.setFilePath(null);
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
            main.load(file);
        }
    }
    @FXML
    private void handleSave(){
        File engFile = main.getFilePath();
        if (engFile != null){
            main.save(engFile);
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
            if(!file.getPath().endsWith(".xml")){
                file = new File(file.getPath() + ".xml");
            }
            main.save(file);
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
    @FXML
    private void handleSpecial(){
        main.showSpecial();
    }
    public RootLayoutController(){}
    @FXML
    public void initialize(){}
}
