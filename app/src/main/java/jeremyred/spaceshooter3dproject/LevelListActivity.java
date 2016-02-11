package jeremyred.spaceshooter3dproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Spinner;

/**
 * Created by jeremy on 1/14/2016.
 */
public class LevelListActivity extends Activity {
    public static float Y1 = 0,Y2 = 0,X1 = 0,X2 = 0;
    public final static String SELECTED_LEVEL= "jeremyred.spaceshooter3dproject.SELECTED_LEVEL";
    public static AssetManager Manager;
    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        //call super
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)
        {
            int selectedIndex = savedInstanceState.getInt("selected");

            ((Spinner)findViewById(R.id.spnLevels)).setSelection(selectedIndex);
        }
        //set the layout
        setContentView(R.layout.main_content);
        Manager = getAssets();


    }

    public void onClickOk(View view)
    {
        //set up new intent to go to new activity
        Intent intent = new Intent(this,LevelDetailsActivity.class);
        Spinner spinner = (Spinner) findViewById(R.id.spnLevels);
        intent.putExtra(SELECTED_LEVEL, spinner.getSelectedItemId());
        startActivity(intent);
    }

    @Override
    public boolean  dispatchGenericMotionEvent (MotionEvent event)
    {

        //check that the event came from a game controller
        if((event.getSource() & InputDevice.SOURCE_JOYSTICK) ==
                InputDevice.SOURCE_JOYSTICK &&
                event.getAction() == MotionEvent.ACTION_MOVE)
        {
            //Process all historical movement samples in the batch
            final int historySise = event.getHistorySize();

            //Process the movements starting from the earliest historical position in the batch
            for(int i = 0;i < historySise;i++)
            {
                //process movement
                processJoystickInput(event, i);
            }

            //Process the current movement sample in the natch
            processJoystickInput(event,-1);
            return true;
        }

        return super.onGenericMotionEvent(event);
    }

    private static float getCenteredAxis(MotionEvent event,
                                         InputDevice device,int axis,int historyPos)
    {
        final InputDevice.MotionRange range = device.getMotionRange(axis,event.getSource());

        if(range != null)
        {
            final float flat = range.getFlat();
            final float value = historyPos < 0? event.getAxisValue(axis):
                    event.getHistoricalAxisValue(axis,historyPos);

            if(Math.abs(value) > flat)
            {
                return value;
            }
        }

        return 0;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        //get selected index for the spinner
        int selectedIndex = (int)((Spinner)(findViewById(R.id.spnLevels))).getSelectedItemId();

        outState.putInt("selected",selectedIndex);
    }

    private void processJoystickInput(MotionEvent event,int historyPos)
    {
        InputDevice device = event.getDevice();

        X1 = getCenteredAxis(event,device,MotionEvent.AXIS_X,historyPos);
        X2 = getCenteredAxis(event,device,MotionEvent.AXIS_RX,historyPos);
        Y1 = getCenteredAxis(event,device,MotionEvent.AXIS_Y,historyPos);
        Y2 = getCenteredAxis(event,device,MotionEvent.AXIS_RY,historyPos);
    }
}
