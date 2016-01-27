precision mediump float;
uniform vec4 vColor;
uniform sampler2D uTexture;
uniform vec4 ambientColor;
uniform vec4 diffuseColor;
uniform vec3 lightDirection;
uniform float specularPower;
uniform vec4 specularColor;
varying vec4 vNormal;
varying vec2 vTexCoord;
varying vec4 viewDirection;
void main() 
{
 vec3 lightDir;
 float lightIntensity;
 vec3 reflection;
 vec4 specular = vec4(0.0,0.0,0.0,0.0);
 vec4 color;
 vec4 textureColor = vColor;
 color = ambientColor;
 
 lightDir = -lightDirection;
 
 lightIntensity = clamp(dot(vNormal.rgb,lightDir),0.0,1.0);
 
 if(lightIntensity > 0.0)
 {
	color+= (diffuseColor*lightIntensity);
    color = clamp(color,0.0,1.0);

	reflection = normalize(2.0*lightIntensity*vNormal.rgb-lightDir);
	
	float specularCo = pow(clamp(dot(reflection,viewDirection.rgb),0.0,1.0),specularPower);
	
	specular = specularColor*specularCo;
 }

 color = color*textureColor;

 color = clamp(color+specular,0.0,1.0);

 gl_FragColor = color;
}
