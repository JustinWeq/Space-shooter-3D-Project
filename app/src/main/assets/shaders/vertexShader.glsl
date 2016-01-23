uniform mat4 uMVPMatrix;
attribute vec4 vPosition;
attribute vec4 aNormal;
attribute vec2 aTexCoord;
varying vec4 vNormal;
varying vec2 vTexCoord;
void main() {
 gl_Position = uMVPMatrix *  vPosition;
 vNormal = aNormal;
 vTexCoord = aTexCoord;
}