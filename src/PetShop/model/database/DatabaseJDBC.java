package PetShop.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseJDBC implements Database {
    private Connection connection;

    @Override
    public Connection conectar() {
        try {
            //Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:derby://127.0.0.1/petshop", "root","123456");
            return this.connection;
        } catch (/*SQLException | ClassNotFoundException e*/ Exception e) {
            Logger.getLogger(DatabaseJDBC.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public void desconectar(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(DatabaseJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
