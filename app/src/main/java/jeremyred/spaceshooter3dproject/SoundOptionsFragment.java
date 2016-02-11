package jeremyred.spaceshooter3dproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by jeremy on 2/9/2016.
 */
public class SoundOptionsFragment extends Fragment {

    private View m_root;
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.sound_options_layout,container,false);
        final GameSettings settings = GameSettings.getGameSettings();
        m_root = rootView;
        TextView lblMusic =(TextView) m_root.findViewById(R.id.lblMusicSound);
        String string = "Music" + settings.getMusicVolume();
        lblMusic.setText(string);
        SeekBar skbMusic = (SeekBar) m_root.findViewById(R.id.skbSound);
        skbMusic.setProgress(settings.getMusicVolume());
        skbMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
;
                TextView lblMusic = (TextView) m_root.findViewById(R.id.lblMusicSound);
                String string = "Music " + progress;
                lblMusic.setText(string);
                GameSettings.getGameSettings().setMusicVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        TextView lblSFX =(TextView)m_root.findViewById(R.id.lblSFX);
        string = "Effects" + settings.getSFXVolume();
        lblSFX.setText(string);
        SeekBar skbSFK = (SeekBar)m_root.findViewById(R.id.skbSFX);
        skbSFK.setProgress(settings.getSFXVolume());
        skbSFK.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView lblSFX =(TextView)m_root.findViewById(R.id.lblSFX);
                String string = "Effects " + progress;
                lblSFX.setText(string);
                GameSettings.getGameSettings().setSFXVolume(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return rootView;
    }
}
