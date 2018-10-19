package Controllers;

import Models.DBConnect;
import Models.Phone;
import Models.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PhoneTableViewController implements Initializable {
    @FXML private TableView<Phone> tableView;
    @FXML private TableColumn<Phone, String> makeColumn;
    @FXML private TableColumn<Phone, String> modelColumn;
    @FXML private TableColumn<Phone, String> osColumn;
    @FXML private TableColumn<Phone, Double> screensizeColumn;
    @FXML private TableColumn<Phone, Double> memoryColumn;
    @FXML private TableColumn<Phone, Double> rearCameraColumn;
    @FXML private TableColumn<Phone, Double> frontCameraColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //configure the table columns to integrate with the phone class
        makeColumn.setCellValueFactory(new PropertyValueFactory<Phone, String>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Phone, String>("make"));
        osColumn.setCellValueFactory(new PropertyValueFactory<Phone, String>("make"));
        screensizeColumn.setCellValueFactory(new PropertyValueFactory<Phone, Double>("screensize"));
        memoryColumn.setCellValueFactory(new PropertyValueFactory<Phone, Double>("memory"));
        rearCameraColumn.setCellValueFactory(new PropertyValueFactory<Phone, Double>("rearCameraRes"));
        frontCameraColumn.setCellValueFactory(new PropertyValueFactory<Phone, Double>("frontCameraRes"));

        try {
            tableView.getItems().addAll(DBConnect.getPhones());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * If user clicks 'Create Contact' button, switch to the 'Create a Contact' page
     * @param sceneChange
     * @throws IOException
     */
    public void changeToPhoneDetailsScene(ActionEvent sceneChange) throws IOException {
        Phone phoneSelected = tableView.getSelectionModel().getSelectedItem();

        //check to ensure that a phone was selected before changing scenes
        if (phoneSelected != null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../Views/PhoneDetailsView.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            //access the controller class and load the Phone object
            PhoneDetailsViewController controller = loader.getController();
            controller.loadPhone(phoneSelected);

            //get the Stage and set the scene/show
            Stage stage = (Stage)((Node)sceneChange.getSource()).getScene().getWindow();
            stage.setTitle("Phone Details");
            stage.setScene(scene);
            stage.show();
        }
        //SceneChanger.changeScenes(sceneChange, "../Views/PhoneDetailsView.fxml", "Phone Details");
    }

    /**
     * If user clicks 'Create Contact' button, switch to the 'Create a Contact' page
     * @param sceneChange
     * @throws IOException
     */
    public void changeToPhoneCreatorScene(ActionEvent sceneChange) throws IOException {
        SceneChanger.changeScenes(sceneChange, "../Views/PhoneCreatorView.fxml", "Create a Phone");
    }
}
