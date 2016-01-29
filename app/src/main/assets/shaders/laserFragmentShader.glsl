uniform vec4 uColor;
uniform sampler2D uTexture;

varying vec3 vTexCoord;

void main()
{
 vec4 textureColor = texture2D(uTexture,vTexCoord);
 
 gl_FragColor = textureColor*uColor;
}
