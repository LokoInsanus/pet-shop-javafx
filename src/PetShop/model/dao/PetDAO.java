package PetShop.model.dao;

import PetShop.model.domain.Cliente;
import PetShop.model.domain.Pet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PetDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Pet pet) {
        String sql = "INSERT INTO pet(nome, animal, dono, raca, rga) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pet.getNome());
            stmt.setString(2, pet.getAnimal());
            stmt.setString(3, pet.donoString());
            stmt.setString(4, pet.getRaca());
            stmt.setString(5, pet.getRga());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean alterar(Pet pet) {
        String sql = "UPDATE pet SET nome=?, animal=?, dono=?, raca=?, rga=? WHERE cdPet=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pet.getNome());
            stmt.setString(2, pet.getAnimal());
            stmt.setString(3, pet.donoString());
            stmt.setString(4, pet.getRaca());
            stmt.setString(5, pet.getRga());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean remover(Pet pet) {
        String sql = "DELETE FROM pet WHERE cdPet=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pet.getId());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public List<Pet> listar() {
        String sql = "SELECT * FROM pet";
        List<Pet> list = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Pet pet = new Pet();
                pet.setId(resultado.getInt("cdPet"));
                pet.setNome(resultado.getString("nome"));
                pet.setNome(resultado.getString("animal"));
                pet.setNome(resultado.getString("dono"));
                pet.setNome(resultado.getString("raca"));
                pet.setNome(resultado.getString("rga"));
                list.add(pet);
            }
        } catch (SQLException e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public Pet buscar(Pet pet) {
        String sql = "SELECT * FROM pet WHERE cdPet=?";
        Pet petAchado = new Pet();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pet.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Cliente dono = new Cliente();
                pet.setNome(resultado.getString("nome"));
                pet.setAnimal(resultado.getString("animal"));
                pet.setDono(dono);
                pet.setRaca(resultado.getString("raca"));
                pet.setRga(resultado.getString("rga"));
                petAchado = pet;
            }
        } catch (SQLException e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return petAchado;
    }
}
