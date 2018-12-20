package core.UI.views;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import core.UI.UIImage;

@SuppressWarnings("serial")
public class UIImageView extends UIView {

    private UIImage image;

    public UIImageView() {
    	
    }

    public UIImageView(UIImage image) {
    	this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int width = this.getWidth();
        int height = this.getHeight();
        
//        g.drawImage(image.getBuffer(), 0, 0, width, height, this);
        
//        g.drawImage(image, 0, 0, this);
        
        if(width > height) {
        	g.drawImage(image.getBuffer().getScaledInstance(width, -1, Image.SCALE_DEFAULT), 0, 0, this);
        } else {
        	g.drawImage(image.getBuffer().getScaledInstance(-1, height, Image.SCALE_DEFAULT), 0, 0, this);
        }
//    	g.drawImage(image.getBuffer().getScaledInstance(width, height, Image.SCALE_DEFAULT), 0, 0, this);
    }
}
