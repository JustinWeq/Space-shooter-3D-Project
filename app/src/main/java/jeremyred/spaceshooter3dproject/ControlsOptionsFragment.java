package jeremyred.spaceshooter3dproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jeremy on 2/9/2016.
 */
public class ControlsOptionsFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.controls_options_layout,container,false);
        Bundle args = getArguments();
        return rootView;
    }
}
