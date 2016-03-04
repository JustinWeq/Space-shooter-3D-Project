package jeremyred.spaceshooter3dproject.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import jeremyred.spaceshooter3dproject.R;

/**
 * A level finish activity that displays a message to the user and allows the user to return to the main menu or
 * to continue to the next one if one exists(if one does not exist it returns back to the main menu by default)
 * @author jeremy red
 * @version 3/1/2016
 */
public class LevelFinishActivity extends Activity{


    /**
     * called upon activity creation
     * @param savedInstanceState the saved instance state
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_finish_layout);
    }

    /**
     * called upon the return button being clicked
     * @param view the button view
     */
    public void onClickReturn(View view) {
        finish();
    }

    /**
     * called upon the continue button being clicked
     * @param view the button view
     */
    public void onClickContinue(View view) {

        finish();
    }
}
