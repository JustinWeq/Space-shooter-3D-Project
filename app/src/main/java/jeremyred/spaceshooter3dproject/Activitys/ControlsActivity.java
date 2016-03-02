package jeremyred.spaceshooter3dproject.Activitys;

import android.app.Activity;
import android.os.Bundle;

import jeremyred.spaceshooter3dproject.R;

/**
 * shows the controls activity
 * @author jeremy red
 * @version 3/1/2016
 */
public class ControlsActivity extends Activity {

    /**
     * the method that is called on activity creation
     * @param savedInstanceState the saved instance state of the last instance
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controls_layout);
    }
}
