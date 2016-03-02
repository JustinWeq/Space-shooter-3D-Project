package jeremyred.spaceshooter3dproject.Managers;

/**
 * Ca class that manages the game settings
 */
public class GameSettings {
    /**
     * the music volume
     */
    private int m_musicVolume;
    /**
     * the SFX volume
     */
    private int m_SFXVolume;
    /**
     * a bool that indicates whether the controller is enabled or not
     */
    private boolean m_controllerEnabled;
    /**
     * indicates whether lighting is enabled or not
     */
    private boolean m_lightingEnabled;
    /**
     * indicates whether AA is enabled or not
     */
    private boolean m_AAEnabled;
    /**
     * indicates the brightness level the game is at
     */
    private int m_brightness;
    /**
     * indicates the alpha blending state
     */
    private boolean m_alphaBlendingEnabled;
    /**
     * indicates the controller sensitivity
     */
    private int m_controllerSensitivity;
    /**
     * indicates the game settings
     */
    private static GameSettings m_gameSettings;
    /**
     * indicates the players current position
     */
    private int m_playerPos;
    /**
     * indicates the current level
     */
    private int m_currentlevel;
    /**
     * indicates the default brightness
     */
    public static final int DEFAULT_BRIGHTNESS = 25;
    /**
     * indicates the default AA settting
     */
    public static final boolean DEFAULT_AA = true;
    /**
     * indicates the default music volume
     */
    public static final int DEFAULT_MUSIC_VOLUME = 100;
    /**
     * indicates the default SFC volume
     */
    public static final int DEFAULT_SFX_VOLUME = 100;
    /**
     * indicates the default light settings
     */
    public static final boolean DEFAULT_LIGHT_SETTING = true;
    /**
     * indicates the default alpha state
     */
    public static final boolean DEFAULT_ALPHASTATE = true;
    /**
     * indicates the default controller sensitivity
     */
    public static final int DEFAULT_CONTROLLER_SENSITIVITY = 100;


    /**
     * overloaded constructor creates a new instance of GameSettings with overloaded parameters
     * @param musicVolume the music volume
     * @param sfxVolume the sfx volume
     * @param controllerEnabled the controller enabled
     * @param lightingEnabled the lighting enabled
     * @param aaEnabled the AA enabled
     * @param brightness the brightness enabled
     * @param alphaBlendingEnabled the alpha blending enabled
     * @param controllerSensitivity the controller sensitivity enabled
     */
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

    /**
     * default constructor creates a new instance of GameSettings with default parameters
     */
    private GameSettings()
    {
        m_musicVolume = DEFAULT_MUSIC_VOLUME;
        m_SFXVolume = DEFAULT_SFX_VOLUME;

        m_controllerEnabled = true;
        m_lightingEnabled = DEFAULT_LIGHT_SETTING;
        m_AAEnabled = DEFAULT_AA;
        m_brightness = DEFAULT_BRIGHTNESS;
        m_alphaBlendingEnabled = DEFAULT_ALPHASTATE;
        m_controllerSensitivity = DEFAULT_CONTROLLER_SENSITIVITY;
    }

    /**
     * returns the music volume
     * @return the music volume
     */
    public int getMusicVolume()
    {
        return m_musicVolume;
    }

    /**
     * sets the music volume
     * @param volume the music volume
     */
    public void setMusicVolume(int volume)
    {
        m_musicVolume = volume;
    }

    /**
     * sets the sfx volume
     * @param volume the sfx volume
     */
    public void setSFXVolume(int volume)
    {
        m_SFXVolume = volume;
    }

    /**
     * returns the sfx volume
     * @return the sfx volume
     */
    public int getSFXVolume()
    {
        return m_SFXVolume;
    }

    /**
     * returns the controller is enabled
     * @return the controller enabled state
     */
    public boolean getControllerEnabled()
    {
        return m_controllerEnabled;
    }

    /**
     * sets the controller enabled state
     * @param controllerEnabled the controller enabled state
     */
    public void setControllerEnabled(boolean controllerEnabled)
    {
        m_controllerEnabled = controllerEnabled;
    }

    /**
     * returns the lighting enabled state
     * @return the lighting enabled state
     */
    public boolean getLightingEnabled()
    {
        return m_lightingEnabled;
    }

    /**
     * sets thge lighting enabled state
     * @param lightingEnabled the lighting enabled state
     */
    public void setLightingEnabled(boolean lightingEnabled)
    {
        m_lightingEnabled = lightingEnabled;
    }

    /**
     * sets the AA enabled state
     * @param aaEnabled the aa enabled state
     */
    public void setAAEnabled(boolean aaEnabled)
    {
        m_AAEnabled = aaEnabled;
    }

    /**
     * returns the AA enabled state
     * @return the aa enabled state
     */
    public boolean getAAEnabled()
    {
        return m_AAEnabled;
    }

    /**
     * returns the alpha enabled state
     * @return the alpha enabled
     */
    public boolean getAlphaEnabled()
    {
        return m_alphaBlendingEnabled;
    }

    /**
     * sets the alpha enabled state
     * @param alphaEnabled the alpha enabled state
     */
    public void setAlphaEnabled(boolean alphaEnabled)
    {
        m_alphaBlendingEnabled = alphaEnabled;
    }

    /**
     * sets the brightness level
     * @param brightness the brightness level
     */
    public void setBrightness(int brightness)
    {
        m_brightness = brightness;
    }

    /**
     * returns the brightness level
     * @return the brightness level
     */
    public int getBrightness()
    {
        return m_brightness;
    }

    /**
     * returns the controller sensitivity
     * @return the controller sensitivity
     */
    public int getControllerSensitivity()
    {
        return m_controllerSensitivity;
    }

    /**
     * sets the controller senstivity
     * @param sensitivity the controller sensitivity
     */
    public void setControllerSensitivity(int sensitivity)
    {
        m_controllerSensitivity = sensitivity;
    }

    /**
     * returns the public GameSettings
     * @return the public game settings
     */
    public static GameSettings getGameSettings()
    {
        if(m_gameSettings == null)
        {
            m_gameSettings = new GameSettings();
        }

        return m_gameSettings;
    }

    /**
     * retursn the players position
     * @return the player position
     */
    public int getPlayerPos()
    {
        return m_playerPos;
    }

    /**
     * returns the current level the player is on
     * @return the current level
     */
    public int getCurrentLevel()
    {
        return m_currentlevel;
    }

    /**
     * sets the current level
     * @param currentLevel the current level
     */
    public void setCurrentLevel(int currentLevel)
    {
        m_currentlevel= currentLevel;
    }

    /**
     * sets the players position
     * @param playerPos the players position
     */
    public void setPlayerPos(int playerPos)
    {
        m_playerPos = playerPos;
    }




}
