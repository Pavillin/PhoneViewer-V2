package Models;

import Controllers.PhoneDetailsViewController;
import Controllers.PhoneLoaderInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {
    /**
     * A method that will change scenes setting the new scene (fxml file) and the title
     * @param sceneChange
     * @param viewName
     * @param title
     * @throws IOException
     */
    public static void changeScenes(ActionEvent sceneChange, String viewName, String title) throws IOException {
        Parent root = FXMLLoader.load(new Object(){}.getClass().getResource(viewName));
        Scene scene = new Scene(root);

        Stage primaryStage = (Stage)((Node)sceneChange.getSource()).getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.show();
    }

    public static void changeScenes(ActionEvent sceneChange, String viewName, String title, Phone phone) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new Object(){}.getClass().getResource("../Views/PhoneDetailsView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        //access the controller class and load the Phone object
        PhoneLoaderInterface controller = loader.getController();
        controller.loadPhone(phone);

        //get the Stage and set the scene/show
        Stage stage = (Stage)((Node)sceneChange.getSource()).getScene().getWindow();
        stage.setTitle("Phone Details");
        stage.setScene(scene);
        stage.show();
    }
}
