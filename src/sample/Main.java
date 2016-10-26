package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.Engineer;
import sample.model.EngineerListWrapper;
import sample.view.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Engineer> engineers = FXCollections.observableArrayList();
//    private ObservableSet<Engineer> observableSet = FXCollections.observableSet((Set)engineers);

    public static void main(String[] args) {launch(args);}
    public ObservableList<Engineer> getEngineers() {return engineers;}
    public Stage getPrimaryStage() {return primaryStage;}

    public Main(){
        engineers.addAll(
                new Engineer(48877, "Клим",13,2),
                new Engineer(57677,"Жуков",10,2),
                new Engineer(15411,"Чехов",2,1),
                new Engineer(32521,"Пучков",6,1),
                new Engineer(21344,"Поттер",4,1),
                new Engineer(12314,"Наумов",11,2),
                new Engineer(21346,"Слуцкий",9,2),
                new Engineer(41131,"Сосюра",20,3),
                new Engineer(3182,"Норбобаев",10,2),
                new Engineer(45613,"Беггинс",7,1),
                new Engineer(54613,"Петров",8,2),
                new Engineer(15431,"Степанянов",6,1),
                new Engineer(12314,"Ретушив",2,1),
                new Engineer(47531,"Авдеев",15,2),
                new Engineer(37981,"Нигородский",21,3),
                new Engineer(31333,"Луговьев",14,2),
                new Engineer(52352,"Корнедев",4,1),
                new Engineer(12948,"Дурнев",15,3),
                new Engineer(25463,"Бурев",11,2),
                new Engineer(27364,"Топор",24,3)
        );
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Lectronic");
        this.primaryStage.getIcons().add(new Image("sample/res/images/main_icon.png"));
        this.primaryStage.setResizable(false);
        initRootLayout();

        showEngineerOverview();
    }
    private void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = getEngineerFilePath();
        if (file != null){
            loadEngineerDataFromFile(file);
        }
    }
    private void showEngineerOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/EngineerOverview.fxml"));
            AnchorPane engineerOverview = loader.load();

            rootLayout.setCenter(engineerOverview);

            EngineerOverviewController controller = loader.getController();
            controller.setMain(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public boolean showEngineerEdit(Engineer engineer){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/Edit.fxml"));
            AnchorPane page = loader.load();

            Stage editStage = new Stage();
            editStage.setTitle("Edit engineer");
            editStage.initModality(Modality.WINDOW_MODAL);
            editStage.initOwner(primaryStage);
            editStage.setResizable(false);

            Scene scene = new Scene(page);
            editStage.setScene(scene);

            EditController controller = loader.getController();
            controller.setEditStage(editStage);
            controller.setEngineer(engineer);
            controller.setMain(this);

            editStage.showAndWait();

            return controller.isOkClicked();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public void showAboutAuthor(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/AboutAuthor.fxml"));
            Pane page = loader.load();

            Stage authorStage = new Stage();
            authorStage.setTitle("About Author");
            authorStage.initModality(Modality.WINDOW_MODAL);
            authorStage.initOwner(primaryStage);

            Scene scene = new Scene(page);
            authorStage.setScene(scene);
            authorStage.getIcons().add(new Image("sample/res/images/holybible.png"));
            authorStage.setResizable(false);

            AboutAuthorController controller = loader.getController();
            controller.setAuthorStage(authorStage);

            authorStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showSpecialData(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/Special.fxml"));
            AnchorPane page = loader.load();

            Stage specialStage = new Stage();
            specialStage.setTitle("Special");
            specialStage.initModality(Modality.WINDOW_MODAL);
            specialStage.initOwner(primaryStage);
            specialStage.setResizable(false);

            Scene scene = new Scene(page);
            specialStage.setScene(scene);

            SpecialDataController controller = loader.getController();
            controller.setSpecialStage(specialStage);
            controller.setEngineers(engineers);
            controller.setMain(this);

            specialStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public File getEngineerFilePath(){
        Preferences preferences = Preferences.userNodeForPackage(getClass());
        String filePath = preferences.get("filePath", null);
        if (filePath != null){
            return new File(filePath);
        }else {
            return null;
        }
    }
    public void setEngineerFilePath(File filePath){
        Preferences preferences = Preferences.userNodeForPackage(getClass());
        if (filePath != null){
            preferences.put("filePath", filePath.getPath());
            primaryStage.setTitle("Lectronic \"" + filePath.getName() + "\"");
        }else{
            preferences.remove("filePath");
            primaryStage.setTitle("Lectronic");
        }
    }
    public void loadEngineerDataFromFile(File file){
        try{
            JAXBContext context = JAXBContext.newInstance(EngineerListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            EngineerListWrapper wrapper = (EngineerListWrapper) um.unmarshal(file);

            engineers.clear();
            engineers.addAll(wrapper.getEngineers());

            setEngineerFilePath(file);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
    public void saveEngineersDataToFile(File file){
        try{
            JAXBContext context = JAXBContext.newInstance(EngineerListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            EngineerListWrapper wrapper = new EngineerListWrapper();
            wrapper.setEngineers(engineers);

            marshaller.marshal(wrapper, file);

            setEngineerFilePath(file);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
    public void removeUnder(int value, boolean isAge){
        ObservableList<Engineer> list = FXCollections.observableArrayList();
        if (isAge){
            for (Engineer engineer: engineers){
                if (engineer.getAge() >= value){
                    list.add(engineer);
                }
            }
        }else{
            for (Engineer engineer: engineers){
                if (engineer.getCategory() >= value){
                    list.add(engineer);
                }
            }
        }
        engineers = list;
    }
}
