package PetShop.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import PetShop.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class FXMLCadClienteController implements Initializable {
    
    @FXML
    private TextField fieldNome;
    
    @FXML
    private TextField fieldEmail;
    
    @FXML
    private TextField fieldRua;
    
    @FXML
    private TextField fieldBairro;
    
    @FXML
    private TextField fieldCasa;
    
    @FXML
    private TextField fieldTelefone;
     
    @FXML
    private TextField fieldCPF;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    public void handleSalvar() {
        Cliente cliente = new Cliente(
            fieldNome.getText(),
            fieldEmail.getText(),
            fieldRua.getText(),
            fieldBairro.getText(),
            fieldCasa.getText(),
            fieldTelefone.getText(),
            fieldCPF.getText()
        );
        List<Cliente> listCliente = new ArrayList<>();
        listCliente.add(cliente);
        
        System.out.println(cliente);
    }
    
}
