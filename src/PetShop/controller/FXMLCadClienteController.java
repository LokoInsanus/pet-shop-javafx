package PetShop.controller;

import PetShop.model.dao.ClienteDAO;
import PetShop.model.database.Database;
import PetShop.model.database.DatabaseFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import PetShop.model.domain.Cliente;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class FXMLCadClienteController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
    
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
    
    private final Database database = DatabaseFactory.getDatabase("jdbc");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
    }
    
    @FXML
    public void handleSalvar() {
        Cliente cliente = new Cliente(
            clienteDAO.listar().size() + 1,
            fieldNome.getText(),
            fieldEmail.getText(),
            fieldRua.getText(),
            fieldBairro.getText(),
            fieldCasa.getText(),
            fieldTelefone.getText(),
            fieldCPF.getText()
        );
        clienteDAO.inserir(cliente);
    }

    @FXML
    public void handleVoltar() throws IOException {
        AnchorPane anchor = FXMLLoader.load(getClass().getResource("/PetShop/view/FXMLMain.fxml"));
        anchorPane.getChildren().setAll(anchor);
    }
}
