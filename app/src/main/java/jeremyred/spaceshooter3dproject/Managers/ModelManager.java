package jeremyred.spaceshooter3dproject.Managers;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import jeremyred.spaceshooter3dproject.Activitys.LevelListActivity;
import jeremyred.spaceshooter3dproject.Data.Model;

/**
 * Created by jeremy on 2/14/2016.
 */
public class ModelManager {

    private ArrayList<Model> m_models;
    private static ModelManager m_manager;
    private ModelManager()
    {
        m_models = new ArrayList<>();

        //load all of the models defined in metadata
        AssetManager manager = LevelListActivity.Manager;
        try {
            InputStream is = manager.open("MetaData/ModelList");

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while((line = br.readLine()) != null)
            {
                Model model = new Model("Models/"+line,manager);

                m_models.add(model);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static ModelManager getModeManager()
    {
        if(m_manager == null) m_manager = new ModelManager();
        return m_manager;
    }

    public Model getModel(int index)
    {
        return m_models.get(index);
    }

    public int getModelCount()
    {
        return m_models.size();
    }


}
