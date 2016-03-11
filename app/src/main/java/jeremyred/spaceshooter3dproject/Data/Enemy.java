package jeremyred.spaceshooter3dproject.Data;

/**
 * A class that is used to generate a game enemy from, sort of like a template for the in game
 * enemys
 * @author jeremy red
 * @version 2/23/2016
 */
public class Enemy {
    //instance data
    /**
     * the hp of the enemy
     */
    private float m_HP;
    /**
     * the x coord of the enemy
     */
    private float m_x;
    /**
     * the y coord of the enemy
     */
    private float m_y;
    /**
     * the z coord of the enemy
     */
    private float m_z;
    /**
     * the damage the enemy deals
     */
    private float m_damage;
    /**
     * the rate of fire for the enemy
     */
    private float m_rateOfFire;
    /**
     * the model ID for the enemy
     */
    private short m_modelID;
    /**
     * the name of the enemy
     */
    private String m_name;
    /**
     * the amount of distance the player has to be for this enemy to be activated
     */
    private float m_execution;

    /**
     * the speed of the enemy
     */
    private float m_speed;

    //contructors

    /**
     * defualt contrcuter, creates a new instance of Enemy with defualt parameters
     */
    public Enemy()
    {
        //set defualt values
        m_rateOfFire = m_damage = m_HP = m_x = m_y = m_z = m_modelID = 0;
        m_name = "enemy";
        m_speed = 1;
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

    /**
     * copy constructor- creates a new instance of Enemy with copied parameters
     * @param other the enemy to copy from
     */
    public Enemy(Enemy other)
    {
        m_damage = other.getDamage();
        m_execution = other.getExection();
        m_HP = other.getHP();
        m_modelID =(short) other.getModelID();
        m_name = other.getName();
        m_damage = other.getRateOfFire();
        m_x = other.getX();
        m_y = other.getY();
        m_z = other.getZ();
        m_speed = other.getSpeed();
    }

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

    /**
     * sets the execution level for the enemy
     * @param exectution the time to execute
     */
    public void setExectution(float exectution)
    {
        m_execution = exectution;
    }

    /**
     * returns the execution time
     * @return the execution time
     */
    public float getExection()
    {
        return m_execution;
    }
    //misc private and protected methods

    @Override
    /**
     * returns the name of the enemy
     */
    public String toString()
    {
        return m_name;
    }

    @Override
    /**
     * checks to see if this enemy is equal to another enemy
     */
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
    /**
     * returns the hash code for the enemy
     */
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

    /**
     * compares this enemy to another enemy, to be used with sorting
     * @param other the nemy to be compared to
     * @return the place in the comparison
     */
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


    /**
     * sets the modelID for the enemy
     * @param modelID the modelID to set the enemy too
     */
    public void setModelID(int modelID )
    {
        m_modelID =(short) modelID;
    }

    /**
     * sets the speed of the enemy
     * @param speed the speed to set the enemy to
     */
    public void setSpeed(float speed)
    {
        m_speed = speed;
    }

    /**
     * returns the speed of the enemy
     * @return
     */
    public float getSpeed()
    {
        return m_speed;
    }

}
