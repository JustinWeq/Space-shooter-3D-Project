package jeremyred.spaceshooter3dproject;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

/**
 * Created by jeremy on 2/9/2016.
 */
public class OptionsActivity extends FragmentActivity
{


    private int m_tab = -1;
    private int m_resetClicks = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_layout);

        if(savedInstanceState != null)
        {
            return;
        }

        //Create the defualt fragment to display
        GraphicsOptionsFragment firstFragment = new GraphicsOptionsFragment();

        Bundle args = new Bundle();
        //pass the intesnts extrans to the fragment
        firstFragment.setArguments(getIntent().getExtras());


        //Add the fragment to the framgent container framelayout
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,firstFragment).commit();
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

    }

}



