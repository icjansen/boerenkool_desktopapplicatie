package boerenkool_core.UI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class UIImage {
	
	private BufferedImage buffer;

    public UIImage(String imagePath) {
    	try {
			buffer = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public UIImage(URL imageURL) {
    	try {
			buffer = ImageIO.read(imageURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public BufferedImage getBuffer() {
    	return buffer;
    }
}
