package jeremyred.spaceshooter3dproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.SystemClock;

import java.util.ArrayList;

/**
 * Created by jeremy on 1/28/2016.
 */
public class GameManager implements Runnable {

    private static GameManager Main_Game_Manager;
    private SensorManager m_sensorManager;
    private Sensor m_sensor;
    private boolean isPuased;
    private long m_lastMilliSeconds;
    private static final float regFrameRate = 1000f/60f;
    private float m_gameTime;
    private Player m_player;
    private boolean quit;
    private Floor m_floor;
    private final float MAX_X = 10;
    private final float MAX_Y = 7;
    private Sheild m_playerSheild;
    private Sphere m_playerSphere;
    private float playerSpeed = 1;
    private ArrayList<Enemy> m_toBeEnemyList = new ArrayList<>();
    private ArrayList<GameEnemy> m_activeEnemys = new ArrayList<>();
    private float m_playerZ;
    private int m_enemyIndex;
    private int m_currentLevel;

    public GameManager()
    {
        isPuased = false;
        quit = false;
        m_lastMilliSeconds = 0;
        m_gameTime = 0;
        m_player = new Player();
        m_player.getPlace().setZ(10);
       // m_player.getPlace().setRotX(180);

        m_floor = new Floor();

        m_floor.setY(-15);

        float[] sheildColor = {1,1,1,1};
        m_playerSheild = new Sheild(sheildColor);

        //m_playerSheild.setPlace(m_player.getPlace());

        m_playerSheild.setPlace( new Place());

        m_playerSphere = new Sphere(m_player.getModel().getVertices());

        m_playerSheild.getPlace().setScaleX(0.3f);

        m_playerSheild.getPlace().setScaleY(0.3f);

        m_playerSheild.getPlace().setScaleZ(0.3f);

        m_playerZ = 10;

    }

    @Override
    public void run() {
        
        //run the GameLoop
        GameLoop();


    }

    public void setLevel(Level level)
    {

        //change the enemy list
        m_toBeEnemyList.clear();
        Level nlevel = level;
        for(int i = 0;i < nlevel.getListOfEnemys().size();i++)
        {
            m_toBeEnemyList.add(nlevel.getListOfEnemys().get(i));
        }

        m_activeEnemys.clear();

    }

    private float temp = 0;
    public void GameLoop()
    {
        //update game Logic
        while(!quit)
        {
            while(isPuased)
            {
                //loop
            }

            //get the gametime
            setGameTime();
            //temp+= 0.2*m_gameTime;
            if(temp > 360)
            {
                temp -= 360;
            }

           // m_player.getPlace().setRotX(temp);
            //handle the players input
            handlePlayerInput();

            //move the floor
            m_floor.setAdvanceX(m_floor.getAdvanceX()+(playerSpeed*m_gameTime));

            if(m_floor.getAdvanceX() > 1)
            {
                m_floor.setAdvanceX(m_floor.getAdvanceX() - 1f);
            }

            //move the player
            m_playerZ+= playerSpeed*m_gameTime;

            //check to see if playerZ is greater then the execution time on the current enemy
            if(m_enemyIndex < m_toBeEnemyList.size())
            {
                if (m_toBeEnemyList.get(m_enemyIndex).getExection() < m_playerZ) {
                    Enemy tobeEnemy = m_toBeEnemyList.get(m_enemyIndex);
                    //add new enemy to list of active enemys
                    GameEnemy nenemy = new GameEnemy();
                    nenemy.setX(tobeEnemy.getX());
                    nenemy.setY(tobeEnemy.getY());
                    nenemy.setZ(tobeEnemy.getZ());
                    nenemy.setRotX(180);
                    nenemy.setModelID(tobeEnemy.getModelID());
                    m_activeEnemys.add(nenemy);
                    m_enemyIndex++;
                }
            }



            for(int i = 0;i < m_activeEnemys.size();i++)
            {
                m_activeEnemys.get(i).setZ(m_activeEnemys.get(i).getZ()- ((playerSpeed/10)*m_gameTime));
            }

        }
    }

    private void handlePlayerInput()
    {
        float dx = GameActivity.X1;
        float dy = GameActivity.Y1;
        dx += GameActivity.AxisX;
        dy += GameActivity.Axisy;



        m_player.getPlace().setX(m_player.getPlace().getX() + (-dx*m_gameTime));
        m_player.getPlace().setY(m_player.getPlace().getY() + (-dy*m_gameTime));

        //make sure the player does not leave its intended boundries

        if(m_player.getPlace().getX() > MAX_X)
        {
            m_player.getPlace().setX(MAX_X);
        }
        else
        if(m_player.getPlace().getX() < -MAX_X)
        {
            m_player.getPlace().setX(-MAX_X);
        }

        if(m_player.getPlace().getY() > MAX_Y)
        {
            m_player.getPlace().setY(MAX_Y);
        }
        else
        if(m_player.getPlace().getY() < -MAX_Y)
        {
            m_player.getPlace().setY(-MAX_Y);
        }

        //move the sheild
        m_playerSheild.getPlace().setX(m_player.getPlace().getX());
        m_playerSheild.getPlace().setY(m_player.getPlace().getY());
        m_playerSheild.getPlace().setZ(m_player.getPlace().getZ());
    }

    public static GameManager getGameManager()
    {
        if(Main_Game_Manager == null)
        {
            Main_Game_Manager = new GameManager();
        }

        return Main_Game_Manager;
    }

    public void quit()
    {
        quit = true;
    }

    public void pause()
    {
        isPuased = true;
    }

    public void unPause()
    {
        isPuased = false;
    }

    private void setGameTime()
    {
        //set the current game time to move things by
        long currentMilli = SystemClock.currentThreadTimeMillis();
        long elapsedMilli = currentMilli- m_lastMilliSeconds;
//        while(elapsedMilli < regFrameRate)
//        {
//            currentMilli = System.currentTimeMillis();
//            elapsedMilli = currentMilli-m_lastMilliSeconds;
//            m_lastMilliSeconds = elapsedMilli;
//        }
//        if(elapsedMilli < regFrameRate)
//        {
//            m_gameTime = regFrameRate/(float)elapsedMilli;
//        }
//        else
//        {
//            m_gameTime = (float)elapsedMilli/regFrameRate;
//        }

        while( elapsedMilli < regFrameRate)
        {
            //just stop
            currentMilli = SystemClock.currentThreadTimeMillis();
            elapsedMilli = currentMilli- m_lastMilliSeconds;
        }

        m_gameTime = regFrameRate/(float)elapsedMilli;



        m_lastMilliSeconds = currentMilli;
    }

    public Player getPlayer()
    {
        return m_player;
    }

    public Floor getFloor()
    {
        return m_floor;
    }

    public Sheild getPlayerSheild()
    {
        return m_playerSheild;
    }

    public Sphere getPlayerSphere()
    {
        return m_playerSphere;
    }

    public ArrayList<GameEnemy> getActiveEnemys()
    {
        return m_activeEnemys;
    }




}
