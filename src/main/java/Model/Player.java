package Model;

import java.util.ArrayList;

import Model.Weapons.*;

public class Player implements Serializer {
    private PlayerObserver observer;
    
    private int point;
    private int score;
    private Weapon currentWeapon;
    private ArrayList<Weapon> weaponList = new ArrayList<Weapon>();


    public Player() {
        Pistol w = new Pistol(WeaponType.PISTOL);
        weaponList.add(w);
        currentWeapon = w;
        // collectObject();
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    public void addWeapon(Weapon weapon) {
        weaponList.add(weapon);
    }

    public void setObserver(PlayerObserver observer) {
        this.observer = observer;
    }

    public PlayerObserver getObserver() {
        return observer;
    }

    @Override
    public String serialize() {
        String serialized = "";
        String typeToSave = "";
    
        if (currentWeapon.getType() == WeaponType.PISTOL) {
            typeToSave = "PISTOL";
        }
        else if (currentWeapon.getType() == WeaponType.RIFLE) {
            typeToSave = "RIFLE";
        }
        else if (currentWeapon.getType() == WeaponType.SNIPER) {
            typeToSave = "SNIPER";
        }

    
        serialized = "PLAYER;"+point+","+score+","+typeToSave+","+currentWeapon.getDamage();

        return serialized;
        
    }

    @Override
    public void deserialize(String data) {
        String[] splitted = data.split(";")[1].split(",");
        point = Integer.parseInt(splitted[0]);
        score = Integer.parseInt(splitted[1]);
        if (splitted[2].equals("PISTOL")) {
            currentWeapon.type = WeaponType.PISTOL;
        }
        else if (splitted[2].equals("RIFLE")) {
            currentWeapon.type = WeaponType.RIFLE;
        }
        else if (splitted[2].equals("SNIPER")) {
            currentWeapon.type = WeaponType.SNIPER;
        }
        currentWeapon.damage = Integer.parseInt(splitted[3]);
    }

    public void collectObject() {
        var objectList = World.instance().getObjectCollection();
        objectList.add(this);
        World.instance().setObjectCollection(objectList);
    }


    
}