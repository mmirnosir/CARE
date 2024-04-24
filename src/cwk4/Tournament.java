package cwk4;
import java.util.*;
import java.io.*;
/**
 * This interface specifies the behaviour expected from CARE
 * as required for 5COM2007 Cwk 4
 * 
 * @author 
 * @version 
 */

public class Tournament implements CARE, Serializable {

    private String vizier;
    private int treasury;
    private String filename;
    private ArrayList<Champion> championReserves;
    private ArrayList<Challenges> challengesReserves;
    private ArrayList<Champion> viziersTeam = new ArrayList<>();;
    private ArrayList<Champion> disqualified;


//**************** CARE ************************** 

    /**
     * Constructor requires the name of the vizier
     *
     * @param viz the name of the vizier
     */
    public Tournament(String viz) {
        this.treasury = 1000;
        this.challengesReserves = new ArrayList<>();
        setupChampions();
        setupChallenges();

    }

    /**
     * Constructor requires the name of the vizier and the
     * name of the file storing challenges
     *
     * @param viz      the name of the vizier
     * @param filename name of file storing challenges
     */
    public Tournament(String viz, String filename)  //Task 3.5
    {
        this.vizier = viz;
        this.filename = filename;

        setupChampions();
        readChallenges(filename);
        Tournament tournament = new Tournament("Vizier's Name", "challengesAM.txt");
    }


    /**
     * Returns a String representation of the state of the game,
     * including the name of the vizier, state of the treasury,
     * whether defeated or not, and the champions currently in the
     * team,(or, "No champions" if team is empty)
     *
     * @return a String representation of the state of the game,
     * including the name of the vizier, state of the treasury,
     * whether defeated or not, and the champions currently in the
     * team,(or, "No champions" if team is empty)
     **/
    public String toString() {
        String s = "\nVizier: " + vizier + "\nTreasury: " + treasury + "\nDefeated: " + isDefeated() +
                "\nChampions: " + getTeam();

        return s;
    }
    
    
    /** returns true if Treasury <=0 and the vizier's team has no 
     * champions which can be retired. 
     * @returns true if Treasury <=0 and the vizier's team has no 
     * champions which can be retired. 
     */
    public boolean isDefeated() {
        return this.treasury <= 0 && this.getReserve() == null;
    }
    
    /** returns the amount of money in the Treasury
     * @returns the amount of money in the Treasury
     */
    public int getMoney() {
        return this.treasury;
    }
    
    
    /**Returns a String representation of all champions in the reserves
     * @return a String representation of all champions in the reserves
     **/
    public String getReserve() {
        String s = "********** All champions *********";

        if (championReserves.isEmpty()) {
            return null;
        }
        else {
            for (Champion champ : championReserves) {
                s += "\n" + champ.toString();
            }
        }
        return s;
    }


    /**
     * Returns details of the champion with the given name.
     * Champion names are unique.
     *
     * @return details of the champion with the given name
     **/
    public String getChampionDetails(String nme)
    {
        Champion champ = getChampion(nme);
        return champ.toString();
    }

    /**
     * returns whether champion is in reserve
     *
     * @param nme champion's name
     * @return true if champion in reserve, false otherwise
     */
    public boolean isInReserve(String nme) {
        return getReserve().contains(nme);
    }
 
    // ***************** Team champions ************************   
     /** Allows a champion to be entered for the vizier's team, if there 
     * is enough money in the Treasury for the entry fee.The champion's 
     * state is set to "active"
     * 0 if champion is entered in the vizier's team, 
     * 1 if champion is not in reserve, 
     * 2 if not enough money in the treasury, 
     * -1 if there is no such champion 
     * @param nme represents the name of the champion
     * @return as shown above
     **/
    public int enterChampion(String nme) {
        try {
            int champsFee = getChampion(nme).getEntryFee();

            if (getMoney() >= champsFee) {
                this.treasury -= champsFee;
                viziersTeam.add(getChampion(nme));
                getChampion(nme).setState(ChampionState.ENTERED);
                championReserves.remove(getChampion(nme));
                return 0;
            }
            else if (getMoney() < champsFee) {
                return 2;
            }
            else if (!isInReserve(nme)) {
                return 1;
            }
            else return -1;
        } catch (NullPointerException e) {
            return -1;
        }
    }
        
     /** Returns true if the champion with the name is in 
     * the vizier's team, false otherwise.
     * @param nme is the name of the champion
     * @return returns true if the champion with the name
     * is in the vizier's team, false otherwise.
     **/
    public boolean isInViziersTeam(String nme) {
        if (viziersTeam.contains(getChampion(nme))) {
            return true;
        }
        else return false;
    }
    
    /** Removes a champion from the team back to the reserves (if they are in the team)
     * Pre-condition: isChampion()
     * 0 - if champion is retired to reserves
     * 1 - if champion not retired because disqualified
     * 2 - if champion not retired because not in team
     * -1 - if no such champion
     * @param nme is the name of the champion
     * @return as shown above 
     **/
    public int retireChampion(String nme)
    {
        try {
            if (viziersTeam.contains(getChampion(nme))) {
                viziersTeam.remove(getChampion(nme));
                getChampion(nme).setState(ChampionState.WAITING);
                this.treasury += getChampion(nme).getEntryFee()/2;
                championReserves.add(getChampion(nme));

                return 0;
            } else if (getChampion(nme).getState() == ChampionState.DISQUALIFIED) {
                return 1;
            } else if (!isInViziersTeam(nme)) {
                return 2;
            } else return -1;
        } catch (NullPointerException e) {
            return 99;
        }

    }
    
    
        
    /**Returns a String representation of the champions in the vizier's team
     * or the message "No champions entered"
     * @return a String representation of the champions in the vizier's team
     **/
    public String getTeam()
    {
        String s = "************ Vizier's Team of champions********";
        // Check if the vizier's team is empty
        if (viziersTeam.isEmpty()) {
            s += "\nNo champions entered";  // Append message if no champions are in the team
        } else {
            // Iterate through each champion in the team and append their details
            for (Champion champion : viziersTeam) {
                s += "\n" + champion.getName();  // Use Champion's toString() method
            }
        }

        return s;
    }
    
     /**Returns a String representation of the disquakified champions in the vizier's team
     * or the message "No disqualified champions "
     * @return a String representation of the disqualified champions in the vizier's team
     **/
    public String getDisqualified()
    {
        String s = "************ Vizier's Disqualified champions********";
        if (disqualified.isEmpty()) {
            return null;
        }
        else {
            for (Champion champ : disqualified) {
                s += "\n" + champ.getName();
            }
        }
        return s;
    }
    
//**********************Challenges************************* 
    /** returns true if the number represents a challenge
     * @param num is the  number of the challenge
     * @return true if the  number represents a challenge
     **/
     public boolean isChallenge(int num)
     {
         for (Challenges challenge : challengesReserves) {
             if (challenge.getChallengeNo() == num) {
                 return true;
             }
         }
         return false;
     }
   
    /** Provides a String representation of an challenge given by 
     * the challenge number
     * @param num the number of the challenge
     * @return returns a String representation of a challenge given by 
     * the challenge number
     **/
    public String getChallenge(int num)
    {
        for (Challenges challenge : challengesReserves) {
            if (challenge.getChallengeNo() == num) {
                // Return the string representation of the challenge
                return challenge.toString();
            }
        }
        return "\nNo such challenge";
    }
    
    /** Provides a String representation of all challenges 
     * @return returns a String representation of all challenges
     **/
    /*public String getAllChallenges()
    {
        String s = "\n************ All Challenges ************\n";
        if (challengesReserves.isEmpty()){
            s += "No challenges available.";
        } else {
            for (Challenges challenge : challengesReserves) {
                s += "\n" + challenge.toString();
            }
        }
        return s;
       }
   */
    public String getAllChallenges() {
        readChallenges("challengesAM.txt");
        String s = "\n************ All Challenges ************\n";
        if (challengesReserves.isEmpty()) {
            s += "No challenges available.";
            return s;
        } else {
            for (Challenges challenge : challengesReserves) {

                s += "\nChallenge No: " + challenge.getChallengeNo() +
                        ", Type: " + challenge.getType() + ", Enemy: " + challenge.getEnemy() +
                        ", Skill Required: " + challenge.getSkillRequired() +
                        ", Reward: " + challenge.getReward();
            }

            return s;
        }
    }


    /** Retrieves the challenge represented by the challenge
     * number.Finds a champion from the team who can meet the 
     * challenge. The results of meeting a challenge will be 
     * one of the following:  
     * 0 - challenge won by champion, add reward to the treasury, 
     * 1 - challenge lost on skills  - deduct reward from
     * treasury and record champion as "disqualified"
     * 2 - challenge lost as no suitable champion is  available, deduct
     * the reward from treasury 
     * 3 - If a challenge is lost and vizier completely defeated (no money and 
     * no champions to withdraw) 
     * -1 - no such challenge 
     * @param chalNo is the number of the challenge
     * @return an int showing the result(as above) of fighting the challenge
     */ 
    public int meetChallenge(int chalNo)
    {
        Challenges challenge = getAChallenge(chalNo);
        if (challenge == null) {
            return -1;
        }

        Champion champion = getChampionForChallenge(challenge);
        if (champion == null) {
            treasury -= challenge.getReward();
            return treasury >= 0 ? 2 : 3;
        }

        if (champion.getskillLevel() > challenge.getSkillRequired()) {
            treasury += challenge.getReward();
            return 0;
        } else {
            treasury -= challenge.getReward();
            champion.setState(ChampionState.DISQUALIFIED);
            disqualified.add(champion);
            championReserves.remove(champion);
            return 1;
        }
    }
 

    //****************** private methods for Task 3 functionality*******************
    //*******************************************************************************


    private void setupChampions()
    {
        championReserves = new ArrayList<>();
        championReserves.add(new Wizard("Ganfrank", 7, "transmutation", true));
        championReserves.add(new Wizard("Rudolf", 6, "invisibility", true));
        championReserves.add(new Warrior("Elblond", 150, "sword"));
        championReserves.add(new Warrior("Flimsi", 200, "bow"));
        championReserves.add(new Dragon("Drabina", false));
        championReserves.add(new Dragon("Golum", true));
        championReserves.add(new Warrior("Argon", 900, "mace"));
        championReserves.add(new Wizard("Neon", 2, "translocation", false));
        championReserves.add(new Dragon("Xenon", true));
        championReserves.add(new Warrior("Atlanta", 500, "bow"));
        championReserves.add(new Wizard("Krypton", 8, "fireballs", false));
        championReserves.add(new Wizard("Hedwig", 1, "flying", true));
   }

   private Champion getChampion(String name){
        for (Champion champion : championReserves){
            if (champion.getName().equals(name)){
                return champion;
            }
        }
        return null;
   }

    public boolean canMeetChallenge(Champion champion, Challenges challenge) {
        ChallengeType challengeType = challenge.getType();

        if (champion instanceof Wizard) {
            // Wizards can meet any type of challenge
            return true;
        } else if (champion instanceof Warrior) {
            // Warriors can only meet Fight challenges
            return challengeType == ChallengeType.FIGHT;
        } else if (champion instanceof Dragon) {
            Dragon dragon = (Dragon) champion;
            // Dragons can meet Fight challenges, or Mystery challenges if they talk
            return challengeType == ChallengeType.FIGHT || (dragon.isTalking() && challengeType == ChallengeType.MYSTERY);
        }
        return false;
    }

    private void setupChallenges()
    {
        challengesReserves = new ArrayList<>();
        challengesReserves.add(new Challenges(1,ChallengeType.MAGIC,"Borg",3,100));
        challengesReserves.add(new Challenges(2,ChallengeType.FIGHT,"Huns",3,120));
        challengesReserves.add(new Challenges(3,ChallengeType.MYSTERY,"Ferengi",3,150));
        challengesReserves.add(new Challenges(4,ChallengeType.MAGIC,"Vandal",9,200));
        challengesReserves.add(new Challenges(5,ChallengeType.MYSTERY,"Borg",7,90));
        challengesReserves.add(new Challenges(6,ChallengeType.FIGHT,"Goth",8,45));
        challengesReserves.add(new Challenges(7,ChallengeType.MAGIC,"Frank ",10,200));
        challengesReserves.add(new Challenges(8,ChallengeType.FIGHT,"Sith ",10,170));
        challengesReserves.add(new Challenges(9,ChallengeType.MYSTERY,"Cardashian",9,300));
        challengesReserves.add(new Challenges(10,ChallengeType.FIGHT,"Jute",2,300));
        challengesReserves.add(new Challenges(11,ChallengeType.MAGIC,"Celt",2,250));
        challengesReserves.add(new Challenges(12,ChallengeType.MYSTERY,"Celt",1,250));
    }

    private Challenges getAChallenge(int challengeNo) {
        for (Challenges challenge : challengesReserves) {
            if (challenge.getChallengeNo() == (challengeNo)) {
                return challenge;
            }
        }
        return null;
    }
    public Champion getChampionForChallenge(Challenges chal) {
        for (Champion viziersTeam : viziersTeam) {
            if (viziersTeam.getState() == ChampionState.ENTERED &&
                    viziersTeam.getskillLevel() >= chal.getSkillRequired() &&
                    canMeetChallenge(viziersTeam, chal)) {
                return viziersTeam;
            }
        }
        return null; // No suitable champion found
    }
    // Possible useful private methods
//     private Challenge getAChallenge(int no)
//     {
//         
//         return null;
//     }
//    
//     private Champion getChampionForChallenge(Challenge chal)
//     {
//         
//         return null;
//     }

    //*******************************************************************************
    //*******************************************************************************
  
    /************************ Task 3.5 ************************************************/  
    
    // ***************   file write/read  *********************
    /**
     * reads challenges from a comma-separated textfile and stores in the game
     * @param filename of the comma-separated textfile storing information about challenges
     */
    /*public void readChallenges(String filename)
    {
        
    }*/
    public void readChallenges(String filename) {
        challengesReserves = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                //if (data.length < 5) continue; // Skip if data is incomplete

                try {
                    int challengeNo = challengesReserves.size() + 1; // Generate sequential challenge number
                    ChallengeType type = ChallengeType.valueOf(data[0].trim().toUpperCase());
                    String enemy = data[1].trim();
                    int skillRequired = Integer.parseInt(data[2].trim());
                    int reward = Integer.parseInt(data[3].trim());

                    challengesReserves.add(new Challenges(challengeNo, type, enemy, skillRequired, reward));
                } catch (NumberFormatException ex) {
                    System.out.println("Error parsing integer values: " + ex.getMessage());
                } catch (IllegalArgumentException ex) {
                    System.out.println("Error with ChallengeType: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading file '" + filename + "': " + ex.getMessage());
        }
    }


    
     /** reads all information about the game from the specified file 
     * and returns a CARE reference to a Tournament object, or null
     * @param fname name of file storing the game
     * @return the game (as a Tournament object)
     */

    public Tournament loadGame(String fname)
    {   // uses object serialisation 

        try {
            FileInputStream fileIn = new FileInputStream(fname);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Tournament yyy = (Tournament) in.readObject();
            in.close();
            fileIn.close();

            return yyy;
        } catch (Exception e) {
            System.out.println("[-] Couldn't been able to load the game");
        }
        return null;
   } 
   
   /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
   public void saveGame(String fname){
        // uses object serialisation
       try {
           FileOutputStream fileOut = new FileOutputStream(fname);
           ObjectOutputStream out = new ObjectOutputStream(fileOut);

           out.writeObject(this);
           out.close();
           fileOut.close();

           System.out.println("[+] Game has been saved successfully.");
       } catch (Exception e){
           System.out.println("[+] Could not save game.");
           e.printStackTrace();
       }
        
    }
 

}



