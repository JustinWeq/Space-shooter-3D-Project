package jeremyred.spaceshooter3dproject.Data;

/**
 * The in game enemy
 * @author jeremy red
 * @version 2/23/2016
 */
public class GameEnemy extends GameObject
{
    /**
     * the speed of the enemy
     */
    private float m_speed;

    /**
     * defualt constructor, creates a new instance of GameEnemy with defualt parameters
     */
    public GameEnemy()
    {
        super();
        m_speed = 1;
    }

    /**
     * sets the speed of the enemy
     * @param speed the new speed for the enemy
     */
    public void setSpeed(float speed)
    {
        m_speed = speed;
    }

    /**
     * returns the speed of the game enemy
     * @return the speed of the enemy
     */
    public float getSpeed()
    {
        return m_speed;
    }

}
