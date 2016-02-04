uniform mat4 uMMatrix;
uniform mat4 uVMatrix;
uniform mat4 uPMatrix;
uniform vec3 uCameraPosition;
attribute vec4 vPosition;
attribute vec4 aNormal;
attribute vec2 aTexCoord;

varying vec4 vNormal;
varying vec2 vTexCoord;
varying vec4 viewDirection;
void main() {
 vec4 worldPosition = vPosition*uMMatrix;
 vNormal = aNormal;
 vTexCoord = aTexCoord;
 vNormal = vNormal*uMMatrix;
 normalize(vNormal);
 viewDirection = vec4(uCameraPosition,1)-worldPosition;
 normalize(viewDirection);
 gl_Position = uPMatrix*uVMatrix*uMMatrix*vPosition;
}