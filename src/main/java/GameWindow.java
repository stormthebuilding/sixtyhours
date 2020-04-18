import java.io.FileInputStream;

import Model.Enemy;
import Model.World;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameWindow {

    @FXML
    Pane map;

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
        double x = enemy.getX();
        double y = enemy.getY();
        ImageView view = new ImageView(new Image("/images/robo.png"));
        view.getStyleClass().add("current");
        view.setId("" + enemy.getId());
        view.relocate(x, y);
        map.getChildren().add(view);
    }

    public void handleEnemies() {
        for (Node node: map.getChildren()) {
            if (node.getStyleClass().contains("current")) {
                for (int i = 0; i < World.instance().enemyList.size(); ++i) {
                    Enemy enemy = World.instance().enemyList.get(i);
                    if (Integer.parseInt(node.getId()) == enemy.getId()) {
                        if (enemy.getHealth() <= 0) {
                            map.getChildren().remove(node);
                            World.instance().enemyList.remove(enemy);
                        }
                        else {
                            if (node.getLayoutX() >= 870) {

                            }
                            else {
                                double x = node.getLayoutX();
                                node.setLayoutX(x + 4);
                                enemy.setX(node.getLayoutX());
                            }
                        }
                    }
                }
            }
        }



        // for (int i = 0; i < World.instance().enemyList.size(); ++i) {
        //     Enemy enemy = World.instance().enemyList.get(i);
        //     if (enemy.getHealth() <= 0) {
        //         World.instance().enemyList.remove(i);
        //     }
        //     else {
        //         if (enemy.getX() >= 870) {
        //             int damage = enemy.attack();
        //             int health = World.instance().stronghold.getHealth();
        //             World.instance().stronghold.setHealth(health - damage);
        //         }
        //         else {
        //             enemy.moveEnemy();
        //         }
        //     }
        // }
    }
}
