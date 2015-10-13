package ST;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Equipment bonus.
 * 
 * @author ergeshbayev
 */

public class BonusEquipment {
	public static BufferedImage bonusEquipmentImg;
	
    // For creating new enemies.
    private static final long timeBetweenNewBonusInit = Framework.secInNanosec * 27;
    public static long timeBetweenNewBonus = timeBetweenNewBonusInit;
    public static long timeOfLastCreatedBonus = 0;	

	// Position of Bonus on the screen
	public int xCoordinate;
	public int yCoordinate;
	
	// Moving speed and direction
	private double movingSpeedInit = 1;
	private double movingXspeed;
	private double mpvingYspeed;
	
	public static String LastEquipment = "INCREASE_DAMAGE"; 	
	
	Random random;	
	
	public BonusEquipment() {
        // Sets Bonus position.
    	random = new Random();
        this.xCoordinate = random.nextInt(Framework.frameWidth - bonusEquipmentImg.getWidth());
        this.yCoordinate = -bonusEquipmentImg.getHeight();
        
        mpvingYspeed = movingSpeedInit;		
	}
	
	public void Update() {
		yCoordinate += mpvingYspeed;
	}
	
	public void Draw(Graphics2D g2d) {
		g2d.drawImage(bonusEquipmentImg, xCoordinate, yCoordinate, null);
	}

}
