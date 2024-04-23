package PetShop.model.database;

public class DatabaseFactory {
    public static Database getDatabase(String nome){
        if(nome.equals("jdbc")){
            return new DatabaseJDBC();
        }
        return null;
    }
}
