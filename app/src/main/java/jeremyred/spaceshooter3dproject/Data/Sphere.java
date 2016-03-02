package jeremyred.spaceshooter3dproject.Data;

/**
 * A class that holds collision info for a sphere
 * @author jeremy red
 * @version 2/29/2016
 */
public class Sphere {

    /**
     * the radius of the sphere
     */
    private float m_radius;


    /**
     * default constructor-- creates anew instance of Sphere with default parameters
     */
    public Sphere()
    {
         m_radius = 0;
    }

    /**
     * overloaded constructor-- creates a new instance of Sphere with defualt parameters
     * @param vertices the vertices to construct the sphere from
     */
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


    /**
     * sets the radius for the sphere
     * @param radius the new radius for the sphere
     */
    public void setRadius(float radius)
    {
        m_radius = radius;
    }

    /**
     * returns a bool indicating whether or not the sphere collided with another sphere
     * @param s1 the first sphere
     * @param s2 the second sphere
     * @param x1 the first spheres x position
     * @param y1 the first spheres y position
     * @param z1 the first spheres z position
     * @param x2 the second spheres x position
     * @param y2 the second spehres y position
     * @param z2 the second spheres z position
     * @return the spheres collision status
     */
    public static boolean collides(Sphere s1,Sphere s2,float x1,float y1, float z1,
                                float x2, float y2,float z2)
    {
        //check distance
        float distance =(float)(Math.pow(x1 - x2,2) + Math.pow(y1-y2,2) + Math.pow(z1-z2,2));

        return  (distance < s1.getRadius()+s2.getRadius());
    }

    /**
     * returns the spheres radius
     * @return the spheres radius
     */
    public float getRadius()
    {
        return m_radius;
    }
}
