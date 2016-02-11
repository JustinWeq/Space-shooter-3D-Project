package jeremyred.spaceshooter3dproject;

/**
 * Created by jeremy on 2/10/2016.
 */
public class GameSettings {
    private int m_musicVolume;
    private int m_SFXVolume;
    private boolean m_controllerEnabled;
    private boolean m_lightingEnabled;
    private boolean m_AAEnabled;
    private int m_brightness;
    private boolean m_alphaBlendingEnabled;
    private int m_controllerSensitivity;
    private static GameSettings m_gameSettings;

    private GameSettings(int musicVolume,int sfxVolume,boolean controllerEnabled,boolean lightingEnabled,boolean aaEnabled,int brightness,
                        boolean alphaBlendingEnabled,int controllerSensitivity)
    {
        m_musicVolume = musicVolume;
        m_SFXVolume = sfxVolume;
        m_controllerEnabled = controllerEnabled;
        m_lightingEnabled = lightingEnabled;
        m_AAEnabled = aaEnabled;
        m_brightness = brightness;
        m_alphaBlendingEnabled = alphaBlendingEnabled;
        m_controllerSensitivity = controllerSensitivity;
    }

    private GameSettings()
    {
        m_musicVolume = 100;
        m_SFXVolume = 100;
        m_controllerEnabled = true;
        m_lightingEnabled = true;
        m_AAEnabled = true;
        m_brightness = 100;
        m_alphaBlendingEnabled = true;
        m_controllerSensitivity = 100;
    }

    public int getMusicVolume()
    {
        return m_musicVolume;
    }

    public void setMusicVolume(int volume)
    {
        m_musicVolume = volume;
    }

    public void setSFXVolume(int volume)
    {
        m_SFXVolume = volume;
    }

    public int getSFXVolume()
    {
        return m_SFXVolume;
    }

    public boolean getControllerEnabled()
    {
        return m_controllerEnabled;
    }

    public void setControllerEnabled(boolean controllerEnabled)
    {
        m_controllerEnabled = controllerEnabled;
    }

    public boolean getLightingEnabled()
    {
        return m_lightingEnabled;
    }

    public void setLightingEnabled(boolean lightingEnabled)
    {
        m_lightingEnabled = lightingEnabled;
    }

    public void setAAEnabled(boolean aaEnabled)
    {
        m_AAEnabled = aaEnabled;
    }

    public boolean getAAEnabled()
    {
        return m_AAEnabled;
    }

    public boolean getAlphaEnabled()
    {
        return m_alphaBlendingEnabled;
    }

    public void setAlphaEnabled(boolean alphaEnabled)
    {
        m_alphaBlendingEnabled = alphaEnabled;
    }

    public void setBrightness(int brightness)
    {
        m_brightness = brightness;
    }

    public int getBrightness()
    {
        return m_brightness;
    }

    public int getControllerSensitivity()
    {
        return m_controllerSensitivity;
    }

    public void setControllerSensitivity(int sensitivity)
    {
        m_controllerSensitivity = sensitivity;
    }

    public static GameSettings getGameSettings()
    {
        if(m_gameSettings == null)
        {
            m_gameSettings = new GameSettings();
        }

        return m_gameSettings;
    }




}
