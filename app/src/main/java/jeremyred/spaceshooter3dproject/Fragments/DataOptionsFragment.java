package jeremyred.spaceshooter3dproject.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import jeremyred.spaceshooter3dproject.R;

/**
 * Created by jeremy on 2/9/2016.
 */
public class DataOptionsFragment extends Fragment {

    View m_root;
    int m_resetsLeft = 3;
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.data_options_layout, container, false);

        Button resetButtton =(Button) rootView.findViewById(R.id.btnResetSave);

        resetButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButtonOnClick();
            }
        });
        m_root = rootView;
        return rootView;
    }

    public void resetButtonOnClick()
    {
       Button resetButton =(Button) m_root.findViewById(R.id.btnResetSave);
        if(--m_resetsLeft < 1) m_resetsLeft = 3;
        String string = "Reset Save ( click " + m_resetsLeft + " more times)";
        resetButton.setText(string);

    }
}
