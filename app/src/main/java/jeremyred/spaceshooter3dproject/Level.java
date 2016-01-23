package jeremyred.spaceshooter3dproject;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;

/**
 * a simple class that contains mehods and propertys for storing basic level data
 */

public class Level {

    public enum LevelDifficulty{
        EASY,
        MODERATE,
        HARD
    }

    private String m_levelName;
    private LevelDifficulty m_difficulty;
    private ArrayList<Enemy> m_enemys;
    private String m_name;
    private boolean m_error;
    public static Level CurrentLevel;

    /**
     * overloaded constructor, creates a new instance of Level with overloaded parameters
     * @param levelName the id of the level script in the context
     * @param manager the context to load resources from
     */
    public Level(String levelName,AssetManager manager)
    {
        //store the level script ID
        m_levelName = levelName;
        //set defualt values
        m_difficulty = LevelDifficulty.EASY;
        m_error = false;
        m_name = "level";
        m_enemys = new ArrayList<>();
        //load the level data
        m_error = loadLevel(manager);
    }

    private boolean loadLevel(AssetManager manager)
    {
        //read the level
        try{
        InputStream is = LevelListActivity.Manager.open(m_levelName);
        //create and initialize a buffered reader
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //read each line
        String line;

            while((line = br.readLine()) != null && line != "")
            {
                //split lines into arguments
                String[] args = line.split(" ");
                //parse input
                if(args[0].toUpperCase().equals("ENEMY"))
                {
                    //create the enemy to add
                    Enemy enemy = new Enemy();
                    //defines an enemy in the following format
                    // x,y,z,damage,hp,Rate of fire,modelID,name
                    //read lines until end
                    while(!(line = br.readLine()).toUpperCase().equals("END"))
                    {
                        //split args
                        args = line.split(" ");
                        if(args[0].toUpperCase().equals("X"))
                        {
                            enemy.setX(Float.valueOf(args[1]));
                        }
                        else if (args[0].toUpperCase().equals("Y"))
                        {
                            enemy.setY(Float.valueOf(args[1]));
                        }
                        else if (args[0].toUpperCase().equals("Z"))
                        {
                            enemy.setZ(Float.valueOf(args[1]));
                        }
                        else if(args[0].toUpperCase().equals("HP"))
                        {
                            enemy.setHP(Float.valueOf(args[1]));
                        }
                        else if(args[0].toUpperCase().equals("DAMAGE"))
                        {
                            enemy.setDamage(Float.valueOf(args[1]));
                        }
                        else if(args[0].toUpperCase().equals("RATEOFFIRE"))
                        {
                            enemy.setDamage(Float.valueOf(args[1]));
                        }
                        else if(args[0].toUpperCase().equals("NAME"))
                        {
                            enemy.setName(args[1]);
                        }

                    }

                    //add the enemy to the list of enemys
                    m_enemys.add(enemy);
                }
                else if(args[0].toUpperCase().equals("NAME"))
                {
                    //set the name of the level
                    m_name = args[1];
                }
                else if(args[0].toUpperCase().equals("DIFFICULTY"))
                {
                    if(args[1].toUpperCase().equals("EASY"))
                    {
                        m_difficulty = LevelDifficulty.EASY;
                    }
                    else if(args[0].toUpperCase().equals("MODERATE"))
                    {
                        m_difficulty = LevelDifficulty.MODERATE;
                    }
                    else if(args[0].toUpperCase().equals("HARD"))
                    {
                        m_difficulty = LevelDifficulty.HARD;
                    }
                }



            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //there was an error so return false
            return false;
        }
        //level was parsed successfully so return true;
        return true;
    }

    /**
     * returns whether there was an error or not
     * @return the error value;
     */
    public boolean getError()
    {
        return m_error;
    }

    /**
     * returns the list of enemys
     * @return the list of enemys for this level
     */
    public ArrayList<Enemy> getListOfEnemys()
    {
        return m_enemys;
    }

    /**
     * returns the name of the level
     * @return the levels name
     */
    public String getName()
    {
        return m_name;
    }

    /**
     * returns the difficulty of the level
     * @return the levels difficulty in string form
     */
    public String getDifficulty()
    {
        //return selected difficulty
        switch (m_difficulty)
        {
            case EASY:
                return "Easy";
            case HARD:
                return "Hard";
            case MODERATE:
                return "Moderate";
        }
        // some error occured return null
        return null;
    }

    @Override
    public String toString()
    {
        return m_levelName;
    }

    @Override
    public boolean equals(Object other){
        if(this == other) return true;

        if(other == null || (this.getClass() != other.getClass())) return false;

        Level otherLevel = (Level) other;
        return (this.m_enemys.size() == otherLevel.m_enemys.size() &&
         this.m_levelName == otherLevel.m_levelName &&
         this.m_difficulty == otherLevel.m_difficulty);
    }

    @Override
    public int hashCode(){
        int result = 0;
        result = 31*result + m_enemys.size();
        result = 31*result+ m_levelName.hashCode();
        result = 31*result + m_difficulty.hashCode();
        return result;
    }


    public int compareTo(Level other)
    {
        return (this.m_difficulty.compareTo(other.m_difficulty));
    }





}
