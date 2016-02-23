package jeremyred.spaceshooter3dproject.Managers;

import jeremyred.spaceshooter3dproject.Data.RenderQueueItem;

/**
 * Created by jeremy on 2/16/2016.
 */
public class RenderQueue {

    private final int MAX_FRAME_STORE = 5;
    private final int MIN_REQUIRED_FRAMES = 2;
    private RenderQueueItem m_front;
    private RenderQueueItem m_back;
    private static RenderQueue m_queue;
    private int m_size;

    private RenderQueue()
    {
        m_back = null;
        m_front = null;
        m_size = 0;
    }

    public static RenderQueue getRenderQueue()
    {
        if (m_queue == null)
        {
            m_queue = new RenderQueue();
        }

        return m_queue;
    }

    public void addFrame(RenderQueueItem newRenderGroup)
    {
        if(m_size>= MAX_FRAME_STORE)
        {
            return;
        }
        if(m_size == 0)
        {
            m_front = newRenderGroup;
            m_back = newRenderGroup;
        }
        else
        {
            m_back.setTail(newRenderGroup);
            m_back = newRenderGroup;
        }

        m_size++;
    }

    public int getSize()
    {
        return m_size;
    }

    public RenderQueueItem getFrame()
    {

        if(m_size < MIN_REQUIRED_FRAMES)
            return null;
        RenderQueueItem frame = m_front;

        m_front = frame.getTail();
        m_size--;

        return frame;
    }
}
