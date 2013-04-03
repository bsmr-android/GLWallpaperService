package com.learnopengles.android.lesson2;

import android.opengl.GLSurfaceView;
import android.os.Build;
import android.widget.Toast;

import com.learnopengles.glwallpaperservice.GLWallpaperService;
import com.learnopengles.glwallpaperservice.OpenGLES2Utils;

public class LessonTwoWallpaperServiceTwo extends GLWallpaperService {
	public Engine onCreateEngine() {
        return new MyGLEngine();
    }

	public class MyGLEngine extends GLEngine {
		@Override
		protected GLSurfaceView.Renderer onCreateRenderer(GLSurfaceView glSurfaceView) {
			if (OpenGLES2Utils.deviceSupportsOpenGLES2(LessonTwoWallpaperServiceTwo.this)) {            
                glSurfaceView.setEGLContextClientVersion(2);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                	// Avoid restarting the renderer when the user leaves and returns to the home screen.
                    glSurfaceView.setPreserveEGLContextOnPause(true);
                }

                return new LessonTwoRenderer();
            } else {
                Toast.makeText(LessonTwoWallpaperServiceTwo.this, 
                    "This device does not support OpenGL ES 2.0.", 
                    Toast.LENGTH_LONG).show();
                return null;
            }   
		}
	}
}
