package jeremyred.spaceshooter3dproject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by jeremy on 1/21/2016.
 */
public class DataManager {
    private static DataManager dataManager;
    private ArrayList<Level> levelList;
    private int nextListLevel;

    private DataManager()
    {
        levelList = new ArrayList<>();
        try {
            InputStream is = LevelListActivity.Manager.open("levels/LevelList");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while((line = br.readLine()) != null && line != "")
            {
                levelList.add(new Level("levels/" +line,LevelListActivity.Manager));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public static DataManager getDataManager()
    {
        if(dataManager == null)
        {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public ArrayList<Level> getLevelList()
    {
        return  levelList;
    }
}
