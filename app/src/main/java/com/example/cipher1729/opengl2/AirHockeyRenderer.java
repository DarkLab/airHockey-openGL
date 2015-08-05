package com.example.cipher1729.opengl2;

import android.content.Context;
import android.graphics.Shader;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by cipher1729 on 7/28/2015.
 */
public class AirHockeyRenderer implements GLSurfaceView.Renderer {

    private static final int POSITION_COMPONENT_COUNT =2;
    private static final int COLOR_COMPONENT_COUNT =3;
    private static final int BYTES_PER_FLOAT = 4;
    private static final int STRIDE =
            (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * BYTES_PER_FLOAT;

    private final FloatBuffer vertexData;
    private final Context context;
    String vertexShaderSource;
    String fragmentShaderSource;

    private static final String A_COLOR= "a_Color";
    private int vColorLocation;
    private static final String A_POSITION = "a_Position";
    private int aPositionLocation;

    public  AirHockeyRenderer(Context context)
    {
       float [] tableWithVertices = { // Triangle Fan
               0f,    0f,   1f,   1f,   1f,
               -0.5f, -0.5f, 0.7f, 0.7f, 0.7f,
               0.5f, -0.5f, 0.7f, 0.7f, 0.7f,
               0.5f,  0.5f, 0.7f, 0.7f, 0.7f,
               -0.5f,  0.5f, 0.7f, 0.7f, 0.7f,
               -0.5f, -0.5f, 0.7f, 0.7f, 0.7f,

               // Line 1
               -0.5f, 0f, 1f, 0f, 0f,
               0.5f, 0f, 1f, 0f, 0f,

               // Mallets
               0f, -0.25f, 0f, 0f, 1f,
               0f,  0.25f, 1f, 0f, 0f
       };
        vertexData = ByteBuffer.allocateDirect(tableWithVertices.length * BYTES_PER_FLOAT).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(tableWithVertices);
        this.context = context;
    }
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glClearColor(0.0f,0.0f,0.0f,0.0f);
        try {
           vertexShaderSource = TextResourceReader.readTextFileFromResource(context,R.raw.simple_vertex_shader);
            fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_fragment_shader);

        } catch (IOException e) {
            e.printStackTrace();
        }

        int vertexShader= ShaderHelper.compileVertexShader(vertexShaderSource);
        int fragmentShader= ShaderHelper.compileFragmentShader(fragmentShaderSource);
        int programId = ShaderHelper.linkProgram(vertexShader, fragmentShader);
        if (LoggerConfig.ON) {
            ShaderHelper.validateProgram(programId);
        }
        GLES20.glUseProgram(programId);

        vColorLocation = GLES20.glGetAttribLocation(programId,A_COLOR);
        aPositionLocation = GLES20.glGetAttribLocation(programId, A_POSITION);
        vertexData.position(0);
        GLES20.glVertexAttribPointer(aPositionLocation,POSITION_COMPONENT_COUNT,GLES20.GL_FLOAT,false, STRIDE, vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);
        vertexData.position(POSITION_COMPONENT_COUNT);
        GLES20.glVertexAttribPointer(vColorLocation,COLOR_COMPONENT_COUNT,GLES20.GL_FLOAT,false, STRIDE, vertexData);
        GLES20.glEnableVertexAttribArray(vColorLocation);

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        gl10.glViewport(0,0,i,i2);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {

        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);

        //GLES20.glUniform4f(uColorLocation,1.0f,1.0f,1.0f,1.0f);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN,0,6);
        //GLES20.glUniform4f(uColorLocation,1.0f,0.0f,0.0f,1.0f);
        GLES20.glDrawArrays(GLES20.GL_LINES,6,2);

        //GLES20.glUniform4f(uColorLocation,0.0f,0.0f,1.0f,1.0f);
        GLES20.glDrawArrays(GLES20.GL_POINTS,8,1);
        // Draw the second mallet red.
       // GLES20.glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 9, 1);

        //GLES20.glUniform4f(uColorLocation, 0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 10, 1);


    }
}
