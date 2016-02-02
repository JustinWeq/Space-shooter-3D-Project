package jeremyred.spaceshooter3dproject;

/**
 * Created by jeremy on 1/29/2016.
 */
public class Sphere {

    private float m_radius;


    public Sphere()
    {
         m_radius = 0;
    }

    public Sphere(float[] vertices)
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

    }



    public void setRadius(float radius)
    {
        m_radius = radius;
    }

    public static boolean collides(Sphere s1,Sphere s2,float x1,float y1, float z1,
                                float x2, float y2,float z2)
    {
        //check distance
        float distance =(float)(Math.pow(x1 - x2,2) + Math.pow(y1-y2,2) + Math.pow(z1-z2,2));

        return  (distance < s1.getRadius()+s2.getRadius());
    }

    public float getRadius()
    {
        return m_radius;
    }
}
