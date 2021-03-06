// GUI controls and event handlers for the Title Screen
import java.io.File;
import java.io.IOException;

import Model.Serializer;
import Model.World;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

// contains GUI controls and event handlers for the Title Screen
public class MainWindow {
    
    // GUI controls

    @FXML Label title; // title on the title screen
    @FXML TextField name; // textfield for the player to enter their name
    @FXML ChoiceBox<String> cbDifficulty; // dropdown for difficulty levels

    // FXML Event Handlers

    // initialize method for the title screen
    @FXML 
    void initialize() {
        title.setText("Storm the Stronghold");
        title.setTextFill(Color.BROWN);
        // create a font 
        Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 35); 
  
        // set font of the text 
        title.setFont(font);
    }

    // Event handler for the Play button
    @FXML
    void onPlayClicked(ActionEvent event) throws IOException {
        if (name.getText() == null || name.getText().isBlank()){
            var alert = new Alert(AlertType.INFORMATION, "Please Enter Name.");
            alert.setHeaderText(null);
            alert.show(); 
        } else {
            World.instance().setUserName(name.getText());
            World.instance().setDifficulty(cbDifficulty.getValue());

            var loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
            var scene = new Scene(loader.load());

            var stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }  

    }

    // Event handler for the About button
    @FXML
    void onAboutClicked(ActionEvent event) throws IOException {
        var loader = new FXMLLoader(getClass().getResource("AboutWindow.fxml"));
        var scene = new Scene(loader.load());

        var stage = new Stage();
        stage.setScene(scene);
        stage.show();

        File audioFile = new File("src/main/resources/sounds/bong_001.mp3");
        Media audio = new Media(audioFile.toURI().toString());
        MediaPlayer audioPlayer = new MediaPlayer(audio);
        audioPlayer.setAutoPlay(true);
        audioPlayer.play();   

    }

    // Event handler for the Help button
    @FXML
    void onHelpClicked(ActionEvent event) throws IOException {
        var loader = new FXMLLoader(getClass().getResource("HelpWindow.fxml"));
        var scene = new Scene(loader.load());

        var stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
        File audioFile = new File("src/main/resources/sounds/bong_001.mp3");
        Media audio = new Media(audioFile.toURI().toString());
        MediaPlayer audioPlayer = new MediaPlayer(audio);
        audioPlayer.setAutoPlay(true);
        audioPlayer.play();   
     
    }

    //Event handler for the Load button
    @FXML
    void onLoadClicked(ActionEvent event) throws IOException {
        World.instance().load("SavedGame.txt");

        var loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
        var scene = new Scene(loader.load());
        var stage = new Stage();
        stage.setScene(scene);
        stage.show();

        File audioFile = new File("src/main/resources/sounds/bong_001.mp3");
        Media audio = new Media(audioFile.toURI().toString());
        MediaPlayer audioPlayer = new MediaPlayer(audio);
        audioPlayer.setAutoPlay(true);
        audioPlayer.play();   

        
    }

    // Event handler for the Highscore button
    @FXML
    void onHighScoreClicked(ActionEvent event) throws IOException {
        File fileObj = new File("src/main/resources/SaveScoresData.txt");
        if (fileObj.exists()){ 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HighScorePlayers.fxml"));

            Stage highscoreData = new Stage();
            highscoreData.setScene(new Scene(loader.load()));
            highscoreData.show();
            SoundPlay(highscoreData);
        } else {
            var alert = new Alert(AlertType.INFORMATION, "There are no high scores yet.");
            alert.setHeaderText(null);
            alert.show();
        }
    }

    // method to play an applause sound
    public void SoundPlay(final Stage stage)
    {
     File audioFile = new File("src/main/resources/applause.mp3");
     Media audio = new Media(audioFile.toURI().toString());
     MediaPlayer audioPlayer = new MediaPlayer(audio);
     audioPlayer.setAutoPlay(true);
     audioPlayer.play();   
     stage.setOnCloseRequest(event -> {
         audioPlayer.dispose();
         stage.close();
     });
    }

}
