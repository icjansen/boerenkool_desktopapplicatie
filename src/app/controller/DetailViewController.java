package app.controller;

import java.awt.Color;

import core.UI.UIViewController;
import core.UI.UIWindow;
import core.UI.views.UIButton;

public class DetailViewController extends UIViewController {
	
	
	public UIButton dismissButton = new UIButton();
	
	public DetailViewController() {
		super();
		
		view.setBackground(new Color(255, 0, 0));
	}
	
	@Override
	public void layoutSubviews() {
		
	}
	
	
}
