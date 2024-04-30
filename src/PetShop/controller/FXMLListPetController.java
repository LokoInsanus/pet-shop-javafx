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

public class FXMLListPetController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private TableView tablePets;
    
    @FXML 
    private TableColumn columnId;
    
    @FXML 
    private TableColumn columnNome;
    
    @FXML 
    private TableColumn columnAnimal;
    
    @FXML 
    private TableColumn columnDono;
    
    @FXML 
    private TableColumn columnRaca;
    
    @FXML 
    private TableColumn columnRga;

    private List<Pet> listPets;
    private ObservableList<Pet> observableListPets;
    
    private final Database database = DatabaseFactory.getDatabase("jdbc");
    private final Connection connection = database.conectar();
    private final PetDAO petDAO = new PetDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        petDAO.setConnection (connection);
        carregarTablePets();
    }    
    
    @FXML
    public void handleVoltar() throws IOException {
        AnchorPane anchor = FXMLLoader.load(getClass().getResource("/PetShop/view/FXMLMain.fxml"));
        anchorPane.getChildren().setAll(anchor);
    }
    
    public void carregarTablePets() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        columnAnimal.setCellValueFactory(new PropertyValueFactory<>("Animal"));
        columnDono.setCellValueFactory(new PropertyValueFactory<>("Dono"));
        columnRaca.setCellValueFactory(new PropertyValueFactory<>("Raca"));
        columnRga.setCellValueFactory(new PropertyValueFactory<>("Rga"));
        
        listPets = petDAO.listar();
        System.out.println(listPets);
        
        observableListPets = FXCollections.observableArrayList(listPets);
        tablePets.setItems(observableListPets);
    }
}
