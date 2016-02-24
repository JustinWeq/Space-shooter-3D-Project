uniform mat4 uMVPMatrix;
attribute vec4 aPosition;
attribute vec2 aTexCoord;

varying vec2 vTexCoord;

void main()
{
	vTexCoord = aTexCoord;
	glPosition = uMVPMatrix*vec4(aPosition.rgb,1.0);
}