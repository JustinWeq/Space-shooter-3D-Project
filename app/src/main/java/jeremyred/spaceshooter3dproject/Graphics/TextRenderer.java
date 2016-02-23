package jeremyred.spaceshooter3dproject.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import jeremyred.spaceshooter3dproject.Activitys.MainMenuActivity;

/**
 * Created by jeremy on 2/22/2016.
 */
public class TextRenderer {

    private String m_text;
    private Bitmap m_bitmap;
    private boolean m_needsGenerated;

    public TextRenderer()
    {
        m_text = "Test";
        m_needsGenerated = true;
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
}
