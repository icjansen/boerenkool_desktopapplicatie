package core.UI;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class UIWindow extends JFrame {
	
	public UIViewController rootViewController;

	public UIWindow(UIViewController rootViewController) {
		this.rootViewController = rootViewController;
		
		setContentPane(rootViewController.view);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400, 400));
		setSize(1000, 700);
		setLocationRelativeTo(null);
	}
	
	public void setRootViewController(UIViewController rootViewController) {
		this.rootViewController = rootViewController;
		setContentPane(rootViewController.view);
	}
	
}
