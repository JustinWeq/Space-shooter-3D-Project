package jeremyred.spaceshooter3dproject.Data;

import android.opengl.Matrix;

import jeremyred.spaceshooter3dproject.Activitys.LevelListActivity;

/**
 * A class that holds information for the player
 * @author jeremy red
 * @version 2/25/2016
 */
public class Player {
    /**
     * the place for the player
     */
    Place m_place;
    /**
     * the model for the player
     */
    Model m_model;
    /**
     * the camera view for the player
     */
    float[] m_cameraView = new float[16];

    /**
     * default constructor-- creates a new instance of Player with default parameters
     */
    public Player()
    {
        m_place = new Place();
        m_model = new Model("Models/ship.obj", LevelListActivity.Manager);
    }

    /**
     * updates the player info
     */
    public void update()
    {
        float[] pos = {0,0,-3};
        float[] cpos = new float[3];
        Matrix.multiplyMV(cpos,0,m_place.getRotationatrix(),0,pos,0);

        Matrix.setLookAtM(m_cameraView,0,cpos[0],cpos[1],cpos[2],m_place.getX(),
                m_place.getY(),m_place.getZ(),0,0,1);

    }

    /**
     * returns the model for the player
     * @return the  model for the player
     */
    public Model getModel()
    {
        return m_model;
    }

    /**
     * returns the view
     * @return the view
     */
    public float[] getView()
    {
        return m_cameraView;
    }

    /**
     * returns the place
     * @return the place
     */
    public  Place getPlace()
    {
        return m_place;
    }

}
