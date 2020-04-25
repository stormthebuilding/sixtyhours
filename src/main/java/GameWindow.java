import java.io.IOException;
import java.util.ArrayList;

import Model.Enemy;
import Model.EnemyType;
import Model.Player;
import Model.PlayerObserver;
import Model.Weapon;
import Model.WeaponType;
import Model.World;
import Model.Enemies.Basic;
import Model.Weapons.Pistol;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.util.Duration;

public class GameWindow implements PlayerObserver {

    @FXML
    Pane map;
    @FXML
    Label lblHealth;
    @FXML
    Label lblCoins;
    @FXML
    Label lblPoints;
    @FXML
    Label lblCurMagazine;
    @FXML
    Label lblMaxMagazine;
    @FXML
    Button btnPistol;
    @FXML
    Button btnRifle;
    @FXML
    Button btnSniper;
    @FXML
    Button btnGrenade;

    @FXML
    void initialize() {
        World.instance();
        ImageView view = new ImageView(new Image("/images/strongholdmap.jpg"));
        // view.setPreserveRatio(true);
        view.setFitWidth(1180);
        view.setFitHeight(850);
        map.getChildren().add(view);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> handleEnemies()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        int bulletNum = World.instance().getPlayer().getClipCapacity();
        lblMaxMagazine.setText(String.valueOf(bulletNum));
        lblCurMagazine.setText(String.valueOf(bulletNum));
    }

    // code for spawning a new enemy
    public void onSpawnEnemyClicked() {
        Basic enemy = World.instance().spawnBasic();
        double x = enemy.getX();
        double y = enemy.getY();
        ImageView view = new ImageView(new Image("/images/robo.png"));
        view.getStyleClass().add("current");
        view.setId("" + enemy.getId());
        view.relocate(x, y);
        view.setUserData(enemy);
        setEnermy(view);
        
        //miss shoot !caution need change in the future
        // if(Integer.parseInt(lblCurMagazine.getText())>=1){
        //     map.setOnMouseClicked(me -> lblCurMagazine.setText(String.valueOf(Integer.parseInt(lblCurMagazine.getText())-1)));
        // }
        
        map.getChildren().add(view);
    }

    @FXML
    public void onSaveClicked() throws IOException {
        World.instance().save("SavedGame.txt");
    }



    

    @FXML
    public void onReloadClicked() throws IOException{
        lblCurMagazine.setText(lblMaxMagazine.getText());
    }

    // code for enemy attack and movement
    public void handleEnemies() {
        for (Node node : map.getChildren()) {
            if (node.getStyleClass().contains("current")) {
                for (int i = 0; i < World.instance().enemyList.size(); ++i) {
                    Enemy enemy = World.instance().enemyList.get(i);
                    if (Integer.parseInt(node.getId()) == enemy.getId()) {
                        if (enemy.getHealth() <= 0) {
                            map.getChildren().remove(node);
                            World.instance().enemyList.remove(enemy);
                            var updatedList = World.instance().getObjectCollection();
                            updatedList.remove(enemy);
                            World.instance().setObjectCollection(updatedList);
                            World.instance().addScore(10);
                            int score = World.instance().getScore();
                            lblPoints.setText("Points: " + score);
                            World.instance().addCoins(3);
                            int coins = World.instance().getCoins();
                            lblCoins.setText("Coins: " + coins);
                        }
                        else {
                            if (node.getLayoutX() >= 700) {
                                if (World.instance().stronghold.getHealth() <= 0) {
                                    //implementation for loosing game goes here
                                }
                                else {
                                    double health = World.instance().stronghold.getHealth();
                                    World.instance().stronghold.setHealth(health - enemy.getDamage());
                                    lblHealth.setText("Stronghold health: " + World.instance().stronghold.getHealth());
                                }
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
    }

    @FXML
    private void setEnermy(Node node) {
        // final Delta dragDelta = new Delta();

        // node.setOnMouseEntered(me -> node.getScene().setCursor(Cursor.HAND));
        // node.setOnMouseExited(me -> node.getScene().setCursor(Cursor.DEFAULT));
        // node.setOnMousePressed(me -> {
        // dragDelta.x = me.getX();
        // dragDelta.y = me.getY();
        // node.getScene().setCursor(Cursor.MOVE);
        // });
        // node.setOnMouseDragged(me -> {
        // node.setLayoutX(node.getLayoutX() + me.getX() - dragDelta.x);
        // node.setLayoutY(node.getLayoutY() + me.getY() - dragDelta.y);
        // Enemy e = (Enemy) node.getUserData();
        // e.setX(node.getLayoutX() + me.getX() - dragDelta.x);
        // e.setY(node.getLayoutY() + me.getY() - dragDelta.y);
        // });
        // node.setOnMouseReleased(me -> node.getScene().setCursor(Cursor.HAND));

        node.setOnMouseClicked(me -> {
            Enemy e = (Enemy) node.getUserData();
            int magazineRest = Integer.parseInt(lblCurMagazine.getText());
            if(magazineRest>=1){
                int health = e.damageEnemy(World.instance().player.getCurrentWeapon().getDamage());
                // !Caution need change in the future
                //Unfinished version Data is not from the model
                
                lblCurMagazine.setText(String.valueOf(magazineRest-1));
            }

            // Player p = World.instance().getPlayer();
            // int dmg = p.attack();
            // Enemy e = (Enemy) node.getUserData();
            // if(p.getClipRest()>=1){
            //     e.damageEnemy(dmg);
            //     p.setClipRest(p.getClipRest()-1); 
            //     lblCurMagazine.setText(String.valueOf(p.getClipRest()));
            // }

            
        });
    }

    void shoot(Node node){
        int dmg = World.instance().getPlayer().attack();
    }

    @Override
    public void update(Player player) {
        // player.();
    }

    public void onPistolClicked(ActionEvent event) {
        if (World.instance().getCoins() >= 50) {
            World.instance().subtractCoins(50);
            lblCoins.setText("Coins: " + World.instance().getCoins());
            ArrayList<Weapon> list = World.instance().player.getWeaponList();
            for (int i = 0; i < list.size(); ++i) {
                Weapon weapon = list.get(i);
                if (weapon.getType() == WeaponType.PISTOL) {
                    weapon.setMagazine(weapon.getMagazine() + 5);
                    lblMaxMagazine.setText("" + weapon.getMagazine());
                }
            }
        }
    }

    public void onRifleClicked(ActionEvent event) {
        if (World.instance().getCoins() >= 15) {
            World.instance().subtractCoins(15);
            lblCoins.setText("Coins: " + World.instance().getCoins());
            btnRifle.setText("Upgrade: TBI");
        }
    }

    public void onSniperClicked(ActionEvent event) {
        if (World.instance().getCoins() >= 100) {
            World.instance().subtractCoins(100);
            lblCoins.setText("Coins: " + World.instance().getCoins());
            btnSniper.setText("Upgrade: TBI");
        }
    }

    public void onGrenadeClicked(ActionEvent event) {
        btnGrenade.setText("Upgrade: TBI");
    }

    // private class Delta {
    //     public double x;
    //     public double y;
    // }
}
