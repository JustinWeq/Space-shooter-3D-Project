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
 * A class that contains methods and feilds for managing the font shader
 * @author jeremy red
 * @version 2/25/2016
 */
public class FontShader {
    /**
     * the GL program for the shader
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
     * the texture coordinates handle
     */
    private int m_aTexCoordHandle;
    /**
     * the model view projection matrix handle
     */
    private int m_uMVPHandle;
    /**
     * the position attribute handle
     */
    private int m_aPosition;

    /**
     * defualt constructor-- creates a new instance of FontShader with defualt parameters
     */
    public FontShader()
    {
        //load the file
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            InputStream is = MainMenuActivity.MANAGER.open("shaders/fontVertexShader.glsl");
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
            is = MainMenuActivity.MANAGER.open("shaders/fontFragmentShader.glsl");
            reader = new BufferedReader(new InputStreamReader(is));

            line = "";
            while((line = reader.readLine())!= null)
                stringBuilder.append(line);


            m_fragmentShader = GLShader.loadShader(GLES20.GL_FRAGMENT_SHADER,stringBuilder.toString());

            GLES20.glAttachShader(m_program,m_vertexShader);
            GLES20.glAttachShader(m_program, m_fragmentShader);
            GLES20.glLinkProgram(m_program);
            int[] textureNames = new int[1];

            //bind texture to texturename
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureNames[0]);

            //Set filtering mode
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

            //Set wrapping mode
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,
                    GLES20.GL_REPEAT);

            m_uTextureHandle = GLES20.glGetUniformLocation(m_program, "uTexture");
            GLES20.glEnable(m_uTextureHandle);
            m_aTexCoordHandle = GLES20.glGetAttribLocation(m_program, "aTexCoord");
            GLES20.glEnable(m_aTexCoordHandle);
            m_uMVPHandle = GLES20.glGetUniformLocation(m_program, "uMVPMatrix");
            GLES20.glEnable(m_uMVPHandle);
            m_aPosition = GLES20.glGetAttribLocation(m_program,"aPos");
            GLES20.glEnable(m_aPosition);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }//lkl

    /**
     * sets the positions for the shader
     * @param positionBuffer the positions
     */
    public void setPositions(FloatBuffer positionBuffer)
    {
        GLES20.glVertexAttribPointer(m_aPosition,4,GLES20.GL_FLOAT,false,4*4,positionBuffer);
    }

    /**
     * sets the texture coordinates
     * @param texCoordBuffer the texture coordinates
     */
    public void setTextureCoords(FloatBuffer texCoordBuffer)
    {
        GLES20.glVertexAttribPointer(m_aTexCoordHandle,2,GLES20.GL_FLOAT,false,2*4,texCoordBuffer);
    }

    /**
     * sets the mvp matrix
     * @param mvp the MVP matrix
     */
    public void setMVP(float[] mvp)
    {
        GLES20.glUniformMatrix4fv(m_uMVPHandle, 1, false, mvp, 0);
    }

    /**
     * sets the texture
     * @param texture the texture to set
     */
    public void setTexture(Bitmap texture)
    {
        GLUtils.texImage2D(0, 0, texture, 0);
    }

    /**
     * draw the preparered model
     * @param vertexCount
     */
    public void drawPreparedModel(int vertexCount)
    {
        GLES20.glUseProgram(m_program);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,vertexCount);
    }


}

