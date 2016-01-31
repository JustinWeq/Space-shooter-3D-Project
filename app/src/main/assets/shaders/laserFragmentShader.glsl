precision mediump float;
uniform vec4 uColor;
uniform sampler2D uTexture;
uniform vec4 uBackColor;
varying vec2 vTexCoord;

void main()
{
 vec4 textureColor = texture2D(uTexture,vTexCoord);
 vec4 color = mix(uBackColor,uColor,uColor);
 gl_FragColor = textureColor*color;
}
