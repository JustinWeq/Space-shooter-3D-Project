package jeremyred.spaceshooter3dproject.Graphics;

import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

import jeremyred.spaceshooter3dproject.Managers.GameSettings;

/**
 * A class that implements GLConfig that is intended to define the parameters for a EGL context
 */
public class GLConfig implements GLSurfaceView.EGLConfigChooser {
    @Override
    /**
     * chooses the configuguration for GLES
     */
    public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
        int a[];
        if(GameSettings.getGameSettings().getAAEnabled()) {
             int attribs[] = {EGL10.EGL_RED_SIZE, 8, EGL10.EGL_GREEN_SIZE, 8,
                    EGL10.EGL_BLUE_SIZE, 8, EGL10.EGL_RENDERABLE_TYPE, 4,
                    EGL10.EGL_SAMPLE_BUFFERS, 1,
                    EGL10.EGL_SAMPLES, 4,
                    EGL10.EGL_DEPTH_SIZE, 16, EGL10.EGL_NONE};
            a = attribs;
        }
        else
        {
            int attribs[] = {EGL10.EGL_RED_SIZE, 8, EGL10.EGL_GREEN_SIZE, 8,
                    EGL10.EGL_BLUE_SIZE, 8, EGL10.EGL_RENDERABLE_TYPE, 4,
                    EGL10.EGL_SAMPLE_BUFFERS, 1,
                    EGL10.EGL_SAMPLES, 1,
                    EGL10.EGL_DEPTH_SIZE, 16, EGL10.EGL_NONE};
            a = attribs;
        }



        EGLConfig[] configs = new EGLConfig[1];
        int[] configCounts = new int[1];
        egl.eglChooseConfig(display, a, configs, 1, configCounts);

        if (configCounts[0] == 0) {
            // Failed! Error handling.
            Log.d("Log", "Bad OGL config");
            return null;
        } else {
            return configs[0];
        }

    }
}
