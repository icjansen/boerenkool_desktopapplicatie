package core.UI.views;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.Document;

@SuppressWarnings("serial")
public class UITextField extends JTextField {

    private String placeholder;
    
    public UITextField() {
    	super();
    }

    public UITextField(final Document pDoc, final String pText, final int pColumns) {
        super(pDoc, pText, pColumns);
    }

    public UITextField(final int pColumns) {
        super(pColumns);
    }

    public UITextField(final String pText) {
        super(pText);
    }

    public UITextField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
            .getMaxAscent() + getInsets().top);
    }

    
}
