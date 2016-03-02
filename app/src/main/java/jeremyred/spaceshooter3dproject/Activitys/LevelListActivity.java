package jeremyred.spaceshooter3dproject.Activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Spinner;

import jeremyred.spaceshooter3dproject.R;
import jeremyred.spaceshooter3dproject.Managers.SoundManager;

/**
 * a class that contains the list of levels
 * @author jeremy red
 * @version 3/1/2016
 */
public class LevelListActivity extends Activity {
    /**
     * the axis stick info
     */
    public static float Y1 = 0,Y2 = 0,X1 = 0,X2 = 0;
    /**
     * the selected level intent id
     */
    public final static String SELECTED_LEVEL= "jeremyred.spaceshooter3dproject.SELECTED_LEVEL";
    /**
     * the public asset manager
     */
    public static AssetManager Manager;
    /**
     * the public context
     */
    public static Context PContext;

    /**
     * called upon the creation of the Level list activity
     * @param savedInstanceState the saved instant state of the last activity
     */
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
        PContext = this;
        SoundManager sound = SoundManager.getSoundManager(this);

        sound.play();


    }

    /**
     * called on the ok button being clicked
     * @param view the view
     */
    public void onClickOk(View view)
    {
        //set up new intent to go to new activity
        Intent intent = new Intent(this,LevelDetailsActivity.class);
        Spinner spinner = (Spinner) findViewById(R.id.spnLevels);
        intent.putExtra(SELECTED_LEVEL, spinner.getSelectedItemId());
        startActivity(intent);
    }

    /**
     * called upon generic motion event being dispatched
     * @param event the evet that was dispatched
     * @return a bool indicating success
     */
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

    /**
     * returns the centered axis
     * @param event the event
     * @param device the InputDevice
     * @param axis the asix
     * @param historyPos the historical pos
     * @return the centered axis
     */
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

    /**
     * called on the saved instance state
     * @param outState the state that is going to be saved
     */
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        //get selected index for the spinner
        int selectedIndex = (int)((Spinner)(findViewById(R.id.spnLevels))).getSelectedItemId();

        outState.putInt("selected",selectedIndex);
    }

    /**
     * process the joystick input
     * @param event the event
     * @param historyPos the historical pos
     */
    private void processJoystickInput(MotionEvent event,int historyPos)
    {
        InputDevice device = event.getDevice();

        X1 = getCenteredAxis(event,device,MotionEvent.AXIS_X,historyPos);
        X2 = getCenteredAxis(event,device,MotionEvent.AXIS_RX,historyPos);
        Y1 = getCenteredAxis(event,device,MotionEvent.AXIS_Y,historyPos);
        Y2 = getCenteredAxis(event,device,MotionEvent.AXIS_RY,historyPos);
    }
}
