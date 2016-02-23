package jeremyred.spaceshooter3dproject.Data;

import android.opengl.Matrix;

import jeremyred.spaceshooter3dproject.Activitys.LevelListActivity;

/**
 * Created by jeremy on 1/27/2016.
 */
public class Player {
    Place m_place;
    Model m_model;
    float[] m_cameraView = new float[16];
    public Player()
    {
        m_place = new Place();
        m_model = new Model("Models/ship.obj", LevelListActivity.Manager);
    }

    public void update()
    {
        float[] pos = {0,0,-3};
        float[] cpos = new float[3];
        Matrix.multiplyMV(cpos,0,m_place.getRotationatrix(),0,pos,0);

        Matrix.setLookAtM(m_cameraView,0,cpos[0],cpos[1],cpos[2],m_place.getX(),
                m_place.getY(),m_place.getZ(),0,0,1);

    }

    public Model getModel()
    {
        return m_model;
    }

    public float[] getView()
    {
        return m_cameraView;
    }

    public  Place getPlace()
    {
        return m_place;
    }

}
