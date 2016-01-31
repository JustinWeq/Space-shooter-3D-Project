package jeremyred.spaceshooter3dproject;

import android.os.SystemClock;

/**
 * Created by jeremy on 1/28/2016.
 */
public class GameManager implements Runnable {

    private static GameManager Main_Game_Manager;
    private boolean isPuased;
    private long m_lastMilliSeconds;
    private static final float regFrameRate = 1000f/60f;
    private float m_gameTime;
    private Player m_player;
    private boolean quit;
    private Floor m_floor;
    private final float MAX_X = 10;
    private final float MAX_Y = 7;
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
    }

    @Override
    public void run() {
        
        //run the GameLoop
        GameLoop();
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
            m_floor.setAdvanceX(1*m_gameTime);

        }
    }

    private void handlePlayerInput()
    {
        float dx = GLRenderer.X1;
        float dy = GLRenderer.Y1;


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




}
