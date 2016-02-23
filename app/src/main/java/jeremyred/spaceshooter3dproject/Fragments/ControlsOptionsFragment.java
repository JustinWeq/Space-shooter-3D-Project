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
public class ControlsOptionsFragment extends Fragment {

    private View m_root;
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.controls_options_layout, container, false);
        Bundle args = getArguments();
        m_root = rootView;

        Switch swhController = (Switch)m_root.findViewById(R.id.swhController);
        swhController.setChecked(GameSettings.getGameSettings().getControllerEnabled());
        swhController.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                GameSettings.getGameSettings().setControllerEnabled(isChecked);
            }
        });
        TextView lblSensitivty = (TextView)m_root.findViewById(R.id.lblSensitivity);
        String string = "Sensitivity " + GameSettings.getGameSettings().getControllerSensitivity();
        lblSensitivty.setText(string);
        SeekBar skbSensitivity = (SeekBar)m_root.findViewById(R.id.skbSensitivity);
        skbSensitivity.setProgress(GameSettings.getGameSettings().getControllerSensitivity());
        skbSensitivity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView lblSensitivity = (TextView)m_root.findViewById(R.id.lblSensitivity);
                String string = "Sensitivity " + GameSettings.getGameSettings().getControllerSensitivity();
                lblSensitivity.setText(string);
                GameSettings.getGameSettings().setControllerSensitivity(progress);

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
