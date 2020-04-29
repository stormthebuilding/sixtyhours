import java.io.IOException;
import java.util.List;

import Model.HighScore;
import Model.Score;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HighScorePlayers {

    @FXML
    VBox vrank;
    @FXML
    VBox vname;
    @FXML
    VBox vscore;
    @FXML
    VBox vdifficulty;

    HighScore highScores = HighScore.getInstance();

    @FXML
    void initialize() throws IOException {
        /**
        1.Load Scores from file
        2.get scores list from getScoresList
        3.Looping through scores list
        4.Display rank, name and scores as labels from scores list
         */
        highScores.loadScores("src/main/resources/SaveScoresData.txt");
        List<Score> slist = highScores.getScoresList();
           
        for (int i = 0; i < slist.size(); ++i) {

            Label rank = new Label();
            Label name = new Label();
            Label scoreLabel = new Label();
            Label difficultyLabel = new Label();

            rank.setText("" + (i + 1));
            vrank.getChildren().add(rank);

            name.setText(slist.get(i).getName());
            vname.getChildren().add(name);

            scoreLabel.setText(String.valueOf(slist.get(i).getScore()));
            vscore.getChildren().add(scoreLabel);

            difficultyLabel.setText(String.valueOf(slist.get(i).getDifficultyType()));
            vdifficulty.getChildren().add(difficultyLabel);


        }
        
    }

}