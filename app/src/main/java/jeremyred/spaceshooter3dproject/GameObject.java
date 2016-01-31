package jeremyred.spaceshooter3dproject;

/**
 * Created by jeremy on 1/30/2016.
 */
public class GameObject extends Place {
    private int m_modelID;

    public GameObject(float x,float y,float z,
    float rotX,float rotY,float rotZ,
                      float scaleX,float scaleY,float scaleZ,int modelID)
    {
        super(x,y,z,rotX,rotY,rotZ,scaleX,scaleY,scaleZ);

        m_modelID = modelID;
    }

    public GameObject()
    {
        super();
        m_modelID = 0;
    }

    public void setModelID(int modelID)
    {

    }
}
