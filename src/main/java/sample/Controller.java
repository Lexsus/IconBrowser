package sample;

//import java.awt.*;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import okhttp3.Request;

public class Controller implements Initializable{
    public Controller() {

    }

    @FXML
    VBox mainPane;
    @FXML
    private ListView<ImageView> listView;
    @FXML
    private TextField textField;
    private final ObservableList<ImageView> data = FXCollections.observableArrayList();
    private Cursor prevCursor;
    public void OnEditBox(ActionEvent actionEvent) {
        System.out.println(actionEvent.toString());
        TextField textField = (TextField)actionEvent.getSource();
        System.out.println(textField.getText());
        //Image image = new Image("https://yandex.ru/collections/card/59c7fd39c75bad00df20be45/");
        //Image image = new Image("_40911817_fashion300.jpg");
        //Image image = new Image("https://static.ngs.ru/news/99/preview/dac93b94a68afc169efb18e8f78f0fdb0e4fd2b8_959_539_c.jpg");
        IconHelper.setQuery(textField.getText());
        textField.setDisable(true);

        prevCursor = mainPane.getScene().getCursor();
        mainPane.getScene().setCursor(Cursor.WAIT);
        CompletableFuture.supplyAsync(IconHelper::userService
        ).thenAccept(this::notifyFuture
        ).exceptionally(throwable -> {
            showErrorDialogFor(throwable);
            return null;
        }
        );
        /*try {
            ArrayList<String> icons = IconHelper.getIcons(textField.getText());
            addImages(icons);
            int p=0;

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //mainPane.getChildren()
        

    }

    private void showErrorDialogFor(Throwable throwable) {
        System.out.println("notifyFuture");
        textField.setDisable(false);
        mainPane.getScene().setCursor(prevCursor);
    }

    private void notifyFuture(ArrayList<ImageView> o) {
        System.out.println("notifyFuture");

        /*Platform.runLater(new Runnable() {
            @Override
            public void run() {
                addImages(o);
            }
        });*/
        addImages(o);
        textField.setDisable(false);
        mainPane.getScene().setCursor(prevCursor);

    }


    public void addImages(ArrayList<ImageView> icons)
    {
        long start = java.lang.System.currentTimeMillis();
        data.clear();
        data.addAll(icons);
        listView.setItems(data);
        /*listView.setCellFactory(new Callback<ListView<Image>, ListCell<Image>>() {

            public ListCell<Image> call(ListView<Image> param) {
                ListCell<Image> cell = new ListCell<Image>() {
                    @Override
                    protected void updateItem(Image item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            ImageView imageView = new ImageView(item);
                            setGraphic(imageView);
                        }

                    }
                };
                return cell;
            }
        });*/
        System.out.println(" Method time addImages="+(java.lang.System.currentTimeMillis()-start));
    }
    public void initialize(URL location, ResourceBundle resources) {

    }
}
