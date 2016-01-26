uniform mat4 uMVPMatrix;
uniform mat4 uMMatrix;
uniform mat4 uVMatrix;
uniform mat4 uPMatrix;
attribute vec4 vPosition;
attribute vec4 aNormal;
attribute vec2 aTexCoord;

varying vec4 vNormal;
varying vec2 vTexCoord;
void main() {
 gl_Position = uPMatrix*uVMatrix*uMMatrix*vPosition;
 vNormal = aNormal;
 vTexCoord = aTexCoord;
}