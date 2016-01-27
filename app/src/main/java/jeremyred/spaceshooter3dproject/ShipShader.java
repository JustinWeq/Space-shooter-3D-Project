package jeremyred.spaceshooter3dproject;

import android.content.res.AssetManager;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.Matrix;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jeremy on 1/15/2016.
 */
public class ShipShader {
    private int m_fragmentShader;
    private int m_vertexShader;
    private int m_program;



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
            m_fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,strBuild.toString());

            //shaders have been compiled
            m_program = GLES20.glCreateProgram();

            //add the vertex shader to the program
            GLES20.glAttachShader(m_program,m_vertexShader);
            //add the fragment shader to the program
            GLES20.glAttachShader(m_program,m_fragmentShader);

            //create program executables
            GLES20.glLinkProgram(m_program);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }

    public void drawModel(float[] modelMatrix,float[] view,float[] projection,float[] color,Model model)
    {
        //Add program to openGL ES enviroment
        GLES20.glUseProgram(m_program);
        //multiply wvp;
        float[] MVP = new float[16];
        Matrix.multiplyMM(MVP,0,projection,0,view,0);
        float[] rotateMatrix = new float[16];
        //Matrix.multiplyMM(scratch,0,WVP,0,world,0);
        //Matrix.setRotateM(rotateMatrix, 0, 0.034f, 1, 0, 0);
       // Matrix.translateM(rotateMatrix,0,rotateMatrix,0,-1,-1,-1);
       // Matrix.setRotateM(rotateMatrix,0,GLSurface.Y2,0,1,0);
        Matrix.multiplyMM(MVP,0,MVP,0,modelMatrix,0);

        //get handle to vertex shaders vPosition member
        int positionHandle = GLES20.glGetAttribLocation(m_program,"vPosition");


        //enable a handle to the models vertices
        GLES20.glEnableVertexAttribArray(positionHandle);

        //Prepare the triangle
        GLES20.glVertexAttribPointer(positionHandle, model.COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false, model.COORDS_PER_VERTEX * 4, model.getVertexBuffer()
        );

        int normalHandle = GLES20.glGetAttribLocation(m_program,"aNormal");

        GLES20.glEnableVertexAttribArray(normalHandle);

        GLES20.glVertexAttribPointer(normalHandle, model.COORDS_PER_VERTEX, GLES20.GL_FLOAT,
                false, model.COORDS_PER_VERTEX * 4, model.getNormalsBuffer());

        //get the handle to the framet shaders color member
        int colorHandle = GLES20.glGetUniformLocation(m_program,"vColor");

        //set color for drawing the triangle
        GLES20.glUniform4fv(colorHandle, 1, color, 0);

        //get handle to shapes transfomr matrix
        int MVPHandle = GLES20.glGetUniformLocation(m_program,"uMVPMatrix");

        //pass the mvp matrix
        GLES20.glUniformMatrix4fv(MVPHandle,1,false,MVP,0);

        int MHandle = GLES20.glGetUniformLocation(m_program, "uMMatrix");

        //pass the model matrix;
        GLES20.glUniformMatrix4fv(MHandle,1,false,modelMatrix,0);

        int VHandle = GLES20.glGetUniformLocation(m_program,"uVMatrix");

        //pass the view matrix
        GLES20.glUniformMatrix4fv(VHandle, 1, false, view, 0);

        int PHandle = GLES20.glGetUniformLocation(m_program,"uPMatrix");


        GLES20.glUniformMatrix4fv(PHandle, 1, false, projection, 0);

        int CPosHandle = GLES20.glGetUniformLocation(m_program,"uCameraPosition");

        GLES20.glUniform3f(CPosHandle, 0, 0, -3);

        //GLES20.glDisable(GLES20.GL_CULL_FACE);

        int ACHandle = GLES20.glGetUniformLocation(m_program,"ambientColor");

        GLES20.glUniform4f(ACHandle, 1, 1, 1, 0.5f);

        int DCHandle = GLES20.glGetUniformLocation(m_program,"diffuseColor");

        GLES20.glUniform4f(DCHandle, 1, 1, 1, 0.5f);

        int LDHandle = GLES20.glGetUniformLocation(m_program,"lightDirection");

        GLES20.glUniform3f(LDHandle, 0, 0, 1);

        int SPHandle = GLES20.glGetUniformLocation(m_program,"specularPower");

        GLES20.glUniform1f(SPHandle, 2);

        int SCHandle = GLES20.glGetAttribLocation(m_program,"specularColor");

        GLES20.glUniform4f(SCHandle,1,1,0,1);


        //Draw the model
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, model.getVertexCount());
        //GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER,);

        //Disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle);
        GLES20.glDisableVertexAttribArray(normalHandle);
    }

    public static int loadShader(int type,String shaderCode)
    {
        int shader = GLES20.glCreateShader(type);

        //add the source code to the shader and compile it
        GLES20.glShaderSource(shader,shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}
