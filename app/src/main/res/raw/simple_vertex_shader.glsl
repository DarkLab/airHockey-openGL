attribute vec4 a_Position;

uniform mat4 u_Matrix;

void main()
{

    gl_Position = a_Position * u_Matrix;

}