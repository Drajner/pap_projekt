package edu.iipw.pap;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App.
 */
public class AppWeb extends Application {

    public static void main(String[] args) {
        System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
        launch();
        System.out.println("Kończę pracę.");
        System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
        System.exit(0);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("LoggingScreenWeb.fxml"));
        int sceneX = 350;
        int sceneY = 300;
        Scene scene = new Scene(fxmlLoader.load(), sceneX, sceneY);
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResource("appointr_logo.png")).toString()));
        stage.setTitle("Appointr");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
