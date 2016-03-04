package jeremyred.spaceshooter3dproject.Data;

import jeremyred.spaceshooter3dproject.Activitys.LevelListActivity;

/**
 * A class that contains methods and properties for a visual sheild around the player
 * @author jeremy red
 * @version 2/25/2016
 */
public class Shield {

    /**
     * the model for the shield
     */
    private static Model model;
    /**
     * the place for the sheild
     */
    private Place m_place;
    /**
     * the color for the sheild(in RGBA format)
     */
    private float[] m_color;

    /**
     * overloaded constructor for the shield
     * @param color the color for the shield to be
     */
    public Shield(float[] color)
    {
        m_color = color;
        if(model == null)
        {
            model = new Model("Models/Shield.obj", LevelListActivity.Manager);
        }
    }

    /**
     * sets the place for the sheild
     * @param place the place that is used
     */
    public void setPlace(Place place)
    {
        m_place  = place;
    }

    /**
     * returns the place
     * @return the place
     */
    public Place getPlace()
    {
        return m_place;
    }

    /**
     * returns the matrix
     * @return the matrix
     */
    public float[] getMatrix()
    {
        return m_place.getMatrix();
    }

    /**
     * decreases the alpha for the sheild
     * @param decrease the amount to decrease
     */
    public void decreaseAlpha(float decrease)
    {
        m_color[3] -= decrease;
        if(m_color[3] < 0)
        {
            m_color[3] = 0;
        }
    }

    /**
     * resets the alpha for the shield
     */
    public void resetAlpha()
    {
        m_color[3] = 1.0f;
    }

    /**
     * returns the color for the shield
     * @return the color
     */
    public float[] getColor()
    {
        return m_color;
    }

    /**
     * returns the model for the sheild
     * @return the model
     */
    public static Model getModel()
    {
        return model;
    }
}
