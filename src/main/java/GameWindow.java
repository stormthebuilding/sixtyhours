import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class GameWindow {

    @FXML Button btnWeapons;
    @FXML Button btnUpgrades;

    @FXML
    void initialize() {
        btnUpgrades.isDisabled();
        btnWeapons.isDisabled();
    }
}
