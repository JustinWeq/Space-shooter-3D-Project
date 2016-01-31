precision mediump float;
uniform vec4 vColor;
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
 vec3 lightDir;
 float lightIntensity;
 vec2 newUV = vTexCoord;
 newUV.r = vTexCoord.r;
 vec3 reflection;
 vec4 specular = vec4(0.0,0.0,0.0,0.0);
 vec4 color;
 vec4 textureColor = texture2D(uTexture,vTexCoord);
 color = ambientColor;
 vec4 worldPos = gl_FragCoord*uMMatrix;
 vec4 viewDir = vec4(uCameraPosition,1.0)-worldPos;
 lightDir = -lightDirection;
 
 lightIntensity = clamp(dot(vNormal.rgb,lightDir),0.0,1.0);
 
 if(lightIntensity > 0.0)
 {
	color+= (diffuseColor*lightIntensity);
    color = clamp(color,0.0,1.0);

	reflection = normalize(2.0*lightIntensity*vNormal.rgb-lightDir);
	
	float specularCo = pow(clamp(dot(reflection,viewDir.rgb),0.0,1.0),specularPower);
	
	specular = specularColor*specularCo;
 }

 color = color*textureColor;

 color = clamp(color+specular,0.0,1.0);

 gl_FragColor = color;
}
