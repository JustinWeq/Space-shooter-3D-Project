package jeremyred.spaceshooter3dproject;

import android.content.Intent;

import junit.framework.TestCase;

/**
 * Created by jeremy on 2/7/2016.
 */
public class GameManagerTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();


    }

    public void testGetGameManager() throws Exception {
        GameManager manager = GameManager.getGameManager();
        //assert that the game manager is not null
        assertNotNull("Game manager is null",manager);
        System.out.println("testGetGameManager");
    }
}