package jeremyred.spaceshooter3dproject;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

/**
 * Created by jeremy on 1/15/2016.
 */
public class ShipShader {
    private int m_fragmentShader;
    private int m_vertexShader;
    private int m_program;
    private int m_positionHandle;
    private int m_normalHandle;
    private int m_uvHandle;
    private int m_colorHandle;
    private int m_MVPHandle;
    private int m_modelHandle;
    private int m_viewHandle;
    private int m_projectionHandle;
    private int m_ambientColorHandle;
    private int m_cameraPosHandle;
    private int m_lightDirectionHandle;
    private int m_specularColorHandle;
    private int m_specularPower;
    private int m_diffuseColor;
    private int m_uAddHandle;
    private boolean m_positionsInit = false;
    private boolean m_normalsInit = false;
    private boolean m_uvssInit = false;
    private boolean m_colorsInit = false;
    private boolean m_mvpInit = false;
    private boolean m_modelInit = false;
    private boolean m_viewInit = false;
    private boolean m_projectionInit = false;
    private boolean m_ambeintInit = false;
    private boolean m_cameraInit = false;
    private boolean m_lightDirInit = false;
    private boolean m_specularColorInit = false;
    private boolean m_specularPowerInit = false;
    private boolean m_diffuseInit = false;
    private boolean m_uAddInit = false;
    private boolean m_textureInit = false;


    public ShipShader(String vertexShaderAddress,String fragmentShaderAddress,AssetManager manager)
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
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_WRAP_T,
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

            //get the handle to the fragment shaders color member
            m_colorHandle = GLES20.glGetUniformLocation(m_program,"vColor");

            //enable vertex array
            GLES20.glEnable(m_colorHandle);

            //get the handle to the MVP matrix
            m_MVPHandle = GLES20.glGetUniformLocation(m_program,"uMVPMatrix");

            //enable vertex array
            GLES20.glEnable(m_MVPHandle);

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
            m_specularColorHandle = GLES20.glGetAttribLocation(m_program, "specularColor");

            GLES20.glEnable(m_specularColorHandle);

            //get a handle to the uAdd value
            m_uAddHandle = GLES20.glGetUniformLocation(m_program,"uAdd");

            GLES20.glEnable(m_uAddHandle);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }

    public void setNormals(FloatBuffer normals)
    {
        GLES20.glVertexAttribPointer(m_normalHandle, Model.COORDS_PER_VERTEX, GLES20.GL_FLOAT, false,
                Model.COORDS_PER_VERTEX * 4, normals);
        m_normalsInit = true;
    }

    public void setTexture(Bitmap texture)
    {
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, texture, 0);
        m_textureInit = true;
    }

    public void setTextureCoords(FloatBuffer texCoords)
    {
        GLES20.glVertexAttribPointer(m_uvHandle, 2, GLES20.GL_FLOAT, false, 2 * 4, texCoords);
        m_uvssInit = true;
    }

    public void setPositions(FloatBuffer positions)
    {
        GLES20.glVertexAttribPointer(m_positionHandle, Model.COORDS_PER_VERTEX, GLES20.GL_FLOAT,
                false, Model.COORDS_PER_VERTEX * 4, positions);
        m_positionsInit = true;
    }

    public void setColor(float[] color)
    {
        GLES20.glUniform4fv(m_colorHandle, 1, color, 0);
        m_colorsInit = true;
    }

    public void setMVP(float[] mvp)
    {
        GLES20.glUniformMatrix4fv(m_MVPHandle, 1, false, mvp, 0);
        m_mvpInit = true;
    }

    public void setModel(float[] model)
    {
        GLES20.glUniformMatrix4fv(m_modelHandle, 1, false, model, 0);
        m_modelInit = true;
    }

    public void setView(float[] view)
    {
        GLES20.glUniformMatrix4fv(m_viewHandle,1,false,view,0);
        m_viewInit = true;
    }

    public void setProjection(float[] projection)
    {
        GLES20.glUniformMatrix4fv(m_projectionHandle, 1, false, projection, 0);
        m_projectionInit = true;
    }

    public void setAmbientColor(float[] ambientColor)
    {
        GLES20.glUniform4fv(m_ambientColorHandle, 1, ambientColor, 0);
        m_ambeintInit = true;
    }

    public void setCameraPosition(float[] cameraPosition)
    {
        GLES20.glUniform3fv(m_cameraPosHandle, 1, cameraPosition, 0);
        m_cameraInit = true;
    }

    public void setDiffuseColor(float[] diffuseColor)
    {
        GLES20.glUniform4fv(m_diffuseColor, 1, diffuseColor, 0);
        m_diffuseInit = true;
    }

    public void setLightDirection(float[] lightDirection)
    {
        GLES20.glUniform3fv(m_lightDirectionHandle, 1, lightDirection, 0);
        m_lightDirInit = true;
    }

    public void setSpecularPower(float specularPower)
    {
        GLES20.glUniform1f(m_specularPower, specularPower);
        m_specularPowerInit = true;
    }

    public void setSpecularColor(float[] specularColor)
    {
        GLES20.glUniform4fv(m_specularColorHandle, 1, specularColor, 0);
        m_specularColorInit = true;
    }

    public void setUAdd(float uAdd)
    {
        GLES20.glUniform1f(m_uAddHandle, uAdd);
        m_uAddInit = true;
    }

    public void drawModel(float[] modelMatrix,float[] view,float[] projection,float[] color,Model model,
                          float[] camerapos,float uAdd)
    {
        //Add program to openGL ES enviroment
        GLES20.glUseProgram(m_program);
        //multiply wvp;
        float[] MVP = new float[16];
        Matrix.multiplyMM(MVP,0,projection,0,view,0);
        //Matrix.multiplyMM(scratch,0,WVP,0,world,0);
        //Matrix.setRotateM(rotateMatrix, 0, 0.034f, 1, 0, 0);
       // Matrix.translateM(rotateMatrix,0,rotateMatrix,0,-1,-1,-1);
       // Matrix.setRotateM(rotateMatrix,0,GLSurface.Y2,0,1,0);
        Matrix.multiplyMM(MVP,0,MVP,0,modelMatrix,0);

        //get handle to vertex shaders vPosition member
//        int positionHandle = GLES20.glGetAttribLocation(m_program,"vPosition");
//
//
//       /// enable a handle to the models vertices
//        GLES20.glEnableVertexAttribArray(positionHandle);
//
//        //Prepare the triangle
//        GLES20.glVertexAttribPointer(positionHandle, model.COORDS_PER_VERTEX,
//                GLES20.GL_FLOAT, false, model.COORDS_PER_VERTEX * 4, model.getVertexBuffer()
//        );
        //set positions
        setPositions(model.getVertexBuffer());

//        int normalHandle = GLES20.glGetAttribLocation(m_program,"aNormal");
//
//        GLES20.glEnableVertexAttribArray(normalHandle);
//
//        GLES20.glVertexAttribPointer(normalHandle, model.COORDS_PER_VERTEX, GLES20.GL_FLOAT,
//                false, model.COORDS_PER_VERTEX * 4, model.getNormalsBuffer());

        //set normals
        setNormals(model.getNormalsBuffer());

//        int uvHandle = GLES20.glGetAttribLocation(m_program,"aTexCoord");
//
//        GLES20.glEnableVertexAttribArray(uvHandle);
//
//        GLES20.glVertexAttribPointer(uvHandle, 2, GLES20.GL_FLOAT, false, 2 * 4, model.getUVsBuffer());

        //set uvs
        setTextureCoords(model.getUVsBuffer());

       // get the handle to the framet shaders color member
//        int colorHandle = GLES20.glGetUniformLocation(m_program,"vColor");
//
//        //set color for drawing the triangle
//        GLES20.glUniform4fv(colorHandle, 1, color, 0);

        //set colors
        setColor(color);

        //get handle to shapes transfomr matrix
//        int MVPHandle = GLES20.glGetUniformLocation(m_program,"uMVPMatrix");
//
//        //pass the mvp matrix
//        GLES20.glUniformMatrix4fv(MVPHandle,1,false,MVP,0);

        //set mvp matrix
        setMVP(MVP);

//        int MHandle = GLES20.glGetUniformLocation(m_program, "uMMatrix");
//
//        //pass the model matrix;
//        GLES20.glUniformMatrix4fv(MHandle,1,false,modelMatrix,0);

        //set model matrix
        setModel(modelMatrix);

//        int VHandle = GLES20.glGetUniformLocation(m_program,"uVMatrix");
//
//        //pass the view matrix
//        GLES20.glUniformMatrix4fv(VHandle, 1, false, view, 0);

        //set view matrix
        setView(view);

//        int PHandle = GLES20.glGetUniformLocation(m_program,"uPMatrix");
//
//
//        GLES20.glUniformMatrix4fv(PHandle, 1, false, projection, 0);
        //set projection matrix
        setProjection(projection);

//        int CPosHandle = GLES20.glGetUniformLocation(m_program,"uCameraPosition");
//
//        GLES20.glUniform3f(CPosHandle, camerapos[0], camerapos[1], camerapos[2]);

        //set camera position
        setCameraPosition(camerapos);

        //GLES20.glDisable(GLES20.GL_CULL_FACE);

//        int ACHandle = GLES20.glGetUniformLocation(m_program,"ambientColor");
//
//        GLES20.glUniform4f(ACHandle, 0.5f, 0.5f, 0.5f, 1);

        //set the ambient color
        float[] ac  = {0.2f,0.2f,0.2f,1f};
        setAmbientColor(ac);

//        int DCHandle = GLES20.glGetUniformLocation(m_program,"diffuseColor");
//
//        GLES20.glUniform4f(DCHandle, 1, 1, 1, 1);
        float[] dc =  {0.75f,0.75f,0.75f,1};
        setDiffuseColor(dc);

//        int LDHandle = GLES20.glGetUniformLocation(m_program,"lightDirection");
//
//        GLES20.glUniform3f(LDHandle, 0, 1, 0);

        //set light direction
        float[] lightDir = {0,1,0};

        setLightDirection(lightDir);

//        int SPHandle = GLES20.glGetUniformLocation(m_program, "specularPower");
//
//        GLES20.glUniform1f(SPHandle, 1);

        //set specular power
        setSpecularPower(1);

//        int SCHandle = GLES20.glGetAttribLocation(m_program, "specularColor");
//
//
//        GLES20.glUniform4f(SCHandle, 1, 1, 0, 1);

        //set specular color
        float[] specularColor = {1,1,0,1};

        setSpecularColor(specularColor);

//        int uAHandle = GLES20.glGetUniformLocation(m_program,"uAdd");
//
//        GLES20.glUniform1f(uAHandle, uAdd);

        //set uAdd
        setUAdd(uAdd);

        //Load the bitmap into the bound texture
//        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, model.getBitmap(), 0);

        //set bitmap
        setTexture(model.getBitmap());
        //Draw the model
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, model.getVertexCount());
        //GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER,);

        //Disable vertex array
//        GLES20.glDisableVertexAttribArray(positionHandle);
//        GLES20.glDisableVertexAttribArray(normalHandle);
    }

    public static int loadShader(int type,String shaderCode)
    {
        int shader = GLES20.glCreateShader(type);

        //add the source code to the shader and compile it
        GLES20.glShaderSource(shader,shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

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

    public void drawPreparedModel(int vertexCount)
    {
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,vertexCount);
    }
}
