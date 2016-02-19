package jeremyred.spaceshooter3dproject;

/**
 * Created by jeremy on 2/16/2016.
 */
public class RenderQueue {

    private final int MAX_FRAME_STORE = 5;
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
        while(m_size >= MAX_FRAME_STORE)
        {
            //wait for there to be a free place to add the frame
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

        while(m_size < 1)
        {
            //loop since there is no frame to draw
        }
        RenderQueueItem frame = m_front;

        m_front = frame.getTail();
        m_size--;

        return frame;
    }
}
