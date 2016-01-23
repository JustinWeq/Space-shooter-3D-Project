package jeremyred.spaceshooter3dproject;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.renderscript.ScriptGroup;
import android.view.InputDevice;
import android.view.MotionEvent;

import java.util.Map;

/**
 * Created by jeremy on 1/15/2016.
 */
public class GLSurface extends GLSurfaceView {

    GLRenderer m_renderer;
    public static float Y1 = 0,Y2 = 0,X1 = 0,X2 = 0;
    public GLSurface(Context context)
    {
        super(context);

        //create an openGL es 2.0
        setEGLContextClientVersion(2);

        m_renderer = new GLRenderer();

        //Set the renderer for drawing the surface view
        setRenderer(m_renderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

    }

    @Override
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
