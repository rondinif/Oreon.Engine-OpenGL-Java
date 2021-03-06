#version 430 core

layout (local_size_x = 8, local_size_y = 8) in;

layout (binding = 0, rgba32f) uniform writeonly image2D velocitymap;

uniform sampler2D depthmap;
uniform float windowWidth;
uniform float windowHeight;
uniform mat4 projectionMatrix;
uniform mat4 inverseViewProjectionMatrix;
uniform mat4 previousViewProjectionMatrix;

float zfar = 10000.0f;
float znear = 0.1f;

float linearize(float depth)
{
	return (2 * znear) / (zfar + znear - depth * (zfar - znear));
	// return (2*depth - zfar - znear) / (zfar - znear) - 1;
}

void main(void){
	
	vec2 texCoord = vec2(gl_GlobalInvocationID.x/windowWidth, gl_GlobalInvocationID.y/windowHeight);
	
	// Get the depth buffer value at this pixel.  
	float depth = texture(depthmap, texCoord).r ; 
	
	// window coords
	vec2 W = vec2(gl_GlobalInvocationID.x/windowWidth, gl_GlobalInvocationID.y/windowHeight);
	
	//ndc coords
	vec3 N = vec3(W.x * 2 - 1, W.y * 2 - 1, depth);
	
	float Cw = projectionMatrix[2][3] / (N.z - projectionMatrix[2][2]); 
	vec3 clip = N * Cw;
	vec4 clipPos = vec4(clip,Cw);

	vec4 worldPos =  inverseViewProjectionMatrix * clipPos;
 	
	vec4 previousPos = previousViewProjectionMatrix * worldPos;
	previousPos /= previousPos.w;
	
	//window space velocity
	vec2 velocity = (previousPos.xy - N.xy).xy * 200;

	imageStore(velocitymap, ivec2(gl_GlobalInvocationID.xy), vec4(velocity,0,1));
}