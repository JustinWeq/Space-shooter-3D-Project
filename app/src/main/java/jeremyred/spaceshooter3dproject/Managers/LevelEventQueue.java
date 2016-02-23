package jeremyred.spaceshooter3dproject.Managers;

/**
 * Class is meant to hold the information for a levels queued events and implement them
 * Created by jeremy on 1/28/2016.
 */
public class LevelEventQueue {

    private static final int OUT_OF_VIEW = 4;
    private enum EVENT_TYPE
    {
        WAVE,
        ItemSpawn,
        FloorChange
    }

    private class LevelEvent{
        private EVENT_TYPE m_type;
    }

    private class WaveEvent extends LevelEvent{

    }
}
