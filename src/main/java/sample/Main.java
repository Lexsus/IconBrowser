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

        /*Request request = IconHelper.getRequest();
        sendRequest(client,request);*/
        //IconHelper.print();
        Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
        primaryStage.setTitle("IconFinder");
        primaryStage.setScene(new Scene(root, 200, 600));
        primaryStage.show();
        getCloudStorageAuthToken();
    }

    static JsonObject sendRequest(OkHttpClient client, Request request) {
        JsonObject result = new JsonObject();

        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            if (str.length() > 0) {
                result = parser.parse(str).getAsJsonObject();
            }
            result.addProperty("Status Code", response.code());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    static public JsonObject getCloudStorageAuthToken()
    {
        String verificationCode = "hBYSNzDzOtjjgGnpFvpgekIpLyExMoJM39espG75mnVUWp00uOK3uGJl0UjeA185";
        String client_ID = "Rma9CCiBOuilUBlg8nD3Yg4XBm1Va9SH7LD3cbZmqZWoOrd66HBODq8ilw1rfXjN";
        String client_secret = "FEH49reZ7xSRJugvHU2wDvvmTbFiD6jkDX7p6ZHTLNNA4C58VzhpPPmAbxW7Id85";

        String bodyParam = "grant_type=authorization_code&code=" + verificationCode+
                "&client_id="+client_ID+"&client_secret="+client_secret;

        /*Request request = new Request.Builder()
                .url(TOKEN_REQ_PATH)
                //.header("Authorization", authCred)
                .post(RequestBody.create(MediaTypes.JSON.getType(), bodyParam))
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                JsonParser parser = new JsonParser();
                JsonObject o = parser.parse(response.body().string()).getAsJsonObject();
                AccessToken accessToken = AccessToken.buildAccessToken(o);
            }
            else
                System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return null;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
