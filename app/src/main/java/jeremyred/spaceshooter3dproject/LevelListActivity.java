package jeremyred.spaceshooter3dproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

/**
 * Created by jeremy on 1/14/2016.
 */
public class LevelListActivity extends Activity {
    public final static String SELECTED_LEVEL= "jeremyred.spaceshooter3dproject.SELECTED_LEVEL";
    public static AssetManager Manager;
    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        //call super
        super.onCreate(savedInstanceState);
        //set the layout
        setContentView(R.layout.main_content);
        Manager = getAssets();

    }

    public void onClickOk(View view)
    {
        //set up new intent to go to new activity
        Intent intent = new Intent(this,LevelDetailsActivity.class);
        Spinner spinner = (Spinner) findViewById(R.id.levels);
        intent.putExtra(SELECTED_LEVEL,"levels/"+spinner.getSelectedItem().toString());
        startActivity(intent);
    }
}
