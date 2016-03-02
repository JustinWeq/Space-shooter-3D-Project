package jeremyred.spaceshooter3dproject.Graphics;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import jeremyred.spaceshooter3dproject.MainMenuActivity;

/**
 * a class that manages the simple shader
 */
public class SimpleShader {
    /**
     * the program
     */
    private int  m_program;
    /**
     * the vertex shader
     */
    private int m_vertexShader;
    /**
     * the fragment shader
     */
    private int m_fragmentShader;
    /**
     * the texture handle
     */
    private int  m_uTextureHandle;
    /**
     * the tex coord handle
     */
    private int m_aTexCoordHandle;
    /**
     * mvp matrix handle
     */
    private int m_uMVPHandle;
    /**
     * the position attribute handle
     */
    private int m_aPosition;

    /**
     * defualt constructor creates a new instance of SimpleShader with default parameters
     */
    public SimpleShader()
    {
        //load the file
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            InputStream is = MainMenuActivity.MANAGER.open("shaders/simpleVertexShader");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while((line = reader.readLine())!= null)
                stringBuilder.append(line);

            m_program = GLES20.glCreateProgram();
            GLES20.glUseProgram(m_program);

            //compile the shader
            m_vertexShader= GLShader.loadShader(GLES20.GL_VERTEX_SHADER,stringBuilder.toString());

            //load the fragment shader
            stringBuilder = new StringBuilder();
            is = MainMenuActivity.MANAGER.open("shaders/simpleFragmentShader");
            reader = new BufferedReader(new InputStreamReader(is));

            line = "";
            while((line = reader.readLine())!= null)
                stringBuilder.append(line);

            m_fragmentShader = GLShader.loadShader(GLES20.GL_FRAGMENT_SHADER,stringBuilder.toString());

            GLES20.glAttachShader(m_program,m_vertexShader);
            GLES20.glAttachShader(m_program, m_fragmentShader);
            GLES20.glLinkProgram(m_program);

            m_uTextureHandle = GLES20.glGetUniformLocation(m_program, "uTexture");
            GLES20.glEnable(m_uTextureHandle);
            m_aTexCoordHandle = GLES20.glGetAttribLocation(m_program, "aTexCoord");
            GLES20.glEnable(m_aTexCoordHandle);
            m_uMVPHandle = GLES20.glGetAttribLocation(m_program,"uMVPMatrix");
            GLES20.glEnable(m_uMVPHandle);
            m_aPosition = GLES20.glGetAttribLocation(m_program,"aPosition");
            GLES20.glEnable(m_aPosition);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * sets the positions attribute in the shader
     * @param positionBuffer the positions buffer
     */
    public void setPositions(FloatBuffer positionBuffer)
    {
        GLES20.glVertexAttrib4fv(m_aPosition,positionBuffer);
    }

    /**
     * sets the texture coords in the shader
     * @param texCoordBuffer the tex coord buffer
     */
    public void setTextureCoords(FloatBuffer texCoordBuffer)
    {
        GLES20.glVertexAttrib2fv(m_aTexCoordHandle, texCoordBuffer);
    }

    /**
     * sets the mvp matrix in the shader
     * @param mvp the mvp matrix
     */
    public void setMVP(float[] mvp)
    {
        GLES20.glUniformMatrix4fv(m_uMVPHandle, 16, false, mvp, 0);
    }

    /**
     * sets the texture in the shader
     * @param texture the texture the shader will use
     */
    public void setTexture(Bitmap texture)
    {
        GLUtils.texImage2D(0, 0, texture, 0);
    }

    /**
     * draws the prepared model in the shader
     * @param vertexCount the models vertex count
     */
    public void drawPreparedModel(int vertexCount)
    {
        GLES20.glUseProgram(m_program);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,vertexCount);
    }


}
