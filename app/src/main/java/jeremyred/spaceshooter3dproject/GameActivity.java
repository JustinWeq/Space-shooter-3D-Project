package jeremyred.spaceshooter3dproject;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created by jeremy on 1/15/2016.
 */
public class GameActivity extends Activity {
    private GLSurfaceView glView;

    @Override
    public void onCreate(Bundle savedInstanceId)
    {
        super.onCreate(savedInstanceId);

        //create a GLSurfaceViewInstance and set it as the content view for this activity
        glView = new GLSurface(this);
        setContentView(glView);
    }
}
