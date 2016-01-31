package jeremyred.spaceshooter3dproject;

/**
 * Created by jeremy on 1/29/2016.
 */
public class Sphere {

    private float m_radius;
    private float m_x;
    private float m_y;
    private float m_z;

    public Sphere()
    {
        m_x = m_y = m_z = m_radius = 0;
    }

    public Sphere(float[] vertices,float x,float y,float z)
    {
        //find the farthest point from the origen
        float farthestDistance = 0f;
        for(int i = 0;i< vertices.length/3;i++) {

            float currDistance = (float) (Math.pow(vertices[i * 3], 2) +
                    Math.pow(vertices[i * 3 + 1], 2) +
                    Math.pow(vertices[i * 3 + 2], 2));
            if (currDistance > farthestDistance)
            {
                farthestDistance = currDistance;
            }
        }

        m_radius = farthestDistance;
        m_x = x;
        m_y = y;
        m_z = z;
    }

    public void setX(float x)
    {
        m_x = x;
    }

    public void setY(float y)
    {
        m_y = y;
    }

    public void setZ(float z)
    {
        m_z = z;
    }

    public float getX()
    {
        return m_x;
    }

    public float getY()
    {
        return m_y;
    }

    public float getZ()
    {
        return m_z;
    }

    public void setRadius(float radius)
    {
        m_radius = radius;
    }

    public float getRadius()
    {
        return m_radius;
    }
}
