package app;

import javax.swing.UIManager;

import app.controller.HomeViewController;
import core.UI.UIViewController;
import core.UI.UIWindow;

public class AppDelegate {
	
	public UIWindow window;
	
	
	public AppDelegate() {
		
		// Set look-and-feel
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// Initialize window
		UIViewController rootViewController = new HomeViewController();
		window = new UIWindow(rootViewController);
		window.setTitle("Boerenkool met worst");
		window.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new AppDelegate();
	}
	
	
}
