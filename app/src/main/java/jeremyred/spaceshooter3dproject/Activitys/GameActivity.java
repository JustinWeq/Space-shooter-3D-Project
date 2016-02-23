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
 * Created by jeremy on 1/15/2016.
 */
public class GameActivity extends Activity implements SensorEventListener {
    private GLSurfaceView glView;
    public static float Y1 = 0,Y2 = 0,X1 = 0,X2 = 0;
    private static final float SCALAR = 3;

    public static float AxisX = 0,Axisy = 0,AxisZ = 0;

    private SensorManager m_manager;
    private Sensor m_sensor;

    @Override
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

    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {

            //its a rotation
            AxisX = event.values[1]*event.values[3]*SCALAR;
            Axisy = -event.values[0]*event.values[3]*SCALAR;
            AxisZ = event.values[2]*event.values[3]*SCALAR;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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

    private void processJoystickInput(MotionEvent event,int historyPos)
    {
        InputDevice device = event.getDevice();

        X1 = getCenteredAxis(event,device,MotionEvent.AXIS_X,historyPos);
        X2 = getCenteredAxis(event,device,MotionEvent.AXIS_RX,historyPos);
        Y1 = getCenteredAxis(event,device,MotionEvent.AXIS_Y,historyPos);
        Y2 = getCenteredAxis(event,device,MotionEvent.AXIS_RY,historyPos);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        m_manager.registerListener(this,m_sensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        m_manager.unregisterListener(this);
    }



}
