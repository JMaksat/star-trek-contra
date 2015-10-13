package ST;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * Bullets of enemy.
 * 
 * @author ergeshbayev
 */

public class EnemyBullet {
    
    // Damage that is made to an enemy ship when it is hit with a bullet.
    public int damagePower = 20;
    public String kindBullet;
    
    // Position of the bullet on the screen. Must be of type double because movingXspeed and movingYspeed will not be a whole number.
    private double xCoordinate;
    private double yCoordinate;
    public int x;
    public int y;
    
    // Moving speed and direction.
    private int bulletSpeed = 3;
    private double movingXspeed;
    private double movingYspeed;
    
    // Images of ship machine gun bullet. Image is loaded and set in Game class in LoadContent() method.
    public BufferedImage bulletImg;
    public static BufferedImage greenBulletImg    = null;
    public static BufferedImage redDoubleLinesImg = null;
    public static BufferedImage blueBulletImg     = null;
    public static BufferedImage redBulletImg      = null;
    public static BufferedImage blueBubbleImg;
    public static BufferedImage shurikenAnimImg;
    public static BufferedImage rocketAnimImg;
    public static BufferedImage rainbowImg;
    
    private Enemy eh;
    Animation shurikenAnim;
    Animation rocketAnim;
    
    /**
     * Creates new machine gun bullet.
     * 
     * @param xCoordinate From which x coordinate was bullet fired?
     * @param yCoordinate From which y coordinate was bullet fired?
     * @param mousePosition Position of the mouse at the time of the shot.
     */
    public EnemyBullet(int xCoordinate, int yCoordinate, PlayerShip player, Enemy eh, String kindBullet, String damagePower, String speed)
    {
    	this.damagePower = Integer.parseInt(damagePower);
    	this.bulletSpeed = Integer.parseInt(speed);
    	this.kindBullet  = kindBullet;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.eh = eh;
        
        if (greenBulletImg == null) {
        	drawBufferedBulletImage();
        }
        
        if (kindBullet == "GREEN_ROUND") {
            bulletImg = greenBulletImg;
            setDirectionAndSpeed(player);        	
        }
        else if (kindBullet == "RED_DOUBLE_LINES") {
        	bulletImg = redDoubleLinesImg;
            this.movingXspeed = 0;
            this.movingYspeed = bulletSpeed;
        }
        else if (kindBullet == "BLUE_ROUND") {
        	bulletImg = blueBulletImg;
            setDirectionAndSpeed(player); 
        }        
        else if (kindBullet == "SHURIKEN") {
        	bulletImg = shurikenAnimImg;
            setDirectionAndSpeed(player); 
            shurikenAnim = new Animation(bulletImg, 15, 15, 3, 45, true, (int) this.movingXspeed, (int) this.movingYspeed, 0);
        }
        else if (kindBullet == "BLUE_BUBBLE") {
        	bulletImg = blueBubbleImg;
            setDirectionAndSpeed(player); 
        }        
        else if (kindBullet == "RED_ROUND") {
        	bulletImg = redBulletImg;
            setDirectionAndSpeed(player); 
        }
        else if (kindBullet == "RAINBOW") {
        	bulletImg = rainbowImg;
            this.movingXspeed = 0;
            this.movingYspeed = bulletSpeed;
        }          
        else if (kindBullet == "ROCKET") {
        	bulletImg = rocketAnimImg;
            this.movingXspeed = 0;
            this.movingYspeed = bulletSpeed;
            rocketAnim = new Animation(bulletImg, 13, 65, 9, 45, true, (int) this.movingXspeed, (int) this.movingYspeed, 0);
        }        

    }
    
    private void drawBufferedBulletImage()
    {
    	greenBulletImg = new BufferedImage(13, 13, BufferedImage.TYPE_INT_RGB);
    	Graphics2D grn = greenBulletImg.createGraphics();
    	grn.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	grn.setColor(new Color(251, 255, 138));
    	grn.fillOval(0, 0, 13, 13);
    	grn.setColor(new Color(222, 252, 123));
    	grn.drawOval(4, 4, 4, 4);    	
    	grn.setColor(new Color(179, 247, 102));
    	grn.drawOval(3, 3, 6, 6);    
    	grn.setColor(new Color(130, 241, 76));
    	grn.drawOval(2, 2, 8, 8);
    	grn.setColor(new Color(81, 235, 51));
    	grn.drawOval(1, 1, 10, 10);     	
    	grn.setColor(new Color(39, 230, 30));
    	grn.drawOval(0, 0, 12, 12);  
    	

    	redDoubleLinesImg = new BufferedImage(3, 25, BufferedImage.TYPE_INT_RGB);
    	Graphics2D red = redDoubleLinesImg.createGraphics();
    	red.setColor(Color.RED);
    	red.fillRect(0, 0, 3, 25);
    	red.setColor(Color.ORANGE);
    	red.drawLine(1, 0, 1, 24);    	
    	
    	blueBulletImg = new BufferedImage(13, 13, BufferedImage.TYPE_INT_RGB);
    	Graphics2D blv = blueBulletImg.createGraphics();
    	blv.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	blv.setColor(new Color(69, 255, 218));
    	blv.fillOval(0, 0, 13, 13);
    	blv.setColor(new Color(67, 234, 218));
    	blv.drawOval(4, 4, 4, 4);    	
    	blv.setColor(new Color(65, 204, 218));
    	blv.drawOval(3, 3, 6, 6);    
    	blv.setColor(new Color(62, 168, 218));
    	blv.drawOval(2, 2, 8, 8);
    	blv.setColor(new Color(59, 133, 219));
    	blv.drawOval(1, 1, 10, 10);     	
    	blv.setColor(new Color(57, 103, 219));
    	blv.drawOval(0, 0, 12, 12);  
    	
    	redBulletImg = new BufferedImage(13, 13, BufferedImage.TYPE_INT_RGB);
    	Graphics2D rbt = redBulletImg.createGraphics();
    	rbt.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	rbt.setColor(new Color(255, 200, 250));
    	rbt.fillOval(0, 0, 13, 13);
    	rbt.setColor(new Color(255, 176, 220));
    	rbt.drawOval(4, 4, 4, 4);    	
    	rbt.setColor(new Color(255, 141, 176));
    	rbt.drawOval(3, 3, 6, 6);    
    	rbt.setColor(new Color(255, 100, 125));
    	rbt.drawOval(2, 2, 8, 8);
    	rbt.setColor(new Color(255, 59, 74));
    	rbt.drawOval(1, 1, 10, 10);     	
    	rbt.setColor(new Color(255, 24, 30));
    	rbt.drawOval(0, 0, 12, 12);      	
    }
    
    /**
     * Calculate the speed on a x and y coordinate.
     * 
     * @param mousePosition 
     */
    private void setDirectionAndSpeed(PlayerShip player)
    {
        // Unit direction vector of the bullet.
        double directionVx = (player.xCoordinate - player.shipBodyImg.getWidth() / 2) - this.xCoordinate;
        double directionVy = (player.yCoordinate - player.shipBodyImg.getHeight() / 2) - this.yCoordinate;
        double lengthOfVector = Math.sqrt(directionVx * directionVx + directionVy * directionVy);
        directionVx = directionVx / lengthOfVector; // Unit vector
        directionVy = directionVy / lengthOfVector; // Unit vector
        
        // Set speed.
        this.movingXspeed = bulletSpeed * directionVx;
        this.movingYspeed = bulletSpeed * directionVy;
    }
    
    
    /**
     * Checks if the bullet is left the screen.
     * 
     * @return true if the bullet left the screen, false otherwise.
     */
    public boolean isItLeftScreen()
    {
        if(x > 0 && x < Framework.frameWidth &&
           y > 0 && y < Framework.frameHeight)
            return false;
        else
            return true;
    }
    
    
    /**
     * Moves the bullet.
     */
    public void Update()
    {
        xCoordinate += movingXspeed;
        yCoordinate += movingYspeed;
        
    	x = (int)xCoordinate + (eh.enemy_shipBodyImg.getWidth() / 2);
    	y = (int)yCoordinate + eh.enemy_shipBodyImg.getHeight();         
    }
    
    
    /**
     * Draws the bullet to the screen.
     * 
     * @param g2d Graphics2D
     */
    public void Draw(Graphics2D g2d)
    {
    	if (kindBullet == "RED_DOUBLE_LINES") {
    		g2d.drawImage(bulletImg, x - 35, y - 25, null);
    		g2d.drawImage(bulletImg, x + 35, y - 25, null);
    	}
    	else if (kindBullet == "SHURIKEN") {
    		shurikenAnim.x = x;
    		shurikenAnim.y = y;
    		shurikenAnim.Draw(g2d);
    	}
    	else if (kindBullet == "BLUE_BUBBLE") {
    		g2d.drawImage(bulletImg, x - 25, y - 10, null);
    	}
    	else if (kindBullet == "RAINBOW") {
    		g2d.drawImage(bulletImg, x - 20, y - 10, null);
    	}
    	else if (kindBullet == "ROCKET") {
    		rocketAnim.x = x - 7;
    		rocketAnim.y = y - 17;
    		rocketAnim.Draw(g2d);
    	}    	
    	else g2d.drawImage(bulletImg, x, y, null);
    }
}
