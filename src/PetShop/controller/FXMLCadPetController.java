package PetShop.controller;

import PetShop.model.Cliente;
import PetShop.model.Pet;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class FXMLCadPetController implements Initializable {
    
    @FXML
    private TextField fieldNome;
    
    @FXML
    private TextField fieldAnimal;
    
    @FXML
    private TextField fieldRaca;
    
    @FXML
    private ComboBox comboDonos;
    
    @FXML
    private TextField fieldRGA;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void handleSalvar() {
        Pet pet = new Pet(
            fieldNome.getText(),
            fieldAnimal.getText(),
            new Cliente(),
            fieldRaca.getText(),
            fieldRGA.getText()
        );
        List<Pet> listPet = new ArrayList<>();
        listPet.add(pet);
        
        System.out.println(pet.getNome());
    }
}
