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
 vec4 specular;
 vec4 color;
 vec4 textureColor = vColor;
 color = ambientColor;
 
 lightDir = -lightDirection;
 lightIntensity = lightDir;
 gl_FragColor = color;
}
