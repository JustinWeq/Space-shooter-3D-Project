package jeremyred.spaceshooter3dproject;

/**
 * Created by jeremy on 1/30/2016.
 */
public class Sheild {

    private static Model model;
    private Place m_place;
    private float[] m_color;
    public Sheild(float[] color)
    {
        m_color = color;
        if(model == null)
        {
            model = new Model("Models/Shield.obj",LevelListActivity.Manager);
        }
    }

    public void setPlace(Place place)
    {
        m_place  = place;
    }

    public Place getPlace()
    {
        return m_place;
    }

    public float[] getMatrix()
    {
        return m_place.getMatrix();
    }

    public void decreaseAlpha(float decrease)
    {
        m_color[3] -= decrease;
        if(m_color[3] < 0)
        {
            m_color[3] = 0;
        }
    }

    public void resetAlpha()
    {
        m_color[3] = 1.0f;
    }

    public float[] getColor()
    {
        return m_color;
    }

    public static Model getModel()
    {
        return model;
    }
}
