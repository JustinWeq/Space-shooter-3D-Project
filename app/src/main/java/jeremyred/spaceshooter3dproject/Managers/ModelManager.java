package jeremyred.spaceshooter3dproject.Managers;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import jeremyred.spaceshooter3dproject.Activitys.LevelListActivity;
import jeremyred.spaceshooter3dproject.Data.Model;
import jeremyred.spaceshooter3dproject.Data.Sphere;

/**
 * A class that handles the models
 * @author jeremy red
 * @version 3/1/2016
 */
public class ModelManager {

    /**
     * the list of models
     */
    private ArrayList<Model> m_models;
    /**
     * the collision info
     */
    private ArrayList<Sphere> m_spheres;
    /**
     * the public model manager
     */
    private static ModelManager m_manager;

    /**
     * defualt constructor creates a new instance of ModelManager with default parameters
     */
    private ModelManager()
    {
        m_models = new ArrayList<>();
        m_spheres = new ArrayList<>();

        //load all of the models defined in metadata
        AssetManager manager = LevelListActivity.Manager;
        try {
            InputStream is = manager.open("MetaData/ModelList");

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while((line = br.readLine()) != null)
            {
                Model model = new Model("Models/"+line,manager);
                //load collision info
                Sphere sphere = new Sphere(model.getVertices());

                m_models.add(model);

                m_spheres.add(sphere);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * returns the publicly available model manager
     * @return the model manager
     */
    public static ModelManager getModeManager()
    {
        if(m_manager == null) m_manager = new ModelManager();
        return m_manager;
    }

    /**
     * returns a model at a given index
     * @param index the index for the model
     * @return the model at the index
     */
    public Model getModel(int index)
    {
        return m_models.get(index);
    }

    /**
     * returns the sphere at the passed in index
     * @param index the model ID the sphere is at
     * @return the collision sphere
     */
    public Sphere getSphere(int index)
    {
        return m_spheres.get(index);
    }


    /**
     * returns the number of models
     * @return the model count
     */
    public int getModelCount()
    {
        return m_models.size();
    }


}
