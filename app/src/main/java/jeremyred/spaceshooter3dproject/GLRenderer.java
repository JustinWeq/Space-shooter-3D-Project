package jeremyred.spaceshooter3dproject;

import android.content.res.AssetManager;
import android.graphics.Shader;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.view.InputDevice;
import android.view.MotionEvent;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by jeremy on 1/15/2016.
 */
public class GLRenderer implements GLSurfaceView.Renderer {

    public static AssetManager Manager;
    Model m_model;
    ShipShader m_shader;
    Place place;
    Place place2;
    float[] m_projection = new float[16];
    float m_angle;
    boolean controllerIsConnected;
    float m_screenWidth;
    float m_screenHeight;
    private int gameController;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        m_model = new Model("Models/ship.obj",Manager);
        m_shader = new ShipShader("shaders/vertexShader.glsl","shaders/fragmentShader.glsl",LevelListActivity.Manager);
        //set the background frame color
        GLES20.glClearColor(0, 0, 0, 0);
        m_angle = 0;
        place = new Place();
        place2 = new Place();
        controllerIsConnected = controllerConnected();
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        controllerConnected();
    }

    @Override
    public void onDrawFrame(GL10 unused)
    {
        m_angle+= 0.2f;
        //m_angle%= Math.PI;
        //redraw the background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT);
        float[] view = new float[16];
        Matrix.perspectiveM(view, 0, 70, m_screenWidth / m_screenHeight, 1, 70);
        Matrix.setLookAtM(view, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        //Matrix.translateM(world, 0, world, 0, 0, 0, 0);
      // Matrix.setRotateM(world, 0, m_angle, 1, 0, 0);

        m_angle = m_angle+GameActivity.X1;
        place.setRotX(m_angle);
        place.setZ(5);
        //place.setRotY(m_angle);
        //place.setRotZ(m_angle);
        //rotate world
        float[] color= {0,0,1,1};
        float[] world2 = place.getMatrix();
        m_shader.drawModel(place.getMatrix(), view, m_projection, color, m_model);


        //m_shader.drawModel(world2,view,m_projection,color,m_model);

        //m_shader.drawModel(place2.getMatrix(),view,m_projection,color,m_model);
    }

    @Override
    public void onSurfaceChanged(GL10 unused,int width,int height)
    {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float)width/height;
        m_screenHeight = height;
        m_screenWidth = width;
        Matrix.frustumM(m_projection,0,-ratio,ratio,-1,1,1,70);
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
            gameController = (int)gameControllers.get(0);
            return true;
        }
        return false;
    }



}
