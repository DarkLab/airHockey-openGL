package com.example.cipher1729.opengl2;

/**
 * Created by cipher1729 on 8/16/2015.
 */
public class Geometry {

    public static class Point
    {
        public final float x,y,z;
        public Point(float x, float y, float z)
        {
            this.x=x;
            this.y=y;
            this.z=z;
        }

        public Point translateY(float distance)
        {
            return new Point(x,y+distance, z);
        }
    }
    public static class Circle
    {
        public final float radius;
        public final Point center;

        public Circle(float radius, Point center)
        {
            this.center = center;
            this.radius = radius;
        }
        public Circle scale(float scale)
        {
            return new Circle(radius*scale,center);
        }

    }

    public static class Cylinder {
        public final Point center;
        public final float radius;
        public final float height;
        public Cylinder(Point center, float radius, float height) {
            this.center = center;
            this.radius = radius;
            this.height = height;
        }
    }

}
