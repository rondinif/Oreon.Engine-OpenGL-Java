package engine.shadersamples.computing;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import engine.shadersamples.Shader;
import engine.texturing.Texture;
import engine.utils.ResourceLoader;

public class NormalMapShader extends Shader{

private static NormalMapShader instance = null;

	public static NormalMapShader getInstance() 
	{
	    if(instance == null) 
	    {
	    	instance = new NormalMapShader();
	    }
	      return instance;
	}
	
	protected NormalMapShader()
	{
		super();
		
		addComputeShader(ResourceLoader.loadShader("shaders/computing/NormalMap.glsl"));
		compileShader();
	
		addUniform("displacementmap");
		addUniform("N");
		addUniform("normalStrength");
	}
	
	public void updateUniforms(Texture heightmap, int N, float strength)
	{
		glActiveTexture(GL_TEXTURE0);
		heightmap.bind();
		setUniformi("displacementmap", 0);
		setUniformi("N", N);
		setUniformf("normalStrength", strength);
	}
}
