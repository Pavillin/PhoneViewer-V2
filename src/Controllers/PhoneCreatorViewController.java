package Controllers;

import Models.DBConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PhoneCreatorViewController implements Initializable {
    @FXML private ChoiceBox<String> makeChoiceBox;
    @FXML private TextField modelTextField;
    @FXML private ChoiceBox<String> osChoiceBox;
    @FXML private TextField screensizeTextField;
    @FXML private TextField memoryTextField;
    @FXML private TextField frontCameraTextField;
    @FXML private TextField rearCameraTextField;
    @FXML private TextField priceTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            makeChoiceBox.getItems().addAll(DBConnect.getPhoneManufacturers());
            osChoiceBox.getItems().addAll(DBConnect.getOSs());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        makeChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                //this is our custom listener code
                (observable, oldValue, newValue) -> {
                    osChoiceBox.setValue(DBConnect.getOSForManufacturer(newValue));
                }
        );
    }
}
