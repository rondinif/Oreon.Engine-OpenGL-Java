#version 330

layout (location = 0) in vec3 position0;
layout (location = 1) in vec3 normal0;

out vec4 normal_GS;

void main()
{
	gl_Position = vec4(position0, 1);
	normal_GS = vec4(normal0,1);
}




