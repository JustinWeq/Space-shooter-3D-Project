package jeremyred.spaceshooter3dproject.Graphics;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import jeremyred.spaceshooter3dproject.Data.Model;

/**
 * a class tha manages the defialt shader
 * @author jeremy red
 * @version 2/25/2016
 */
public class GLShader {
    /**
     * a handle to the fragment shader
     */
    private int m_fragmentShader;
    /**
     * a handle to the vertex shader
     */
    private int m_vertexShader;
    /**
     * a handle to the program
     */
    private int m_program;
    /**
     * the position handle
     */
    private int m_positionHandle;
    /**
     * the normal handle
     */
    private int m_normalHandle;
    /**
     * the uv handle
     */
    private int m_uvHandle;
    /**
     * the model handle
     */
    private int m_modelHandle;
    /**
     * the view handle
     */
    private int m_viewHandle;
    /**
     * the projection handle
     */
    private int m_projectionHandle;
    /**
     * the ambient color handle
     */
    private int m_ambientColorHandle;
    /**
     * the camera pos  handle
     */
    private int m_cameraPosHandle;
    /**
     * the light direction handle
     */
    private int m_lightDirectionHandle;
    /**
     * the specular color handle
     */
    private int m_specularColorHandle;
    /**
     * the specular power handle
     */
    private int m_specularPower;
    /**
     * the diffuse color handle
     */
    private int m_diffuseColor;
    /**
     * the u add handle
     */
    private int m_uAddHandle;
    /**
     * the draw type
     */
    private int m_uDrawType;
    /**
     * test bool
     */
    private boolean m_positionsInit = false;
    /**
     * test bool
     */
    private boolean m_normalsInit = false;
    /**
     * test bool
     */
    private boolean m_uvssInit = false;
    /**
     * test bool
     */
    private boolean m_modelInit = false;
    /**
     * test bool
     */
    private boolean m_viewInit = false;
    /**
     * test bool
     */
    private boolean m_projectionInit = false;
    /**
     * test bool
     */
    private boolean m_ambeintInit = false;
    /**
     * test bool
     */
    private boolean m_cameraInit = false;
    /**
     * test bool
     */
    private boolean m_lightDirInit = false;
    /**
     * test bool
     */
    private boolean m_specularColorInit = false;
    /**
     * test bool
     */
    private boolean m_specularPowerInit = false;
    /**
     * test bool
     */
    private boolean m_diffuseInit = false;
    /**
     * test bool
     */
    private boolean m_uAddInit = false;
    /**
     * test bool
     */
    private boolean m_textureInit = false;


    /**
     * defualt constructor--creates a new instance of GLShader with defualt parmameters
     * @param vertexShaderAddress the address for the vertex shader
     * @param fragmentShaderAddress the address for the fragment shader
     * @param manager the amanger to laod the shader from
     */
    public GLShader(String vertexShaderAddress, String fragmentShaderAddress, AssetManager manager)
    {
        try {

            //read the shader in
            InputStream is = manager.open(vertexShaderAddress);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder strBuild = new StringBuilder();
            String news;
            while((news = reader.readLine()) != null) strBuild.append(news);
            //compile vertex shader
            m_vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,strBuild.toString());

            is.close();
            //load the fragment shader
            is = manager.open(fragmentShaderAddress);
            reader = new BufferedReader(new InputStreamReader(is));
            strBuild = new StringBuilder();
            while((news = reader.readLine()) != null) strBuild.append(news);
            //compile fragment shader code
            m_fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, strBuild.toString());

            //shaders have been compiled
            m_program = GLES20.glCreateProgram();

            //add the vertex shader to the program
            GLES20.glAttachShader(m_program, m_vertexShader);
            //add the fragment shader to the program
            GLES20.glAttachShader(m_program, m_fragmentShader);

            //create program executables
            GLES20.glLinkProgram(m_program);

            int[] textureNames = new int[1];

            //bind texture to texturename
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureNames[0]);

            //Set filtering mode
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_NEAREST);

            //Set wrapping mode
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_WRAP_S,GLES20.GL_REPEAT);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,
                    GLES20.GL_REPEAT);

            //get positions of handles

            //get handle to vertex shaders vPosition member
            m_positionHandle = GLES20.glGetAttribLocation(m_program,"vPosition");

            //enable vertex array
            GLES20.glEnableVertexAttribArray(m_positionHandle);

            //get position to the normal
            m_normalHandle = GLES20.glGetAttribLocation(m_program,"aNormal");

            //enable vertex array
            GLES20.glEnableVertexAttribArray(m_normalHandle);

            //get handle to the UV attribute
            m_uvHandle = GLES20.glGetAttribLocation(m_program, "aTexCoord");

            //enable vertex array
            GLES20.glEnableVertexAttribArray(m_uvHandle);

            //get a handle to the model matrix
            m_modelHandle = GLES20.glGetUniformLocation(m_program, "uMMatrix");

            //enable vertex array
            GLES20.glEnable(m_modelHandle);

            //get a handle to the view matrix
            m_viewHandle = GLES20.glGetUniformLocation(m_program,"uVMatrix");

            //enable vertex array
            GLES20.glEnable(m_viewHandle);

            //get a handle to the projection matrix
            m_projectionHandle = GLES20.glGetUniformLocation(m_program,"uPMatrix");

            //enable vertex array
            GLES20.glEnable(m_projectionHandle);

            //get a handle to the camera position
            m_cameraPosHandle = GLES20.glGetUniformLocation(m_program,"uCameraPosition");

            //enable vertex array
            GLES20.glEnable(m_cameraPosHandle);

            //get a handle to the ambient light color
            m_ambientColorHandle = GLES20.glGetUniformLocation(m_program,"ambientColor");

            //enable vertex array
            GLES20.glEnable(m_ambientColorHandle);

            //get a handle to the diffuse lighting color
            m_diffuseColor =  GLES20.glGetUniformLocation(m_program,"diffuseColor");

            //enable vertex array
            GLES20.glEnable(m_diffuseColor);

            //get a handle to the lighting direction
            m_lightDirectionHandle = GLES20.glGetUniformLocation(m_program,"lightDirection");

            //enable vertex array
            GLES20.glEnable(m_lightDirectionHandle);

            //get a handle to the lighting specular power
            m_specularPower = GLES20.glGetUniformLocation(m_program, "specularPower");

            //enable vertex
            GLES20.glEnable(m_specularPower);

            //get a handle to the lightins specular color
            m_specularColorHandle = GLES20.glGetUniformLocation(m_program, "specularColor");

            GLES20.glEnable(m_specularColorHandle);

            //get a handle to the uAdd value
            m_uAddHandle = GLES20.glGetUniformLocation(m_program,"uAdd");

            GLES20.glEnable(m_uAddHandle);

            //get a handle to the uDrawType data, this is used to determine the draw type in the shader
            m_uDrawType = GLES20.glGetUniformLocation(m_program,"uDrawType");

            GLES20.glEnable(m_uDrawType);

            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }

    /**
     * sets the normals
     * * @param normals the normals
     */
    public void setNormals(FloatBuffer normals)
    {
        GLES20.glVertexAttribPointer(m_normalHandle, Model.COORDS_PER_VERTEX, GLES20.GL_FLOAT, false,
                Model.COORDS_PER_VERTEX * 4, normals);
        m_normalsInit = true;
    }

    /**
     * sets the texture
     * @param texture the texture
     */
    public void setTexture(Bitmap texture)
    {
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, texture, 0);
        m_textureInit = true;
    }

    /**
     * sets the texture coords
     * @param texCoords the texture coords
     */
    public void setTextureCoords(FloatBuffer texCoords)
    {
        GLES20.glVertexAttribPointer(m_uvHandle, 2, GLES20.GL_FLOAT, false, 2 * 4, texCoords);
        m_uvssInit = true;
    }

    /**
     * sets the positions
     * @param positions the positions
     */
    public void setPositions(FloatBuffer positions)
    {
        GLES20.glVertexAttribPointer(m_positionHandle, Model.COORDS_PER_VERTEX, GLES20.GL_FLOAT,
                false, Model.COORDS_PER_VERTEX * 4, positions);
        m_positionsInit = true;
    }


    /**
     * sets the model matrix
     * * @param model the model matrix
     */
    public void setModel(float[] model)
    {
        GLES20.glUniformMatrix4fv(m_modelHandle, 1, false, model, 0);
        m_modelInit = true;
    }

    /**
     * sets the view matrix
     * @param view the view matrix
     */
    public void setView(float[] view)
    {
        GLES20.glUniformMatrix4fv(m_viewHandle,1,false,view,0);
        m_viewInit = true;
    }

    /**
     * sets the projection
     * @param projection the projection
     */
    public void setProjection(float[] projection)
    {
        GLES20.glUniformMatrix4fv(m_projectionHandle, 1, false, projection, 0);
        m_projectionInit = true;
    }

    /**
     * sets the ambient color
     * @param ambientColor the ambient color
     */
    public void setAmbientColor(float[] ambientColor)
    {
        GLES20.glUniform4fv(m_ambientColorHandle, 1, ambientColor, 0);
        m_ambeintInit = true;
    }

    /**
     * sets the camera position
     * @param cameraPosition the camera position
     */
    public void setCameraPosition(float[] cameraPosition)
    {
        GLES20.glUniform3fv(m_cameraPosHandle, 1, cameraPosition, 0);
        m_cameraInit = true;
    }

    /**
     * sets the diffuse color
     * @param diffuseColor the diffuse color
     */
    public void setDiffuseColor(float[] diffuseColor)
    {
        GLES20.glUniform4fv(m_diffuseColor, 1, diffuseColor, 0);
        m_diffuseInit = true;
    }

    /**
     * sets the light direction
     * @param lightDirection the light direction
     */
    public void setLightDirection(float[] lightDirection)
    {
        GLES20.glUniform3fv(m_lightDirectionHandle, 1, lightDirection, 0);
        m_lightDirInit = true;
    }

    /**
     * sets the specular power
     * @param specularPower the specular power
     */
    public void setSpecularPower(float specularPower)
    {
        GLES20.glUniform1f(m_specularPower, specularPower);
        m_specularPowerInit = true;
    }

    /**
     * sets the specular color
     * @param specularColor the specular color
     */
    public void setSpecularColor(float[] specularColor)
    {
        GLES20.glUniform4fv(m_specularColorHandle, 1, specularColor, 0);
        m_specularColorInit = true;
    }

    /**
     * sets the u add form
     * @param uAdd the u add value
     */
    public void setUAdd(float uAdd)
    {
        GLES20.glUniform1f(m_uAddHandle, uAdd);
        m_uAddInit = true;
    }

    /**
     * draws the model
     * @param modelMatrix the model matrix
     * @param view the view matrix
     * @param projection the projection matrix
     * @param color the color
     * @param model the model
     * @param camerapos the camera position
     * @param uAdd the uAdd value
     */
    public void drawModel(float[] modelMatrix,float[] view,float[] projection,float[] color,Model model,
                          float[] camerapos,float uAdd)
    {
        //Add program to openGL ES enviroment
        GLES20.glUseProgram(m_program);
        //multiply wvp;
        float[] MVP = new float[16];
        Matrix.multiplyMM(MVP,0,projection,0,view,0);

        Matrix.multiplyMM(MVP,0,MVP,0,modelMatrix,0);


        //set positions
        setPositions(model.getVertexBuffer());

        //set normals
        setNormals(model.getNormalsBuffer());

        //set uvs
        setTextureCoords(model.getUVsBuffer());

        //set model matrix
        setModel(modelMatrix);

        //set view matrix
        setView(view);

        //set projection matrix
        setProjection(projection);

        //set camera position
        setCameraPosition(camerapos);

        //set the ambient color
        float[] ac  = {0.2f,0.2f,0.2f,1f};
        setAmbientColor(ac);

        float[] dc =  {0.75f,0.75f,0.75f,1};
        setDiffuseColor(dc);

        //set light direction
        float[] lightDir = {0,1,0};

        setLightDirection(lightDir);

        //set specular power
        setSpecularPower(1);

        //set specular color
        float[] specularColor = {1,1,0,1};

        setSpecularColor(specularColor);

        //set uAdd
        setUAdd(uAdd);

        //set bitmap
        setTexture(model.getBitmap());

        //Draw the model
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, model.getVertexCount());
    }

    /**
     * loads the shader
     * @param type the type of shader
     * @param shaderCode the shader code
     * @return the shader id
     */
    public static int loadShader(int type,String shaderCode)
    {
        int shader = GLES20.glCreateShader(type);

        //add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        final int[] compileStatus = new int[1];
        String error = GLES20.glGetShaderInfoLog(shader);
        Log.e("Shader error",error);
        return shader;
    }

    /**
     * sets the model atributes
     * @param model the model attributes to set
     */
    public void setModelAttributes(Model model)
    {
        //set uvs
        setPositions(model.getVertexBuffer());

        //set tex coords
        setTextureCoords(model.getUVsBuffer());

        //set normals
        setNormals(model.getNormalsBuffer());

        //set texture
        setTexture(model.getBitmap());
    }

    /**
     * draws the prepared model
     * @param vertexCount the vertex count for the model
     */
    public void drawPreparedModel(int vertexCount)
    {
        GLES20.glUseProgram(m_program);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
    }
}
