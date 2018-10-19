package Controllers;

import Models.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PhoneDetailsViewController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    /**
     * If user clicks 'Create Contact' button, switch to the 'Create a Contact' page
     * @param sceneChange
     * @throws IOException
     */
    public void changeToPhoneTableScene(ActionEvent sceneChange) throws IOException {
        SceneChanger.changeScenes(sceneChange, "../Views/PhoneTableView.fxml", "Phones");
    }
}
