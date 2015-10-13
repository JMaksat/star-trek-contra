package ST;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Life bonus.
 * 
 * @author ergeshbayev
 */

public class BonusLife {
	public static BufferedImage bonusLifeImg;
	
    // For creating new enemies.
    private static final long timeBetweenNewBonusInit = Framework.secInNanosec * 90;
    public static long timeBetweenNewBonus = timeBetweenNewBonusInit;
    public static long timeOfLastCreatedBonus = 0;
	
	// Position of Bonus on the screen
	public int xCoordinate;
	public int yCoordinate;
	
	// Moving speed and direction
	private double movingSpeedInit = 1;
	private double movingXspeed;
	private double mpvingYspeed;
	
	public static int life = 1; 
	
	Random random;
	
	public BonusLife() {
        // Sets Bonus position.
    	random = new Random();
        this.xCoordinate = random.nextInt(Framework.frameWidth - bonusLifeImg.getWidth());
        this.yCoordinate = -bonusLifeImg.getHeight();
        
        mpvingYspeed = movingSpeedInit;
	}
	
	public void Update() {
		yCoordinate += mpvingYspeed;
	}
	
	public void Draw(Graphics2D g2d) {
		g2d.drawImage(bonusLifeImg, xCoordinate, yCoordinate, null);
	}

}
