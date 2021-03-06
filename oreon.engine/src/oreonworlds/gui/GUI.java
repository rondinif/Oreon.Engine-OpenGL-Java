package oreonworlds.gui;

import modules.gui.GUIElement;
import modules.gui.Screen;

public class GUI extends modules.gui.GUI{

	private OpenCloseButton openCloseButton;
	private Screen guiButtonsPanel;
	
	@Override
	public void init() {
		Screen screen0 = new Screen();
		screen0.setElements(new GUIElement[2]);
		openCloseButton =  new OpenCloseButton();
		screen0.getElements()[0] = openCloseButton;
		screen0.getElements()[1] = new FPSPanel();
		screen0.init();
		getScreens().add(screen0);
		
		guiButtonsPanel = new Screen();
		guiButtonsPanel.setElements(new GUIElement[2]);
		guiButtonsPanel.getElements()[0] = new FullScreenButton();
		guiButtonsPanel.getElements()[1] = new GridButton();
		guiButtonsPanel.init();
	}
	
	@Override
	public void update(){
		super.update();
		if (!openCloseButton.isClosed()){
			if (getScreens().size() == 1)
				getScreens().add(guiButtonsPanel);
		}
		else if (openCloseButton.isClosed()){
			if (getScreens().size() == 2)
				getScreens().remove(1);
		}
	}
}
