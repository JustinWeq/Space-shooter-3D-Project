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
public class GLSurface extends GLSurfaceView{

    /**
     * the renderer
     */
    private GLRenderer m_renderer;
    /**
     * the positions of the joysticks
     */
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






}
