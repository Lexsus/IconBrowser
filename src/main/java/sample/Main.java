    package sample;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;

public class Main extends Application {
    private static OkHttpClient client = new OkHttpClient();
    private static JsonParser parser = new JsonParser();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Object obj = getClass();
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
        primaryStage.setTitle("IconFinder");
        primaryStage.setScene(new Scene(root, 200, 600));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
