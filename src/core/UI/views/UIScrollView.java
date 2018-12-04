package core.UI.views;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class UIScrollView extends JScrollPane {

	public UIScrollView() {
        super();
        
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

	public UIScrollView(Component view) {
        super(view);
        
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
    }
	
	
}
