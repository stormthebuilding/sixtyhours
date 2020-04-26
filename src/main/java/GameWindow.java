import java.io.IOException;
import java.util.ArrayList;

import Model.Enemy;
import Model.Player;
import Model.PlayerObserver;
import Model.Weapon;
import Model.WeaponType;
import Model.World;
import Model.Enemies.Basic;
import Model.Weapons.Pistol;
import Model.Weapons.Rifle;
import Model.Weapons.Sniper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


import javafx.util.Duration;

public class GameWindow implements PlayerObserver {

    @FXML Pane map;
    @FXML Label lblHealth;
    @FXML Label lblCoins;
    @FXML Label lblPoints;
    @FXML Label lblCurMagazine;
    @FXML Label lblMaxMagazine;
    @FXML Label lblCurrentWeapon;
    @FXML Label lblWeaponDamage;
    @FXML Label lblStatus;
    @FXML Label lblShield;
    @FXML Button btnPistol;
    @FXML Button btnRifle;
    @FXML Button btnSniper;
    @FXML Button btnGrenade;
    @FXML Button btnNuke;
    @FXML Menu lstWeapons;
    @FXML CheckBox cboxCheatMode;
    

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
        int bulletNum = World.instance().getPlayer().getCurrentWeapon().getMagazine();
        lblMaxMagazine.setText(String.valueOf(bulletNum));
        lblCurMagazine.setText(String.valueOf(bulletNum));
        lblCoins.setText("Coins: " + World.instance().getCoins());
        lblWeaponDamage.setText("Damage: " + World.instance().player.getCurrentWeapon().getDamage());
        lblHealth.setText("Stronghold health: " + World.instance().stronghold.getHealth());
        lblPoints.setText("Points: " + World.instance().getScore());
        loadEnemies();
        btnNuke.setDisable(true);
       
        
        

        


    }

    // code for spawning a new enemy
    public void spawnEnemies() {
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

    public void loadEnemies() {
        if (!World.instance().enemyList.isEmpty()) {
            ImageView view = null;
            for (Enemy e : World.instance().enemyList) {
                var enemy = e;
                double x = enemy.getX();
                double y = enemy.getY();
                view = new ImageView(new Image("/images/robo.png"));
                view.getStyleClass().add("current");
                view.setId("" + enemy.getId());
                view.relocate(x, y);
                view.setUserData(enemy);
                setEnermy(view);
                map.getChildren().add(view);
            }  
        }
    }

    

    @FXML
    public void onSaveClicked() throws IOException {
        World.instance().save("SavedGame.txt");
    }

    @FXML 
    public void onCheatModeChecked() throws IOException {
        if (cboxCheatMode.isSelected() ) {
            World.instance().setCheatMode(true);
            lblShield.setText("Shield on");
            btnNuke.setDisable(false);
        }
        else {
            World.instance().setCheatMode(false);
            lblShield.setText("Shield off");
            btnNuke.setDisable(true);
        }
        
    }

    @FXML
    public void onNukeClicked() throws IOException {
        // delete from GUI
        map.getChildren().removeIf(n -> n.getId() != null);
        // delete from model
        World.instance().enemyList.clear();
        var updatedList = World.instance().getObjectCollection();
        updatedList.clear();
        World.instance().setObjectCollection(updatedList);
        
    }
    @FXML
    public void onReloadClicked() throws IOException{
        Player p = World.instance().getPlayer();
        p.getCurrentWeapon().setMagazineRest(p.getCurrentWeapon().getMagazine());
        lblCurMagazine.setText(String.valueOf(p.getCurrentWeapon().getMagazineRest()));
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
                            // remove enemy from object list
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
                                    if (!World.instance().isCheatMode() ) { // enemy doesn't damage stronghold if cheat mode is on
                                    World.instance().stronghold.setHealth(health - enemy.getDamage());
                                    }
                                    lblHealth.setText("Stronghold health: " + World.instance().stronghold.getHealth());
                                    
                                    //update status
                                    if(World.instance().stronghold.getHealth() == 0){
                                        lblStatus.setStyle("-fx-text-fill: red; -fx-font-size: 35px;");
                                        lblStatus.setText("Defeat");
                                    }
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

    public void onNextWaveClicked(ActionEvent event) {
        if (World.instance().getCurrentWave() == 0) {
            spawnEnemies();
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), e -> spawnEnemies()));
            timeline.setCycleCount(5);
            timeline.play();
            World.instance().addWave();
        }
        else if (World.instance().getCurrentWave() == 1) {
            spawnEnemies();
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> spawnEnemies()));
            timeline.setCycleCount(10);
            timeline.play();
            World.instance().addWave();
        }
        else if (World.instance().getCurrentWave() == 2) {
            spawnEnemies();
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1150), e -> spawnEnemies()));
            timeline.setCycleCount(25);
            timeline.play();
            World.instance().addWave();
        }
        else if (World.instance().getCurrentWave() == 3) {
            spawnEnemies();
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> spawnEnemies()));
            timeline.setCycleCount(40);
            timeline.play();
            World.instance().addWave();
        }
        else if (World.instance().getCurrentWave() == 4) {
            spawnEnemies();
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(925), e -> spawnEnemies()));
            timeline.setCycleCount(55);
            timeline.play();
            World.instance().addWave();
        }
    }

    @FXML
    private void setEnermy(Node node) {

        node.setOnMouseClicked(me -> {
            Weapon w = World.instance().getPlayer().getCurrentWeapon();
            Enemy e = (Enemy) node.getUserData();
            if(w.getMagazineRest()>=1){
                w.setMagazineRest(w.getMagazineRest()-1);
                e.damageEnemy(w.getDamage());
                
                lblCurMagazine.setText(String.valueOf(w.getMagazineRest()));
            }
            
        });
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
                    if(World.instance().player.getCurrentWeapon() == weapon){
                        lblMaxMagazine.setText("" + weapon.getMagazine());
                    }
                }
            }
            btnPistol.setText("Fully Upgraded");
        }
    }

    public void onRifleClicked(ActionEvent event) {
        ArrayList<Weapon> list = World.instance().player.getWeaponList();
        boolean check = false;
        for (int i = 0; i < list.size(); ++i) {
            Weapon weapon = list.get(i);
            if (weapon.getType() == WeaponType.RIFLE) {
                check = true;
                // TODO: Here is a bug, I dont know waht you mean, it will upgrade without deduction when the getCoin didn;t achieve the requirement
                if (World.instance().getCoins() >= 250) {
                    World.instance().subtractCoins(250);
                    weapon.setDamage(weapon.getDamage() + 8);
                    lblWeaponDamage.setText("Damage: " + World.instance().player.getCurrentWeapon().getDamage());
                    btnRifle.setText("Fully Upgraded");
                }
            }
        }
        if (check == false && World.instance().getCoins() >= 100) {
            World.instance().subtractCoins(100);
            lblCoins.setText("Coins: " + World.instance().getCoins());
            btnRifle.setText("Upgrade: 250 Coins");
            Rifle rifle = new Rifle(WeaponType.RIFLE);
            World.instance().player.addWeapon(rifle);
            MenuItem item = new MenuItem();
            item.setText("Rifle");
            item.setOnAction(this::handleRifle);
            lstWeapons.getItems().add(item);
        }
    }

    public void onSniperClicked(ActionEvent event) {
        ArrayList<Weapon> list = World.instance().player.getWeaponList();
        boolean check = false;
        for (int i = 0; i < list.size(); ++i) {
            Weapon weapon = list.get(i);
            if (weapon.getType() == WeaponType.SNIPER) {
                check = true;
                if (World.instance().getCoins() >= 500) {
                    World.instance().subtractCoins(500);
                    weapon.setMagazine(weapon.getMagazine() + 3);
                    if(World.instance().player.getCurrentWeapon() == weapon){
                        lblMaxMagazine.setText("" + weapon.getMagazine());
                    }
                    lblWeaponDamage.setText("Damage: " + World.instance().player.getCurrentWeapon().getDamage());
                    btnSniper.setText("Fully Upgraded");
                }
            }
        }
        if (check == false && World.instance().getCoins() >= 100) {
            World.instance().subtractCoins(100);
            lblCoins.setText("Coins: " + World.instance().getCoins());
            btnSniper.setText("Upgrade: 500 Coins");
            Sniper sniper = new Sniper(WeaponType.SNIPER);
            World.instance().player.addWeapon(sniper);
            MenuItem item = new MenuItem();
            item.setText("Sniper");
            item.setOnAction(this::handleSniper);
            lstWeapons.getItems().add(item);
        }
    }

    public void onlstPistolClicked(ActionEvent event) {
        ArrayList<Weapon> list = World.instance().player.getWeaponList();
        for (int i = 0; i < list.size(); ++i) {
            Weapon weapon = list.get(i);
            if (weapon.getType() == WeaponType.PISTOL) {
                World.instance().player.setCurrentWeapon(weapon);
                lblMaxMagazine.setText("" + World.instance().player.getCurrentWeapon().getMagazine());
                lblCurMagazine.setText("" + World.instance().player.getCurrentWeapon().getMagazineRest());
                lblCurrentWeapon.setText("Current Weapon: Pistol");
                lblWeaponDamage.setText("Damage: " + World.instance().player.getCurrentWeapon().getDamage());
                
            }
        }
    }

    public void handleRifle(ActionEvent event) {
        ArrayList<Weapon> list = World.instance().player.getWeaponList();
        for (int i = 0; i < list.size(); ++i) {
            Weapon weapon = list.get(i);
            if (weapon.getType() == WeaponType.RIFLE) {
                World.instance().player.setCurrentWeapon(weapon);
                lblMaxMagazine.setText("" + World.instance().player.getCurrentWeapon().getMagazine());
                lblCurMagazine.setText("" + World.instance().player.getCurrentWeapon().getMagazineRest());
                lblCurrentWeapon.setText("Current Weapon: Rifle");
                lblWeaponDamage.setText("Damage: " + World.instance().player.getCurrentWeapon().getDamage());
            }
        }
    }

    public void handleSniper(ActionEvent event) {
        ArrayList<Weapon> list = World.instance().player.getWeaponList();
        for (int i = 0; i < list.size(); ++i) {
            Weapon weapon = list.get(i);
            if (weapon.getType() == WeaponType.SNIPER) {
                World.instance().player.setCurrentWeapon(weapon);
                lblMaxMagazine.setText("" + World.instance().player.getCurrentWeapon().getMagazine());
                lblCurMagazine.setText("" + World.instance().player.getCurrentWeapon().getMagazineRest());
                lblCurrentWeapon.setText("Current Weapon: Sniper");
                lblWeaponDamage.setText("Damage: " + World.instance().player.getCurrentWeapon().getDamage());
            }
        }
    }

}
