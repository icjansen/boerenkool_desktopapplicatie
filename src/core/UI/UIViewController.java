package core.UI;

import core.UI.views.UIView;

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
			view = parentView;
			view.getRootPane().validate();
		}
	}
	
	public void present(UIViewController viewController) {
		present(viewController, true);
		System.out.print("Dismiss UIViewController");
	}
	
	public void present(UIViewController viewController, boolean animated) {
		view = viewController.view;
		view.getRootPane().validate();
		System.out.print("Present UIViewController");
	}
	
	
}
