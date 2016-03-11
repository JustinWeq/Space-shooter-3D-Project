package jeremyred.spaceshooter3dproject.Managers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import jeremyred.spaceshooter3dproject.Data.Level;
import jeremyred.spaceshooter3dproject.Activitys.LevelListActivity;

/**
 * A class that handle level data
 * @author jeremy red
 * @version 3/1/2016
 */
public class DataManager {
    /**
     * the public data manager
     */
    private static DataManager dataManager;
    /**
     * the level list
     */
    private ArrayList<Level> levelList;
    /**
     * indicates the number of levelLists created
     */
    private int m_nextListLevel;

    /**
     * defualt contructor creates a new instance of DataManager with overloaded
     */
    private DataManager()
    {
        levelList = new ArrayList<>();
        try {
            InputStream is = LevelListActivity.Manager.open("levels/LevelList");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = br.readLine()) != null && !line.equals(""))
            {
                levelList.add(new Level("levels/" +line,LevelListActivity.Manager));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        m_nextListLevel = 0;

    }


    /**
     * returns the public data manager
     * @return the public data manager
     */
    public static DataManager getDataManager()
    {
        if(dataManager == null)
        {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    /**
     * returns the level list
     * @return the level list
     */
    public ArrayList<Level> getLevelList()
    {
        return  levelList;
    }

    /**
     * returns the next level in the list of levels
     * @return the next level in the list, if there is no next level available it returns null
     */
    public Level getNextLevel()
    {
        m_nextListLevel++;
        if(m_nextListLevel+1 > levelList.size())
        {
            m_nextListLevel = 0;
            return null;
        }

        return levelList.get(m_nextListLevel);
    }
}
