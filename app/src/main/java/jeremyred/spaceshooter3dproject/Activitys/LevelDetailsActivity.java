package jeremyred.spaceshooter3dproject.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import jeremyred.spaceshooter3dproject.Data.Level;
import jeremyred.spaceshooter3dproject.Graphics.GLRenderer;
import jeremyred.spaceshooter3dproject.Managers.DataManager;
import jeremyred.spaceshooter3dproject.R;

/**
 * Created by jeremy on 1/15/2016.
 */
public class LevelDetailsActivity extends Activity {

    Level m_level;
    private int m_selectedLevel;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_details_layout);
        //create the level data and load it
        AssetManager manager = getAssets();
        try{
            DataManager dataManager = DataManager.getDataManager();
            if(savedInstanceState != null)
            {
                m_selectedLevel = savedInstanceState.getInt("level");
            }
            else
            {
                m_selectedLevel = (int) (getIntent().getLongExtra(
                        LevelListActivity.SELECTED_LEVEL, 0));
            }
            m_level  = dataManager.getLevelList().get(m_selectedLevel);
            m_selectedLevel = (int)(getIntent().getLongExtra(
                    LevelListActivity.SELECTED_LEVEL,0));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        //set the text values
        TextView view = (TextView) findViewById(R.id.lblLevel);
        String addStr = view.getText() +m_level.getName();
        view.setText(addStr);

        view = (TextView) findViewById(R.id.lblDifficulty);
        addStr = view.getText() +m_level.getDifficulty();
        view.setText(addStr);

        view =(TextView) findViewById(R.id.lblNumber);
         addStr = view.getText() + ""+m_level.getListOfEnemys().size();
        view.setText(addStr);

        GLRenderer.Manager = getAssets();
        Level.CurrentLevel = m_level;
    }

    public void onClickOK(View view)
    {
        //set up new intent to go to the game
        Intent intent= new Intent(this,GameActivity.class);
        //luanch intent
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        outState.putInt("level",m_selectedLevel);
    }
}
