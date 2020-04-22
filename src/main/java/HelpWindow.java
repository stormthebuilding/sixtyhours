
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class HelpWindow {
        // GUI controls

        @FXML Label title;
        @FXML Label greet;
    
        // FXML Event Handlers
    
        @FXML
        void initialize() {
            title.setText("Instructions");
            title.setTextFill(Color.BROWN);
            // create a font 
            Font titleFont = Font.font("Verdana", FontWeight.EXTRA_BOLD, 30); 
      
            // set font of the text 
            title.setFont(titleFont);


            greet.setText("Have a great game!");
            greet.setTextFill(Color.BROWN);
            // create a font 
            Font greetFont = Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 30); 
      
            // set font of the text 
            greet.setFont(greetFont);
        }

}