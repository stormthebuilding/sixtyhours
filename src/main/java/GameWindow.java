import java.io.FileInputStream;

import Model.Enemy;
import Model.World;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class GameWindow {

    @FXML Pane map;
    
    @FXML
    void initialize() {
        ImageView view = new ImageView(new Image("/images/strongholdmap.jpg"));
        map.getChildren().add(view);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> handleEnemies()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    public void onSpawnEnemyClicked() {
        Enemy enemy = World.instance().spawnEnemy();
        int x = enemy.getX();
        int y = enemy.getY();
        ImageView view = new ImageView(new Image("/images/robo.png"));
        view.relocate(x, y);
        map.getChildren().add(view);
    }

    public void handleEnemies() {
        for (int i = 0; i < World.instance().enemyList.size(); ++i) {
            Enemy enemy = World.instance().enemyList.get(i);
            if (enemy.getHealth() <= 0) {
                World.instance().enemyList.remove(i);
            }
            else {
                if (enemy.getX() >= 870) {
                    int damage = enemy.attack();
                    int health = World.instance().stronghold.getHealth();
                    World.instance().stronghold.setHealth(health - damage);
                }
                else {
                    enemy.moveEnemy();
                }
            }
        }
    }

    public void moveEnemy(Enemy enemy) {

    }
}
