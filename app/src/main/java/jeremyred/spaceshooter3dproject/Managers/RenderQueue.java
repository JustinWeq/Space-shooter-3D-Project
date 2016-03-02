package jeremyred.spaceshooter3dproject.Managers;

import jeremyred.spaceshooter3dproject.Data.RenderQueueItem;

/**
 * A class that manages a queue of frames to render
 */
public class RenderQueue {

    /**
     * the maximum frames that can be stored
     */
    private final int MAX_FRAME_STORE = 5;
    /**
     * the minimum required frames to draw
     */
    private final int MIN_REQUIRED_FRAMES = 2;
    /**
     * the front render queue item
     */
    private RenderQueueItem m_front;
    /**
     * the back render queue item
     */
    private RenderQueueItem m_back;
    /**
     * the public render queue
     */
    private static RenderQueue m_queue;
    /**
     * the current number of frames stored
     */
    private int m_size;

    /**
     * default constructor
     */
    private RenderQueue()
    {
        m_back = null;
        m_front = null;
        m_size = 0;
    }

    /**
     * returns the public render queue
     * @return the public render queue
     */
    public static RenderQueue getRenderQueue()
    {
        if (m_queue == null)
        {
            m_queue = new RenderQueue();
        }

        return m_queue;
    }

    /**
     * adds a frame to be renderer
     * @param newRenderGroup the new frame to be added
     */
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

    /**
     * returns the size
     * @return the number of stored frames
     */
    public int getSize()
    {
        return m_size;
    }

    /**
     * pops the front frame off the queue if the number of stored frames is at least 2
     * @return the front frame
     */
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
