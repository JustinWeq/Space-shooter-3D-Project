package jeremyred.spaceshooter3dproject.Activitys;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.InputDevice;
import android.view.MotionEvent;

import jeremyred.spaceshooter3dproject.Graphics.GLSurface;
import jeremyred.spaceshooter3dproject.Managers.GameManager;
import jeremyred.spaceshooter3dproject.Data.Level;
import jeremyred.spaceshooter3dproject.Managers.SoundManager;


/**
 * an activity that the game is played in
 * @author jeremy red
 * @version 3/1/2016
 */
public class GameActivity extends Activity implements SensorEventListener {
    /**
     * the view surface that the game is rendererd too
     */
    private GLSurfaceView glView;
    /**
     * the joystick values
     */
    public static float Y1 = 0,Y2 = 0,X1 = 0,X2 = 0;
    /**
     * the scaler
     */
    private static final float SCALAR = 3;

    /**
     * the x,y and z axis of device rotation
     */
    public static float AxisX = 0,Axisy = 0,AxisZ = 0;

    /**
     * the sensor manager
     */
    private SensorManager m_manager;
    /**
     * the sensor to use for sensing
     */
    private Sensor m_sensor;

    /**
     * the public game activity
     */
    public GameActivity MainGameActivity;

    /**
     * called upon game activity creation
     * @param savedInstanceId the saved instance of the last game activity
     */
    public void onCreate(Bundle savedInstanceId)
    {
        super.onCreate(savedInstanceId);

        //create a GLSurfaceViewInstance and set it as the content view for this activity
        glView = new GLSurface(this);
        setContentView(glView);
        //create the game Manager
        GameManager.getGameManager();
        GameManager.getGameManager().setLevel(Level.CurrentLevel);

        Thread thread = new Thread(GameManager.getGameManager());
        thread.start();

        //handler.postAtFrontOfQueue(GameManager.getGameManager());
        m_manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        m_sensor = m_manager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        SoundManager.getSoundManager(this).play();

        MainGameActivity = this;

    }

    /**
     * called upon sensor change
     * @param event the event that triggered the sensor
     */
    public void onSensorChanged(SensorEvent event)
    {

            //its a rotation
            AxisX = event.values[1]*event.values[3]*SCALAR;
            Axisy = -event.values[0]*event.values[3]*SCALAR;
            AxisZ = event.values[2]*event.values[3]*SCALAR;

    }

    /**
     * called on sensor accuracy changed
     * @param sensor the sensor that is used
     * @param accuracy the accuracy for the sensor
     */
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * called upon dispatch of the generic motion envent
     * @param event the event that was dispatched
     * @return  a bool indicating sucsess
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
     * gets the centered version on the passed in axis
     * @param event the event that is used
     * @param device the inut device
     * @param axis the axis
     * @param historyPos the historical position
     * @return the value on the passed in axis
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
     * processes the joystick input
     * @param event the even that was called
     * @param historyPos the historical position
     */
    private void processJoystickInput(MotionEvent event,int historyPos)
    {
        InputDevice device = event.getDevice();

        X1 = getCenteredAxis(event,device,MotionEvent.AXIS_X,historyPos);
        X2 = getCenteredAxis(event,device,MotionEvent.AXIS_RX,historyPos);
        Y1 = getCenteredAxis(event,device,MotionEvent.AXIS_Y,historyPos);
        Y2 = getCenteredAxis(event,device,MotionEvent.AXIS_RY,historyPos);
    }

    /**
     * called upon resum of the game activity
     */
    protected void onResume()
    {
        super.onResume();
        m_manager.registerListener(this,m_sensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    /**
     * called on pause of the activity
     */
    protected void onPause()
    {
        super.onPause();
        m_manager.unregisterListener(this);
    }

    @Override
    public void onBackPressed()
    {

        finish();
    }



}
