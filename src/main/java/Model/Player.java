package Model;

import java.util.ArrayList;

import Model.Weapons.*;

public class Player implements Serializer {
    
    private int point;
    private int score;
    private Weapon currentWeapon;
    private ArrayList<Weapon> weaponList = new ArrayList<Weapon>();


    public Player() {
        Pistol w = new Pistol(WeaponType.PISTOL);
        weaponList.add(w);
        currentWeapon = w;
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

    public void setWeaponList(ArrayList<Weapon> weaponList) {
        this.weaponList = weaponList;
	}

    public void addWeapon(Weapon weapon) {
        weaponList.add(weapon);
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

        serialized = "PLAYER;"+point+","+score+","+typeToSave;

        return serialized;
        
    }

    @Override
    public void deserialize(String data) {

        String[] splitted = data.split(";")[1].split(",");
        point = Integer.parseInt(splitted[0]);
        score = Integer.parseInt(splitted[1]);

        if (splitted[2].equals("PISTOL")) {
            for (Weapon weapon : weaponList) {
                if (weapon.getType() == WeaponType.PISTOL) {
                    currentWeapon = weapon;
                }
            }
        }
        else if (splitted[2].equals("RIFLE")) {
            for (Weapon weapon : weaponList) {
                if (weapon.getType() == WeaponType.RIFLE) {
                    currentWeapon = weapon;
                }
            }
        }
        else if (splitted[2].equals("SNIPER")) {
            for (Weapon weapon : weaponList) {
                if (weapon.getType() == WeaponType.SNIPER) {
                    currentWeapon = weapon;
                }
            }
        }
    }



}