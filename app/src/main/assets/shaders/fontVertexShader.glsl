uniform mat4 uMVPMatrix;
attribute vec4 aPos;
attribute vec2 aTexCoord;

varying vec2 vTexCoord;

void main()
{
	vTexCoord = aTexCoord;
	glPosition = aPos*uMVPMatrix;
}