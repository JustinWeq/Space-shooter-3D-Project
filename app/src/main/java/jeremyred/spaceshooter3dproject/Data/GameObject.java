package jeremyred.spaceshooter3dproject.Data;

/**
 * A simple class that hold methods and data for in game objects
 * @author jeremy red
 * @version 2/23/2016
 */
public class GameObject extends Place {
    /**
     * the model id for the game object
     */
    private int m_modelID;

    /**
     * overloaded constructor, creates a new instance of GameObject with overloaded parameters
     * @param x the x coord for the game object
     * @param y the y coord for the game Object
     * @param z the z coord for the gameObject
     * @param rotX the amount the game object is rotated around the x axis
     * @param rotY the amount the game object is rotated around the y axis( which is up)
     * @param rotZ the amount the game object it rotated around the z axis
     * @param scaleX the amount the game object is scaled on the x axis
     * @param scaleY the amount the game object is scaled on the y axis
     * @param scaleZ the amount the game object it scales on the z axis
     * @param modelID the model id for the game object
     */
    public GameObject(float x,float y,float z,
    float rotX,float rotY,float rotZ,
                      float scaleX,float scaleY,float scaleZ,int modelID)
    {
        super(x,y,z,rotX,rotY,rotZ,scaleX,scaleY,scaleZ);

        m_modelID = modelID;
    }

    /**
     * defualt constructor-- creates a new instance of GameObject with default parameters
     */
    public GameObject()
    {
        super();
        m_modelID = 0;
    }

    /**
     * sets the modelIF for the game object
     * @param modelID the model id for the game object
     */
    public void setModelID(int modelID)
    {
        m_modelID = modelID;
    }

    /**
     * returns the model id for the game object
     * @return the model id for the game object
     */
    public int getM_modelID()
    {
        return m_modelID;
    }
}
