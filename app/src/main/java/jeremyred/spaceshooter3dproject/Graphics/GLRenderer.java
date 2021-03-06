package jeremyred.spaceshooter3dproject.Graphics;

import android.content.res.AssetManager;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import jeremyred.spaceshooter3dproject.Activitys.GameActivity;
import jeremyred.spaceshooter3dproject.Activitys.LevelListActivity;
import jeremyred.spaceshooter3dproject.Data.Level;
import jeremyred.spaceshooter3dproject.Managers.GameManager;
import jeremyred.spaceshooter3dproject.Managers.GameSettings;
import jeremyred.spaceshooter3dproject.Data.Model;
import jeremyred.spaceshooter3dproject.Managers.ModelManager;
import jeremyred.spaceshooter3dproject.Data.Place;
import jeremyred.spaceshooter3dproject.Data.Player;
import jeremyred.spaceshooter3dproject.Managers.RenderQueue;
import jeremyred.spaceshooter3dproject.Data.RenderQueueItem;

/**
 * A class that is used for rendering the game
 */
public class GLRenderer implements GLSurfaceView.Renderer {

    /**
     * the asset manager for the GLRednerer
     */
    public static AssetManager Manager;
    /**
     * the defualt shader
     */
    private GLShader m_shader;
    /**
     * the effect shader
     */
    private LaserShader m_effectShader;
    /**
     * the player
     */
    private Player player;
    /**
     * the joystick pos
     */
    public static float X1 = 0,Y1 = 0;
    /**
     * the projection matrix
     */
    private float[] m_projection = new float[16];
    /**
     * the screen width
     */
    private float m_screenWidth;
    /**
     * the screen height
     */
    private float m_screenHeight;
    /**
     * the text renderer
     */
    private TextRenderer m_text;
    /**
     * the font shader
     */
    private FontShader m_fontShader;

    /**
     * the parent of the GLRenderer
     */
    private GameActivity m_parent;

    /**
     * overloaded contructor creates a new instance of GLRednerer with overloaded parameters
     * @param parent the parent of the GLRenderer
     */
    public GLRenderer(GameActivity parent)
    {
        super();
        m_parent = parent;
    }

    @Override
    /**
     * the method that is activated upon surface creation
     */
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        m_shader = new GLShader("shaders/vertexShader.glsl","shaders/fragmentShader.glsl", LevelListActivity.Manager);
        //set the background frame color
        GLES20.glClearColor(0, 0, 0, 0);

        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        m_effectShader = new LaserShader();


        //float[]
        //prepare the shader
        GameSettings settings = GameSettings.getGameSettings();
        float[] ac  = {(float)settings.getBrightness()/100f,(float)settings.getBrightness()/100f, (float)settings.getBrightness()/100f,1f};
        m_shader.setAmbientColor(ac);

        float[] dc =  {0.75f,0.75f,0.75f,1};
        m_shader.setDiffuseColor(dc);

        float[] lightDir = {0,1,0};

        m_shader.setLightDirection(lightDir);

        m_shader.setSpecularPower(1);



        m_fontShader = new FontShader();

        m_text = new TextRenderer();
    }


    @Override
    /**
     * the method that is called upon frame draw
     */
    public void onDrawFrame(GL10 unused)
    {
        try {

            GLES20.glEnable(GLES20.GL_BLEND);
            GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
           // M_Semaphore.acquire();
            RenderQueueItem frame = RenderQueue.getRenderQueue().getFrame();
            if(frame == null)
            {
                return;
            }
            ArrayList<ArrayList<float[]>> renderGroups = frame.getModelGroups();
            GameManager gameManager = GameManager.getGameManager();
            float[] playerMatrix = frame.getPlayerMatrix();


            //clear the buffer
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
            GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT);

            float[] view = new float[16];
            float[] pos = {0, 0, 0, 0};
            Player newPlayer = gameManager.getPlayer();
            Place place = newPlayer.getPlace();
            pos[0] = frame.getPlayerPos()[0] * 0.8f;
            pos[1] = frame.getPlayerPos()[1] * 0.8f;

            //pos = frame.getPlayerPos();
            //Player newPlayer = gameManager.getPlayer();
            Matrix.perspectiveM(view, 0, 70, m_screenWidth / m_screenHeight, 1, 70);
            Matrix.setLookAtM(view, 0, pos[0], pos[1], pos[2], frame.getPlayerPos()[0],
                    frame.getPlayerPos()[1], frame.getPlayerPos()[2], 0f, 1.0f, 0.0f);



//        m_shader.drawModel(GameManager.getGameManager().getPlayer().getPlace().getMatrix(), view, m_projection, color, gameManager.getPlayer().getModel()
//                ,pos,0);

            //prepare the model
            // m_shader.setModelAttributes(gameManager.getPlayer().getModel());

            //m_shader.setModel(gameManager.getPlayer().getPlace().getMatrix());

            //set view and projection
            m_shader.setView(view);

            m_shader.setProjection(m_projection);

            m_shader.setCameraPosition(pos);

            //m_shader.setUAdd(1);


            // m_shader.drawPreparedModel(gameManager.getPlayer().getModel().getVertexCount());
            GameSettings settings = GameSettings.getGameSettings();
            //draw the floor
            float[] ac  = {(float)settings.getBrightness()/100f,(float)settings.getBrightness()/100f, (float)settings.getBrightness()/100f,1f};
            m_shader.setAmbientColor(ac);
            float[] dc = {1f, 1f, 1f, 1};
            m_shader.setDiffuseColor(dc);
            m_shader.setLightDirection(new float[] {0,1,1});
            m_shader.setModelAttributes(newPlayer.getModel());

            //m_shader.setModel(newPlayer.getPlace().getMatrix());
            m_shader.setModel(frame
                    .getPlayerMatrix());
            //m_shader.setUAdd(0);
            m_shader.drawPreparedModel(newPlayer.getModel().getVertexCount());

            for (int i = 0; i < renderGroups.size(); i++) {
                if (renderGroups.get(i).size() != 0) {
                    //render since there is at least one model
                    m_shader.setModelAttributes(ModelManager.getModeManager().getModel(i));

                    for (int k = 0; k < renderGroups.get(i).size(); k++) {
                        m_shader.setModel(renderGroups.get(i).get(k));

                        m_shader.drawPreparedModel(ModelManager.getModeManager().getModel(i).getVertexCount());
                    }
                }
            }

            //draw the floor
            m_shader.setModelAttributes(frame.getFloor().getModel());

            m_shader.setTexture(frame.getFloor().getModel().getBitmap());

            m_shader.setModel(frame.getFloor().getMatrix());

            m_shader.setUAdd(frame.getFloor().getAdvanceX());

            m_shader.drawPreparedModel(frame.getFloor().getModel().getVertexCount());

            //check level finish quit
            if(frame.getShouldNext()) {
                //finish the level
                m_parent.next(frame.getGameOver());

            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    @Override
    /**
     * the method that is called upon surface changed(EG: device rotated)
     */
    public void onSurfaceChanged(GL10 unused,int width,int height)
    {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float)width/height;
        m_screenHeight = height;
        m_screenWidth = width;
        Matrix.frustumM(m_projection,0,-ratio,ratio,-1,1,1,70);
    }







}
