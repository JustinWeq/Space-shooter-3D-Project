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
	gl_Position = uMMatrix*vec4(vPosition.rgb,1.0);
	gl_Position = uVMatrix*gl_Position;
	gl_Position = uPMatrix*gl_Position;

	vTexCoord = aTexCoord;
	vNormal = uMMatrix*aNormal;
	vNormal = normalize(vNormal);
}