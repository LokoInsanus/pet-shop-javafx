package PetShop.model.dao;

import PetShop.model.domain.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente(CdCliente, nome, email, rua, bairro, numCasa, telefone, cpf) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getId());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getRua());
            stmt.setString(5, cliente.getBairro());
            stmt.setString(6, cliente.getNumeroCasa());
            stmt.setString(7, cliente.getNumeroTelefone());
            stmt.setString(8, cliente.getCpf());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean alterar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome=?, email=?, rua=?, bairro=?, numCasa=?, telefone=?, cpf=? WHERE cdCliente=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getRua());
            stmt.setString(4, cliente.getBairro());
            stmt.setString(5, cliente.getNumeroCasa());
            stmt.setString(6, cliente.getNumeroTelefone());
            stmt.setString(7, cliente.getCpf());
            stmt.setInt(8, cliente.getId());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean remover(Cliente cliente) {
        String sql = "DELETE FROM cliente WHERE cdCliente=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getId());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public List<Cliente> listar() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> list = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultado.getInt("cdCliente"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setRua(resultado.getString("rua"));
                cliente.setBairro(resultado.getString("bairro"));
                cliente.setNumeroCasa(resultado.getString("numCasa"));
                cliente.setNumeroTelefone(resultado.getString("telefone"));
                cliente.setCpf(resultado.getString("cpf"));
                list.add(cliente);
            }
        } catch (SQLException e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public Cliente buscar(Cliente cliente) {
        String sql = "SELECT * FROM cliente WHERE cdCliente=?";
        Cliente clienteAchado = new Cliente();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                cliente.setNome(resultado.getString("nome"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setRua(resultado.getString("rua"));
                cliente.setBairro(resultado.getString("bairro"));
                cliente.setNumeroCasa(resultado.getString("numCasa"));
                cliente.setNumeroTelefone(resultado.getString("telefone"));
                cliente.setCpf(resultado.getString("cpf"));
                clienteAchado = cliente;
            }
        } catch (Exception e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return clienteAchado;
    }
}
