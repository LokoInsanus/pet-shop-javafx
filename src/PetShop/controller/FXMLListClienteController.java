package PetShop.controller;

import PetShop.model.dao.ClienteDAO;
import PetShop.model.database.Database;
import PetShop.model.database.DatabaseFactory;
import PetShop.model.domain.Cliente;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class FXMLListClienteController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private TableView tableClientes;
    
    @FXML 
    private TableColumn columnId;
    
    @FXML 
    private TableColumn columnNome;
    
    @FXML 
    private TableColumn columnEmail;
    
    @FXML 
    private TableColumn columnRua;
    
    @FXML 
    private TableColumn columnBairro;
    
    @FXML 
    private TableColumn columnCasa;
    
    @FXML 
    private TableColumn columnTelefone;
    
    @FXML 
    private TableColumn columnCpf;
    
    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;
    
    private final Database database = DatabaseFactory.getDatabase("jdbc");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection (connection);
        carregarTableClientes();
    }    
    
    @FXML
    public void handleVoltar() throws IOException {
        AnchorPane anchor = FXMLLoader.load(getClass().getResource("/PetShop/view/FXMLMain.fxml"));
        anchorPane.getChildren().setAll(anchor);
    }
    
    public void carregarTableClientes() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        columnRua.setCellValueFactory(new PropertyValueFactory<>("Rua"));
        columnBairro.setCellValueFactory(new PropertyValueFactory<>("Bairro"));
        columnCasa.setCellValueFactory(new PropertyValueFactory<>("NumeroCasa"));
        columnTelefone.setCellValueFactory(new PropertyValueFactory<>("NumeroTelefone"));
        columnCpf.setCellValueFactory(new PropertyValueFactory<>("Cpf"));
        
        listClientes = clienteDAO.listar();
        
        observableListClientes = FXCollections.observableArrayList(listClientes);
        tableClientes.setItems(observableListClientes);
    }
}
