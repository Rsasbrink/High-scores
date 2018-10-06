package nl.hva.ict.ds;
import nl.hva.ict.ds.DummyHighScores;
import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import nl.hva.ict.ds.sortMethods.BucketSortHighScores;
import nl.hva.ict.ds.sortMethods.InsertionSortHighScores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains some unit tests. They by no means ensure that all the
 * requirements are implemented correctly.
 */


public class HighScoreListTest {

    private static final int MAX_HIGH_SCORE = 100000;
    private Random randomizer = new SecureRandom();
    private HighScoreList highScores;
    private Player nearlyHeadlessNick;
    private Player dumbledore;

    @Before
    public void setup() {
        // Here you should select your implementation to be tested.
 //       highScores = new DummyHighScores();
        highScores = new InsertionSortHighScores();
 //       highScores = new BucketSortHighScores();
//        highScores = new PriorityQueueHighScores();

        nearlyHeadlessNick = new Player("Nicholas", "de Mimsy-Porpington", getHighScore() % 200);
        dumbledore = new Player("Albus", "Dumbledore", nearlyHeadlessNick.getHighScore() * 1000);
    }
    private void addManyPlayers(int amount, HighScoreList list) {
        for (int i = 1; i <= amount; i++) {
            String firstName = "play";
            String lastName = "er" + i;
            list.add(new Player(firstName, lastName, getHighScore()));
            
        }
    }
    @Test
    public void noPlayerNoHighScore() {
        assertTrue("There are high-score while there should be no high-scores!", highScores.getHighScores(1).isEmpty());
    }

    @Test
    public void whenNoHighScoreIsAskedForNonShouldBeGiven() {
        highScores.add(dumbledore);

        assertEquals(0, highScores.getHighScores(0).size());
    }

    @Test
    public void noMoreHighScoresCanBeGivenThenPresent() {
        highScores.add(nearlyHeadlessNick);
        highScores.add(dumbledore);

        assertEquals(2, highScores.getHighScores(10).size());
    }

    @Test
    public void keepAllHighScores() {
        highScores.add(nearlyHeadlessNick);
        highScores.add(dumbledore);

        assertEquals(2, highScores.getHighScores(2).size());
    }

    @Test
    public void singlePlayerHasHighScore() {
        highScores.add(dumbledore);

        assertEquals(dumbledore, highScores.getHighScores(1).get(0));
    }

    @Test
    public void harryBeatsDumbledore() {
        highScores.add(dumbledore);
        Player harry = new Player("Harry", "Potter", dumbledore.getHighScore() + 1);
     // test always failed, because harry is made, but never added. Fixed this, mention in report
     highScores.add(harry);
        assertEquals(harry, highScores.getHighScores(1).get(0));
    }

    // Extra unit tests go here
    private long getHighScore() {
        return randomizer.nextInt(MAX_HIGH_SCORE);
    }
    
    @Test
    public void CheckScore(){
        int amount = 1000;
        addManyPlayers(amount, highScores);
        List<Player> list = highScores.getHighScores(amount);
        list.forEach((player) -> {
            System.out.println(player.getFirstName() + "\t " + player.getLastName() + ": \t\t " + player.getHighScore());
        });
        assertEquals(amount, highScores.getHighScores(amount).size());
    }
}
