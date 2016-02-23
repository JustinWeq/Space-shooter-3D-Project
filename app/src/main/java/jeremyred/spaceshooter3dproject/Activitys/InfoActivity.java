package jeremyred.spaceshooter3dproject.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import jeremyred.spaceshooter3dproject.R;

/**
 * Created by jeremy on 2/11/2016.
 */
public class InfoActivity extends Activity {

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_layout);
    }

    public void onClickBack(View view) {
        //go back to parent activity
        finish();
    }
}
