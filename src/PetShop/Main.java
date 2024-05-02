package PetShop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try {
            OutputStream nullOutputStream = new FileOutputStream(new File(System.getProperty("os.name").startsWith("Windows") ? "NUL" : "/dev/null"));
            System.setOut(new PrintStream(nullOutputStream));
            System.setErr(new PrintStream(nullOutputStream));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLMain.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Pet Shop");
        stage.setResizable(false);
        stage.setHeight(700);
        stage.setWidth(1000);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
