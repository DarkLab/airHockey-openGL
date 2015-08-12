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

    public ColorShaderProgram(Context context)
    {
        super(context,R.raw.texture_vertex_shader,R.raw.texture_fragment_shader);
        uMatrixLocation = GLES20.glGetUniformLocation(program,U_MATRIX);
        aPositionLocation = GLES20.glGetUniformLocation(program,A_POSITION);
        aColorLocation = GLES20.glGetUniformLocation(program,A_COLOR);
    }

    public void setUniforms(float[] matrix) {
// Pass the matrix into the shader program.
        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
    }
    public int getPositionAttributeLocation() {
        return aPositionLocation;
    }
    public int getColorAttributeLocation() {
        return aColorLocation;
    }


}
