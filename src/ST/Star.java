package ST;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Background stars in space.
 * 
 * @author ergeshbayev
 */

public class Star {

	public int x; 
	public int y = 0;
	private int speed;
	private int color;
	private int diameter;
	private final static int WIDTH = 800;
	public static long lastStr = 0;
	public static int nextStr = 0;
	public static Long timeBetween = 1000000000L / 10;
	public static int[][] StrArr = {
		                            {WIDTH, 1, 1, 2},
		                            {WIDTH, 2, 2, 2},
		                            {WIDTH, 4, 3, 3},
		                            {WIDTH, 8, 4, 3}
	                               };
	
	public Star(int x, int speed, int color, int diameter) {
		this.x = x;
		this.speed = speed;
		this.color = color;
		this.diameter = diameter;
	}
	
	public void update() {
		y += speed;
	}
	
	public void draw(Graphics2D g2d) {
		if (color == 1) g2d.setColor(Color.DARK_GRAY);
		if (color == 2) g2d.setColor(Color.GRAY);
		if (color == 3) g2d.setColor(Color.LIGHT_GRAY);
		if (color == 4) g2d.setColor(Color.WHITE);
		g2d.fillOval(x, y, diameter, diameter);
	}
}