package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    public static Main main;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Password Generator");
        primaryStage.getIcons().add(new Image("https://webstockreview.net/images/lock-clipart-safe-lock-11.png"));
        primaryStage.setScene(new Scene(root, 800, 450));
        primaryStage.setResizable(true);
        primaryStage.show();
        Encryption_Decryption.prompt(primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
