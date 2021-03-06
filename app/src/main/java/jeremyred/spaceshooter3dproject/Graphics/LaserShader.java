package jeremyred.spaceshooter3dproject.Graphics;

import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import jeremyred.spaceshooter3dproject.Activitys.LevelListActivity;
import jeremyred.spaceshooter3dproject.Data.Model;

/**
 * A shader class for effects such as lasers or explosions that do not want to have lightin applied to them
 * Created by jeremy on 1/28/2016.
 */
public class LaserShader {

    private int m_program;
    private int m_vertexShader;
    private int m_fragmentShader;
    private int m_MVPHandle;
    private int m_vertexHandle;
    private int m_colorHandle;
    private int m_uvHandle;
    private int m_backColor;

    public LaserShader()
    {
        try
        {
            m_program = GLES20.glCreateProgram();
            //read the shader code in
            InputStream  is = LevelListActivity.Manager.open("shaders/laserVertexShader.glsl");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder strBuild = new StringBuilder();
            String news;
            while((news = reader.readLine())!= null)strBuild.append(news);
            //compile vertexShader code
            m_vertexShader = GLShader.loadShader(GLES20.GL_VERTEX_SHADER, strBuild.toString());

            is.close();
            //load the fragment shader
            is = LevelListActivity.Manager.open("shaders/laserFragmentShader.glsl");
            reader = new BufferedReader(new InputStreamReader(is));
            strBuild = new StringBuilder();
            while ((news = reader.readLine())!= null)strBuild.append(news);
            //compile fragment shader code
            m_fragmentShader = GLShader.loadShader(GLES20.GL_FRAGMENT_SHADER, strBuild.toString());

            //shaders have been compiled so create program
            GLES20.glAttachShader(m_program,m_vertexShader);

            GLES20.glAttachShader(m_program,m_fragmentShader);

            GLES20.glLinkProgram(m_program);
            m_MVPHandle = GLES20.glGetUniformLocation(m_program, "uMVPMatrix");

            //enable vertex array
            GLES20.glEnable(m_MVPHandle);

            m_vertexHandle = GLES20.glGetAttribLocation(m_program, "vPosition");

            //enable vertex array
            GLES20.glEnable(m_vertexHandle);

            m_uvHandle = GLES20.glGetAttribLocation(m_program,"aTexCoord");

            //eneable vertex array
            GLES20.glEnable(m_uvHandle);

            m_colorHandle = GLES20.glGetUniformLocation(m_program, "uColor");

            //enable vertex array
            GLES20.glEnable(m_colorHandle);

            m_backColor = GLES20.glGetUniformLocation(m_program,"uBackColor");

            //enable vertex array
            GLES20.glEnable(m_backColor);


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public void setMVP(float[] mvp)
    {
        GLES20.glUniform4fv(m_MVPHandle,1,mvp,0);
    }

    public void setPositions(FloatBuffer positions)
    {
        GLES20.glVertexAttribPointer(m_vertexHandle, Model.COORDS_PER_VERTEX, GLES20.GL_FLOAT, false,
                Model.COORDS_PER_VERTEX * 4, positions);
    }

    public void setTextureCoords(FloatBuffer texCoords)
    {
        GLES20.glVertexAttribPointer(m_uvHandle,2,GLES20.GL_FLOAT,false,
                2*4,texCoords);
    }

    public void setColor(float[] color)
    {
        GLES20.glUniform4fv(m_colorHandle, 1, color, 0);
    }

    public void setBackColor(float[] backColor)
    {
        GLES20.glUniform4fv(m_backColor,1,backColor,0);
    }

    public void drawModel(float[] mmodel,float[] view,float[] projection,
                          float[] color,Model model,float[] backColor)
    {

        //tell openGL to use the saler shaders program
        GLES20.glUseProgram(m_program);

        //Multiply MVP
        float[] MVP= new float[16];
        Matrix.multiplyMM(MVP, 0, projection, 0, view, 0);
        Matrix.multiplyMM(MVP, 0, MVP, 0, mmodel, 0);

        //enable vertex attrib array
        GLES20.glEnable(m_vertexHandle);

        //prepare the models positions
        GLES20.glVertexAttribPointer(m_vertexHandle, model.COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false, model.COORDS_PER_VERTEX * 4, model.getVertexBuffer());

        //enable handle for UV
        GLES20.glEnable(m_uvHandle);

        //prepare the models UV
        GLES20.glVertexAttribPointer(m_uvHandle, 2, GLES20.GL_FLOAT,
                false, 2 * 4, model.getUVsBuffer());

        //set the color for drawing
        GLES20.glUniform4fv(m_colorHandle, 1, color, 0);

        //pass in the mvp matrix
        GLES20.glUniformMatrix4fv(m_MVPHandle, 1, false, MVP, 0);

        //Load the bitmap into the bound texture
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, model.getBitmap(), 0);

        //Enable alpha blending
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE);

        GLES20.glEnable(m_backColor);

        GLES20.glUniform4fv(m_backColor,1,backColor,0);


        //GLES20.glEnable(GLES20.GL_BLEND_SRC_ALPHA);

        //Draw the model
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,model.getVertexCount());

        GLES20.glDisable(GLES20.GL_BLEND);
        


    }

}
