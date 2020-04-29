import java.io.File;
import javafx.scene.media.AudioClip;
import java.io.IOException;
import java.util.ArrayList;

import Model.DifficultyType;
import Model.Enemy;
import Model.HighScore;
import Model.Player;
import Model.Score;
import Model.Serializer;
import Model.Weapon;
import Model.WeaponType;
import Model.World;
import Model.Enemies.Advanced;
import Model.Enemies.Basic;
import Model.Enemies.Boss;
import Model.Enemies.Heavy;
import Model.Weapons.Pistol;
import Model.Weapons.Rifle;
import Model.Weapons.Sniper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameWindow {

    public String difficulty = "Easy";

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
    @FXML Button btnNextWave;
    @FXML Menu lstWeapons;
    @FXML CheckBox cboxCheatMode;

    // Audio
    AudioClip loserSound = new AudioClip(new File("src/main/resources/sounds/loser.mp3").toURI().toString());
    AudioClip confirmationSound = new AudioClip(new File("src/main/resources/sounds/confirmation_001.mp3").toURI().toString());
    AudioClip applauseSound = new AudioClip(new File("src/main/resources/sounds/applause.mp3").toURI().toString());
    AudioClip clickSound = new AudioClip(new File("src/main/resources/sounds/click_001.mp3").toURI().toString());
    AudioClip gameOverSound = new AudioClip(new File("src/main/resources/sounds/game_over.mp3").toURI().toString());
    AudioClip laserSound = new AudioClip(new File("src/main/resources/sounds/laser1.mp3").toURI().toString());
    AudioClip explosionSound = new AudioClip(new File("src/main/resources/sounds/explosion.mp3").toURI().toString());
    AudioClip emptySound = new AudioClip(new File("src/main/resources/sounds/empty.mp3").toURI().toString());
    AudioClip reloadSound = new AudioClip(new File("src/main/resources/sounds/reload.mp3").toURI().toString());

    
    
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
        for (Weapon weapon : World.instance().player.getWeaponList()) {
            MenuItem item = null;
            if (weapon.getType() == WeaponType.RIFLE) {
                item = new MenuItem();
                item.setText("Rifle");
                item.setOnAction(this::handleRifle);
            }
            else if (weapon.getType() == WeaponType.SNIPER) {
                item = new MenuItem();
                item.setText("Sniper");
                item.setOnAction(this::handleSniper);
            }
            if (item!= null) {
                lstWeapons.getItems().add(item);
            }
        }
        for (Weapon weapon : World.instance().player.getWeaponList()) {
            if (weapon.getType() == WeaponType.PISTOL) {
                if (weapon.getDamage()!=2 || weapon.getMagazine()!= 7) {
                    btnPistol.setText("Fully Upgraded");
                    btnPistol.setDisable(true);
                }   
            }
            else if (weapon.getType() == WeaponType.RIFLE) {
                if (weapon.getDamage()!=4 || weapon.getMagazine()!= 10) {
                    btnRifle.setText("Fully Upgraded");
                    btnRifle.setDisable(true);
                }
            }
            else if (weapon.getType() == WeaponType.SNIPER) {
                if (weapon.getDamage()!=20 || weapon.getMagazine()!= 1) {
                    btnSniper.setText("Fully Upgraded");
                    btnSniper.setDisable(true);
                }
            }
        }
        // update currently selected Weapon
        String currentWeaponLowerCase = (""+World.instance().getPlayer().getCurrentWeapon().getType()).toLowerCase();
        String currentWeaponCapitalized = currentWeaponLowerCase.substring(0, 1).toUpperCase() + currentWeaponLowerCase.substring(1);
        lblCurrentWeapon.setText("Current Weapon: "+currentWeaponCapitalized);
        int bulletNum = World.instance().getPlayer().getCurrentWeapon().getMagazine();
        lblMaxMagazine.setText(String.valueOf(bulletNum));
        lblCurMagazine.setText(String.valueOf(bulletNum));
        lblWeaponDamage.setText("Damage: " + World.instance().player.getCurrentWeapon().getDamage());
        lblCoins.setText("Coins: " + World.instance().getCoins());
        lblHealth.setText("Stronghold health: " + World.instance().stronghold.getHealth());
        lblPoints.setText("Points: " + World.instance().getScore());
        loadEnemies(); // load preexisting enemies if there are any
        if (!World.instance().isCheatMode()) { 
            cboxCheatMode.setSelected(false); 
            btnNuke.setDisable(true);
            lblShield.setText("Shield off");
        }
        else {
            cboxCheatMode.setSelected(true); 
            btnNuke.setDisable(false);
            lblShield.setText("Shield on");
        }
    }

    // code for spawning a new enemy
    public void spawnBasic() {
        Basic enemy = World.instance().spawnBasic();
        double x = enemy.getX();
        double y = enemy.getY();
        ImageView view = new ImageView(new Image("/images/robo.png"));
        view.getStyleClass().add("current");
        view.setId("" + enemy.getId());
        view.relocate(x, y);
        view.setUserData(enemy);
        setEnermy(view);       
        map.getChildren().add(view);
    }

    public void spawnAdvanced() {
        Advanced enemy = World.instance().spawnAdvanced();
        double x = enemy.getX();
        double y = enemy.getY();
        ImageView view = new ImageView(new Image("/images/advanced.png"));
        view.getStyleClass().add("current");
        view.setId("" + enemy.getId());
        view.relocate(x, y);
        view.setUserData(enemy);
        setEnermy(view);       
        map.getChildren().add(view);
    }

    public void spawnHeavy() {
        Heavy enemy = World.instance().spawnHeavy();
        double x = enemy.getX();
        double y = enemy.getY();
        ImageView view = new ImageView(new Image("/images/Heavy.png"));
        view.getStyleClass().add("current");
        view.setId("" + enemy.getId());
        view.relocate(x, y);
        view.setUserData(enemy);
        setEnermy(view);
        map.getChildren().add(view);
    }

    public void spawnBoss() {
        Boss enemy = World.instance().spawnBoss();
        double x = enemy.getX();
        double y = enemy.getY();
        ImageView view = new ImageView(new Image("/images/Boss.png"));
        view.getStyleClass().add("current");
        view.setId("" + enemy.getId());
        view.relocate(x, y);
        view.setUserData(enemy);
        setEnermy(view);
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
        confirmationSound.play();
    }

    @FXML 
    public void onCheatModeChecked() throws IOException {

        World.instance().addCoins(10000);
        lblCoins.setText("Coins: " + World.instance().getCoins());
        
        if (cboxCheatMode.isSelected() ) {
            World.instance().setCheatMode(true);
            lblShield.setText("Shield on");
            btnNuke.setDisable(false);
            clickSound.play();
            loserSound.play();
            
        }
        else {
            World.instance().setCheatMode(false);
            lblShield.setText("Shield off");
            btnNuke.setDisable(true);
            clickSound.play();

        }
        
    }

    @FXML
    public void onMapClicked() {
        Weapon w = World.instance().getPlayer().getCurrentWeapon();
        if(w.getMagazineRest()>=1){
            w.setMagazineRest(w.getMagazineRest()-1);
            laserSound.play();

            lblCurMagazine.setText(String.valueOf(w.getMagazineRest()));
        }
        else {
            emptySound.play();
        }
    }

    @FXML
    public void onNukeClicked() throws IOException {
        explosionSound.play();
        // delete from GUI
        map.getChildren().removeIf(n -> n.getId() != null);
        // delete from model
        World.instance().enemyList.clear();
        World.instance().getObjectCollection().removeIf(n -> n instanceof Enemy);
    }
    @FXML
    public void onReloadClicked() throws IOException{
        reloadSound.play();
        map.setDisable(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1950), e -> reload()));
        timeline.play();
    }

    public void reload(){
        Player p = World.instance().getPlayer();

        p.getCurrentWeapon().setMagazineRest(p.getCurrentWeapon().getMagazine());
        lblCurMagazine.setText(String.valueOf(p.getCurrentWeapon().getMagazineRest()));

        map.setDisable(false);
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
                                        gameOverSound.play();
                                        // Higscores implementation
                                        Score score = new Score(World.instance().getUserName(), World.instance().getScore(), 
                                            DifficultyType.valueOf(World.instance().getDifficulty()));
                                        //System.out.println(score.toString());
                                        try {
                                            if (HighScore.getInstance().findIfScoreQualifiesAsHigh(score)) {
                                                //System.out.println("It is a high Score");
                                                // Show the new Score Screen
                                                displayNewHighScore();

                                                HighScore.getInstance().processScore(score);
                                            }
                                        } catch (IOException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                        
                                        
                                        
                                    }
                                }
                            }
                            else {
                  
                                enemy.setX(enemy.getX() +enemy.getSpeed());
                                node.setLayoutX(node.getLayoutX() +enemy.getSpeed());
                                
                            }
                        }
                    }
                }
            }
        }
    }

    public void onNextWaveClicked(ActionEvent event) {
        if (difficulty.equals("Easy")) {
            if (World.instance().getCurrentWave() == 0) {
                spawnBasic();
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), e -> spawnBasic()));
                timeline.setCycleCount(5);
                timeline.play();
                World.instance().addWave();
                btnNextWave.setDisable(false);
            }
            else if (World.instance().getCurrentWave() == 1) {
                spawnBasic();
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1700), e -> spawnBasic()));
                timeline.setCycleCount(12);
                timeline.play();
                World.instance().addWave();
            }
            else if (World.instance().getCurrentWave() == 2) {
                spawnAdvanced();
                World.instance().addWave();
            }
            else if (World.instance().getCurrentWave() == 3) {
                spawnBasic();
                Thread thread1 = new Thread(() -> {
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2700), e -> spawnBasic()));
                    timeline.setCycleCount(12);
                    timeline.play();
                });
                Thread thread2 = new Thread(() -> {
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(6000), e -> spawnAdvanced()));
                    timeline.setCycleCount(5);
                    timeline.play();
                });
                thread1.start();
                thread2.start();
                World.instance().addWave();
            }
            else if (World.instance().getCurrentWave() == 4) {
                spawnBasic();
                Thread thread1 = new Thread(() -> {
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2700), e -> spawnBasic()));
                    timeline.setCycleCount(17);
                    timeline.play();
                });
                Thread thread2 = new Thread(() -> {
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5600), e -> spawnAdvanced()));
                    timeline.setCycleCount(8);
                    timeline.play();
                });
                thread1.start();
                thread2.start();
                World.instance().addWave();
            }
            else if (World.instance().getCurrentWave() == 5) {
                spawnBasic();
                Thread thread1 = new Thread(() -> {
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1700), e -> spawnBasic()));
                    timeline.setCycleCount(20);
                    timeline.play();
                });
                Thread thread2 = new Thread(() -> {
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5500), e -> spawnAdvanced()));
                    timeline.setCycleCount(12);
                    timeline.play();
                });
                thread1.start();
                thread2.start();
                World.instance().addWave();
            }
            else if (World.instance().getCurrentWave() == 6) {
                spawnAdvanced();
                Thread thread1 = new Thread(() -> {
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1700), e -> spawnAdvanced()));
                    timeline.setCycleCount(12);
                    timeline.play();
                });
                Thread thread2 = new Thread(() -> {
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), e -> spawnAdvanced()));
                    timeline.setCycleCount(8);
                    timeline.play();
                });
                thread1.start();
                thread2.start();
                World.instance().addWave();
            }
            else if (World.instance().getCurrentWave() == 7) {
                spawnHeavy();
                World.instance().addWave();
            }
            else if (World.instance().getCurrentWave() == 8) {
                spawnAdvanced();
                Thread thread1 = new Thread(() -> {
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1700), e -> spawnAdvanced()));
                    timeline.setCycleCount(15);
                    timeline.play();
                });
                Thread thread2 = new Thread(() -> {
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3000), e -> spawnHeavy()));
                    timeline.setCycleCount(7);
                    timeline.play();
                });
                thread1.start();
                thread2.start();
                World.instance().addWave();
            }
            else if (World.instance().getCurrentWave() == 9) {
                spawnAdvanced();
                Thread thread1 = new Thread(() -> {
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> spawnAdvanced()));
                    timeline.setCycleCount(15);
                    timeline.play();
                });
                Thread thread2 = new Thread(() -> {
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2500), e -> spawnHeavy()));
                    timeline.setCycleCount(9);
                    timeline.play();
                });
                Thread thread3 = new Thread(() -> {
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1400), e -> spawnBasic()));
                    timeline.setCycleCount(20);
                });
                Thread thread4 = new Thread(() -> {
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30000), e -> spawnBoss()));
                    timeline.setCycleCount(1);
                    timeline.play();
                });
                thread1.start();
                thread2.start();
                thread3.start();
                thread4.start();
                World.instance().addWave();
            }
        }
        else if (difficulty.equals("Hard")) {

        }
        else if (difficulty.equals("Insane")) {

        }
    }

    @FXML
    private void setEnermy(Node node) {

        node.setOnMouseClicked(me -> {
            Weapon w = World.instance().getPlayer().getCurrentWeapon();
            Enemy e = (Enemy) node.getUserData();
            if(w.getMagazineRest()>=1){
                e.damageEnemy(w.getDamage());
            }
        });
    }

    public void onPistolClicked(ActionEvent event) {
        clickSound.play();
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
            btnPistol.setDisable(true);
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
                    btnRifle.setDisable(true);
                    World.instance().subtractCoins(250);
                    weapon.setDamage(weapon.getDamage() + 8);
                    lblWeaponDamage.setText("Damage: " + World.instance().player.getCurrentWeapon().getDamage());
                    btnRifle.setText("Fully Upgraded");
                    clickSound.play();
                }
            }
        }
        if (check == false && World.instance().getCoins() >= 100) {
            World.instance().subtractCoins(100);
            lblCoins.setText("Coins: " + World.instance().getCoins());
            btnRifle.setText("Upgrade: 250 Coins");
            Rifle rifle = new Rifle(WeaponType.RIFLE);
            // save new rifle to list of objects
            var updatedList = World.instance().getObjectCollection();
            updatedList.add(rifle);
            World.instance().setObjectCollection(updatedList);
            World.instance().player.addWeapon(rifle);
            MenuItem item = new MenuItem();
            item.setText("Rifle");
            item.setOnAction(this::handleRifle);
            lstWeapons.getItems().add(item);
            confirmationSound.play();
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
                    clickSound.play();
                    btnSniper.setDisable(true);
                }
            }
        }
        if (check == false && World.instance().getCoins() >= 100) {
            World.instance().subtractCoins(500);
            lblCoins.setText("Coins: " + World.instance().getCoins());
            btnSniper.setText("Upgrade: 500 Coins");
            Sniper sniper = new Sniper(WeaponType.SNIPER);
            // add new sniper to list of objects
            var updatedList = World.instance().getObjectCollection();
            updatedList.add(sniper);
            World.instance().setObjectCollection(updatedList);
            World.instance().player.addWeapon(sniper);
            MenuItem item = new MenuItem();
            item.setText("Sniper");
            item.setOnAction(this::handleSniper);
            lstWeapons.getItems().add(item);
            confirmationSound.play();
        }
    }

    public void onlstPistolClicked(ActionEvent event) {
        clickSound.play();
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
        clickSound.play();
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
        clickSound.play();
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

    @FXML
    public void onHighScoreClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HighScorePlayers.fxml"));

        Stage highscoreData = new Stage();
        highscoreData.setScene(new Scene(loader.load()));
        highscoreData.show();
        applauseSound.play();
    }

    @FXML
    public void displayNewHighScore() throws IOException {
        // Show the new Score Scrren
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewHighScore.fxml"));
        Stage newHighscore = new Stage();
        newHighscore.setScene(new Scene(loader.load()));
        newHighscore.show();
    }

}
