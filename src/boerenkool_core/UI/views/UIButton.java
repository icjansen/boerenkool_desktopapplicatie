package boerenkool_core.UI.views;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class UIButton extends JButton {

	public UIButton() {
		super();
		setup();
	}
	
	public UIButton(String text) {
		super(text);
		setup();
	}
	
	
	private void setup() {
		setBackground(new Color(238, 116, 2));
		setForeground(new Color(255, 255, 255));
	}
	
}
