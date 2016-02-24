precision mediump float;
uniform sampler2D uTexture;
varying vTexCoord;

void main()
{
	vec4 color = texture2d(uTexture,vTexCoord);
	

	if(color.rgb == 0.0)
	{
		color.a = 0.0;
	}
	gl_FragColor = color;
}