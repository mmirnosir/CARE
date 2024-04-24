package cwk4;

public class Champion {
    private String name;
    private int skillLevel;
    private int entryFee;
    private ChampionState state;

    public Champion(String name, int skillLevel, int entryFee) {
        this.name = name;
        this.skillLevel = Math.min(Math.max(entryFee, 1), 10);
        this.entryFee = entryFee;
        this.state = ChampionState.WAITING;
    }

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
        return "Name: " + getName() +
                "\nSKill level: " + getskillLevel() +
                "\nEntry fee: " + getEntryFee() +
                "Champion state: " + getState();
    }

}
