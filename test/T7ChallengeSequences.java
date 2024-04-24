/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cwk4.CARE;
import cwk4.Tournament;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author comqaam
 */
public class T7ChallengeSequences {
    CARE game;
    public T7ChallengeSequences() {
    }
    
     @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        game = new Tournament("Jean");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    //Action sequences
    @Test
    public void warriorDeadUsedAgain() {
        int expected = 2;
        game.enterChampion("Flimsi");
        game.meetChallenge(2);  //should be dead
        int actual = game.meetChallenge(2); //re-used ?
        assertEquals(expected, actual);
    }
    
    @Test
    public void warriorDeadUsedAgainMoney() {
        int expected = 1000-200-120-120;
        game.enterChampion("Flimsi");
        game.meetChallenge(2);  //should be dead
        game.meetChallenge(2); //re-used ?
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    @Test
    public void warriorWithdrawnBeforeFight() {
        int expected = 2;
        game.enterChampion("Argon");
        game.retireChampion("Argon");
        int actual = game.meetChallenge(2); //used ?
        assertEquals(expected, actual);
    }
    
    @Test
    public void warriorWithdrawnBeforeFightMoney() {
        int expected = 1000-900+450-120;
        game.enterChampion("Argon");
        game.retireChampion("Argon");
        game.meetChallenge(2);  //not available
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    @Test
    public void defeatAchieved() {
        int expected = 3;
        game.enterChampion("Rudolf");
        game.enterChampion("Flimsi");
        game.meetChallenge(8);  //lose
        game.meetChallenge(8);  //lose
        game.meetChallenge(8);  //no one left
        int actual = game.meetChallenge(8);  //no one left
        assertEquals(expected, actual);
    }
    
    @Test
    public void defeatWithWithdrawalMoney() {
        int expected = 1000-400-200-170+100-170-170;
        game.enterChampion("Rudolf");
        game.enterChampion("Flimsi");
        game.meetChallenge(8);  //lose
        game.retireChampion("Flimsi");
        game.meetChallenge(8);  //no one left
        game.meetChallenge(8);  //no one left
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    @Test
    public void defeatNotAchieved() {
        int expected = 2;
        game.enterChampion("Drabina");
        game.enterChampion("Flimsi");
        game.meetChallenge(4);  //lose as no one available
        game.meetChallenge(4);  //lose as no one available
        int actual = game.meetChallenge(4);  //lose as no one available
        assertEquals(expected, actual);
    }
    
    @Test
    public void defeatNotAchievedMoney() {
        int expected = 1000-500-200-200-200-200;
        game.enterChampion("Drabina");
        game.enterChampion("Flimsi");
        game.meetChallenge(4);  //lose as no one available
        game.meetChallenge(4);  //lose as no one available
        game.meetChallenge(4);  //lose as no one available
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }

    @Test
    public void defeatAchievedAfterWithdraw() {
        int expected = 3;
        game.enterChampion("Drabina");
        game.enterChampion("Flimsi");
        game.meetChallenge(4);  //lose as no one available
        game.meetChallenge(4);  //lose as no one available
        game.retireChampion("Drabina");
        game.retireChampion("Flimsi"); //no one in Team
        game.meetChallenge(4);  //lose as no one available
        int actual = game.meetChallenge(4);  //lose as no one available
        assertEquals(expected, actual);
    }
    
    @Test
    public void defeatAchievedAfterWithdrawMoney() {
        int expected = 1000-500-200-200-200+250+100-200-200;
        game.enterChampion("Drabina");
        game.enterChampion("Flimsi");
        game.meetChallenge(4);  //lose as no one available
        game.meetChallenge(4);  //lose as no one available
        game.retireChampion("Drabina");
        game.retireChampion("Flimsi"); //no one in Team
        game.meetChallenge(4);  //lose as no one available
        game.meetChallenge(4);  //lose as no one available
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
// Add your own tests    

}
