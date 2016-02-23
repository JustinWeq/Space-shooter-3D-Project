package jeremyred.spaceshooter3dproject.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import jeremyred.spaceshooter3dproject.Managers.GameSettings;
import jeremyred.spaceshooter3dproject.R;

/**
 * Created by jeremy on 2/9/2016.
 */
public class GraphicsOptionsFragment extends Fragment {

    View m_root;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.graphics_options_layout, container, false);

        m_root = rootView;
        String string = "";
        final GameSettings settings= GameSettings.getGameSettings();

        TextView lblBrightness = (TextView)rootView.findViewById(R.id.lblBrightness);
        string = "Brightness " + settings.getBrightness();
        lblBrightness.setText(string);

        SeekBar brightness =(SeekBar) m_root.findViewById(R.id.skbBrightness);

        brightness.setProgress(settings.getBrightness());

        brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                GameSettings gameSettings = GameSettings.getGameSettings();
                int brightness = seekBar.getProgress();
                if (brightness < gameSettings.DEFAULT_BRIGHTNESS)
                    brightness = gameSettings.DEFAULT_BRIGHTNESS;
                gameSettings.setBrightness(brightness);
                TextView lblBrightness = (TextView) m_root.findViewById(R.id.lblBrightness);
                String string = "Brightness " + brightness;
                lblBrightness.setText(string);
                seekBar.setProgress(brightness);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Switch swhAA = (Switch)m_root.findViewById(R.id.swhAA);
        swhAA.setChecked(settings.getAAEnabled());
        swhAA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                GameSettings.getGameSettings().setAAEnabled(isChecked);
            }
        });

        Switch swhLighting = (Switch)m_root.findViewById(R.id.swhLighting);
        swhLighting.setChecked(settings.getLightingEnabled());
        swhLighting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                GameSettings.getGameSettings().setLightingEnabled(isChecked);
            }
        });

        Switch swhAlphaBlending = (Switch)m_root.findViewById(R.id.swhAlphaBlending);
        swhAlphaBlending.setChecked(settings.getAlphaEnabled());
        swhAlphaBlending.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                GameSettings.getGameSettings().setAlphaEnabled(isChecked);
            }
        });

        return rootView;
    }
}
