import java.io.IOException;
import java.util.List;

import Model.HighScore;
import Model.Score;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class NewHighscore {

    @FXML TextField playername;
    @FXML Label newScore;
    @FXML Label playerName;
    @FXML Label message; 

    HighScore highScores = HighScore.getInstance();
    Score score;


    @FXML
    public void initialize() { 
    }    

}