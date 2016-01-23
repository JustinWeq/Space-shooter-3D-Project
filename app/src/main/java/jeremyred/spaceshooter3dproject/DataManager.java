package jeremyred.spaceshooter3dproject;

import java.util.ArrayList;

/**
 * Created by jeremy on 1/21/2016.
 */
public class DataManager {
    private static DataManager dataManager;
    private ArrayList<Enemy> enemyList;
    private int nextListEnemy;

    private DataManager()
    {
        enemyList = new ArrayList<>();
        enemyList.add(new Enemy());
        nextListEnemy++;
    }

    public static DataManager getDataManager()
    {
        if(dataManager == null)
        {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public ArrayList<Enemy> getEnemyList()
    {
        return  enemyList;
    }
}
