package jeremyred.spaceshooter3dproject.Activitys;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import jeremyred.spaceshooter3dproject.Fragments.ControlsOptionsFragment;
import jeremyred.spaceshooter3dproject.Fragments.DataOptionsFragment;
import jeremyred.spaceshooter3dproject.Fragments.GraphicsOptionsFragment;
import jeremyred.spaceshooter3dproject.R;
import jeremyred.spaceshooter3dproject.Fragments.SoundOptionsFragment;

/**
 * Created by jeremy on 2/9/2016.
 */
public class OptionsActivity extends FragmentActivity
{


    private int m_tab = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_layout);

        if(savedInstanceState != null)
        {
            return;
        }

        //Create the default fragment to display
        GraphicsOptionsFragment firstFragment = new GraphicsOptionsFragment();


        //pass the intents extras to the fragment
        firstFragment.setArguments(getIntent().getExtras());


        //Add the fragment to the fragment container frame layout
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();

        m_tab = 0;
    }


    public void onClickGraphics(View view) {

        if(m_tab != 0)
        {
            //not already on graphics tab so switch to it
            GraphicsOptionsFragment fragment = new GraphicsOptionsFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            m_tab = 0;
        }

    }

    public void onClickSound(View view) {
        if(m_tab != 1)
        {
            //not already in sound tab so switch to it
            SoundOptionsFragment fragment = new SoundOptionsFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            m_tab = 1;
        }
    }

    public void onClickInput(View view) {
        if(m_tab != 2)
        {
            //not already in controls tab so switch to it
            ControlsOptionsFragment fragment = new ControlsOptionsFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            m_tab = 2;
        }
    }

    public void onClickData(View view) {
        if(m_tab != 3)
        {
            //not already in data tab so switch to it
            DataOptionsFragment fragment = new DataOptionsFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            m_tab = 3;
        }
    }

    public void onClickSave(View view) {
        //go back to parent
        finish();
    }

}



