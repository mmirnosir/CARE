package cwk4;

public class Warrior extends Champion {
    private String weapon;

    public Warrior (String name, int entryFee, String weapon){
        super(name, 0, entryFee);
        this.setSkillLevel(entryFee/100);
        this.weapon = weapon;
    }

    public String toString(){
        return super.toString() +
                "\nWeapon: " + weapon;
    }

}
