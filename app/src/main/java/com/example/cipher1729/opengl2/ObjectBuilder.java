package com.example.cipher1729.opengl2;

import android.opengl.GLES20;
import android.util.FloatMath;

import java.util.ArrayList;

/**
 * Created by cipher1729 on 8/16/2015.
 */
public class ObjectBuilder {
    private static final int FLOATS_PER_VERTEX =3;
    private final float[] vertexData;
    private int offset=0;
    private final ArrayList<DrawCommand> drawList;
    private ObjectBuilder(int sizeInVertices)
    {
        vertexData = new float[sizeInVertices* FLOATS_PER_VERTEX];
        drawList = new ArrayList<DrawCommand>();
    }

    private static int sizeOfCircleInVertices(int numPoints) {
        return 1 + (numPoints + 1);
    }
    private static int sizeOfOpenCylinderInVertices(int numPoints) {
        return (numPoints + 1) * 2;
    }

    static class GeneratedData
    {
        final float[] vertexData;
        final ArrayList<DrawCommand> drawList;
        GeneratedData(float[] vertexData, ArrayList<DrawCommand> drawList) {
            this.vertexData = vertexData;
            this.drawList = drawList;
        }
    }

    static GeneratedData CreatePuck(Geometry.Cylinder puck,final int numPoints)
    {
        int size = sizeOfCircleInVertices(numPoints) + sizeOfOpenCylinderInVertices(numPoints);
        ObjectBuilder objectBuilder = new ObjectBuilder(size);
        Geometry.Circle puckTop = new Geometry.Circle(puck.radius, puck.center.translateY(puck.height/2f));
        objectBuilder.appendCircle(puckTop, numPoints);
        objectBuilder.appendOpenCylinder(puck, numPoints);
        return objectBuilder.build();
    }

    private void appendOpenCylinder(Geometry.Cylinder cylinder, final int numPoints) {
        final int startVertex = offset / FLOATS_PER_VERTEX;
        final int numVertices = sizeOfOpenCylinderInVertices(numPoints);
        final float yStart = cylinder.center.y - (cylinder.height / 2f);
        final float yEnd = cylinder.center.y + (cylinder.height / 2f);

        float angleInRadians= (float) (2 * Math.PI / numPoints);
        for(int i=0;i<=numPoints;i++)
        {
            float tempx= (float) ((float)(cylinder.center.x)+ (float)(cylinder.radius) + i*Math.cos(angleInRadians));
            vertexData[offset++]= tempx;
            vertexData[offset++]= yStart;
            float tempz= (float) ((float)(cylinder.center.x)+ (float)(cylinder.radius) + i*Math.sin(angleInRadians));
            vertexData[offset++]= tempz;

            vertexData[offset++]= tempx;
            vertexData[offset++]= yEnd;
            vertexData[offset++]= tempz;
            drawList.add(new DrawCommand() {
                @Override
                public void draw() {
                    GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP,startVertex ,sizeOfCircleInVertices(numPoints));
                }
            });
        }
    }

    private void appendCircle(Geometry.Circle puckTop, final int numPoints) {
        final int startVertex = offset / FLOATS_PER_VERTEX;
        //fill up the 3 points of the vertex with the center x y z coordinates
        vertexData[offset++]= puckTop.center.x;
        vertexData[offset++]= puckTop.center.y;
        vertexData[offset++]= puckTop.center.z;
        float angleInRadians= (float) (2 * Math.PI / numPoints);
        for(int i=0;i<=numPoints;i++)
        {
            float tempx= (float) ((float)(puckTop.center.x)+ (float)(puckTop.radius) + i*Math.cos(angleInRadians));
            vertexData[offset++]= tempx;
            vertexData[offset++]= puckTop.center.y;
            float tempz= (float) ((float)(puckTop.center.x)+ (float)(puckTop.radius) + i*Math.sin(angleInRadians));
            vertexData[offset++]= tempz;
        }
        drawList.add(new DrawCommand() {
            @Override
            public void draw() {
                GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN,startVertex ,sizeOfCircleInVertices(numPoints));
            }
        });
    }

    static interface DrawCommand
    {
        void draw();
    }
    private GeneratedData build() {
        return new GeneratedData(vertexData, drawList);
    }



}
