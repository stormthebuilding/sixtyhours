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

public class NewHighscore {

    @FXML Label newScore;
    @FXML Label playerName;
    @FXML Label message; 

    HighScore highScores = HighScore.getInstance();
    Score score;


    @FXML
    public void initialize() { 
        playerName.setText(World.instance().getUserName());
        newScore.setText(String.valueOf(World.instance().getScore()));
    }    

}