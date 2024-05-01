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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private TableColumn<Cliente, String> columnId;
    
    @FXML 
    private TableColumn<Cliente, String> columnNome;
    
    @FXML 
    private TableColumn<Cliente, String> columnEmail;
    
    @FXML 
    private TableColumn<Cliente, String> columnRua;
    
    @FXML 
    private TableColumn<Cliente, String> columnBairro;
    
    @FXML 
    private TableColumn<Cliente, String> columnCasa;
    
    @FXML 
    private TableColumn<Cliente, String> columnTelefone;
    
    @FXML 
    private TableColumn<Cliente, String> columnCpf;
    
    @FXML
    private Label labelCliente;
    
    @FXML
    private Button btnEditar;
    
    @FXML 
    private Button btnApagar;
    
    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;
    private Cliente clienteSelecionado;
    
    private final Database database = DatabaseFactory.getDatabase("jdbc");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection (connection);
        carregarTableClientes();
        
        labelCliente.setVisible(false);
        btnEditar.setVisible(false);
        btnApagar.setVisible(false);
        
        tableClientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionarItem((Cliente) newValue));
    }    
    
    @FXML
    public void handleVoltar() throws IOException {
        AnchorPane anchor = FXMLLoader.load(getClass().getResource("/PetShop/view/FXMLMain.fxml"));
        anchorPane.getChildren().setAll(anchor);
    }
    
    @FXML
    public void handleEditar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PetShop/view/FXMLCadCliente.fxml"));
        AnchorPane anchor = loader.load();
        FXMLCadClienteController controller = loader.getController();
        
        Cliente cliente = getClienteSelecionado();
        
        controller.setEditar(true);
        controller.setIdEditar(cliente.getId());
        controller.setFieldNome(cliente.getNome());
        controller.setFieldEmail(cliente.getEmail());
        controller.setFieldRua(cliente.getRua());
        controller.setFieldBairro(cliente.getBairro());
        controller.setFieldCasa(cliente.getNumeroCasa());
        controller.setFieldTelefone(cliente.getNumeroTelefone());
        controller.setFieldCPF(cliente.getCpf());
        
        anchorPane.getChildren().setAll(anchor);
    }
    
    @FXML
    public void handleApagar() {
        Cliente cliente = getClienteSelecionado();
        clienteDAO.remover(cliente);
        carregarTableClientes();
    }
    
    public void carregarTableClientes() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnRua.setCellValueFactory(new PropertyValueFactory<>("rua"));
        columnBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        columnCasa.setCellValueFactory(new PropertyValueFactory<>("numeroCasa"));
        columnTelefone.setCellValueFactory(new PropertyValueFactory<>("numeroTelefone"));
        columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        listClientes = clienteDAO.listar();
        
        observableListClientes = FXCollections.observableArrayList(listClientes);
        tableClientes.setItems(observableListClientes);
    }
    
    public void selecionarItem(Cliente cliente) {
        setClienteSelecionado(cliente);
        labelCliente.setText(cliente.getNome());
        labelCliente.setVisible(true);
        btnEditar.setVisible(true);
        btnApagar.setVisible(true);
    }
    
    public void setClienteSelecionado(Cliente cliente) {
        this.clienteSelecionado = cliente;
    }
    
    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }
}
