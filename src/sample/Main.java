package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.Engineer;
import sample.view.EditController;
import sample.view.EngineerOverviewController;
import sample.view.RootLayoutController;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Engineer> engineers = FXCollections.observableArrayList();
//    private ObservableSet<Engineer> observableSet = FXCollections.emptyObservableSet();

    public Main(){
        engineers.add(new Engineer(123, "qwe",112,2));
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Lectronic");

        initRootLayout();
        showEngineerOverview();
    }

    public void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
            this.rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout,800,425);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEngineerOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/EngineerOverview.fxml"));
            AnchorPane engineerOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(engineerOverview);

            EngineerOverviewController controller = loader.getController();
            controller.setMain(this);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showEngineerEdit(Engineer engineer){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/Edit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage editStage = new Stage();
            editStage.setTitle("Edit engineer");
            editStage.initModality(Modality.WINDOW_MODAL);
            editStage.initOwner(primaryStage);

            Scene scene = new Scene(page);
            editStage.setScene(scene);

            EditController controller = loader.getController();
            controller.setEditStage(editStage);
            controller.setEngineer(engineer);

            editStage.showAndWait();

//            return;

        }catch (IOException e){
            e.printStackTrace();
            return;
        }
    }


    public static void main(String[] args) {launch(args);}
    public ObservableList<Engineer> getEngineers() {return engineers;}
    public Stage getPrimaryStage() {return primaryStage;}
}
