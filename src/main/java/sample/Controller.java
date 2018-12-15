package sample;

//import java.awt.*;




import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class Controller {
    public Controller() {
        IconHelper.setCount((byte) 20);
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
        textField.setDisable(true);
        IconHelper.setQuery(textField.getText());

        prevCursor = mainPane.getScene().getCursor();
        mainPane.getScene().setCursor(Cursor.WAIT);
        CompletableFuture.supplyAsync(IconHelper::userService
        ).thenAccept(this::notifyFuture
        ).exceptionally(throwable -> {
                    showErrorDialogFor(throwable);
                    return null;
        }
        );
    }

    private void showErrorDialogFor(Throwable throwable) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Request error!");

        alert.showAndWait();

        textField.setDisable(false);
        mainPane.getScene().setCursor(prevCursor);
    }

    private void notifyFuture(ArrayList<ImageView> o) {

        Platform.runLater(() -> addImages(o));
        textField.setDisable(false);
        mainPane.getScene().setCursor(prevCursor);

    }


    public void addImages(ArrayList<ImageView> icons)
    {
        data.clear();
        data.addAll(icons);
        listView.setItems(data);

    }

}
