package jeremyred.spaceshooter3dproject.Data;

import java.util.ArrayList;

import jeremyred.spaceshooter3dproject.Managers.ModelManager;

/**
 * Created by jeremy on 2/16/2016.
 */
public class RenderQueueItem {
    private ArrayList<ArrayList<float[]>> m_renderGroups;

    private RenderQueueItem m_tail;

    private float[] m_playerMatrix = new float[16];

    private float[] m_playerPos = new float[4];

    private String m_text;

    public RenderQueueItem()
    {
        m_renderGroups = new ArrayList<>();
        for(int i =0;i < ModelManager.getModeManager().getModelCount();i++)
        {
            m_renderGroups.add(new ArrayList<float[]>());
        }
    }

    public boolean AddItem(int modelID,float[] modelMatrix)
    {
        //make sure the item id is not greater then the number of models avaible

        if(modelID > ModelManager.getModeManager().getModelCount())
            return false;



        //add model to que
        m_renderGroups.get(modelID).add(modelMatrix);
        return true;
    }

    public ArrayList<ArrayList<float[]>> getModelGroups()
    {
        return m_renderGroups;
    }


    public RenderQueueItem getTail()
    {
        return m_tail;
    }

    public void setTail(RenderQueueItem tail)
    {
        m_tail = tail;
    }

    public float[] getPlayerMatrix()
    {
        return m_playerMatrix;
    }

    public void copyPlayerMatrix(float[] playerWorld)
    {

        for(int i = 0;i < 16;i++)
        {
            m_playerMatrix[i] = playerWorld[i];
        }
    }

    public float[] getPlayerPos()
    {
        return m_playerPos;
    }

    public void setPlayerPos(float[] playerPos)
    {
        m_playerPos = playerPos;
    }

    public void setText(String text)
    {
        m_text  = text;
    }

    public String getText()
    {
        return m_text;
    }


}
