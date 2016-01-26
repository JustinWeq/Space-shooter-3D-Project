precision mediump float;
uniform vec4 vColor;
uniform sampler2D uTexture;
varying vec4 vNormal;
varying vec2 vTexCoord;
void main() 
{
 gl_FragColor = vColor*vNormal;
}
