package cwk4;

import java.io.Serializable;

public class Champion implements Serializable {
    private String name;
    private int skillLevel;
    private int entryFee;
    private ChampionState state;

    public Champion(String name, int skillLevel, int entryFee) {
        this.name = name;
        this.skillLevel = Math.min(Math.max(skillLevel, 1), 10);
        this.entryFee = entryFee;
        this.state = ChampionState.WAITING;
    }

    public Champion() {}

    public String getName(){
        return name;
    }

    public int getskillLevel(){
        return skillLevel;
    }

    public int getEntryFee(){
        return entryFee;
    }

    public ChampionState getState(){
        return state;
    }

    public void setState(ChampionState state){
        this.state = state;
    }

    public void setSkillLevel(int skillLevel){
        if (skillLevel >= 1  && skillLevel <=10) {
            this.skillLevel = skillLevel;
        }
    }

    public String toString(){
        return "\nName: " + getName() +
                "\nSKill level: " + getskillLevel() +
                "\nEntry fee: " + getEntryFee() +
                "\nChampion state: " + getState();
    }

}
