package ST;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * Bullets of player.
 * 
 * @author ergeshbayev
 */

public class Bullet {
    
    // For creating new bullets.
    public final static long timeBetweenNewBullets = Framework.secInNanosec / 5;
    public static long timeOfLastCreatedBullet = 0;
    
    // Damage that is made to an enemy ship when it is hit with a bullet.
    public static int damagePowerInit = 20;
    public static int damagePower;
    
    // Increase bullet when get bonus.
    public static int r = 0;
    
    // Position of the bullet on the screen. Must be of type double because movingXspeed and movingYspeed will not be a whole number.
    private double xCoordinate;
    private double yCoordinate;
    public static final int radius = 13;
    public int x;
    public int y;
    
    // Moving speed and direction.
    private static int bulletSpeed = 10;
    //private double movingXspeed;
    //private double movingYspeed;
    
    // Images of ship machine gun bullet. Image is loaded and set in Game class in LoadContent() method.
    public static BufferedImage bulletImg = null;
    
    
    /**
     * Creates new machine gun bullet.
     * 
     * @param xCoordinate From which x coordinate was bullet fired?
     * @param yCoordinate From which y coordinate was bullet fired?
     * @param mousePosition Position of the mouse at the time of the shot.
     */
    public Bullet(int xCoordinate, int yCoordinate, Point mousePosition)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

        //if (bulletImg == null) {
        //	drawBufferedBulletImage();
        //}
        //setDirectionAndSpeed(mousePosition);
    }
    
    /*private void drawBufferedBulletImage()
    {
    	bulletImg = new BufferedImage(radius, radius, BufferedImage.TYPE_INT_RGB);
    	Graphics2D g2d = bulletImg.createGraphics();
    	
    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g2d.setColor(Color.YELLOW.brighter());
    	g2d.fillOval(0, 0, r + radius, r + radius);
    	g2d.setColor(Color.YELLOW);
    	g2d.drawOval(4, 4, r + 4, r +4);    	
    	g2d.setColor(Color.ORANGE.brighter());
    	g2d.drawOval(3, 3, r + 6, r + 6);    
    	g2d.setColor(Color.ORANGE);
    	g2d.drawOval(2, 2, r + 8, r + 8);
    	g2d.setColor(Color.ORANGE);
    	g2d.drawOval(1, 1, r + 10, r + 10);     	
    	g2d.setColor(Color.RED.darker());
    	g2d.drawOval(0, 0, r + 12, r + 12);  
    }*/
    
    /**
     * Calculate the speed on a x and y coordinate.
     * 
     * @param mousePosition 
     */
    /*private void setDirectionAndSpeed(Point mousePosition)
    {
        // Unit direction vector of the bullet.
        double directionVx = mousePosition.x - this.xCoordinate;
        double directionVy = mousePosition.y - this.yCoordinate;
        double lengthOfVector = Math.sqrt(directionVx * directionVx + directionVy * directionVy);
        directionVx = directionVx / lengthOfVector; // Unit vector
        directionVy = directionVy / lengthOfVector; // Unit vector
        
        // Set speed.
        this.movingXspeed = bulletSpeed * directionVx;
        this.movingYspeed = bulletSpeed * directionVy;
    }*/
    
    
    /**
     * Checks if the bullet is left the screen.
     * 
     * @return true if the bullet left the screen, false otherwise.
     */
    public boolean isItLeftScreen()
    {
        if(xCoordinate > -6 && xCoordinate < Framework.frameWidth &&
           yCoordinate > 0 && yCoordinate < Framework.frameHeight)
            return false;
        else
            return true;
    }
    
    
    /**
     * Moves the bullet.
     */
    public void Update()
    {
        //xCoordinate += movingXspeed;
        yCoordinate -= bulletSpeed;//movingYspeed;
        
    	x = ((int)xCoordinate + 15) - (r / 2);
    	y = ((int)yCoordinate - 80) - (r / 2);         
    }
    
    
    /**
     * Draws the bullet to the screen.
     * 
     * @param g2d Graphics2D
     */
    public void Draw(Graphics2D g2d)
    {
        //g2d.drawImage(bulletImg, x, y, null);
    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g2d.setColor(Color.YELLOW.brighter());
    	g2d.fillOval(x, y, r + radius, r + radius);
    	g2d.setColor(Color.YELLOW);
    	g2d.drawOval(x + 4, y + 4, r + 4, r +4);    	
    	g2d.setColor(Color.ORANGE.brighter());
    	g2d.drawOval(x + 3, y + 3, r + 6, r + 6);    
    	g2d.setColor(Color.ORANGE);
    	g2d.drawOval(x + 2, y + 2, r + 8, r + 8);
    	g2d.setColor(Color.ORANGE);
    	g2d.drawOval(x + 1, y + 1, r + 10, r + 10);     	
    	g2d.setColor(Color.RED.darker());
    	g2d.drawOval(x, y, r + 12, r + 12);
    }
}
