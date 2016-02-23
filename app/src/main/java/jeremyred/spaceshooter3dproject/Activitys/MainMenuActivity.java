package jeremyred.spaceshooter3dproject.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.os.Bundle;
import android.view.InputDevice;
import android.view.View;

import java.util.ArrayList;

import jeremyred.spaceshooter3dproject.Managers.GameSettings;
import jeremyred.spaceshooter3dproject.R;

/**
 * A simple activity intferace that displays buttons for the user to click on
 */
public class MainMenuActivity extends Activity {

    public static AssetManager MANAGER;
    private static int m_screenWidth;
    private static int m_screenHeight;
    public void onCreate(Bundle savedInstanceID)
    {
        super.onCreate(savedInstanceID);

        setContentView(R.layout.main_menu_layout);

        GameSettings.getGameSettings().setControllerEnabled(controllerConnected());
        MANAGER = getAssets();

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        m_screenWidth = size.x;
        m_screenHeight = size.y;

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

    public void onClickControls(View view)
    {
        //go to the controls activity
        Intent intent = new Intent(this,ControlsActivity.class);
        //start the controls activity
        startActivity(intent);
    }

    public void onClickInfo(View view)
    {
        //go to the info actvity
        Intent intent = new Intent(this,InfoActivity.class);
        //start the info activity
        startActivity(intent);
    }

    private boolean controllerConnected()
    {
        ArrayList gameControllers = new ArrayList();
        int[] deviceIds = InputDevice.getDeviceIds();
        for(int deviceId: deviceIds)
        {
            InputDevice device = InputDevice.getDevice(deviceId);
            int sources= device.getSources();

            //Veryfy that the device is a gamecontroller
            if((sources & InputDevice.SOURCE_GAMEPAD) == InputDevice.SOURCE_GAMEPAD)
            {
                if (!gameControllers.contains(deviceId)) {
                    gameControllers.add(deviceId);
                }
            }
        }

        if(gameControllers.size() != 0)
        {
            return true;
        }
        return false;
    }

    public static int getScreenWidth()
    {
        return m_screenWidth;
    }

    public static int getScreenHeight()
    {
        return m_screenHeight;
    }

}
