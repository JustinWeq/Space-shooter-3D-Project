package jeremyred.spaceshooter3dproject.Managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import jeremyred.spaceshooter3dproject.MainMenuActivity;
import jeremyred.spaceshooter3dproject.Misc.MathHelper;

/**
 * a class that help manage the data base
 * @author jeremy red
 * @version 3/1/2016
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    /**
     * the name of the data base
     */
    private final static String DB_NAME = "GameDataDB";

    private final static String TABLE_NAME = "GameData";
    /**
     * the data base version
     */
    private final static int DB_VERSOIN = 1;
    /**
     * teh location of the sql script
     */
    private final String ON_CREATE_SCRIPT = "SQLScripts/onCreate.sql";

    /**
     * the id of the save table
     */
    private int m_id;

    /**
     * overloaded constructor creates a new instance of DataBaseHelper with overloaded parameters
     * @param context the context
     */
    public DataBaseHelper(Context context)

    {
        super(context,DB_NAME,null,DB_VERSOIN);
        loadSave();
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
    /**
     * activated upon upgrade
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * updates the save file in the data base
     */
    public void updateSave()
    {
        SQLiteDatabase db = null;
        GameSettings settings = GameSettings.getGameSettings();
        try
        {
            db =  this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("musicVolume",settings.getMusicVolume());
            values.put("sfxVolume",settings.getSFXVolume());
            values.put("controllerEnabled",MathHelper.INT(settings.getControllerEnabled()));
            values.put("lightingEnabled",MathHelper.INT(settings.getLightingEnabled()));
            values.put("aaEnabled",MathHelper.INT(settings.getAAEnabled()));
            values.put("brightness",settings.getBrightness());
            values.put("controllerSensitivity",settings.getControllerSensitivity());
            values.put("playerPos",settings.getPlayerPos());
            values.put("currentLevel",settings.getCurrentLevel());
            int num = db.update(TABLE_NAME,values,"_id = ?",new String[]{String.valueOf(m_id)});
        }
        catch (Exception ex)
        {
            Log.e("Data Base Error","Failed to create data base table :" +ex.getMessage());
        }
        finally {
            if(db != null && db.isOpen())
                db.close();
        }
    }

    /**
     * loads the save file in the data base
     */
    public void loadSave()
    {
        //load a data base data if it exists
        SQLiteDatabase db = null;

        Cursor cursor = null;
        try
        {
            db = getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
            if(cursor.moveToFirst())
            {
                GameSettings settings = GameSettings.getGameSettings();
                m_id = cursor.getInt(0);
                settings.setMusicVolume(cursor.getInt(1));
                settings.setSFXVolume(cursor.getInt(2));
                settings.setControllerEnabled(MathHelper.BOOL(cursor.getInt(3)));
                settings.setLightingEnabled(MathHelper.BOOL(cursor.getInt(4)));
                settings.setAAEnabled(MathHelper.BOOL(cursor.getInt(5)));
                settings.setBrightness(cursor.getInt(6));
                settings.setControllerSensitivity(cursor.getInt(7));
                settings.setPlayerPos(cursor.getInt(8));
                settings.setCurrentLevel(cursor.getInt(9));
            }
            else
            {
                db.close();
                //data base does not exist so create one
                addTable();
            }
        }
        catch (Exception ex)
        {
            Log.e("Data base error","data base failed to load :"+ ex.getMessage());
        }
        finally {
            if(db != null && db.isOpen())
                db.close();
        }
    }

    /**
     * adds a table to the data base
     */
    public void addTable() {
        GameSettings settings = GameSettings.getGameSettings();
        SQLiteDatabase db = null;
        try {
            int id;
            db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("musicVolume", settings.getMusicVolume());
            values.put("sfxVolume", settings.getSFXVolume());
            values.put("controllerEnabled", settings.getControllerEnabled());
            values.put("lightingEnabled", settings.getLightingEnabled());
            values.put("aaEnabled", settings.getAAEnabled());
            values.put("brightness", settings.getBrightness());
            values.put("controllerSensitivity", settings.getControllerSensitivity());
            values.put("playerPos", settings.getPlayerPos());
            values.put("currentLevel", settings.getCurrentLevel());
            id = (int) db.insert(TABLE_NAME, null, values);
            m_id = id;
        } catch (Exception ex) {
            Log.e("Data Base Error", "Failed to create data base table :" + ex.getMessage());
        } finally {
            if (db != null && db.isOpen())
                db.close();
        }
    }
}
