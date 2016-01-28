package jeremyred.spaceshooter3dproject;

import android.content.res.AssetManager;
import android.graphics.Shader;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;

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
    Player player;
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
        player = new Player();
    }

    float dx = 0;
    float dy = 0;
    float dz = 0;

    @Override
    public void onDrawFrame(GL10 unused)
    {
        m_angle+= 0.2f;
        //m_angle%= Math.PI;
        //redraw the background color
        dx+= GameActivity.Y1*2;
        dy+= GameActivity.X1*2;
        dz += GameActivity.X2*2;
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT);
        player.getPlace().setRotX(m_angle*2);
        player.getPlace().setZ(5);
        float[] view = new float[16];
        float[] pos = {0,0,-3,0};


        Matrix.perspectiveM(view, 0, 70, m_screenWidth / m_screenHeight, 1, 70);
       Matrix.setLookAtM(view, 0, pos[0], pos[1], pos[2], player.getPlace().getX(),
                player.getPlace().getY(), player.getPlace().getZ(), 0f, 1.0f, 0.0f);




        float[] color= {0,0,1,1};
        m_shader.drawModel(player.getPlace().getMatrix(), view, m_projection, color, m_model,pos);

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
