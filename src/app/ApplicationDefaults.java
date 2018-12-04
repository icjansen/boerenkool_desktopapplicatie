package app;

import java.awt.Color;

public class ApplicationDefaults {
	
	// Application
	
//	public static final Icon ICON = new ImageIcon().loadImage(new Image());
	
	// Database
	
	public static final String DATABASE = "lawren38_boerenkool"; // basbieckmann_boerenkool
	
	public static final String HOST = "107.6.153.58"; // 185.104.29.16
	
	public static final int PORT = 3306;
	
	public static final String PASSWORD = "COwu^6n996IT"; // metworst

	public static final String USERNAME = "lawren38_worst"; // basbieckmann_boerenkool
	
	public static String getLink() {
		return "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
	}
	
	// Color

	public static Color BLUE = new Color(0, 0, 255);
	
}
