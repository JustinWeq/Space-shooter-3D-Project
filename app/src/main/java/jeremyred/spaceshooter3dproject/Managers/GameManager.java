package jeremyred.spaceshooter3dproject.Managers;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.SystemClock;

import java.util.ArrayList;

import jeremyred.spaceshooter3dproject.Activitys.GameActivity;
import jeremyred.spaceshooter3dproject.Data.Enemy;
import jeremyred.spaceshooter3dproject.Data.Floor;
import jeremyred.spaceshooter3dproject.Data.GameEnemy;
import jeremyred.spaceshooter3dproject.Data.Level;
import jeremyred.spaceshooter3dproject.Data.Place;
import jeremyred.spaceshooter3dproject.Data.Player;
import jeremyred.spaceshooter3dproject.Data.RenderQueueItem;
import jeremyred.spaceshooter3dproject.Data.Shield;
import jeremyred.spaceshooter3dproject.Data.Sphere;

/**
 * A class tha handle game simulation
 * @author jeremy red
 * @version 3/1/2016
 */
public class GameManager implements Runnable {

    /**
     * the public game manager
     */
    private static GameManager Main_Game_Manager;
    /**
     * a bool that indicates whether the game is paused or not
     */
    private boolean isPuased;
    /**
     * indicates the last frame time in milliseconds
     */
    private long m_lastMilliSeconds;
    /**
     * the regular frame rate
     */
    private static final float regFrameRate = 1000f/60f;
    /**
     * the game time
     */
    private float m_gameTime;
    /**
     * the player
     */
    private Player m_player;
    /**
     * a bool indicating whether the thread should quit or not
     */
    private boolean quit;
    /**
     * the floor of the game
     */
    private Floor m_floor;
    /**
     * the max x value
     */
    private final float MAX_X = 10;
    /**
     * the max play y value
     */
    private final float MAX_Y = 7;
    /**
     * the player sheild
     */
    private Shield m_playerShield;
    /**
     * the player sphere
     */
    private Sphere m_playerSphere;
    /**
     * the player speed
     */
    private float playerSpeed = 1;
    /**
     * the list of enemys to be crated in the level
     */
    private ArrayList<Enemy> m_toBeEnemyList = new ArrayList<>();
    /**
     * the list of active enemys
     */
    private ArrayList<GameEnemy> m_activeEnemys = new ArrayList<>();
    /**
     * the plays z location
     */
    private float m_playerZ;
    /**
     * the players current index
     */
    private int m_enemyIndex;
    /**
     * the sound pool to use for SFX effects
     */
    private SoundPool soundPool;


    /**
     * defualt constructor, creates a new instance of GameManager with default parameters
     */
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
        m_playerShield = new Shield(sheildColor);

        //m_playerShield.setPlace(m_player.getPlace());

        m_playerShield.setPlace(new Place());

        m_playerSphere = new Sphere(m_player.getModel().getVertices());

        m_playerShield.getPlace().setScaleX(0.3f);

        m_playerShield.getPlace().setScaleY(0.3f);

        m_playerShield.getPlace().setScaleZ(0.3f);

        m_playerZ = 10;

        //create soundPool
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);



    }

    @Override
    /**
     * runs the game logic
     */
    public void run() {


        //start playing the game music
        //m_render_groups = new ArrayList<>(Level.CurrentLevel.get);


        //run the GameLoop
        GameLoop();


    }

    /**
     * sets the games current level
     * @param level the current level to set the game to
     */
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

    int temp = 0;
    /**
     * the main game loop
     */
    private void GameLoop()
    {

        //play the backgournd music

        try {
            //update game Logic
            while (!quit) {
                //GLRenderer.M_Semaphore.acquire();
                RenderQueueItem frame = new RenderQueueItem();
                while (isPuased) {
                    //loop
                }

                //get the gametime
                setGameTime();
                //temp+= 0.2*m_gameTime;
                if (temp > 360) {
                    temp -= 360;
                }

                // m_player.getPlace().setRotX(temp);
                //handle the players input
                handlePlayerInput();

                //move the floor
                m_floor.setAdvanceX(m_floor.getAdvanceX() + (playerSpeed * m_gameTime));

                if (m_floor.getAdvanceX() > 1) {
                    m_floor.setAdvanceX(m_floor.getAdvanceX() - 1f);
                }

                //move the player
                m_playerZ += playerSpeed * m_gameTime;

                //check to see if playerZ is greater then the execution time on the current enemy
                if (m_enemyIndex < m_toBeEnemyList.size()) {
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


                for (int i = 0; i < m_activeEnemys.size(); i++) {
                    m_activeEnemys.get(i).setZ(m_activeEnemys.get(i).getZ() - ((playerSpeed / 10) * m_gameTime));
                }

                //clear previous render groups
//                for (int i = 0; i < m_render_groups.size(); i++) {
//                    m_render_groups.get(i).clear();
//                }

                //set render groups
//                for (int i = 0; i < m_activeEnemys.size(); i++) {
//                    m_render_groups.get(m_activeEnemys.get(i).getM_modelID()).add(m_activeEnemys.get(i).getMatrix());
//                }

                for(int i = 0; i < m_activeEnemys.size();i++)
                {
                    frame.AddItem(m_activeEnemys.get(i).getM_modelID(),m_activeEnemys.get(i).getWorldCopy());
                }

                frame.copyPlayerMatrix(m_player.getPlace().getMatrix());
                frame.setPlayerPos(new float[]{m_player.getPlace().getX(), m_player.getPlace().getY(),
                        m_player.getPlace().getZ(), 0});
                frame.setText("Test");
                RenderQueue.getRenderQueue().addFrame(frame);

                //detect collisions and delete colidding enemy ships
                ArrayList<Integer> killList = new ArrayList<>();
                for(int  i =0;i < m_activeEnemys.size();i++)
                {
                    if(Sphere.collides(ModelManager.getModeManager().getSphere(m_activeEnemys.get(i).getM_modelID())
                            ,m_player.getCollisionSphere(),
                            m_activeEnemys.get(i).getX(),
                            m_activeEnemys.get(i).getY(),
                            m_activeEnemys.get(i).getZ(),
                            m_player.getPlace().getX(),
                            m_player.getPlace().getY(),
                            m_player.getPlace().getZ())) killList.add(i);

                    if(m_activeEnemys.get(i).getZ() < 0)
                    {
                        killList.add(i);
                    }
                }

                //delete enemys in the kill list
                for(int i = 0;i < killList.size();i++)
                {
                    m_activeEnemys.remove(killList.size()-1-i);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * handles the players input and movement
     */
    private void handlePlayerInput()
    {
        float dx,dy;
        if(GameSettings.getGameSettings().getControllerEnabled()) {
            dx = GameActivity.X1;
            dy = GameActivity.Y1;
        }
        else {
            dx = GameActivity.AxisX;
            dy = GameActivity.Axisy;
        }



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
        m_playerShield.getPlace().setX(m_player.getPlace().getX());
        m_playerShield.getPlace().setY(m_player.getPlace().getY());
        m_playerShield.getPlace().setZ(m_player.getPlace().getZ());
    }

    /**
     * returns the public game manager
     * @return the game manager
     */
    public static GameManager getGameManager()
    {
        if(Main_Game_Manager == null)
        {
            Main_Game_Manager = new GameManager();
        }

        return Main_Game_Manager;
    }

    /**
     * tells the game manager to quit
     */
    public void quit()
    {
        quit = true;
    }

    /**
     * tells the game amanger to pause
     */
    public void pause()
    {
        isPuased = true;
    }

    /**
     * tells the game manager to unpause
     */
    public void unPause()
    {
        isPuased = false;
    }

    /**
     * sets up the game time to be used
     */
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

    /**
     * returns the game managers player(note* this is not to be used except for testing, use renderqueus instead
     * @return the player
     */
    @Deprecated
    public Player getPlayer()
    {
        return m_player;
    }

    /**
     * returns the floor for the game manager
     * @return the floor
     */
    public Floor getFloor()
    {
        return m_floor;
    }

    /**
     * returns the players sheild
     * @return the players sheild
     */
    public Shield getPlayerSheild()
    {
        return m_playerShield;
    }

    /**
     * returns the players collision sphere
     * @return the players sphere
     */
    public Sphere getPlayerSphere()
    {
        return m_playerSphere;

    }






}
