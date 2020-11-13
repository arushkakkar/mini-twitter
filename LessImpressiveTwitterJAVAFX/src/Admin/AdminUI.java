package Admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
        primaryStage.setTitle("Admin Panel");
        primaryStage.setScene(new Scene(root, 892, 633));
        primaryStage.show();
    }

    protected static void startUI(String[] args) {
        launch(args);
    }
}
