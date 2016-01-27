precision mediump float;
uniform vec4 vColor;
uniform sampler2D uTexture;
varying vec4 vNormal;
varying vec2 vTexCoord;
varying vec4 viewDirection;
void main() 
{
 vec4 color;
 color.r = vNormal.r;
 color.g = vNormal.g;
 color.b = vNormal.b;
 color.a = 1.0;
 gl_FragColor = color;
}
