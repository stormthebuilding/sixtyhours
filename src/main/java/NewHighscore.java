// GUI controls and event handlers for the screen that appears when a user makes a new highscore
import java.io.IOException;
import java.util.List;

import Model.HighScore;
import Model.Score;
import Model.World;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class NewHighScore {

    @FXML Label newScore; // new highscore of the player
    @FXML Label playerName; // name of the player
    @FXML Label message; // message displayed on the screen when the player makes a new highscore

    HighScore highScores = HighScore.getInstance(); // accessing highscore instance
    Score score; // score object


    // initialize method for the screen that appears when a user makes a new highscore
    @FXML
    public void initialize() { 
        playerName.setText(World.instance().getUserName());
        newScore.setText(String.valueOf(World.instance().getScore()));
    }    

}