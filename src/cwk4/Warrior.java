package cwk4;

import java.io.Serializable;

public class Warrior extends Champion implements Serializable {
    private String weapon;

    public Warrior (String name, int entryFee, String weapon){
        super(name, 0, entryFee);
        this.setSkillLevel(entryFee/100);
        this.weapon = weapon;
    }

    public String toString(){
        return super.toString() +
                "\nWeapon: " + weapon +
                "\nType: Warrior";
    }

}
