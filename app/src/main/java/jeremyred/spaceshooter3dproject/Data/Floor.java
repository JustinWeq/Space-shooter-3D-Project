package jeremyred.spaceshooter3dproject.Data;

import jeremyred.spaceshooter3dproject.Activitys.LevelListActivity;

/**
 * A simple class that keeps track of the floors position and movement
 * @author jeremy red
 * @version 2/23/2016
 */
public class Floor extends Place {
    /**
     * the amount the floor should advance by each frame
     */
    private float m_advanceX;
    /**
     * the model for the floor
     */
    private Model m_model;

    /**
     * defualt constructor-- creates a new instance of floor with defualt parameters
     */
    public Floor()
    {
        super();
        m_advanceX = 0;
        m_model = new Model("Models/ground.obj", LevelListActivity.Manager);
    }


    /**
     * sets the amount the floor should advance by each frame
     * @param advanceX the new amount to advance by
     */
    public void setAdvanceX(float advanceX)
    {
        m_advanceX = advanceX;
    }

    /**
     * returns the amount to advance
     * @return the amount to advance
     */
    public float getAdvanceX()
    {
        return m_advanceX;
    }

    /**
     * returns the model the floor uses
     * @return the model the floor uses
     */
    public Model getModel()
    {
        return m_model;
    }
}
