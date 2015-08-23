package com.example.cipher1729.opengl2;

import android.content.Context;
import android.opengl.GLES20;

/**
 * Created by cipher1729 on 8/11/2015.
 */
public class ColorShaderProgram extends ShaderProgram{

    //Uniform locations
    private final int uMatrixLocation;
    //Attribute locations
    private int aPositionLocation;
    private int aColorLocation;
    private int uColorLoation;

    public ColorShaderProgram(Context context)
    {
        super(context,R.raw.simple_vertex_shader,R.raw.simple_fragment_shader);
        uMatrixLocation = GLES20.glGetUniformLocation(program,U_MATRIX);
        aPositionLocation = GLES20.glGetAttribLocation(program,A_POSITION);
        aColorLocation = GLES20.glGetAttribLocation(program,A_COLOR);
        uColorLoation =GLES20.glGetUniformLocation(program,U_COLOR);
    }

    public void setUniforms(float[] matrix, float r, float g, float b) {
// Pass the matrix into the shader program.
        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
        GLES20.glUniform4f(uColorLoation,r,g,b,1f);
    }
    public int getPositionAttributeLocation() {
        return aPositionLocation;
    }
    public int getColorAttributeLocation() {
        return aColorLocation;
    }


}
