package jeremyred.spaceshooter3dproject.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import jeremyred.spaceshooter3dproject.MainMenuActivity;

/**
 * Created by jeremy on 2/22/2016.
 */
public class TextRenderer {

    private String m_text;
    private Bitmap m_bitmap;
    private boolean m_needsGenerated;
    private int m_vertexCount;
    private FloatBuffer m_vertexBuffer;
    private FloatBuffer m_uvBuffer;

    public TextRenderer()
    {
        m_text = "Test";
        m_needsGenerated = true;
        //generate model
        setUpModel();

        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        m_bitmap =Bitmap.createBitmap(MainMenuActivity.getScreenWidth(),MainMenuActivity.getScreenHeight(),config);
    }

    public void generateBitmap()
    {
        m_bitmap.recycle();
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        m_bitmap =Bitmap.createBitmap(MainMenuActivity.getScreenWidth(),MainMenuActivity.getScreenHeight(),config);

        Canvas canvas  = new Canvas(m_bitmap);
        canvas.clipRect(new Rect(0,0,m_bitmap.getWidth(),m_bitmap.getHeight()));

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.rgb(255,255,255));
        paint.setTextSize(16);

        paint.setShadowLayer(1f,0,1f,Color.WHITE);

        //draw the new text
        canvas.drawText(m_text,0,0,paint);

        m_needsGenerated = false;
    }

    public Bitmap getBitmap()
    {
        if(m_needsGenerated)
        {
            generateBitmap();
        }
        return m_bitmap;
    }

    public void setText(String text)
    {
        m_text = text;
        m_needsGenerated = true;
    }

    private void setUpModel()
    {
        //set the vertices
        float[] vertices=
        {
            //upper left corner
                -0.5f,0.5f,0,

                //upper right corner
                0.5f,0.5f,0,

                //lower left corner
                -0.5f,00.5f,0,

                //upper right corner
                0.5f,0.5f,0,

                //lower riht corner
                0.5f,-0.5f,0,

                //lower left corner,
                -0.5f,-0.5f,0
        };
        //set up uvs
        float[] uvs=
        {
            //upper left corner
                0,0,

                //upper right corner
                1,0,

                //lower left corner
                0,1,

                //upper right corner
                1,0,

                //lower right corner
                1,1,

                //lower left corner
                0,1
        };


        //set up the two buffers

        //set up the vertex buffer
        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length*4);

        bb.order(ByteOrder.nativeOrder());

        m_vertexBuffer = bb.asFloatBuffer();

        m_vertexBuffer.put(vertices);

        m_vertexBuffer.position(0);

        //set up the uv buffer
        bb = ByteBuffer.allocateDirect(uvs.length*4);

        bb.order(ByteOrder.nativeOrder());

        m_uvBuffer = bb.asFloatBuffer();

        m_uvBuffer = bb.asFloatBuffer();

        m_uvBuffer.put(uvs);

        m_uvBuffer.position(0);
    }

    public FloatBuffer getVertexBuffer()
    {
        return m_vertexBuffer;
    }

    public FloatBuffer getUVBuffer()
    {
        return m_uvBuffer;
    }
}
