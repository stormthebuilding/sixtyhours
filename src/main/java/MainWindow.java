import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainWindow {
    
    // GUI controls

    @FXML Label title;

    // FXML Event Handlers

    @FXML 
    void initialize() {
        title.setText("Storm the Stronghold");
        title.setTextFill(Color.BROWN);
        // create a font 
        Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 35); 
  
        // set font of the text 
        title.setFont(font);
    }

    @FXML
    void onPlayClicked(ActionEvent event) throws IOException {
        var loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
        var scene = new Scene(loader.load());

        var stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
