package jeremyred.spaceshooter3dproject;

/**
 * Created by jeremy on 1/14/2016.
 */
public class Enemy {
    //instance data
    private float m_HP;
    private float m_x;
    private float m_y;
    private float m_z;
    private float m_damage;
    private float m_rateOfFire;
    private short m_modelID;
    private String m_name;

    //contructors

    /**
     * defualt contrcuter, creates a new instance of Enemy with defualt parameters
     */
    public Enemy()
    {
        //set defualt values
        m_rateOfFire = m_damage = m_HP = m_x = m_y = m_z = m_modelID = 0;
        m_name = "enemy";
    }

    /**
     * overloaded Constructor, creates a new instance of Enemy with overloaded parameters
     * @param hp the hp of the enemy
     * @param x the x coord of the enemy
     * @param y the y coord of the enemy
     * @param z the z coord of the enemy
     * @param damage the damage of the enemy
     * @param rateOfFire the rate of fire for the enemy
     * @param modelID  the models id of the enemy
     */
    public Enemy(float hp,float x,float y,float z,float damage,float rateOfFire,short modelID,String name)
    {
        //store position
        m_x = x;
        m_y  =y;
        m_z  =z;
        //store combat information
        m_HP = hp;
        m_damage  =damage;
        m_rateOfFire = rateOfFire;
        //store the modelID
        m_modelID = modelID;
        //store
    }

    //misc public method

    /**
     * returns the hp of the enemy
     * @return the hp of the enemy
     */
    public float getHP()
    {
        return m_HP;
    }

    /**
     * returns the damage the enemy does
     * @return the enemys damage value
     */
    public float getDamage()
    {
        return m_damage;
    }

    /**
     * returns the enemys rate of fire
     * @return the rate of fire for this enemy
     */
    public float getRateOfFire()
    {
        return m_rateOfFire;
    }

    /**
     * returns the x coord of the enemy
     * @return the x coord of the enemy
     */
    public float getX()
    {
        return m_x;
    }

    /**
     * returns the y coord of the enemy
     * @return the y coord of the enemy
     */
    public float getY()
    {
        return m_y;
    }

    /**
     * returns the z coord of the enemy
     * @return the z coord of the enemy
     */
    public float getZ()
    {
        return m_z;
    }

    /**
     * returns the model id of the enemy
     * @return the model id of the enemy
     */
    public int getModelID()
    {
        return m_modelID;
    }

    /**
     * returns the name of the enemy
     * @return the enemys name
     */
    public String getName()
    {
        return m_name;
    }

    /**
     * sets the x coord of the enemy
     * @param x the new x coord for the enemy
     */
    public void setX(float x)
    {
        m_x = x;
    }

    /**
     * sets the y coord of the enemy
     * @param y the new y coord for the enemy
     */
    public void setY(float y)
    {
        m_y = y;
    }

    /**
     * sets the z coord of the enemy
     * @param z the new z coord
     */
    public void setZ(float z)
    {
        m_z = z;
    }

    /**
     * sets the damage for the enemy
     * @param damage the new damage value for the enemy
     */
    public void setDamage(float damage)
    {
        m_damage = damage;
    }

    /**
     * sets the HP for the enemy
     * @param hp the new HP for the enemy
     */
    public void setHP(float hp)
    {
        m_HP = hp;
    }

    /**
     * sets the rate of fire for the enemy
     * @param rateOfFire the new rate of fire for the enemy
     */
    public void setRateOfFire(float rateOfFire)
    {
        m_rateOfFire = rateOfFire;
    }

    /**
     * sets the name of the enemy
     * @param name the name of the enemy
     */
    public void setName(String name)
    {
        m_name = name;
    }
    //misc private and protected methods

    @Override
    public String toString()
    {
        return m_name;
    }

    @Override
    public boolean equals(Object other)
    {
        if(this == other) return true;

        if(other == null || (this.getClass() != other.getClass())) return false;

        Enemy otherEnemy = (Enemy)other;

        return(
                this.m_name == otherEnemy.m_name &&
                        this.m_rateOfFire == otherEnemy.m_rateOfFire
                &&
                        this.m_damage == otherEnemy.m_damage
                &&
                        this.m_HP == otherEnemy.m_HP
                &&
                        this.m_modelID == otherEnemy.m_modelID
                &&
                        this.m_x == otherEnemy.m_x
                &&
                        this.m_y == otherEnemy.m_y
                &&
                        this.m_z == otherEnemy.m_z
                );
    }

    @Override
    public int hashCode()
    {
        float result = 0;
        result = 31*result + m_x;
        result = 31*result + m_y;
        result = 31*result+ m_z;
        result = 31*result + m_name.hashCode();
        result = 31*result + m_modelID;
        result = 32*result + m_HP;
        result = 31*result + m_damage;
        result = 31*result + m_rateOfFire;
        return (int)result;
    }

    public int compareTo(Object other)
    {
        //return 0 if its a diffrent typ
        if(this.getClass() != other.getClass())
        {
            return 0;
        }
        Enemy otherEnemy = (Enemy)other;
        return (int)(this.m_damage - otherEnemy.getDamage());
    }
}
