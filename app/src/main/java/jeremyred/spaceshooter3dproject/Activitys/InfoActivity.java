package jeremyred.spaceshooter3dproject.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import jeremyred.spaceshooter3dproject.R;

/**
 * An acitivty that displays game info
 * @author jeremy red
 * @version 3/4/2016
 */
public class InfoActivity extends Activity {

    /**
     * called on creation
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_layout);
    }

    /**
     * called on the back button being clicked
     * @param view
     */
    public void onClickBack(View view) {
        //go back to parent activity
        finish();
    }
}
