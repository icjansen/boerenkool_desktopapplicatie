package app;

import java.awt.Color;

public class ApplicationDefaults {
	
	// Application
	
//	public static final Icon ICON = new ImageIcon().loadImage(new Image());
	
	// Database
	
	public static final String DATABASE = "basbieckmann_boerenkool";
	
	public static final String HOST = "185.104.29.16";
	
	public static final int PORT = 3306;
	
	public static final String PASSWORD = "metworst"; // COwu^6n996IT

	public static final String USERNAME = "basbieckmann_boerenkool"; // lawren38_worst
	
	public static String getLink() {
		return "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
	}
	
	// Color

	public static Color BLUE = new Color(0, 0, 255);
	
}
