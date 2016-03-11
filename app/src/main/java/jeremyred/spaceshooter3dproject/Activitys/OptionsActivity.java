package jeremyred.spaceshooter3dproject.Activitys;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import jeremyred.spaceshooter3dproject.Fragments.ControlsOptionsFragment;
import jeremyred.spaceshooter3dproject.Fragments.DataOptionsFragment;
import jeremyred.spaceshooter3dproject.Fragments.GraphicsOptionsFragment;
import jeremyred.spaceshooter3dproject.MainMenuActivity;
import jeremyred.spaceshooter3dproject.Managers.DataBaseHelper;
import jeremyred.spaceshooter3dproject.R;
import jeremyred.spaceshooter3dproject.Fragments.SoundOptionsFragment;

/**
 * The actvity that manages options
 * @author jeremy red
 * @version 3/4/2016
 */
public class OptionsActivity extends FragmentActivity
{


    /**
     * the selected tab
     */
    private int m_tab = -1;

    /**
     * called upon creation
     * @param savedInstanceState the last saved instance
     */
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

    /**
     * called on the graphics button being clicked
     * @param view the view
     */
    public void onClickGraphics(View view) {

        if(m_tab != 0)
        {
            //not already on graphics tab so switch to it
            GraphicsOptionsFragment fragment = new GraphicsOptionsFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            m_tab = 0;
        }

    }

    /**
     * called on the sound button being clicked
     * @param view the view
     */
    public void onClickSound(View view) {
        if(m_tab != 1)
        {
            //not already in sound tab so switch to it
            SoundOptionsFragment fragment = new SoundOptionsFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            m_tab = 1;
        }
    }

    /**
     * called on the input button being clicked
     * @param view the view
     */
    public void onClickInput(View view) {
        if(m_tab != 2)
        {
            //not already in controls tab so switch to it
            ControlsOptionsFragment fragment = new ControlsOptionsFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            m_tab = 2;
        }
    }

    /**
     * called on the data button being clicked
     * @param view the view
     */
    public void onClickData(View view) {
        if(m_tab != 3)
        {
            //not already in data tab so switch to it
            DataOptionsFragment fragment = new DataOptionsFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            m_tab = 3;
        }
    }

    /**
     * called on the save button being clicked
     * @param view the view
     */
    public void onClickSave(View view) {
        //go back to parent
        MainMenuActivity.DB.updateSave();
        finish();
    }

}



