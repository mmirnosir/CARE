package cwk4;

public class Challenges {
    private int challengeNo;
    private ChallengeType type;
    private String enemy;
    private int skillRequired;
    private int reward;

    public Challenges(int challengeNo,ChallengeType type, String enemy, int skillRequired, int reward) {
        this.challengeNo = challengeNo;
        this.type = type;
        this.enemy = enemy;
        this.skillRequired = skillRequired;
        this.reward = reward;
    }

    // Accessors and mutators
    public int getChallengeNo() {
        return challengeNo;
    }

    public void setChallengeNo(int challengeNo) {
        this.challengeNo = challengeNo;
    }

    public ChallengeType getType() {
        return type;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }

    public int getSkillRequired() {
        return skillRequired;
    }

    public void setSkillRequired(int skillRequired) {
        this.skillRequired = skillRequired;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    // Business methods
    /**
     * Determines if the player has the necessary skill to undertake the challenge.
     * @param skillLevel the skill level of the player
     * @return true if the player's skill matches or exceeds the required skill, false otherwise
     */
    public boolean checkEligibility(int skillLevel) {
        return skillLevel == skillRequired;
    }

    /**
     * Provides a textual description of the challenge.
     * @return a string representing the challenge details
     */
    @Override
    public String toString() {
        return "Challenge{" +
                "Challenge No=" + challengeNo +
                ", Type='" + type + '\'' +
                ", Enemy='" + enemy + '\'' +
                ", Skill Required='" + skillRequired + '\'' +
                ", Reward='" + reward + '\'' +
                '}';
    }
}


