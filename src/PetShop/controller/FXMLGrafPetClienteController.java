package PetShop.controller;

import PetShop.model.dao.PetDAO;
import PetShop.model.database.Database;
import PetShop.model.database.DatabaseFactory;
import PetShop.model.domain.Pet;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

public class FXMLGrafPetClienteController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private BarChart<String, Number> grafPet;
 
    private List<Pet> listPets;
    private List<String> listAnimais = new ArrayList<String>();
    private Map<String, Integer> mapQuant = new HashMap();
    
    private final Database database = DatabaseFactory.getDatabase("jdbc");
    private final Connection connection = database.conectar();
    private final PetDAO petDAO = new PetDAO();
    
    public void initialize(URL url, ResourceBundle rb) {
        petDAO.setConnection(connection);
        listPets = petDAO.listar();
        
        for(Pet pet : listPets) {
            if(listAnimais.contains(pet.getAnimal())) {
                mapQuant.put(pet.getAnimal(), mapQuant.get(pet.getAnimal()) + 1);
            } else {
                listAnimais.add(pet.getAnimal());
                mapQuant.put(pet.getAnimal(), 1);
            }
        }
        System.out.println(mapQuant);
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        
        for(Map.Entry<String, Integer> entry : mapQuant.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        
        grafPet.getData().addAll(series);
    }
    
    @FXML
    public void handleVoltar() throws IOException {
        AnchorPane anchor = FXMLLoader.load(getClass().getResource("/PetShop/view/FXMLMain.fxml"));
        anchorPane.getChildren().setAll(anchor);
    }
}
