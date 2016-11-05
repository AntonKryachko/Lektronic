package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.controller.*;
import sample.model.AlertData;
import sample.model.Engineer;
import sample.model.EngineerListWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

/**
* @author Lektor
*/
public class Main extends Application{
    private Stage primaryStage;
    private BorderPane rootLayout;
    private EngineersSingleton engineers = EngineersSingleton.getInstance();
    public final String NAME_APP = "Lectronic";
    public final String KEY_FILE = "filePath";

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(NAME_APP);
        this.primaryStage.getIcons().add(new Image("sample/model/res/images/main_icon.png"));
        this.primaryStage.setResizable(false);

        initRooLayout();

        showEngineerOverview();
    }
    private void initRooLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
        File file = getFilePath();
        if(file != null){
            load(file);
        }
    }
    private void showEngineerOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/EngineerOverview.fxml"));
            AnchorPane mainPage = loader.load();

            rootLayout.setCenter(mainPage);

            EngineerOverviewController controller = loader.getController();
            controller.setMain(this);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showAboutAuthor(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/AboutAuthor.fxml"));
            Pane page = loader.load();

            Stage authorStage = new Stage();
            authorStage.setTitle("About Author");
            authorStage.initModality(Modality.WINDOW_MODAL);
            authorStage.initOwner(primaryStage);
            authorStage.getIcons().add(new Image("sample/model/res/images/holybible.png"));
            authorStage.setResizable(false);

            Scene scene = new Scene(page);
            authorStage.setScene(scene);

            AboutAuthorController controller = loader.getController();
            controller.setAuthorStage(authorStage);

            authorStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showSpecial(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/Special.fxml"));
            Pane page = loader.load();

            Stage specialStage = new Stage();
            specialStage.setTitle("Special");
            specialStage.initModality(Modality.WINDOW_MODAL);
            specialStage.initOwner(primaryStage);
            specialStage.setResizable(false);
            specialStage.getIcons().add(new Image("sample\\model\\res\\images\\document.png"));

            Scene scene = new Scene(page);
            specialStage.setScene(scene);

            SpecialController controller = loader.getController();
            controller.setSpecialStage(specialStage);

            specialStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public boolean showEditEngineers(Engineer engineer){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/Edit.fxml"));
            AnchorPane page = loader.load();

            Stage editStage = new Stage();
            editStage.setTitle("Edit engineer");
            editStage.initModality(Modality.WINDOW_MODAL);
            editStage.initOwner(primaryStage);
            editStage.setResizable(false);
            editStage.getIcons().add(new Image("sample\\model\\res\\images\\Data_settings.png"));

            Scene scene = new Scene(page);
            editStage.setScene(scene);

            EditController controller = loader.getController();
            controller.setEditStage(editStage);
            controller.setEngineer(engineer);

            editStage.showAndWait();

            return controller.isOkClicked();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public File getFilePath(){
        Preferences preferences = Preferences.userNodeForPackage(getClass());
        String filePath = preferences.get(KEY_FILE, null);
        if (filePath != null){
            return new File(filePath);
        } else {
            return null;
        }
    }
    public void setFilePath(File filePath){
        Preferences preferences = Preferences.userNodeForPackage(getClass());
        if (filePath != null){
            preferences.put(KEY_FILE, filePath.getPath());
            primaryStage.setTitle(NAME_APP + " \"" + filePath.getName() + "\"");
        } else {
            preferences.remove(KEY_FILE);
            primaryStage.setTitle(NAME_APP);
        }
    }
    public void load(File file){
        try{
            JAXBContext context = JAXBContext.newInstance(EngineerListWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            EngineerListWrapper wrapper = (EngineerListWrapper) unmarshaller.unmarshal(file);

            this.engineers.setAll(wrapper.getEngineers());

            setFilePath(file);
        }catch (Exception e){
            new AlertData(
                    primaryStage,
                    "Error",
                    "Could not load data",
                    "Could not load data from file:\n" + file.getPath(),
                    "ERROR"
            );
        }
    }
    public void save(File file){
        try{
            JAXBContext context = JAXBContext.newInstance(EngineerListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            EngineerListWrapper wrapper = new EngineerListWrapper();
            wrapper.setEngineers(engineers.getEngineers());

            marshaller.marshal(wrapper,file);

            setFilePath(file);
        }catch (Exception e){
            new AlertData(
                    primaryStage,
                    "Error",
                    "Could not save data",
                    "Could not save data to file:\n" + file.getPath(),
                    "ERROR"
            );
        }
    }
}
