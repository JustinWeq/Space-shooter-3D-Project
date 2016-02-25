package jeremyred.spaceshooter3dproject.Managers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import jeremyred.spaceshooter3dproject.MainMenuActivity;

/**
 * Created by jeremy on 2/21/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "GameDataDB";
    private final static int DB_VERSOIN = 1;
    private final String ON_CREATE_SCRIPT = "SQLScripts/onCreate.sql";

    public DataBaseHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSOIN);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            InputStream is = MainMenuActivity.MANAGER.open(ON_CREATE_SCRIPT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;

            StringBuilder builder = new StringBuilder();
            while((line = reader.readLine())!= null)builder.append(line);

            db.execSQL(builder.toString());

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
