precision mediump float;
uniform sampler2D uTexture;
varying vec2 vTexCoord;

void main()
{
	vec4 color = texture2D(uTexture,vTexCoord);
	

	if(color.rgb == 0.0)
	{
		color.a = 0.0;
	}
	color = vec4(1.0,0.0,0.0,1.0);
	gl_FragColor = vec4(1.0,0,1.0,1.0);
}