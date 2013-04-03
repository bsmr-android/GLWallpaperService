package com.learnopengles.glwallpaperservice;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

public abstract class GLWallpaperService extends WallpaperService {		
    public abstract class GLEngine extends Engine {
        private WallpaperGLSurfaceView glSurfaceView;
        private boolean rendererSet;
        
        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);            
            glSurfaceView = new WallpaperGLSurfaceView(GLWallpaperService.this);
            GLSurfaceView.Renderer renderer = onCreateRenderer(glSurfaceView);
            
            if (renderer != null) {
            	glSurfaceView.setRenderer(renderer);
            	rendererSet = true;
            }             
        }
        
        protected abstract GLSurfaceView.Renderer onCreateRenderer(GLSurfaceView glSurfaceView);

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            
            if (rendererSet) {
                if (visible) {
                    glSurfaceView.onResume();
                } else {                   
                    glSurfaceView.onPause();
                }
            }
        }           

        @Override
        public void onDestroy() {
            super.onDestroy();
            glSurfaceView.onWallpaperDestroy();
        }            

        class WallpaperGLSurfaceView extends GLSurfaceView {
            WallpaperGLSurfaceView(Context context) {
                super(context);
            }            

            @Override
            public SurfaceHolder getHolder() {
                return getSurfaceHolder();
            }

            public void onWallpaperDestroy() {
                super.onDetachedFromWindow();
            }
        }    
    }
}

