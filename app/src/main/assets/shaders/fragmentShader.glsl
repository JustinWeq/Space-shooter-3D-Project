precision mediump float;
uniform vec3 specularPower;
uniform sampler2D uTexture;
uniform vec4 ambientColor;
uniform vec4 diffuseColor;
uniform vec3 lightDirection;
varying vec4 vNormal;
varying vec2 vTexCoord;
void main() 
{
		vec4 textureColor;
		vec4 color;
		vec3 lightDir;
		float lightIntensity;
		vec2 newTex = vTexCoord;
		newTex.x += specularPower.x;


		textureColor = texture2D(uTexture,vTexCoord);

		color = ambientColor;

		lightDir = lightDirection;

		lightIntensity = clamp(dot(vNormal.rgb,lightDir),0.0,1.0);

		if(lightIntensity > 0.0)
		{
			color+=(diffuseColor*lightIntensity);
		}
		color = clamp(color,0.0,1.0);

		color = color*textureColor;

		color.a = 1.0;
		gl_FragColor = color;
}
