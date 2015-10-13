package ST;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/**
 * Ship which is managed by player.
 * 
 * @author Maksat E.
 */

public class PlayerShip {
	// Light on the ship
	private static boolean isLightOn = false;
	private static long currenTimeLight;
	private static long lastTimeLight;
    
	// Hit moment
	public static boolean isHit = false;
	public static long currenTimeHit;
	public static long lastTimeHit;	
	private final static long delayTimeHit = Framework.secInNanosec / 10;
	
	// Re-born
	public static boolean isGhost = false;
	public static long currenTimeGhost;
	public static long lastTimeGhost;	
	private final static long delayTimeGhost = Framework.secInNanosec * 3;
	
	// Blinking when re-born
	public static boolean isGhostBlink = false;
	public static long currenTimeGhostBlink;	
	private static long lastTimeGhostBlink;	
	private final static long delayTimeGhostBlink = Framework.secInNanosec / 6;	
	
	// Print info text
	public static long currenTimeInfo;	
	public static long lastTimeInfo;	
	private final static long delayTimeInfo = Framework.secInNanosec * 2;		
	
    // Health of the ship.
    public final int healthInit = 100;
    public final int lifeInit = 3;
    public int health;
    public int life;
    
    // Position of the ship on the screen.
    public int xCoordinate;
    public int yCoordinate;
    
    // Moving speed and also direction.
    private double movingXspeed;
    public  double movingYspeed;
    private double maximumXspeed;
    private double maximumYspeed;
    private double acceleratingXspeed;
    private double acceleratingYspeed;
    private double stoppingXspeed;
    private double stoppingYspeed;
    
    public static boolean isExploded = false;  
  
    // Images of ship and its propellers.
    public BufferedImage shipBodyImg;
    public BufferedImage shipRedImg;
    public BufferedImage shipBlinkImg;
    public static BufferedImage playerLifeImg;

    // Offset of the ship machine gun. We add offset to the position of the position of ship.
    private int offsetXMachineGun;
    private int offsetYMachineGun;
    
    // Position on the frame/window of the ship machine gun.
    public int machineGunXcoordinate;
    public int machineGunYcoordinate;
    
    // Font for info text
    private Font font;
    public static String infoText = "";
    
    /**
     * Creates object of player.
     * 
     * @param xCoordinate Starting x coordinate of ship.
     * @param yCoordinate Starting y coordinate of ship.
     */
    public PlayerShip(int xCoordinate, int yCoordinate)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        font = new Font("monospaced", Font.BOLD, 16);
        
        LoadContent();
        Initialize();
    }
    
    
    /**
     * Set variables and objects for this class.
     */
    private void Initialize()
    {
        this.health        = healthInit;
        this.life          = lifeInit;
        Bullet.damagePower = Bullet.damagePowerInit;
        Bullet.r           = 0;
        
        this.movingXspeed       = 0;
        this.movingYspeed       = 0;
        this.maximumXspeed      = 5;
        this.maximumYspeed      = 5;        
        this.acceleratingXspeed = 0.4;
        this.acceleratingYspeed = 0.4;
        this.stoppingXspeed     = 0.2;
        this.stoppingYspeed     = 0.2;

        this.offsetXMachineGun     = shipBodyImg.getWidth() - 40;
        this.offsetYMachineGun     = shipBodyImg.getHeight();
        this.machineGunXcoordinate = this.xCoordinate + this.offsetXMachineGun;
        this.machineGunYcoordinate = this.yCoordinate + this.offsetYMachineGun;       
    }
    
    /**
     * Load files for this class.
     */
    private void LoadContent()
    {
        try 
        {
            URL shipBodyImgUrl = this.getClass().getResource("/res/NCC1701.png");
            shipBodyImg = ImageIO.read(shipBodyImgUrl);
            
            URL shipRedImgUrl = this.getClass().getResource("/res/NCC1701_red.png");
            shipRedImg = ImageIO.read(shipRedImgUrl);
            
            URL shipBlinkImgUrl = this.getClass().getResource("/res/NCC1701_blink.png");
            shipBlinkImg = ImageIO.read(shipBlinkImgUrl);            

        } 
        catch (IOException ex) {
            Logger.getLogger(PlayerShip.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /**
     * Resets the player.
     * 
     * @param xCoordinate Starting x coordinate of ship.
     * @param yCoordinate Starting y coordinate of ship.
     */
    public void Reset(int xCoordinate, int yCoordinate)
    {
        this.health = healthInit;
        this.life   = lifeInit;
        
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

        this.machineGunXcoordinate = this.xCoordinate + this.offsetXMachineGun;
        this.machineGunYcoordinate = this.yCoordinate + this.offsetYMachineGun;
        
        this.movingXspeed = 0;
        this.movingYspeed = 0;
        
        isGhost      = false;
        isGhostBlink = false;
        isExploded   = false;
    }
    
    
    /**
     * Checks if player is shooting. It also checks if player can 
     * shoot (time between bullets, does a player have any bullet left).
     * 
     * @param gameTime The current elapsed game time in nanoseconds.
     * @return true if player is shooting.
     */
    public boolean isShooting(long gameTime)
    {
        // Checks if left mouse button is down && if it is the time for a new bullet.
        if( Canvas.keyboardKeyState(KeyEvent.VK_SPACE) && 
            ((gameTime - Bullet.timeOfLastCreatedBullet) >= Bullet.timeBetweenNewBullets)) 
        {
            return true;
        } else
            return false;
    }
    
    
    /**
     * Checks if player moving ship and sets its moving speed if player is moving.
     */
    public void isMoving()
    { 
        // Moving on the x coordinate.
        if (Canvas.keyboardKeyState(KeyEvent.VK_RIGHT))
            movingXspeed += acceleratingXspeed;
        else if (Canvas.keyboardKeyState(KeyEvent.VK_LEFT))
            movingXspeed -= acceleratingXspeed;
        else    // Stopping
        	if ((int) movingXspeed == 0) 
        		movingXspeed = 0;
            else if (movingXspeed < 0)
                movingXspeed += stoppingXspeed;
            else if (movingXspeed > 0)
                movingXspeed -= stoppingXspeed;
        
        if (movingXspeed < -maximumXspeed) movingXspeed = -maximumXspeed;
        if (movingXspeed > maximumXspeed) movingXspeed = maximumXspeed;
    	if (xCoordinate  < 0) movingXspeed = acceleratingXspeed;
    	if (xCoordinate  > (Framework.frameWidth - shipBodyImg.getWidth())) movingXspeed = -acceleratingXspeed; 

    		// Moving on the y coordinate.
        if (Canvas.keyboardKeyState(KeyEvent.VK_UP))
            movingYspeed -= acceleratingYspeed;
        else if (Canvas.keyboardKeyState(KeyEvent.VK_DOWN))
            movingYspeed += acceleratingYspeed;
        else    // Stoping
        	if ((int) movingYspeed == 0) 
        		movingYspeed = 0;
        	else if (movingYspeed < 0)
                movingYspeed += stoppingYspeed;
            else if (movingYspeed > 0)
                movingYspeed -= stoppingYspeed;

        if (movingYspeed < -maximumYspeed) movingYspeed = -maximumYspeed;
        if (movingYspeed > maximumYspeed) movingYspeed = maximumYspeed;
        if (yCoordinate  < 0) movingYspeed = acceleratingYspeed;
        if (yCoordinate  > (Framework.frameHeight - shipBodyImg.getHeight())) movingYspeed = -acceleratingYspeed;
    }
    
    
    /**
     * Updates position of ship, animations.
     */
    public void Update()
    {
    	
		currenTimeLight = System.nanoTime() - lastTimeLight;
		if (currenTimeLight > Framework.secInNanosec) {
			if (isLightOn) isLightOn = false;
			else isLightOn = true;
			lastTimeLight = System.nanoTime();
		}
		
		currenTimeHit = System.nanoTime() - lastTimeHit;
		if (currenTimeHit > delayTimeHit) {
			if (isHit) isHit = false;
			lastTimeHit = System.nanoTime();
		}		

		currenTimeGhost = System.nanoTime() - lastTimeGhost;
		if (currenTimeGhost > delayTimeGhost) {
			if (isGhost) isGhost = false;
			lastTimeGhost = System.nanoTime();
		}
		
		currenTimeInfo = System.nanoTime() - lastTimeInfo;
		if (currenTimeInfo > delayTimeInfo) {
			if (infoText.length() > 0) infoText = "";
			lastTimeInfo = System.nanoTime();
		}		
		
        // Move ship and its propellers.
        xCoordinate += movingXspeed;
        yCoordinate += movingYspeed;

        // Move the machine gun with ship.
        this.machineGunXcoordinate = this.xCoordinate + this.offsetXMachineGun;
        this.machineGunYcoordinate = this.yCoordinate + this.offsetYMachineGun;
    }
    
    
    /**
     * Draws ship to the screen.
     * 
     * @param g2d Graphics2D
     */
    public void Draw(Graphics2D g2d)
    {
    	if (infoText.length() > 0) {
	    	g2d.setFont(font);
	    	g2d.setColor(Color.WHITE);
	    	g2d.drawString(infoText, xCoordinate, yCoordinate - 10);
    	}
    	
    	if (isHit) g2d.drawImage(shipRedImg, xCoordinate, yCoordinate, null);
    	else if (isGhost) {
    		currenTimeGhostBlink = System.nanoTime() - lastTimeGhostBlink;
    		if (currenTimeGhostBlink > delayTimeGhostBlink && !isExploded) {
    			if (isGhostBlink) isGhostBlink = false;
    			else isGhostBlink = true;
    			lastTimeGhostBlink = System.nanoTime();
    		}				    		
    		if (isGhostBlink) g2d.drawImage(shipBlinkImg, xCoordinate, yCoordinate, null);
    	}
    	else {
    		g2d.drawImage(shipBodyImg, xCoordinate, yCoordinate, null);
    		
            if (isLightOn) g2d.setColor(Color.GREEN.brighter());
            else g2d.setColor(Color.GREEN.darker());
            g2d.fillRect(xCoordinate + 16, yCoordinate + 31, 2, 2);    		
    	}
    }
}
