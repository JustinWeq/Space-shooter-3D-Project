package jeremyred.spaceshooter3dproject;

import android.opengl.Matrix;

/**
 * Created by jeremy on 1/21/2016.
 */
public class Place {
    private float m_x;
    private float m_y;
    private float m_z;
    private float m_rotX;
    private float m_rotY;
    private float m_rotZ;
    private float m_scaleX;
    private float m_scaleY;
    private float m_scaleZ;
    private float[] m_matrix = new float[16];
    private boolean m_needsGenerated;
    public Place()
    {
        m_x = m_y = m_z =
                m_rotX = m_rotY= m_rotZ =0 ;
        m_scaleX = m_scaleY = m_scaleZ = 1;

        m_needsGenerated = true;
    }

    public Place(float x,float y,float z, float rotX,
                 float rotY,float rotZ,float scaleX,
                 float scaleY,float scaleZ)
    {
        m_rotX = rotX;
        m_rotY = rotY;
        m_rotZ = rotZ;
        m_x = x;
        m_y = y;
        m_z = z;
        m_scaleX = x;
        m_scaleY = y;
        m_scaleZ = z;
        m_needsGenerated = true;
    }

    public float[] getMatrix()
    {
        if(m_needsGenerated)
        {
            generateMatrix();
        }
        return m_matrix;
    }

    private void generateMatrix()
    {
        float[] translation = new float[16];
        float[] rotation = new float[16];
        float[] transformation = new float[16];
        float[] rotationX = new float[16];
        float[] rotationY = new float[16];
        float[] rotationZ = new float[16];
        //identify translation
        Matrix.setIdentityM(translation, 0);
        //translate translation
        Matrix.translateM(translation, 0, translation, 0, m_x, m_y, m_z);
        Matrix.setIdentityM(rotation, 0);

        //rotate rotation matrix
        Matrix.setRotateM(rotationX,0,m_rotX,1,0,0);
        Matrix.setRotateM(rotationY,0,m_rotY,0,1,0);
        Matrix.setRotateM(rotationZ,0,m_rotY,0,0,1);

        //put rotaions to gether
        Matrix.multiplyMM(rotation,0,rotationX,0,rotationY,0);
        Matrix.multiplyMM(rotation,0,rotation,0,rotationZ,0);
        //set transform matrix
        Matrix.setIdentityM(transformation, 0);
        Matrix.scaleM(transformation, 0, transformation, 0, m_scaleX, m_scaleY, m_scaleZ);

        float[] scratch = new float[16];
        Matrix.setIdentityM(scratch,0);
        Matrix.setIdentityM(m_matrix,0);
        //update matrix
        Matrix.multiplyMM(scratch, 0, translation, 0, rotation, 0);
        Matrix.multiplyMM(m_matrix,0,transformation,0,scratch,0);
        m_needsGenerated = false;
    }

    public void setX(float x)
    {
        m_x  =x;
        m_needsGenerated = true;
    }

    public void setY(float y)
    {
        m_y  = y;
        m_needsGenerated = true;
    }

    public void setZ(float z)
    {
        m_z = z;
        m_needsGenerated = true;
    }

    public void setRotX(float rotX)
    {
        m_rotX = rotX;
        m_needsGenerated = true;
    }

    public void setRotY(float rotY)
    {
        m_rotY = rotY;
        m_needsGenerated = true;
    }

    public void setRotZ(float rotZ)
    {
        m_rotZ = rotZ;
        m_needsGenerated = true;
    }

    public void setScaleX(float scaleX)
    {
        m_scaleX = scaleX;
        m_needsGenerated = true;
    }

    public void setScaleY(float scaleY)
    {
        m_scaleY = scaleY;
        m_needsGenerated = true;
    }

    public void setScaleZ(float scaleZ)
    {
        m_scaleZ = scaleZ;
        m_needsGenerated = true;
    }
}
