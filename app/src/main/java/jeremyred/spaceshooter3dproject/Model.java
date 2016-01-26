package jeremyred.spaceshooter3dproject;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

/**
 * Created by jeremy on 1/15/2016.
 */
public class Model {
    public final int COORDS_PER_VERTEX = 3;
    private FloatBuffer m_vertexBuffer;
    private FloatBuffer m_normalsBuffer;
    private FloatBuffer m_uvsBuffer;
    private ShortBuffer m_indexBuffer;
    private int m_vertexCount;
    private float[] m_vertices;
    private float[] m_normals;
    private float[] m_uvs;
    private short[] m_indices;
    private Bitmap m_bitmap;

    public Model(String modelAddress,AssetManager manager)
    {
        //load the model data

        readOBJModel(m_vertices,modelAddress,manager);
        //convert vertices to vertexBuffer
        ByteBuffer bb = ByteBuffer.allocateDirect(
                m_vertices.length*4);
        //use the hardware devices native order
        bb.order(ByteOrder.nativeOrder());

        //create a floating point buffer from the byte buffer

        m_vertexBuffer = bb.asFloatBuffer();
        //add the coordinates to the float buffer
        m_vertexBuffer.put(m_vertices);
        //set the buffer position to read the first coordinate
        m_vertexBuffer.position(0);
        m_vertexCount = m_vertices.length/COORDS_PER_VERTEX;

        //create buffer for the normals
        bb = ByteBuffer.allocateDirect(m_normals.length*4);
        bb.order(ByteOrder.nativeOrder());

        m_normalsBuffer = bb.asFloatBuffer();
        //add the normals data to the normals buffer
        m_normalsBuffer.put(m_normals);
        //set buffer position to zero
        m_normalsBuffer.position(0);

        //create index buffer
        bb = ByteBuffer.allocateDirect(m_indices.length*2);
        bb.order(ByteOrder.nativeOrder());

        m_indexBuffer = bb.asShortBuffer();
        m_indexBuffer.put(m_indices);
        m_indexBuffer.position(0);

        //create buffer for the texture coordinates
        //bb = ByteBuffer.allocateDirect(m_uvs.length*4);
      //  bb.order(ByteOrder.nativeOrder());

      //  m_uvsBuffer = bb.asFloatBuffer();
        //put the uv data in the uv buffer
        //m_uvsBuffer.put(m_uvs);
        //set the buffer position to 0
       // m_uvsBuffer.position(0);
    }

    public FloatBuffer getVertexBuffer()
    {
        return m_vertexBuffer;
    }

    private void readOBJModel(float[] vertices,String modelAddres,AssetManager manager)
    {
        String bitmapName = "";
        try {
            ArrayList<float[]> vertexData = new ArrayList<>();
            ArrayList<Integer> indexData = new ArrayList<>();
            ArrayList<float[]> normalsData = new ArrayList<>();
            ArrayList<float[]> textureData = new ArrayList<>();
            ArrayList<Integer> normalsIndexData = new ArrayList<>();
            ArrayList<Integer> textureIndexData = new ArrayList<>();

            InputStream inputStream = manager.open(modelAddres);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            String args[];
            while ((line = reader.readLine()) != null)
            {
                args = line.split(" ");

                if(args[0].equals("v"))
                {
                    //add new vertex
                    float vertex[] = new float[COORDS_PER_VERTEX];

                    vertex[0] = Float.parseFloat(args[1]);
                    vertex[1] = Float.parseFloat(args[2]);
                    vertex[2] = Float.parseFloat(args[3]);
                    vertexData.add(vertex);
                }
                else if(args[0].equals("vt"))
                {
                    //read new texture
//                    float texture[] = new float[2];
//
//                    texture[0] = Float.parseFloat(args[1]);
//                    texture[1] = Float.parseFloat(args[2]);
//
//                    textureData.add(texture);

                }
                else if(args[0].equals("vn"))
                {
                    //read new normal
                    float normal[] = new float[3];

                    normal[0] = Float.parseFloat(args[1]);
                    normal[1] = Float.parseFloat(args[2]);
                    normal[2] = Float.parseFloat(args[3]);

                    normalsData.add(normal);
                }
                else if(args[0].equals("f"))
                {
                    //get first face point
                    String[] face = args[1].split("/");
                    //read vertex
                    int index = Integer.parseInt(face[0])-1;
                    indexData.add(index);
                    //read texture index data
//                    index = Integer.parseInt(face[1]) -1;
//                    textureIndexData.add(index);
                    //read normal index data
                    index = Integer.parseInt(face[2])-1;
                    normalsIndexData.add(index);

                    //get second face point
                    face = args[2].split("/");
                    //read vertex
                    index = Integer.parseInt(face[0])-1;
                    indexData.add(index);
                    //read texture index data
//                    index = Integer.parseInt(face[1]) -1;
//                    textureIndexData.add(index);
                    //read normal index data
                    index = Integer.parseInt(face[2])-1;
                    normalsIndexData.add(index);


                    //get third face point
                    face = args[3].split("/");
                    //read vertex
                    index = Integer.parseInt(face[0])-1;
                    indexData.add(index);
                    //read texture index data
//                    index = Integer.parseInt(face[1]) -1;
//                    textureIndexData.add(index);
                    //read normal index data
                    index = Integer.parseInt(face[2])-1;
                    normalsIndexData.add(index);
                }
                else if(args[0].equals("texture"))
                {
                    //store the ships texture name
                    bitmapName = args[1];
                }
            }
            ArrayList<Float> verticies = new ArrayList<>();
            ArrayList<Float> normals = new ArrayList<>();
            ArrayList<Float> textures = new ArrayList<>();
            //finished parsing file so read in vetex data
            for(int i = 0;i < indexData.size();i++ )
            {
                int index = indexData.get(i);
                float[] vertex = vertexData.get(index);
                verticies.add(vertex[0]);
                verticies.add(vertex[1]);
                verticies.add(vertex[2]);
                //read texture
//                index = textureIndexData.get(i);
//                float[] texture;
//                texture = textureData.get(index);
//                textures.add(texture[0]);
//                textures.add(texture[1]);
                //read normal
                index = normalsIndexData.get(i);
                float[] normal = normalsData.get(index);
                normals.add(normal[0]);
                normals.add(normal[1]);
                normals.add(normal[2]);
            }

            m_vertices = new float[verticies.size()];
            for(int i = 0;i < m_vertices.length;i++)
            {
                m_vertices[i] = verticies.get(i);
            }

//            m_uvs = new float[textures.size()];
//            for(int i = 0;i < m_uvs.length;i++)
//            {
//                m_uvs[i] = textures.get(i);
//            }

            m_normals = new float[normals.size()];
            for(int i = 0;i < m_normals.length;i++)
            {
                m_normals[i] = normals.get(i);
            }

//            inputStream = manager.open("Textures/" + bitmapName);
//            m_bitmap = BitmapFactory.decodeStream(inputStream);
            m_indices= new short[indexData.size()];
            for(int i = 0;i < m_indices.length;i++)
            {
                m_indices[i] =indexData.get(i).shortValue();
            }


        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }

    }

    public int getVertexCount()
    {
        return m_vertexCount;
    }

    public Bitmap getBitmap()
    {
        return m_bitmap;
    }

    public FloatBuffer getUVsBuffer()
    {
        return m_uvsBuffer;
    }

    public FloatBuffer getNormalsBuffer()
    {
        return m_normalsBuffer;
    }

    public ShortBuffer getIndexBuffer(){
        return m_indexBuffer;
    }


}
