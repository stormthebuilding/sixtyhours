package Model;

public class Player implements Serializer {
    private PlayerObserver observer;
    
    private int clipCapacity;
    private int clipRest;
    private int point;
    private int score;
    private Weapon currentWeapon;


    public Player() {
        Weapon pistol = new Weapon(WeaponType.PISTOL, 2);
        currentWeapon = pistol;
        pistol.setMagazine(6);
        clipCapacity = currentWeapon.getMagazine();
        clipRest = clipCapacity;
    }

    //called when the player attacks an enemy
    //check the weapon then edit the corresponding enemy
    public int attack() {
        //get the category of weapon, get the number of magazine
        int magazine = currentWeapon.getMagazine();
        observer.update(this);

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
        String[] splitted = data.split(";")[1].split(",");
        clipCapacity = Integer.parseInt(splitted[0]);
        clipRest = Integer.parseInt(splitted[1]);
        point = Integer.parseInt(splitted[2]);
        score = Integer.parseInt(splitted[3]);
        if (splitted[4].equals("PISTOL")) {
            currentWeapon.type = WeaponType.PISTOL;
        }
        else if (splitted[4].equals("RIFLE")) {
            currentWeapon.type = WeaponType.RIFLE;
        }
        else if (splitted[4].equals("SNIPER")) {
            currentWeapon.type = WeaponType.SNIPER;
        }
        else if (splitted[4].equals("MACHINEGUN")) {
            currentWeapon.type = WeaponType.MACHINEGUN;
        }
        else if (splitted[4].equals("GRENADE")) {
            currentWeapon.type = WeaponType.GRENADE;
        }
        currentWeapon.cost = Integer.parseInt(splitted[5]);
        currentWeapon.damage = Integer.parseInt(splitted[6]);
    }


    
}