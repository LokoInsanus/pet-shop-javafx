package PetShop.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class FXMLMainController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    @FXML
    private void handleCadCliente(ActionEvent event) throws IOException {
        AnchorPane anchor = FXMLLoader.load(getClass().getResource("/PetShop/view/FXMLCadCliente.fxml"));
        anchorPane.getChildren().setAll(anchor);
    }
    
    @FXML
    private void handleCadPet(ActionEvent event) throws IOException {
        AnchorPane anchor = FXMLLoader.load(getClass().getResource("/PetShop/view/FXMLCadPet.fxml"));
        anchorPane.getChildren().setAll(anchor);
    }
    
    @FXML
    private void handleListCliente(ActionEvent event) throws IOException {
        AnchorPane anchor = FXMLLoader.load(getClass().getResource("/PetShop/view/FXMLListCliente.fxml"));
        anchorPane.getChildren().setAll(anchor);
    }
    
    @FXML
    private void handleListPet(ActionEvent event) throws IOException {
        AnchorPane anchor = FXMLLoader.load(getClass().getResource("/PetShop/view/FXMLListPet.fxml"));
        anchorPane.getChildren().setAll(anchor);
    } 
}
