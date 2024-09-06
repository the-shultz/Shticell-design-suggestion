package shticell.jfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello There in FXML");

        Parent load = FXMLLoader.load(getClass().getResource("sheet/sheet.fxml"));
        Scene scene = new Scene(load, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
