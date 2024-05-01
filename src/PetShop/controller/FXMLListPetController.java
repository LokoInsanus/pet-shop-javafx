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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
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
    private TableColumn<Pet, String> columnId;
    
    @FXML 
    private TableColumn<Pet, String> columnNome;
    
    @FXML 
    private TableColumn<Pet, String> columnAnimal;
    
    @FXML 
    private TableColumn<Pet, String> columnDono;
    
    @FXML 
    private TableColumn<Pet, String> columnRaca;
    
    @FXML 
    private TableColumn<Pet, String> columnRga;
    
    @FXML
    private Label labelPet;
    
    @FXML
    private Button btnEditar;
    
    @FXML 
    private Button btnApagar;

    private List<Pet> listPets;
    private ObservableList<Pet> observableListPets;
    private Pet petSelecionado;
    
    private final Database database = DatabaseFactory.getDatabase("jdbc");
    private final Connection connection = database.conectar();
    private final PetDAO petDAO = new PetDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        petDAO.setConnection(connection);
        carregarTablePets();
        
        labelPet.setVisible(false);
        btnEditar.setVisible(false);
        btnApagar.setVisible(false);
        
        tablePets.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionarItem((Pet) newValue));
    }    
    
    @FXML
    public void handleVoltar() throws IOException {
        AnchorPane anchor = FXMLLoader.load(getClass().getResource("/PetShop/view/FXMLMain.fxml"));
        anchorPane.getChildren().setAll(anchor);
    }
    
    @FXML
    public void handleEditar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PetShop/view/FXMLCadPet.fxml"));
        AnchorPane anchor = loader.load();
        FXMLCadPetController controller = loader.getController();
        
        Pet pet = getPetSelecionado();
        
        controller.setEditar(true);
        controller.setIdEditar(pet.getId());
        controller.setFieldNome(pet.getNome());
        controller.setFieldAnimal(pet.getAnimal());
        controller.setComboDono(pet.getDono().getId());
        controller.setFieldRaca(pet.getRaca());
        controller.setFieldRGA(pet.getRga());
        
        anchorPane.getChildren().setAll(anchor);
    }
    
    @FXML
    public void handleApagar() {
        Pet pet = getPetSelecionado();
        petDAO.remover(pet);
        carregarTablePets();
    }
    
    public void carregarTablePets() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnAnimal.setCellValueFactory(new PropertyValueFactory<>("animal"));
        columnDono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDono().getNome()));
        columnRaca.setCellValueFactory(new PropertyValueFactory<>("raca"));
        columnRga.setCellValueFactory(new PropertyValueFactory<>("rga"));
        
        listPets = petDAO.listar();
        
        observableListPets = FXCollections.observableArrayList(listPets);
        tablePets.setItems(observableListPets);
    }
    
    public void selecionarItem(Pet pet) {
        setPetSelecionado(pet);
        labelPet.setText(pet.getNome());
        labelPet.setVisible(true);
        btnEditar.setVisible(true);
        btnApagar.setVisible(true);
    }
    
    public void setPetSelecionado(Pet pet) {
        this.petSelecionado = pet;
    }
    
    public Pet getPetSelecionado() {
        return petSelecionado;
    }
}
