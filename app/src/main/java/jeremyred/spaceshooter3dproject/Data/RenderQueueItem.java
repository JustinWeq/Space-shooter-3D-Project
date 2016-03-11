package jeremyred.spaceshooter3dproject.Data;

import java.util.ArrayList;

import jeremyred.spaceshooter3dproject.Managers.ModelManager;

/**
 * An item class intended to be used with the RenderQueue class
 * @author jeremy red
 * @version 2/25/2016
 */
public class RenderQueueItem {
    /**
     * the groups to be rendered
     */
    private ArrayList<ArrayList<float[]>> m_renderGroups;
    /**
     * the tail(pointer to the back RenderQueueItem)
     */
    private RenderQueueItem m_tail;

    /**
     * the player matrix for the frame
     */
    private float[] m_playerMatrix = new float[16];

    /**
     * the players position
     */
    private float[] m_playerPos = new float[4];

    /**
     * the info text to be rendered in 2D
     */
    private String m_text;

    /**
     * indicates whether the level should move on or not
     */
    private boolean m_shouldNext;

    /**
     * a boolean indicating whether the player passed or failed the level
     */
    private boolean m_gameOver;

    /**
     * the floor of the game
     */
    private Floor m_floor;
    /**
     * default constructor-- creates a new instance of RenderQueueItem with default parameters
     */
    public RenderQueueItem()
    {
        m_renderGroups = new ArrayList<>();
        for(int i =0;i < ModelManager.getModeManager().getModelCount();i++)
        {
            m_renderGroups.add(new ArrayList<float[]>());
        }
        //set the should next to the default(false)
        m_shouldNext = false;
        m_gameOver = false;
    }

    /**
     * adds a new item to the render queue
     * @param modelID the model id of the item to be rendered
     * @param modelMatrix the model matrix
     * @return a bool indicating wheter the item was passed in corrextly
     */
    public boolean AddItem(int modelID,float[] modelMatrix)
    {
        //make sure the item id is not greater then the number of models avaible

        if(modelID > ModelManager.getModeManager().getModelCount())
            return false;



        //add model to que
        m_renderGroups.get(modelID).add(modelMatrix);
        return true;
    }

    /**
     * returns the model groups to be rendered
     * @return
     */
    public ArrayList<ArrayList<float[]>> getModelGroups()
    {
        return m_renderGroups;
    }

    /**
     * returns the tail
     * @return the tail
     */
    public RenderQueueItem getTail()
    {
        return m_tail;
    }

    /**
     * sets the tail for this render queue item
     * @param tail the tail for the item
     */
    public void setTail(RenderQueueItem tail)
    {
        m_tail = tail;
    }

    /**
     * returns the matrix for the player this frame
     * @return the player matrix
     */
    public float[] getPlayerMatrix()
    {
        return m_playerMatrix;
    }

    /**
     * copys the passed in matrix to the player matrix to be rendered
     * @param playerWorld the players world matrix
     */
    public void copyPlayerMatrix(float[] playerWorld)
    {

        for(int i = 0;i < 16;i++)
        {
            m_playerMatrix[i] = playerWorld[i];
        }
    }

    /**
     * returns the players position
     * @return the player position for this frame
     */
    public float[] getPlayerPos()
    {
        return m_playerPos;
    }

    /**
     * sets the players position
     * @param playerPos the player position
     */
    public void setPlayerPos(float[] playerPos)
    {
        m_playerPos = playerPos;
    }

    /**
     * sets the text for this frame
     * @param text the text to bernedered in 2D this frame
     */
    public void setText(String text)
    {
        m_text  = text;
    }

    /**
     * returns the text for the frame
     * @return the text for the frame
     */
    public String getText()
    {
        return m_text;
    }

    /**
     * sets whether the game should go to the next level or not
     * @param shouldNext the value indicating whether the game should go to the next level or not
     */
    public void setShouldNext(boolean  shouldNext)
    {
        this.m_shouldNext = shouldNext;
    }

    /**
     * returns whether the game should go to the next level or not
     * @return
     */
    public boolean getShouldNext()
    {
        return m_shouldNext;
    }

    public void setGameOver(boolean gameOver)
    {
        m_gameOver = gameOver;
    }

    public boolean getGameOver()
    {
        return m_gameOver;
    }

    public void setFloor(Floor floor)
    {
        m_floor = floor;
    }

    public Floor getFloor()
    {
        return m_floor;
    }


}
