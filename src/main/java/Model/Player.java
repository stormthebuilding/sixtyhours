package Model;

public class Player implements Serializer {
    private int clipCapacity;
    private int clipRest;
    private int point;
    private int score;
    private Weapon currentWeapon;


    public Player() {
        Weapon pistol = new Weapon(WeaponType.PISTOL, 2);
        currentWeapon = pistol;
    }

    //called when the player attacks an enemy
    //check the weapon then edit the corresponding enemy
    public int attack() {
        return currentWeapon.getDamage();
    }

    public int getClipCapacity() {
        return clipCapacity;
    }

    public void setClipCapacity(int clipCapacity) {
        this.clipCapacity = clipCapacity;
    }

    public int getClipRest() {
        return clipRest;
    }

    public void setClipRest(int clipRest) {
        this.clipRest = clipRest;
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
        else if (currentWeapon.getType() == WeaponType.MACHINEGUN) {
            typeToSave = "MACHINEGUN";
        }
        else if (currentWeapon.getType() == WeaponType.GRENADE) {
            typeToSave = "GRENADE";
        }

    

        serialized = "PLAYER;"+clipCapacity+","+clipRest+","+point+","+score
        +","+typeToSave+","+Integer.toString(currentWeapon.getCost())+","+Integer.toString(currentWeapon.getDamage());

        return serialized;
        
    }

    @Override
    public void deserialize(String data) {
        // TODO Auto-generated method stub

    }


    
}