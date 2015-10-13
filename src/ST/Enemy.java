package ST;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Enemy space ship.
 * 
 * @author Maksat E.
 */

public class Enemy {
    
    // For creating new enemies.
    private static final long timeBetweenNewEnemiesInit = Framework.secInNanosec * 3;
    public  static long timeBetweenNewEnemies = timeBetweenNewEnemiesInit;
    public  static long timeOfLastCreatedEnemy = 0;

	// Hit moment
	public  boolean isHit = false;
	public  long    currenTimeHit;
	public  long    lastTimeHit;
	private final static long delayTimeHit = Framework.secInNanosec / 10;
    
    // For creating new bullets.
    public long timeBetweenNewBullets = Framework.secInNanosec * 3;
    public long timeOfLastCreatedBullet = 0;    
    
    // Health of the enemy_ship.
    public int health;
    
    // Position enemy in array
    public int enemyType;
    
    // Position of the enemy_ship on the screen.
    public int xCoordinate;
    public int yCoordinate;
    
    // Moving speed and direction.
    private double movingSpeedInit = 2;
    private double movingXspeed = 0;
    private double movingYspeed = 0;
    private double movingBuffer = 0;
    private boolean isMoveNegative = true;
    
    // Images of enemy enemy_ship. Images are loaded and set in Game class in LoadContent() method.
    public static BufferedImage enemyShip001;
    public static BufferedImage enemyShip001White;    
    public static BufferedImage enemyShip002;
    public static BufferedImage enemyShip002White; 
    public static BufferedImage enemyShip003;
    public static BufferedImage enemyShip003White; 
    public static BufferedImage enemyShip004;
    public static BufferedImage enemyShip004White; 
    public static BufferedImage enemyShip005;
    public static BufferedImage enemyShip005White; 
    public static BufferedImage enemyShip006;
    public static BufferedImage enemyShip006White; 
    public static BufferedImage enemyShip007;
    public static BufferedImage enemyShip007White; 
    public static BufferedImage enemyShip008;
    public static BufferedImage enemyShip008White; 
    public static BufferedImage enemyShip009;
    public static BufferedImage enemyShip009White; 
    public static BufferedImage enemyShip010;
    public static BufferedImage enemyShip010White; 
    public static BufferedImage enemyShip011;
    public static BufferedImage enemyShip011White; 
    public static BufferedImage enemyShip012;
    public static BufferedImage enemyShip012White;
    public static BufferedImage enemyShip013;
    public static BufferedImage enemyShip013White;    
    
    public BufferedImage enemy_shipBodyImg;
    public BufferedImage enemy_shipWhiteImg;
    
    /* [0] Image, [1] Kind_of_enemy, [2] Health, [3] Bullet_kind, [4] Bullet_damage_power, [5] Bullet_speed, [6] Shots_interval, [7] Enemy_speed, [8] Enemy_cost*/
    public static String[][] EnemyArray = {
    	                                   {"ENEMY001", "SHIP_MOVE_STRAIGHT_BULLET_MOVE_TOWARD_PLAYER", "60",  "GREEN_ROUND",      "20", "3",  "3", "2", "10"},
    	                                   {"ENEMY002", "SHIP_MOVE_TOWARD_PLAYER_BULLET_MOVE_STRAIGHT", "70",  "RED_DOUBLE_LINES", "25", "15", "1", "2", "20"},
    	                                   {"ENEMY003", "SHIP_MOVE_STRAIGHT_BULLET_MOVE_TOWARD_PLAYER", "60",  "BLUE_ROUND",       "25", "4",  "1", "6", "30"},
    	                                   {"ENEMY004", "SHIP_MOVE_TOWARD_PLAYER_BULLET_MOVE_STRAIGHT", "90",  "RED_DOUBLE_LINES", "25", "15", "2", "2", "40"},
    	                                   {"ENEMY005", "SHIP_MOVE_STRAIGHT_BULLET_MOVE_TOWARD_PLAYER", "70",  "SHURIKEN",         "30", "3",  "3", "2", "50"},
    	                                   {"ENEMY006", "SHIP_MOVE_TOWARD_PLAYER_BULLET_MOVE_STRAIGHT", "80",  "GREEN_ROUND",      "40", "4",  "2", "2", "60"},
    	                                   {"ENEMY007", "SHIP_WAVE_TOWARD_PLAYER_BULLET_MOVE_STRAIGHT", "80",  "BLUE_BUBBLE",      "45", "4",  "2", "2", "70"},
    	                                   {"ENEMY008", "SHIP_MOVE_TOWARD_PLAYER_BULLET_MOVE_STRAIGHT", "90",  "BLUE_ROUND",       "45", "5",  "2", "3", "80"},
    	                                   {"ENEMY009", "SHIP_MOVE_STRAIGHT_BULLET_MOVE_TOWARD_PLAYER", "80",  "RED_ROUND",        "45", "5",  "1", "6", "90"},
    	                                   {"ENEMY010", "SHIP_MOVE_TOWARD_PLAYER_BULLET_MOVE_STRAIGHT", "100", "RED_DOUBLE_LINES", "50", "15", "2", "2", "100"},
    	                                   {"ENEMY011", "SHIP_MOVE_STRAIGHT_BULLET_MOVE_TOWARD_PLAYER", "90",  "RED_ROUND",        "45", "5",  "1", "6", "110"},
    	                                   {"ENEMY012", "SHIP_MOVE_TOWARD_PLAYER_BULLET_MOVE_STRAIGHT", "110", "RAINBOW",          "50", "10", "2", "3", "120"},
    	                                   {"ENEMY013", "SHIP_WAVE_SCREEN_BULLET_MOVE_TOWARD_STRAIGHT", "1000", "ROCKET",          "50", "7", "1", "5", "10000"}
                                          };

    private Random random;
    PlayerShip player;
    
    /**
     * Initialize enemy enemy_ship.
     * 
     * @param xCoordinate Starting x coordinate of enemy_ship.
     * @param yCoordinate Starting y coordinate of enemy_ship.
     * @param enemy_shipBodyImg Image of enemy_ship body.
     * @param enemy_shipFrontPropellerAnimImg Image of front enemy_ship propeller.
     * @param enemy_shipRearPropellerAnimImg Image of rear enemy_ship propeller.
     */
    public void Initialize(int enemyType, PlayerShip player)
    {
    	this.enemyType = enemyType;
    	this.player = player;
    	this.timeBetweenNewBullets = Framework.secInNanosec * Integer.parseInt(EnemyArray[enemyType][6]);
    	
    	if (EnemyArray[enemyType][0] == "ENEMY001") {
    		enemy_shipWhiteImg = enemyShip001White;
    		enemy_shipBodyImg = enemyShip001;
    	}
    	else if (EnemyArray[enemyType][0] == "ENEMY002") {
    		enemy_shipWhiteImg = enemyShip002White;
    		enemy_shipBodyImg = enemyShip002;
    	}
    	else if (EnemyArray[enemyType][0] == "ENEMY003") {
    		enemy_shipWhiteImg = enemyShip003White;
    		enemy_shipBodyImg = enemyShip003;
    	}
    	else if (EnemyArray[enemyType][0] == "ENEMY004") {
    		enemy_shipWhiteImg = enemyShip004White;
    		enemy_shipBodyImg = enemyShip004;
    	}
    	else if (EnemyArray[enemyType][0] == "ENEMY005") {
    		enemy_shipWhiteImg = enemyShip005White;
    		enemy_shipBodyImg = enemyShip005;
    	}    	
    	else if (EnemyArray[enemyType][0] == "ENEMY006") {
    		enemy_shipWhiteImg = enemyShip006White;
    		enemy_shipBodyImg = enemyShip006;
    	}    	    	
    	else if (EnemyArray[enemyType][0] == "ENEMY007") {
    		enemy_shipWhiteImg = enemyShip007White;
    		enemy_shipBodyImg = enemyShip007;
    		movingBuffer = 150;
    	}
    	else if (EnemyArray[enemyType][0] == "ENEMY008") {
    		enemy_shipWhiteImg = enemyShip008White;
    		enemy_shipBodyImg = enemyShip008;
    	}
    	else if (EnemyArray[enemyType][0] == "ENEMY009") {
    		enemy_shipWhiteImg = enemyShip009White;
    		enemy_shipBodyImg = enemyShip009;
    	}   
    	else if (EnemyArray[enemyType][0] == "ENEMY010") {
    		enemy_shipWhiteImg = enemyShip010White;
    		enemy_shipBodyImg = enemyShip010;
    	}
    	else if (EnemyArray[enemyType][0] == "ENEMY011") {
    		enemy_shipWhiteImg = enemyShip011White;
    		enemy_shipBodyImg = enemyShip011;
    	}      
    	else if (EnemyArray[enemyType][0] == "ENEMY012") {
    		enemy_shipWhiteImg = enemyShip012White;
    		enemy_shipBodyImg = enemyShip012;
    	}  
    	else if (EnemyArray[enemyType][0] == "ENEMY013") {
    		enemy_shipWhiteImg = enemyShip013White;
    		enemy_shipBodyImg = enemyShip013;
    	}      	
    	
        health = Integer.parseInt(EnemyArray[enemyType][2]);
        movingSpeedInit = Integer.parseInt(EnemyArray[enemyType][7]);
        
        // Sets enemy position.
        if (EnemyArray[enemyType][0] == "ENEMY013") {
        	this.xCoordinate = 0;//(Framework.frameWidth / 2) - (enemy_shipBodyImg.getWidth() / 2);
        	this.yCoordinate = -enemy_shipBodyImg.getHeight();
        } else {
	    	random = new Random();
	        this.xCoordinate = random.nextInt(Framework.frameWidth - enemy_shipBodyImg.getWidth());
	        this.yCoordinate = -enemy_shipBodyImg.getHeight();
        }
    }
    
    /**
     * It sets speed and time between enemies to the initial properties.
     */
    public static void restartEnemy(){
        Enemy.timeBetweenNewEnemies = timeBetweenNewEnemiesInit;
        Enemy.timeOfLastCreatedEnemy = 0;
    }
    
    
    /**
     * It increase enemy speed and decrease time between new enemies.
     */
    public static void speedUp(){
        if(Enemy.timeBetweenNewEnemies > Framework.secInNanosec)
            Enemy.timeBetweenNewEnemies -= Framework.secInNanosec / 100;
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
        movingXspeed = movingSpeedInit * directionVx;
        movingYspeed = movingSpeedInit * directionVy;
    }    
    
    /**
     * Checks if the enemy is left the screen.
     * 
     * @return true if the enemy is left the screen, false otherwise.
     */
    public boolean isBottomScreen()
    {
        if(yCoordinate > Framework.frameHeight + enemy_shipBodyImg.getWidth()) // When the entire enemy_ship is out of the screen.
            return true;
        else
            return false;
    }
    
        
    /**
     * Updates position of enemy_ship, animations.
     */
    public void Update()
    {
    	if (EnemyArray[enemyType][1] == "SHIP_MOVE_TOWARD_PLAYER_BULLET_MOVE_STRAIGHT") {
    		setDirectionAndSpeed(player);
    		xCoordinate += movingXspeed;
    		yCoordinate += movingSpeedInit;
    	}
    	
    	if (EnemyArray[enemyType][1] == "SHIP_MOVE_STRAIGHT_BULLET_MOVE_TOWARD_PLAYER") {
    		yCoordinate += movingSpeedInit;
    	}    
    	
    	if (EnemyArray[enemyType][1] == "SHIP_WAVE_SCREEN_BULLET_MOVE_TOWARD_STRAIGHT") {
    		if (yCoordinate < 102) yCoordinate += movingSpeedInit;
    		else {
        		if (isMoveNegative) {
        			xCoordinate += movingSpeedInit;
        			movingBuffer += movingSpeedInit;
        		} else {
        			xCoordinate -= movingSpeedInit;
        			movingBuffer += movingSpeedInit;
        		}
        		
        		if (movingBuffer > Framework.frameWidth - enemy_shipBodyImg.getWidth()) {
        			isMoveNegative = !isMoveNegative;
        			movingBuffer = 0;
        		}    			
    		}
    	}
    	
    	if (EnemyArray[enemyType][1] == "SHIP_WAVE_TOWARD_PLAYER_BULLET_MOVE_STRAIGHT") {
    		yCoordinate += movingSpeedInit;
    		if (isMoveNegative) {
    			xCoordinate += movingSpeedInit;
    			movingBuffer += movingSpeedInit;
    		} else {
    			xCoordinate -= movingSpeedInit;
    			movingBuffer += movingSpeedInit;
    		}
    		
    		if (movingBuffer > 300) {
    			isMoveNegative = !isMoveNegative;
    			movingBuffer = 0;
    		}
    	}
    	
		currenTimeHit = System.nanoTime() - lastTimeHit;
		if (currenTimeHit > delayTimeHit) {
			if (isHit) isHit = false;
			lastTimeHit = System.nanoTime();
		}	        
    }
    
    
    /**
     * Draws enemy_ship to the screen.
     * 
     * @param g2d Graphics2D
     */
    public void Draw(Graphics2D g2d)
    {
    	if (isHit) g2d.drawImage(enemy_shipWhiteImg, xCoordinate, yCoordinate, null);
    	else {
    		g2d.drawImage(enemy_shipBodyImg, xCoordinate, yCoordinate, null);    		
    	}
    }
    
}

