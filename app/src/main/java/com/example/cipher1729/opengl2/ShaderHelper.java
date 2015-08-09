package com.example.cipher1729.opengl2;

import android.opengl.GLES20;
import android.util.Log;

import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glLinkProgram;

/**
 * Created by cipher1729 on 7/31/2015.
 */
public class ShaderHelper {
    private static final String TAG = "ShaderHelper";

    public static int compileVertexShader(String shaderCode) {
        return compileShader(GLES20.GL_VERTEX_SHADER, shaderCode);
    }

    public static int compileFragmentShader(String shaderCode) {
        return compileShader(GLES20.GL_FRAGMENT_SHADER, shaderCode);
    }

    private static int compileShader(int type, String shaderCode) {
        final int shaderObjectId = GLES20.glCreateShader(type);
        if (shaderObjectId == 0) {
            if (LoggerConfig.ON) {
                Log.w(TAG, "Could not create new shader.");
            }
            return 0;
        }
        GLES20.glShaderSource(shaderObjectId, shaderCode);
        GLES20.glCompileShader(shaderObjectId);
        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shaderObjectId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
        if (LoggerConfig.ON) {
// Print the shader info log to the Android log output.
            Log.v(TAG, "Results of compiling source:" + "\n" + shaderCode + "\n:"
                    + GLES20.glGetShaderInfoLog(shaderObjectId));
        }

        if (compileStatus[0] == 0) {
            GLES20.glDeleteShader(shaderObjectId);
            if (LoggerConfig.ON) {
                Log.w(TAG, "Compilation of shader failed.");
            }
            return 0;
        }


        return shaderObjectId;

    }

    public static int linkProgram(int vertexShaderId, int fragmentShaderId) {
        final int programId = GLES20.glCreateProgram();
        if (programId == 0) {
            if (LoggerConfig.ON) {
                Log.w(TAG, "Could not create new program");
            }
            return 0;
        }
        glAttachShader(programId, vertexShaderId);
        glAttachShader(programId, fragmentShaderId);
        glLinkProgram(programId);
        final int[] linkStatus = new int[1];
        glGetProgramiv(programId, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (LoggerConfig.ON) {
// Print the program info log to the Android log output.
            Log.v(TAG, "Results of linking program:\n"
                    + GLES20.glGetProgramInfoLog(programId));
        }
        if (linkStatus[0] == 0) {
// If it failed, delete the program object.
            GLES20.glDeleteProgram(programId);
            if (LoggerConfig.ON) {
                Log.w(TAG, "Linking of program failed.");
            }
            return 0;
        }

        return programId;
    }

    public static boolean validateProgram(int programId)

    {
        GLES20.glValidateProgram(programId);
        final int[] validateStatus = new int[1];
        glGetProgramiv(programId, GLES20.GL_VALIDATE_STATUS, validateStatus, 0);
        Log.v(TAG, "Results of validating program: " + validateStatus[0]
                + "\nLog:" + GLES20.glGetProgramInfoLog(programId));
        return validateStatus[0] != 0;
    }

    public static int buildProgram(String vertexShaderSource,
                                   String fragmentShaderSource) {

        int vertexProgramId = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        int fragmentProgramId= GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(vertexProgramId,vertexShaderSource);
        GLES20.glShaderSource(fragmentProgramId,fragmentShaderSource);
        GLES20.glCompileShader(vertexProgramId);
        GLES20.glCompileShader(fragmentProgramId);
        final int programId = GLES20.glCreateProgram();
        GLES20.glAttachShader(programId,vertexProgramId);
        GLES20.glAttachShader(programId,fragmentProgramId);
        GLES20.glLinkProgram(programId);


        return programId;
    }

    }

