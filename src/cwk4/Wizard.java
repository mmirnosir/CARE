package cwk4;

public class Wizard extends Champion {
    private String specialitySpell;
    private boolean isNecromancer;

    public Wizard(String name, int skillLevel, String specialitySpell, boolean isNecromancer) {
        super(name, skillLevel, (isNecromancer ? 400 : 300)); // Entry fee for Wizard is 300 gulden
        this.specialitySpell = specialitySpell;
        this.isNecromancer = isNecromancer;
    }

    public String getSpecialitySpell() {
        return specialitySpell;
    }

    public String toString(){
        return super.toString() +
                "Speciality spell: " + specialitySpell +
                "Necromancer: " + isNecromancer;
    }

}
