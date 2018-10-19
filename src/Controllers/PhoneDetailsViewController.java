package Controllers;

import Models.Phone;
import Models.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PhoneDetailsViewController implements Initializable, PhoneLoaderInterface {
    @FXML private Label makeLabel;
    @FXML private Label modelLabel;
    @FXML private Label memoryLabel;
    @FXML private Label osLabel;

    Phone activePhone;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void loadPhone(Phone phone){
        activePhone = phone;
        updateView();
    }
    public void updateView(){
        makeLabel.setText(activePhone.getMake());
        modelLabel.setText(activePhone.getModel());
        memoryLabel.setText(String.format("%.0f", activePhone.getMemory()));
        osLabel.setText(activePhone.getOs());
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
