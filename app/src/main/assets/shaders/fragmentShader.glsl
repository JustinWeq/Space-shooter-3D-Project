precision mediump float;
uniform sampler2D uTexture;
uniform vec4 ambientColor;
uniform vec4 diffuseColor;
uniform mat4 uMMatrix;
uniform vec3 uCameraPosition;
uniform vec3 lightDirection;
uniform float specularPower;
uniform vec4 specularColor;
uniform float uAdd;
varying vec4 vNormal;
varying vec2 vTexCoord;
varying vec4 viewDirection;
void main() 
{
		vec4 textureColor;
		vec4 color;
		vec3 lightDir;
		float lightIntensity;
		vec2 newTex = vTexCoord;
		newTex.y = vTexCoord.y+uAdd;

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
