package oreonworlds.gui;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import engine.core.OpenGLDisplay;
import engine.math.Vec2f;
import engine.texturing.Texture;
import modules.gui.Button;

public class FullScreenButton  extends Button{

	private boolean isFullScreenMode = false;
	
	public FullScreenButton()
	{
		buttonMap = new Texture("./res/gui/tex/maximize.png");
		buttonClickMap = new Texture("./res/gui/tex/maximize.png");
		getOrthoTransform().setTranslation(10, OpenGLDisplay.getInstance().getHeight()-70, 0);
		getOrthoTransform().setScaling(15,30,0);
		Vec2f[] texCoords = new Vec2f[4];
		texCoords[0] = new Vec2f(0,1f);
		texCoords[1] = new Vec2f(0,0);
		texCoords[2] = new Vec2f(1,0);
		texCoords[3] = new Vec2f(1,1f);
		setTexCoords(texCoords);
	}
	
	@Override
	public void onClickActionPerformed()
	{
		if (!isFullScreenMode){
			buttonMap = new Texture("./res/gui/tex/minimize.png");
			buttonClickMap = new Texture("./res/gui/tex/minimize.png");
			try {
				Display.setFullscreen(true);
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
			isFullScreenMode = true;
		}
		else {
			buttonMap = new Texture("./res/gui/tex/maximize.png");
			buttonClickMap = new Texture("./res/gui/tex/maximize.png");
			try {
				Display.setFullscreen(false);
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
			isFullScreenMode = true;
			isFullScreenMode = false;
		}
	}
}
