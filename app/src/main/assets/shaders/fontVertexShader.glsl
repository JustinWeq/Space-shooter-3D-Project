uniform sampler2D uTexture;
attribute vec4 aPos;
attribute vec2 aTexCoord;
uniform mat4 uMVPMatrix;
varying vec2 vTexCoord;

void main()
{
	vTexCoord = aTexCoord;
	mat4 test = uMVPMatrix;
	gl_Position = aPos;
}