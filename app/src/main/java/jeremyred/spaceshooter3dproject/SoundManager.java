package jeremyred.spaceshooter3dproject;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

/**
 * Created by jeremy on 2/12/2016.
 */
public class SoundManager {
   // private SoundPool m_sounds;
    private int m_music;
    private static SoundManager m_manager;
    private MediaPlayer m_player;

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
        m_player = MediaPlayer.create(context,R.raw.desolation);
        m_player.start();
        m_player.setLooping(true);
        m_player.setVolume(GameSettings.getGameSettings().getMusicVolume(),GameSettings.getGameSettings().getMusicVolume());
        int test = 4;
    }

    public static SoundManager getSoundManager(Context context)
    {
        if(m_manager == null)
        {
            m_manager = new SoundManager(context);
        }
        return m_manager;
    }

    public void play()
    {

    }

    public boolean hasLoaded()
    {
        return false;
    }


}
