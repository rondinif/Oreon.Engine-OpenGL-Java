package engine.shadersamples.motionblur;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import engine.core.OpenGLDisplay;
import engine.math.Matrix4f;
import engine.shadersamples.Shader;
import engine.texturing.Texture;
import engine.utils.ResourceLoader;

public class PixelVelocityShader extends Shader{
	
	private static PixelVelocityShader instance = null;
	
	public static PixelVelocityShader getInstance() 
	{
	    if(instance == null) 
	    {
	    	instance = new PixelVelocityShader();
	    }
	      return instance;
	}
	
	protected PixelVelocityShader()
	{
		super();
		
		addComputeShader(ResourceLoader.loadShader("shaders/computing/motionBlur/PixelVelocity.glsl"));
		
		compileShader();
		
		addUniform("depthmap");
		addUniform("windowWidth");
		addUniform("windowHeight");
		addUniform("projectionMatrix");
		addUniform("inverseViewProjectionMatrix");
		addUniform("previousViewProjectionMatrix");
	}
	
	public void sendUniforms(Matrix4f projectionMatrix, Matrix4f inverseViewProjectionMatrix, Matrix4f previousViewProjectionMatrix, Texture depthmap)
	{
		glActiveTexture(GL_TEXTURE0);
		depthmap.bind();
		setUniformi("depthmap", 0);
		setUniformf("windowWidth", OpenGLDisplay.getInstance().getWidth());
		setUniformf("windowHeight", OpenGLDisplay.getInstance().getHeight());
		setUniform("projectionMatrix", projectionMatrix);
		setUniform("inverseViewProjectionMatrix", inverseViewProjectionMatrix);
		setUniform("previousViewProjectionMatrix", previousViewProjectionMatrix);
	}
}
