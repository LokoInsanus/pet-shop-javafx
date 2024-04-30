package PetShop.controller;

import PetShop.model.dao.ClienteDAO;
import PetShop.model.dao.PetDAO;
import PetShop.model.database.Database;
import PetShop.model.database.DatabaseFactory;
import PetShop.model.domain.Cliente;
import PetShop.model.domain.Pet;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FXMLCadPetController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
    
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
    
    private List<Cliente> listCliente;
    private ObservableList<String> observableListCliente;
    
    private final Database database = DatabaseFactory.getDatabase("jdbc");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final PetDAO petDAO = new PetDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        petDAO.setConnection(connection);
        
        carregarComboCliente();
    }
    
    public void carregarComboCliente() {
        listCliente = clienteDAO.listar();
        observableListCliente = FXCollections.observableArrayList();
        listCliente.forEach((i) -> {
            observableListCliente.add(i.getNome());
        });
        
        comboDonos.setItems(observableListCliente);
    }
    
    @FXML
    public void handleSalvar() {
        Pet pet = new Pet(
            petDAO.listar().size() + 1,
            fieldNome.getText(),
            fieldAnimal.getText(),
            listCliente.get(comboDonos.getSelectionModel().getSelectedIndex()),
            fieldRaca.getText(),
            fieldRGA.getText()
        );
        petDAO.inserir(pet);
    }
    
    @FXML
    public void handleVoltar() throws IOException {
        AnchorPane anchor = FXMLLoader.load(getClass().getResource("/PetShop/view/FXMLMain.fxml"));
        anchorPane.getChildren().setAll(anchor);
    }
}
