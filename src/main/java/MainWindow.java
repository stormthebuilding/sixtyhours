import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


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
    void onPlayClicked(ActionEvent event) {
        var alert = new Alert(AlertType.INFORMATION, "Game screen still has to be attached.");
        alert.setHeaderText(null);
        alert.show();

    }
}
