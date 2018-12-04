package core.UI.views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class UIImageView extends UIView {

    private BufferedImage image;

    public UIImageView(BufferedImage image) {
    	this.image = image;
    }

    public UIImageView(String imagePath) throws IOException {          
    	image = ImageIO.read(new File(imagePath));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
//        g.drawImage(image, 0, 0, this);
        
//        g.drawImage(image.getScaledInstance(sthis.getWidth(), this.getHeight(), 0), 0, 0, this);
    }
}
