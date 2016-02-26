package jeremyred.spaceshooter3dproject.Data;

import android.opengl.Matrix;

/**
 * A class that maintains information in cartesian and polar space and generates
 * an appropriate matrix for it
 * @author jeremy red
 * @version 2/25/2016
 */
public class Place {
    /**
     * the x coord
     */
    private float m_x;
    /**
     * the y coord
     */
    private float m_y;
    /**
     * the z coord
     */
    private float m_z;
    /**
     * the rotation on the x axis
     */
    private float m_rotX;
    /**
     * the rotation on the y axis
     */
    private float m_rotY;
    /**
     * the rotation on the z axis
     */
    private float m_rotZ;
    /**
     * the x scale
     */
    private float m_scaleX;
    /**
     * the y scale
     */
    private float m_scaleY;
    /**
     * the z scale
     */
    private float m_scaleZ;
    /**
     * the matrix
     */
    private float[] m_matrix = new float[16];
    /**
     * the rotation matrix(usefull for fireing projectiles)
     */
    private float[] m_rotMatrix;
    /**
     * a bool that indicates that the matrix info needs updated
     */
    private boolean m_needsGenerated;

    /**
     * default constructor-- creates a new instance of Place with default parameters
     */
    public Place()
    {
        m_x = m_y = m_z =
                m_rotX = m_rotY= m_rotZ =0 ;
        m_scaleX = m_scaleY = m_scaleZ = 1;

        m_needsGenerated = true;
    }

    /**
     * overloaded construtor-- creates a new instance of Place with overloaded parameters
     * @param x the x coord
     * @param y the y coord
     * @param z the z coord
     * @param rotX the rotation along the x axis
     * @param rotY the rotation along the y axis
     * @param rotZ the rotation along the z axis
     * @param scaleX the x scale
     * @param scaleY the y scale
     * @param scaleZ the z scale
     */
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

    /**
     * returns the matrix
     * @return the matrix
     */
    public float[] getMatrix()
    {
        if(m_needsGenerated)
        {
            generateMatrix();
        }
        return m_matrix;
    }

    /**
     * generates the matrix
     */
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
        m_rotMatrix = rotation;
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

    /**
     * sets the x coord
     * @param x the new x coord
     */
    public void setX(float x)
    {
        m_x  =x;
        m_needsGenerated = true;
    }

    /**
     * sets the y coord
     * @param y the new y coord
     */
    public void setY(float y)
    {
        m_y  = y;
        m_needsGenerated = true;
    }

    /**
     * sets the z coord
     * @param z the new z coord
     */
    public void setZ(float z)
    {
        m_z = z;
        m_needsGenerated = true;
    }

    /**
     * sets the x rotation
     * @param rotX the new x rotation
     */
    public void setRotX(float rotX)
    {
        m_rotX = rotX;
        m_needsGenerated = true;
    }

    /**
     * sets the rotation along the y axis
     * @param rotY the new rotation on the y axis
     */
    public void setRotY(float rotY)
    {
        m_rotY = rotY;
        m_needsGenerated = true;
    }

    /**
     * sets the rotation along the z axis
     * @param rotZ the new z rotation
     */
    public void setRotZ(float rotZ)
    {
        m_rotZ = rotZ;
        m_needsGenerated = true;
    }

    /**
     * sets the x scal
     * @param scaleX the new x sclae
     */
    public void setScaleX(float scaleX)
    {
        m_scaleX = scaleX;
        m_needsGenerated = true;
    }

    /**
     * sets the y scale
     * @param scaleY the new y scale
     */
    public void setScaleY(float scaleY)
    {
        m_scaleY = scaleY;
        m_needsGenerated = true;
    }

    /**
     * sets the z scale
     * @param scaleZ the new z scale
     */
    public void setScaleZ(float scaleZ)
    {
        m_scaleZ = scaleZ;
        m_needsGenerated = true;
    }

    /**
     * returns the rotation matrix
     * @return the rotation matrix
     */
    public float[] getRotationatrix()
    {
        if(m_needsGenerated)
        {
            generateMatrix();
        }

        return m_rotMatrix;
    }

    /**
     * returns the position for the place in an array of floatt
     * @return the cartasian point
     */
    public float[] getPos()
    {
        float[] pos = new float[3];
        pos[0] = m_x;
        pos[1] = m_y;
        pos[2] = m_z;

        return  pos;
    }

    /**
     * returns the x coord
     * @return the x coord
     */
    public float getX()
    {
        return m_x;
    }

    /**
     * returns the y coord
     * @return the y coord
     */
    public float getY()
    {
        return m_y;
    }

    /**
     * returns the z coord
     * @return the z coord
     */
    public float getZ()
    {
        return m_z;
    }

    /**
     * returns a copy of the matrix, suitable for thread queues
     * @return a copy of the matrix(meant for world matrix)
     */
    public float[] getWorldCopy()
    {
        if(m_needsGenerated)
        {
            generateMatrix();
        }

        float[] copy = new float[16];
        for(int i = 0;i < 16;i++)
        {
            copy[i] = m_matrix[i];
        }

        return copy;
    }

}
