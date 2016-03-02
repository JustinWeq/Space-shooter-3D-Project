package jeremyred.spaceshooter3dproject.Managers;

import android.content.Context;
import android.media.MediaPlayer;

import jeremyred.spaceshooter3dproject.R;

/**
 *
 */
public class SoundManager {
   // private SoundPool m_sounds;
    private int m_music;
    private static SoundManager m_manager;
    private MediaPlayer m_player;

    /**
     * overloaded constructor creates a new instance of SoundManager with overloaded parameters
     * @param context the context to use for loading the sounds
     */
   // private boolean m_loaded = false;
    private SoundManager(Context context) {
//        //load all of the sounds defined
//        m_sounds = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
//        m_sounds.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
//            @Override
//            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//                m_loaded = true;
//            }
//        });

//        m_music = m_sounds.load(context, R.raw.desolation, 1);
        m_player = MediaPlayer.create(context, R.raw.desolation);
        m_player.start();
        m_player.setLooping(true);
        m_player.setVolume(GameSettings.getGameSettings().getMusicVolume(),GameSettings.getGameSettings().getMusicVolume());
        int test = 4;
    }

    /**
     * returns the sound manager
     * @param context the context to use for sounds
     * @return the sound manager
     */
    public static SoundManager getSoundManager(Context context)
    {
        if(m_manager == null)
        {
            m_manager = new SoundManager(context);
        }
        return m_manager;
    }

    /**
     * plays the sound
     */
    public void play()
    {

    }

    /**
     * returns a boo, indicating that the sounds have loaded
     * @return
     */
    public boolean hasLoaded()
    {
        return false;
    }


}
