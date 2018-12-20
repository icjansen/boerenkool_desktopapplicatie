package boerenkool_core.UI.views;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class UITextArea extends JTextArea {

	public UITextArea(String name) {
		super(name);
        setBackground(null);
        setEditable(false);
        setBorder(null);
        setLineWrap(true);
        setWrapStyleWord(true);
        setFocusable(false);
        
	}
	
	
}
