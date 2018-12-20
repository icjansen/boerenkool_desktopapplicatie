package boerenkool_app;

import boerenkool_core.UI.UIViewController;
import boerenkool_core.UI.UIWindow;
import boerenkool_core.UI.views.UILabel;

public class LoadingWindow extends UIWindow {
	
	public LoadingWindow() {
		super(new UIViewController() {
			@Override
			public void viewWillLoad() {
				view.add(new UILabel("Loading..."));
			}
		});
		
		setDefaultLookAndFeelDecorated(false);
		setTitle(AppDelegate.window.getTitle());
		setVisible(true);
	}
	
	
}
