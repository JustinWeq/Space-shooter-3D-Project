package jeremyred.spaceshooter3dproject;

/**
 * Created by jeremy on 1/30/2016.
 */
public class Floor extends Place {
    private float m_advanceX;
    private Model m_model;

    public Floor()
    {
        super();
        m_advanceX = 0;
        m_model = new Model("Models/ground.obj",LevelListActivity.Manager);
    }

    public void setAdvanceX(float advanceX)
    {
        m_advanceX = advanceX;
    }

    public float getAdvanceX()
    {
        return m_advanceX;
    }

    public Model getModel()
    {
        return m_model;
    }
}
