package jeremyred.spaceshooter3dproject.Data;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import jeremyred.spaceshooter3dproject.Activitys.LevelListActivity;

/**
 * a simple class that contains methods and propertys for storing basic level data
 * @author jeremy red
 * @version 2/23/2016
 */
public class Level {

    /**
     * the difficulty for a level
     */
    public enum LevelDifficulty{
        /**
         * the easiest dificulty
         */
        EASY,
        /**
         * the medium difficulty
         */
        MODERATE,
        /**
         * the hardest difficulty
         */
        HARD
    }

    /**
     * the name of the level
     */
    private String m_levelName;
    /**
     * the difficulty of the level
     */
    private LevelDifficulty m_difficulty;
    /**
     * the list of to be enemys for the level
     */
    private ArrayList<Enemy> m_enemys;
    /**
     * the list of models for the level
     */
    private static ArrayList<Model> m_models = new ArrayList<>();
    /**
     * the list of spheres for the level
     */
    private static ArrayList<Sphere> m_modelSpheres = new ArrayList<>();
    /**
     * the name of the level
     */
    private String m_name;
    /**
     * indicates wheter there is an error or not on this level
     */
    private boolean m_error;

    /**
     * the current level the game is on
     */
    public static Level CurrentLevel;

    /**
     * the end of the level
     */
    private float m_end;

    /**
     * the speed of the player
     */
    private float m_playerSpeed;

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

    /**
     * loads the level data in
     * @param manager he manager to use for loading the level data
     * @return a boolean indicating where the level was or was not loaded successfully
     */
    private boolean loadLevel(AssetManager manager)
    {
        //read the level
        try{
        InputStream is = LevelListActivity.Manager.open(m_levelName);
        //create and initialize a buffered reader
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //read each line
        String line;

            while((line = br.readLine()) != null)
            {
                //split lines into arguments
                String[] args = line.split(" ");
                //parse input
                if(args[0].toUpperCase().equals("MODELDEF"))
                {
                    //load new model and add it to the level
                    Model model = new Model("Models/" + args[1],LevelListActivity.Manager);

                    //add new model to the list of models
                    m_models.add(model);

                    //generate model collision information
                    m_modelSpheres.add( new Sphere(model.getVertices()));
                }
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
                        else if(args[0].toUpperCase().equals("MODEL"))
                        {
                            enemy.setModelID(Integer.valueOf(args[1]));
                        }
                        else if(args[0].toUpperCase().equals("EXECUTION"))
                        {
                            enemy.setExectution(Float.parseFloat(args[1]));
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
                else if(args[0].toUpperCase().equals("FINISH"))
                {
                    if(args[1].toUpperCase().equals("LAST_ENEMY"))
                    {
                        if(args.length > 2)
                        {
                            m_end = m_enemys.get(m_enemys.size()-1).getExection()+Integer.parseInt(args[2]);
                        }
                        else
                        {
                            m_end = m_enemys.get(m_enemys.size()-1).getExection();
                        }
                    }
                    else
                    m_end = Float.parseFloat(args[1]);
                }
                else if(args[0].toUpperCase().equals("PLAYER_SPEED"))
                {
                    m_playerSpeed = Float.parseFloat(args[1]);
                }
                else if(args[0].toUpperCase().equals("STREAM"))
                {
                    float exectuionTime=0;
                    int streamCount = 0;
                    int streamExecution = 0;
                    int seed = 0;
                    boolean random = false;
                    ArrayList<Enemy> chanceEnemys = new ArrayList<>();
                    while(!(line = br.readLine()).toUpperCase().equals("END"))
                    {
                        //split args
                        args = line.split(" ");
                        if(args[0].toUpperCase().equals("EXECUTION"))
                        {
                            exectuionTime = Float.parseFloat(args[1]);
                        }
                        else if(args[0].toUpperCase().equals("STREAM_COUNT"))
                        {
                            streamCount = Integer.parseInt(args[1]);
                        }
                        else if(args[0].toUpperCase().equals("STREAM_EXECUTION"))
                        {
                            streamExecution = Integer.parseInt(args[1]);
                        }
                        else if(args[0].toUpperCase().equals("CHANCE"))
                        {
                            Enemy nenemy = new Enemy();
                            while(!(line = br.readLine()).toUpperCase().equals("END"))
                            {
                                args = line.split(" ");
                                if(args[0].toUpperCase().equals("MODEL"))
                                {
                                    nenemy.setModelID(Integer.parseInt(args[1]));
                                }
                                else if(args[0].toUpperCase().equals("X"))
                                {
                                    nenemy.setX(Integer.parseInt(args[1]));
                                }
                                else if(args[0].toUpperCase().equals("Y"))
                                {
                                    nenemy.setY(Integer.parseInt(args[1]));
                                }
                                else if(args[0].toUpperCase().equals("Z"))
                                {
                                    nenemy.setZ(Integer.parseInt(args[1]));
                                }
                                else if(args[0].toUpperCase().equals("SPEED"))
                                {
                                    nenemy.setSpeed(Float.parseFloat(args[1]));
                                }
                            }
                            chanceEnemys.add(nenemy);
                        }
                        else if(args[0].toUpperCase().equals("SEED"))
                        {
                            if(args[1].toUpperCase().equals("RANDOM"))
                            {
                                random = true;
                            }
                            else
                            {
                                seed = Integer.parseInt(args[1]);
                            }
                        }
                    }

                    //now all of the stream info is read in start generating

                    for(int i = 0;i < streamCount;i++)
                    {
                        Random r;
                        if(random)
                        {
                            r = new Random();
                        }
                        else
                        {
                            r = new Random(seed+(i*7));
                        }

                        int selection = r.nextInt(chanceEnemys.size());
                        Enemy nenemy = new Enemy(chanceEnemys.get(selection));
                        float exeTime = exectuionTime+(streamExecution*i);
                        nenemy.setExectution(exeTime);
                        m_enemys.add(nenemy);
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
    /**
     * returns the name of the level
     */
    public String toString()
    {
        return m_levelName;
    }

    @Override
    /**
     * determines whether this level is equal to another level
     */
    public boolean equals(Object other){
        if(this == other) return true;

        if(other == null || (this.getClass() != other.getClass())) return false;

        Level otherLevel = (Level) other;
        return (this.m_enemys.size() == otherLevel.m_enemys.size() &&
         this.m_levelName == otherLevel.m_levelName &&
         this.m_difficulty == otherLevel.m_difficulty);
    }

    @Override
    /**
     * returns the hashcode for the level
     */
    public int hashCode(){
        int result = 0;
        result = 31*result + m_enemys.size();
        result = 31*result+ m_levelName.hashCode();
        result = 31*result + m_difficulty.hashCode();
        return result;
    }

    /**
     * compares this level to another level
     * @param other the level to compare to
     * @return the comparison value
     */
    public int compareTo(Level other)
    {
        return (this.m_difficulty.compareTo(other.m_difficulty));
    }

    /**
     * adds a model to the list of models for this level
     * @param model
     */
    public void addModel(Model model)
    {
        m_models.add(model);
    }

    /**
     * returns a model from the model list
     * @param index the index to find the model at( the modelId)
     * @return the model
     */
    public Model getModel(int index)
    {
        return m_models.get(index);
    }

    /**
     * returns the number of models for the level
     * @return the number of models on this level
     */
    public int getNumberOfModels(){
        return m_models.size();
    }

    /**
     * returns the end of the level
     * @return the end pf the level
     */
    public float getEnd()
    {
        return m_end;
    }

    /**
     * returns the players speed for the level
     * @return the players speed
     */
    public float getSpeed()
    {
        return m_playerSpeed;
    }





}
