package boerenkool_core.UI;

import boerenkool_app.AppDelegate;
import boerenkool_core.UI.views.UIView;

public class UIViewController {

	public UIView view = new UIView();
	
	private UIView parentView;
	
	public UIViewController() {
//		new Thread() {
//			@Override
//			public void run() {				
//				layoutSubviews();
//			}
//		}.run();
		viewWillLoad();
	}
	
	public void viewWillLoad() {
		
	}
	
	public void layoutSubviews() {
		
	}
	
	public void dismiss() {
		dismiss(true);
	}
	
	public void dismiss(boolean animated) {
		if (parentView != null) {
			AppDelegate.window.getRootPane().setContentPane(parentView);
			AppDelegate.window.getRootPane().validate();
		} else {
			System.out.println("Can't dismiss UIViewController because no parent is set");
		}
	}
	
	public void present(UIViewController viewController) {
		present(viewController, true);
	}
	
	public void present(UIViewController viewController, boolean animated) {
		viewController.parentView = view;
		AppDelegate.window.getRootPane().setContentPane(viewController.view);
		AppDelegate.window.getRootPane().validate();
	}
	
	
}
