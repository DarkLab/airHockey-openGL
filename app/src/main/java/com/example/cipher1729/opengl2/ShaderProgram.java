package com.example.cipher1729.opengl2;

import android.content.Context;
import android.opengl.GLES20;

import java.io.IOException;

/**
 * Created by cipher1729 on 8/8/2015.
 */
public class ShaderProgram {
    // Uniform constants
    protected static final String U_MATRIX = "u_Matrix";
    protected static final String U_TEXTURE_UNIT = "u_TextureUnit";
    protected static final String U_COLOR= "u_Color";
    // Attribute constants
    protected static final String A_POSITION = "a_Position";
    protected static final String A_COLOR = "a_Color";
    protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";
    int program;



    protected ShaderProgram(Context context, int vertexShaderResourceId,
                            int fragmentShaderResourceId)
    {
        try {
            program = ShaderHelper.buildProgram(TextResourceReader.readTextFileFromResource(context,vertexShaderResourceId),
                    TextResourceReader.readTextFileFromResource(context, fragmentShaderResourceId));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void useProgram() {
// Set the current OpenGL shader program to this program.
        GLES20.glUseProgram(program);
    }
}
