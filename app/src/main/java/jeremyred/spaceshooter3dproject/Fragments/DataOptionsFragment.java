package jeremyred.spaceshooter3dproject.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import jeremyred.spaceshooter3dproject.R;

/**
 * a fragment that shows options for the data
 * @author jeremy red
 * @version 3/1/2016
 */
public class DataOptionsFragment extends Fragment {

    /**
     * the root view
     */
    View m_root;
    /**
     * the number of reset clicks left
     */
    int m_resetsLeft = 3;

    /**
     * the method that is used to create the view
     * @param inflater the inflater to use for inflating the view
     * @param container the container the view is in
     * @param savedInstanceState the seved instance state of the last view
     * @return the view to use for the fragment
     */
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

    /**
     * called when the reset button is clicked
     */
    public void resetButtonOnClick()
    {
       Button resetButton =(Button) m_root.findViewById(R.id.btnResetSave);
        if(--m_resetsLeft < 1) m_resetsLeft = 3;
        String string = "Reset Save ( click " + m_resetsLeft + " more times)";
        resetButton.setText(string);

    }
}
