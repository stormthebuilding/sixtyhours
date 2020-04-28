import java.io.IOException;

import Model.Serializer;
import Model.World;
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
    @FXML TextField name; 

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

    @FXML
    void onAboutClicked(ActionEvent event) throws IOException {
        var loader = new FXMLLoader(getClass().getResource("AboutWindow.fxml"));
        var scene = new Scene(loader.load());

        var stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onHelpClicked(ActionEvent event) throws IOException {
        var loader = new FXMLLoader(getClass().getResource("HelpWindow.fxml"));
        var scene = new Scene(loader.load());

        var stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onLoadClicked(ActionEvent event) throws IOException {
        World.instance().load("SavedGame.txt");

        var loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
        var scene = new Scene(loader.load());
        var stage = new Stage();
        stage.setScene(scene);
        stage.show();
        // for testing purposes
        var listy = World.instance().getObjectCollection();
        for (Serializer object : listy) {
            System.out.println(object);
        }
    }

    @FXML
    void onHighScoreClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HighScorePlayers.fxml"));

        Stage highscoreData = new Stage();
        highscoreData.setScene(new Scene(loader.load()));
        highscoreData.show();
        
    }
}
