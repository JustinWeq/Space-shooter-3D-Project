package jeremyred.spaceshooter3dproject;

import junit.framework.TestCase;

import java.util.ArrayList;

import jeremyred.spaceshooter3dproject.Data.Level;
import jeremyred.spaceshooter3dproject.Managers.DataManager;

/**
 * Created by jeremy on 2/7/2016.
 */
public class DataManagerTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void testGetLevelList() throws Exception {
        ArrayList<Level> list = DataManager.getDataManager().getLevelList();
        assertNotNull("null returned",list);
        assertTrue("size returned != 0", list.size() == 0);
        System.out.print("GetLevelList - passed");
    }
}