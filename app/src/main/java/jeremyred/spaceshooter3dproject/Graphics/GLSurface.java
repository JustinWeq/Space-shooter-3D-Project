package jeremyred.spaceshooter3dproject.Graphics;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.opengl.GLSurfaceView;
import android.view.InputDevice;
import android.view.MotionEvent;

import jeremyred.spaceshooter3dproject.Graphics.GLConfig;
import jeremyred.spaceshooter3dproject.Graphics.GLRenderer;

/**
 * a class tha manges the games drawing
 * @author jeremy red
 * @version 3/1/2016
 */
public class GLSurface extends GLSurfaceView implements SensorEventListener{

    /**
     * the renderer
     */
    GLRenderer m_renderer;
    /**
     * the positions of the joysticks
     */
    public static float Y1 = 0,Y2 = 0,X1 = 0,X2 = 0;
    /**
     * used to convert nanoseconds to seconds
     */
    private final static float NS2S = 1.0f/1000000000.0f;
    /**
     * the configuration chooser
     */
    private GLConfig configChooser;

    /**
     * overloaded constructor creates a new instance of GLSurface with overloaded parameters
     * @param context the context
     */
    public GLSurface(Context context)
    {
        super(context);

        //create an openGL es 2.0
        setEGLContextClientVersion(2);
        setEGLConfigChooser(configChooser =new GLConfig());

        m_renderer = new GLRenderer();
        //Set the renderer for drawing the surface view
        setRenderer(m_renderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

    }

    @Override
    /**
     * activated upon a generic motion dispatch
     */
    public boolean onGenericMotionEvent(MotionEvent event)
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

    @Override
    /**
     * called on senso accuaracy changed
     */
    public void onSensorChanged(SensorEvent event)
    {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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


}
