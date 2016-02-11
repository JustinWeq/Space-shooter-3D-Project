package jeremyred.spaceshooter3dproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * A simple activity intferace that displays buttons for the user to click on
 */
public class MainMenuActivity extends Activity {

    public void onCreate(Bundle savedInstanceID)
    {
        super.onCreate(savedInstanceID);

        setContentView(R.layout.main_menu_layout);

    }

    public void onClickPlay(View view)
    {
        //go to level details activity
        Intent intent = new Intent(this,LevelListActivity.class);
        //start the activity
        startActivity(intent);
    }

    public void  onClickOptions(View view)
    {
        //go to the options activity
        Intent intent = new Intent(this,OptionsActivity.class);
        //start the activity
        startActivity(intent);
    }

}
