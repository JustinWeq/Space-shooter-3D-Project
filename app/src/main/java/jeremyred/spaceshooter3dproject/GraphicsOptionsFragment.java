package jeremyred.spaceshooter3dproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

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
        GameSettings settings= GameSettings.getGameSettings();

        TextView lblBrightness = (TextView)rootView.findViewById(R.id.lblBrightness);
        string = "Brightness " + settings.getBrightness();
        lblBrightness.setText(string);

        SeekBar brightness =(SeekBar) m_root.findViewById(R.id.skbBrightness);

        brightness.setProgress(settings.getBrightness());

        return rootView;
    }
}
