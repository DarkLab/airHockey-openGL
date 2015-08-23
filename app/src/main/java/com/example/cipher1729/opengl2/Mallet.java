package com.example.cipher1729.opengl2;

import android.opengl.GLES20;

import java.util.List;

/**
 * Created by cipher1729 on 8/8/2015.
 */
public class Mallet {

    public static int componentCount=3;
    private static final int POSITION_COMPONENT_COUNT = 3;

    float radius,height;
    VertexArray vertexArray;
    List<ObjectBuilder.DrawCommand> drawList;

    public Mallet(float radius, float height, int  numPoints)
    {   ObjectBuilder.GeneratedData generatedData = ObjectBuilder.createMallet(new Geometry.Point(0f, 0f, 0f), radius, height,numPoints);
        this.radius = radius;
        this.height = height;
        vertexArray = new VertexArray(generatedData.vertexData);
        drawList=  generatedData.drawList;
    }
    public void bindData(ColorShaderProgram colorProgram) {
        vertexArray.setVertexAttribPointer(0,
                colorProgram.getPositionAttributeLocation(),
                POSITION_COMPONENT_COUNT, 0);
    }

    public void draw() {

        for(ObjectBuilder.DrawCommand drawCommand:drawList)
            drawCommand.draw();
    }
}
