
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GUIApplication extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        var loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        var scene = new Scene(loader.load());

        stage.setTitle("Storm the Stronghold"); // Title of main window
        
        // MenuBar menuBar = new MenuBar();
        // Menu menuSave = new Menu("Save");
        // Menu menuLoad = new Menu("Load");
        // Menu menuHighScore = new Menu("High Score");
        // menuBar.getMenus().addAll(menuSave, menuLoad, menuHighScore);
        // ((VBox) scene.getRoot()).getChildren().addAll(menuBar);

        stage.setScene(scene);
        stage.show();
    }

}
