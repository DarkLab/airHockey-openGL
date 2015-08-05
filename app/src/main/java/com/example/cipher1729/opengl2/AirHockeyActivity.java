package com.example.cipher1729.opengl2;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;


public class AirHockeyActivity extends Activity {
    private GLSurfaceView mSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSurfaceView = new GLSurfaceView(this);
        mSurfaceView.setEGLContextClientVersion(2);
        mSurfaceView.setRenderer(new AirHockeyRenderer(this));
        setContentView(mSurfaceView);
    }
}
