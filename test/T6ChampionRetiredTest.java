import cwk4.*;

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
public class T6ChampionRetiredTest {
    CARE game;
    public T6ChampionRetiredTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        game = new Tournament("OO");
        game.enterChampion("Ganfrank");
        game.enterChampion("Elblond");
        game.enterChampion("Neon");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void retireChampionTestResult0(){
        int expected = 0;
        int actual = game.retireChampion("Neon");
        assertEquals(expected, actual);
    }
    
    @Test
    public void retireChampionTestMoneyAdded(){
        int expected = 1000 -(400+150+300)+ 300/2;
        game.retireChampion("Neon");
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    @Test
    public void retireChampionNotInTeam(){
        game.retireChampion("Neon");
        boolean actual = game.isInViziersTeam("Neon");
        assertFalse(actual);
    }
    
    @Test
    public void retireChampionInReserve(){
        game.retireChampion("Flimsi");
        String list = game.getReserve();
        boolean actual = list.contains("Flimsi");
        assertTrue(actual);
    }
    
    @Test
    public void retireChampionTestResult2(){
        int expected = 2;
        int actual = game.retireChampion("Flimsi");
        assertEquals(expected, actual);
    }
    
    @Test
    public void retireChampionTestResult2Money(){
        int expected = 150;
        game.retireChampion("Flimsi");
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    @Test
    public void retireNoSuchChampionTestResult2(){
        int expected = -1;
        int actual = game.retireChampion("Boggle");
        assertEquals(expected, actual);
    }
    
    @Test
    public void retireNoSuchChampionTestMoney(){
        int expected = 150;
        game.retireChampion("Boggle");
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
        
    @Test
    public void retiredNoSuchChampionNotInTeam(){
        game.retireChampion("Boggle");
        boolean actual = game.isInViziersTeam("Boggle");
        assertFalse(actual);
    }
    
    @Test
    public void retiredNoSuchChampionNotInReserve(){
        game.retireChampion("Boggle");
        String list = game.getReserve();
        boolean actual = list.contains("Boggle");
        assertFalse(actual);
    }
    //can't yet do test for withdraw dead champion
}
