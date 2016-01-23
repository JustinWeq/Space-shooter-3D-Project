package jeremyred.spaceshooter3dproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jeremy on 1/15/2016.
 */
public class LevelDetailsActivity extends Activity {

    Level m_level;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_details_layout);
        //create the level data and load it
        AssetManager manager = getAssets();
        m_level = new Level(getIntent().getStringExtra(LevelListActivity.SELECTED_LEVEL),manager);
        //set the text values
        TextView view = (TextView) findViewById(R.id.level_txtView);
        view.setText(view.getText()+m_level.getName());
        view = (TextView) findViewById(R.id.difficulty_txtView);
        view.setText(view.getText()+m_level.getDifficulty());
        view =(TextView) findViewById(R.id.number_txtView);
        view.setText(view.getText()+""+m_level.getListOfEnemys().size());
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
}
