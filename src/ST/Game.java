package ST;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/**
 * Actual game.
 * 
 * @author ergeshbayev
 */

public class Game {
    
    // Use this to generate a random number.
    private Random random;
    
    // We will use this for seting mouse position.
    private Robot robot;
    
    // Player - ship that is managed by player.
    private PlayerShip player;
    
    // Enemy ships.
    private ArrayList<Enemy> enemyShipList = new ArrayList<Enemy>();
    
    // Explosions
    private ArrayList<Animation> explosionsList;
    private BufferedImage explosionAnimImg;
    
    // Stars in space
    private ArrayList<Star> stars;
    
    // List of all the machine gun bullets.
    private ArrayList<Bullet> bulletsList;
    
    // List of all the machine gun bullets.
    private ArrayList<EnemyBullet> enemyBulletsList;
    
    // Life bonus
    private ArrayList<BonusLife> bonusLifeList;    
    
    // Life bonus
    private ArrayList<BonusEquipment> bonusEquipmentList;    
    
    // Font that we will use to write statistic to the screen.
    private Font font;
    
    // Statistics (destroyed enemies, run away enemies)
    private int runAwayEnemies;
    private int destroyedEnemies;
    private int playerScore;
    private static boolean isBoss = false;
    private static boolean bossDead = false;
    private static int enemyType = 0;
    private static int queueStep = 0;
    private static int NumberCreatedEnemies = 0;
    private static int[] queueEnemy = {10, 20, 30, 10, 20, 30, 10, 20, 30, 10, 20, 30, 10, 20, 30, 10, 20, 30, 10, 20, 30, 10, 20, 30, 10, 20, 30, 10, 20, 30, 10, 20, 30, 10, 20, 30, 1, 0};

    public Game()
    {
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){
                // Sets variables and objects for the game.
                Initialize();
                // Load game files (images, sounds, ...)
                LoadContent();
                
                Framework.gameState = Framework.GameState.GAME;
            }
        };
        threadForInitGame.start();
    }
    
    
   /**
     * Set variables and objects for the game.
     */
    private void Initialize()
    {
        random = new Random();
        
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        player = new PlayerShip(Framework.frameWidth / 2, Framework.frameHeight / 2);
        
        enemyShipList = new ArrayList<Enemy>();
        
        explosionsList = new ArrayList<Animation>();
        
        bulletsList = new ArrayList<Bullet>();
        
        stars = new ArrayList<Star>();
        
        enemyBulletsList = new ArrayList<EnemyBullet>();
        
        bonusLifeList = new ArrayList<BonusLife>();
        
        bonusEquipmentList = new ArrayList<BonusEquipment>();
        
        font = new Font("monospaced", Font.BOLD, 17);
        
        runAwayEnemies = 0;
        destroyedEnemies = 0;
        playerScore = 0;
        
        Sound.INTRO.stop();
        Sound.GAME.loop();
    }
    
    /**
     * Load game files (images).
     */
    private void LoadContent()
    {
        try 
        {
            URL shipBodyImgUrl;
            URL shipWhiteImgUrl;
            
            // Load images for enemy 1 ship
            shipBodyImgUrl = this.getClass().getResource("/res/enemy001.png");
            Enemy.enemyShip001 = ImageIO.read(shipBodyImgUrl);
            shipWhiteImgUrl = this.getClass().getResource("/res/enemy001_white.png");
            Enemy.enemyShip001White = ImageIO.read(shipWhiteImgUrl);
            
            // Load images for enemy 2 ship
            shipBodyImgUrl = this.getClass().getResource("/res/enemy002.png");
            Enemy.enemyShip002 = ImageIO.read(shipBodyImgUrl);
            shipWhiteImgUrl = this.getClass().getResource("/res/enemy002_white.png");
            Enemy.enemyShip002White = ImageIO.read(shipWhiteImgUrl);
            
            // Load images for enemy 3 ship
            shipBodyImgUrl = this.getClass().getResource("/res/enemy003.png");
            Enemy.enemyShip003 = ImageIO.read(shipBodyImgUrl);
            shipWhiteImgUrl = this.getClass().getResource("/res/enemy003_white.png");
            Enemy.enemyShip003White = ImageIO.read(shipWhiteImgUrl);
            
            // Load images for enemy 4 ship
            shipBodyImgUrl = this.getClass().getResource("/res/enemy004.png");
            Enemy.enemyShip004 = ImageIO.read(shipBodyImgUrl);
            shipWhiteImgUrl = this.getClass().getResource("/res/enemy004_white.png");
            Enemy.enemyShip004White = ImageIO.read(shipWhiteImgUrl);
            
            // Load images for enemy 5 ship
            shipBodyImgUrl = this.getClass().getResource("/res/enemy005.png");
            Enemy.enemyShip005 = ImageIO.read(shipBodyImgUrl);
            shipWhiteImgUrl = this.getClass().getResource("/res/enemy005_white.png");
            Enemy.enemyShip005White = ImageIO.read(shipWhiteImgUrl);
            
            // Load images for enemy 6 ship
            shipBodyImgUrl = this.getClass().getResource("/res/enemy006.png");
            Enemy.enemyShip006 = ImageIO.read(shipBodyImgUrl);
            shipWhiteImgUrl = this.getClass().getResource("/res/enemy006_white.png");
            Enemy.enemyShip006White = ImageIO.read(shipWhiteImgUrl);
            
            // Load images for enemy 7 ship
            shipBodyImgUrl = this.getClass().getResource("/res/enemy007.png");
            Enemy.enemyShip007 = ImageIO.read(shipBodyImgUrl);
            shipWhiteImgUrl = this.getClass().getResource("/res/enemy007_white.png");
            Enemy.enemyShip007White = ImageIO.read(shipWhiteImgUrl);
            
            // Load images for enemy 8 ship
            shipBodyImgUrl = this.getClass().getResource("/res/enemy008.png");
            Enemy.enemyShip008 = ImageIO.read(shipBodyImgUrl);
            shipWhiteImgUrl = this.getClass().getResource("/res/enemy008_white.png");
            Enemy.enemyShip008White = ImageIO.read(shipWhiteImgUrl);
            
            // Load images for enemy 9 ship
            shipBodyImgUrl = this.getClass().getResource("/res/enemy009.png");
            Enemy.enemyShip009 = ImageIO.read(shipBodyImgUrl);
            shipWhiteImgUrl = this.getClass().getResource("/res/enemy009_white.png");
            Enemy.enemyShip009White = ImageIO.read(shipWhiteImgUrl);
            
            // Load images for enemy 10 ship
            shipBodyImgUrl = this.getClass().getResource("/res/enemy010.png");
            Enemy.enemyShip010 = ImageIO.read(shipBodyImgUrl);
            shipWhiteImgUrl = this.getClass().getResource("/res/enemy010_white.png");
            Enemy.enemyShip010White = ImageIO.read(shipWhiteImgUrl);
            
            // Load images for enemy 11 ship
            shipBodyImgUrl = this.getClass().getResource("/res/enemy011.png");
            Enemy.enemyShip011 = ImageIO.read(shipBodyImgUrl);
            shipWhiteImgUrl = this.getClass().getResource("/res/enemy011_white.png");
            Enemy.enemyShip011White = ImageIO.read(shipWhiteImgUrl);
            
            // Load images for enemy 12 ship
            shipBodyImgUrl = this.getClass().getResource("/res/enemy012.png");
            Enemy.enemyShip012 = ImageIO.read(shipBodyImgUrl);
            shipWhiteImgUrl = this.getClass().getResource("/res/enemy012_white.png");
            Enemy.enemyShip012White = ImageIO.read(shipWhiteImgUrl);
            
            // Load images for enemy 13 ship
            shipBodyImgUrl = this.getClass().getResource("/res/enemy013.png");
            Enemy.enemyShip013 = ImageIO.read(shipBodyImgUrl);
            shipWhiteImgUrl = this.getClass().getResource("/res/enemy013_white.png");
            Enemy.enemyShip013White = ImageIO.read(shipWhiteImgUrl);
            
            // Image of explosion animation.
            URL explosionAnimImgUrl = this.getClass().getResource("/res/explosion_anim.png");
            explosionAnimImg = ImageIO.read(explosionAnimImgUrl);
            
            // Image of shuriken animation.
            URL shurikenAnimImgUrl = this.getClass().getResource("/res/shuriken.png");
            EnemyBullet.shurikenAnimImg = ImageIO.read(shurikenAnimImgUrl);
            
            // Image of rocket animation.
            URL rocketAnimImgUrl = this.getClass().getResource("/res/rocket.png");
            EnemyBullet.rocketAnimImg = ImageIO.read(rocketAnimImgUrl);            
            
            // Image of BLUE_BUBBLE bullet
            URL blueBubbleImgImgUrl = this.getClass().getResource("/res/blue_bubble.png");
            EnemyBullet.blueBubbleImg = ImageIO.read(blueBubbleImgImgUrl);

            // Image of RAINBOW bullet
            URL rainbowImgImgUrl = this.getClass().getResource("/res/rainbow.png");
            EnemyBullet.rainbowImg = ImageIO.read(rainbowImgImgUrl);
            
            // Image of player life.
            URL playerLifeImgUrl = this.getClass().getResource("/res/player_life.png");
            PlayerShip.playerLifeImg = ImageIO.read(playerLifeImgUrl);
            
            // Image of life bonus.
            URL bonusLifeImgUrl = this.getClass().getResource("/res/soyuz.png");
            BonusLife.bonusLifeImg = ImageIO.read(bonusLifeImgUrl);
            
            // Image of equipment bonus.
            URL bonusEquipmentImgUrl = this.getClass().getResource("/res/spaceshuttle.png");
            BonusEquipment.bonusEquipmentImg = ImageIO.read(bonusEquipmentImgUrl);            
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Restart game - reset some variables.
     */
    public void RestartGame()
    {
        player.Reset(Framework.frameWidth / 2, Framework.frameHeight / 2);
        
        Enemy.restartEnemy();
        
        Bullet.timeOfLastCreatedBullet = 0;
        
        // Empty all the lists.
        enemyShipList.clear();
        bulletsList.clear();
        enemyBulletsList.clear();
        explosionsList.clear();
        bonusLifeList.clear();
        bonusEquipmentList.clear();
        
        // Statistics
        runAwayEnemies = 0;
        destroyedEnemies = 0;
        playerScore = 0;
        
        // Levels
        enemyType = 0;
        queueStep = 0;
        NumberCreatedEnemies = 0;        
        isBoss = false;
        bossDead = false;
        
        Sound.GAME_OVER.stop();
        Sound.GAME.loop();
    }
    
    
    /**
     * Update game logic.
     * 
     * @param gameTime The elapsed game time in nanoseconds.
     * @param mousePosition current mouse position.
     */
    public void UpdateGame(long gameTime, Point mousePosition)
    {
        /* Player */

        if(!isPlayerAlive() && !PlayerShip.isExploded) {    	
        	// Add explosion of ship.
        	Animation expAnim = new Animation(explosionAnimImg, 134, 134, 12, 45, false, player.xCoordinate - player.shipBodyImg.getWidth() / 3, player.yCoordinate, 0); // Substring 1/3 explosion image height (explosionAnimImg.getHeight()/3) so that explosion is drawn more at the center of the ship.
        	explosionsList.add(expAnim);
        	Sound.EXPLOSION.play();
        	
        	PlayerShip.isExploded = true;
        	
        	if (player.life > 1 && !PlayerShip.isGhost) {
            	PlayerShip.isGhost    = true;
            	PlayerShip.lastTimeGhost = System.nanoTime();
            	
        		player.health = player.healthInit;
        		player.life -= 1;
                Bullet.damagePower = Bullet.damagePowerInit;
                Bullet.r = 0;
        		PlayerShip.isExploded = false;
        	}
        }
    	
        // When player is destroyed and all explosions are finished showing we change game status.
        if( !isPlayerAlive() && explosionsList.isEmpty() && player.health <= 0 && player.life <= 1){
            Framework.gameState = Framework.GameState.GAMEOVER;
            Sound.GAME.stop();
            Sound.BOSS.stop();
            Sound.GAME_OVER.play();            
            return; // If player is destroyed, we don't need to do thing below.
        }

        // If player is alive we update him.
        if(isPlayerAlive() || !PlayerShip.isGhost){
            isPlayerShooting(gameTime, mousePosition);
            player.isMoving();
            player.Update();
        }
        
        /* Stars */
        updateStars();
        
        /* Bullets */
        updateBullets();
        
        /* Enemies */
        if (NumberCreatedEnemies == queueEnemy[queueStep] && queueEnemy[queueStep + 1] != 0) {
        	NumberCreatedEnemies = 0;
        	queueStep += 1;
        	if (queueStep > 0 && queueEnemy[queueStep - 1] > queueEnemy[queueStep]) enemyType += 1;        	
        	Enemy.timeBetweenNewEnemies = Framework.secInNanosec * (4 - queueEnemy[queueStep] / 10);
        	
        	if (queueStep == 36) {
        		Sound.GAME.stop();
        		Sound.BOSS.loop();
        	}
        }
        if (isBoss && bossDead && explosionsList.isEmpty()) {
            Framework.gameState = Framework.GameState.GAMEOVER;
            Sound.GAME.stop();
            Sound.BOSS.stop();
            Sound.GAME_OVER.play();
            return;
        }        
        if (NumberCreatedEnemies < queueEnemy[queueStep]) {
        	createEnemyShip(gameTime, enemyType);	
        }        
        updateEnemies(gameTime);
        updateEnemyBullets();
        
        /* Life bonus */
        createBonusLife(gameTime);
        updateBonusLife();
        
        /* Equipment bonus */
        createBonusEquipment(gameTime);
        updateBonusEquipment();        
        
        /* Explosions */
        updateExplosions();
    }
    
    /**
     * Draw the game to the screen.
     * 
     * @param g2d Graphics2D
     * @param mousePosition current mouse position.
     */
    public void Draw(Graphics2D g2d, Point mousePosition, long gameTime)
    {
        // Draw all stars.
		for (int i = 0; i < stars.size(); i++) {
			stars.get(i).draw(g2d);
		}         	
    	
		// Draws player.
        if(isPlayerAlive())
            player.Draw(g2d);
        
        // Draws all the enemies.
        for(int i = 0; i < enemyShipList.size(); i++)
        {
            enemyShipList.get(i).Draw(g2d);
        }
        
        // Draws player the bullets. 
        for(int i = 0; i < bulletsList.size(); i++)
        {
            bulletsList.get(i).Draw(g2d);
        }
        
        // Draws enemy the bullets. 
        for(int i = 0; i < enemyBulletsList.size(); i++)
        {
        	enemyBulletsList.get(i).Draw(g2d);
        }
        
        // Draw all explosions.
        for(int i = 0; i < explosionsList.size(); i++)
        {
            explosionsList.get(i).Draw(g2d);
        }
        
        // Draw bonus life ship.
        for(int i = 0; i < bonusLifeList.size(); i++)
        {
        	bonusLifeList.get(i).Draw(g2d);
        }        
        
        // Draw bonus equipment ship.
        for(int i = 0; i < bonusEquipmentList.size(); i++)
        {
        	bonusEquipmentList.get(i).Draw(g2d);
        }                
        
        // Draw statistics
        g2d.setFont(font);
        g2d.setColor(Color.LIGHT_GRAY);
        
        g2d.drawString(formatTime(gameTime), Framework.frameWidth / 2 - 40, 21);
        g2d.drawString("SCORE: " + Integer.toString(playerScore), Framework.frameWidth - 200 , 21);
        g2d.drawString("DESTROYED: " + destroyedEnemies, Framework.frameWidth - 200, 38);
        g2d.drawString("RUNAWAY: "   + runAwayEnemies,   Framework.frameWidth - 200, 55);

        
        for (int i = 0; i < player.life; ++i) {
        	g2d.drawImage(PlayerShip.playerLifeImg, 11 + i * (PlayerShip.playerLifeImg.getWidth() + 4), 11, null);
        }

        g2d.setColor(new Color(107, 107, 107));
      	g2d.fillRect(11, 40, 11 + player.health, 17);
      	g2d.setColor(Color.WHITE);
      	g2d.drawRect(11, 40, 11 + player.healthInit, 17);          	
    }
    
    /**
     * Draws some game statistics when game is over.
     * 
     * @param g2d Graphics2D
     * @param gameTime Elapsed game time.
     */
    public void DrawStatistic(Graphics2D g2d, long gameTime){
    	g2d.setColor(Color.WHITE);
        g2d.drawString("Time: " + formatTime(gameTime),                Framework.frameWidth/2 - 50, Framework.frameHeight/6 + 30);
        g2d.drawString("Score: " + playerScore,                        Framework.frameWidth/2 - 50, Framework.frameHeight/6 + 50);
        g2d.drawString("Destroyed enemies: " + destroyedEnemies,       Framework.frameWidth/2 - 50, Framework.frameHeight/6 + 70);
        g2d.drawString("Runaway enemies: "   + runAwayEnemies,         Framework.frameWidth/2 - 50, Framework.frameHeight/6 + 90);
        g2d.setFont(font);
        g2d.drawString("Statistics: ",                                 Framework.frameWidth/2 - 50, Framework.frameHeight/6);
    }
    
    public String getFinalResult() {
    	String retVal;
    	if (isBoss && bossDead)
    		retVal = "YOU WIN";
    	else
    		retVal = "GAME OVER";
    		
    	return retVal;
    }

    
    /**
     * Format given time into 00:00 format.
     * 
     * @param time Time that is in nanoseconds.
     * @return Time in 00:00 format.
     */
    private static String formatTime(long time){
            // Given time in seconds.
            int sec = (int)(time / Framework.milisecInNanosec / 1000);

            // Given time in minutes and seconds.
            int min = sec / 60;
            sec = sec - (min * 60);

            String minString, secString;

            if(min <= 9)
                minString = "0" + Integer.toString(min);
            else
                minString = "" + Integer.toString(min);

            if(sec <= 9)
                secString = "0" + Integer.toString(sec);
            else
                secString = "" + Integer.toString(sec);

            return minString + ":" + secString;
    }
    
    
    
    
    
    /*
     * 
     * Methods for updating the game. 
     * 
     */
    
     
    /**
     * Check if player is alive. If not, set game over status.
     * 
     * @return True if player is alive, false otherwise.
     */
    private boolean isPlayerAlive()
    {
        if(player.health <= 0) {
            return false;
        }
        return true;
    }
    
    /**
     * Checks if the player is shooting with the machine gun and creates bullets if he shooting.
     * 
     * @param gameTime Game time.
     */
    private void isPlayerShooting(long gameTime, Point mousePosition)
    {
        if(player.isShooting(gameTime) && !PlayerShip.isGhost)
        {
            Bullet.timeOfLastCreatedBullet = gameTime;
            
            Bullet b = new Bullet(player.machineGunXcoordinate, player.machineGunYcoordinate, mousePosition);
            bulletsList.add(b);
            Sound.PLAYER_SHOT.play();
        }
    }
    
    
    /**
     * Creates a new enemy if it's time.
     * 
     * @param gameTime Game time.
     */
    private void createEnemyShip(long gameTime, int enemyType)
    {
        if(gameTime - Enemy.timeOfLastCreatedEnemy >= Enemy.timeBetweenNewEnemies)
        {
            Enemy eh = new Enemy();
            eh.Initialize(enemyType, player);
            
            // Check boss
            if (eh.EnemyArray[enemyType][0] == "ENEMY013") isBoss = true;

            // Add created enemy to the list of enemies.
            enemyShipList.add(eh);
            // Speed up enemy speed and aperence.
            Enemy.speedUp();
            NumberCreatedEnemies += 1;
            // Sets new time for last created enemy.
            Enemy.timeOfLastCreatedEnemy = gameTime;
        }
    }
    
    /**
     * Updates all enemies.
     * Move the ship and checks if he left the screen.
     * Updates ship animations.
     * Checks if enemy was destroyed.
     * Checks if any enemy collision with player.
     */
    private void updateEnemies(long gameTime)
    {
        for(int i = 0; i < enemyShipList.size(); i++)
        {
            Enemy eh = enemyShipList.get(i);
            
            eh.Update();
            
            // Is chrashed with player?
            Rectangle playerRectangel = new Rectangle(player.xCoordinate, player.yCoordinate, player.shipBodyImg.getWidth(), player.shipBodyImg.getHeight());
            Rectangle enemyRectangel = new Rectangle(eh.xCoordinate, eh.yCoordinate, eh.enemy_shipBodyImg.getWidth(), eh.enemy_shipBodyImg.getHeight());
            if(playerRectangel.intersects(enemyRectangel) && !PlayerShip.isGhost){
                player.health -= eh.health;
                
                if  (player.health > 0 && player.life > 0) {
                    // Add explosion of ship.
                    Animation expAnim = new Animation(explosionAnimImg, 134, 134, 12, 45, false, eh.xCoordinate - eh.enemy_shipBodyImg.getWidth() / 3, eh.yCoordinate, 0); // Substring 1/3 explosion image height (explosionAnimImg.getHeight()/3) so that explosion is drawn more at the center of the ship.
                    explosionsList.add(expAnim);
                    Sound.EXPLOSION.play();
                    
                    if (eh.EnemyArray[eh.enemyType][0] != "ENEMY013") {
	                    // Increase the destroyed enemies counter.
	                    destroyedEnemies++;
	                    
	                    // Remove ship from the list.
	                    enemyShipList.remove(i);
                    }
                    
                    // Ship was destroyed so we can move to next ship.
                    continue;
                }
                else {
	                // Add explosion of player ship.
	                for(int exNum = 0; exNum < 3; exNum++){
	                    Animation expAnim = new Animation(explosionAnimImg, 134, 134, 12, 45, false, player.xCoordinate - random.nextInt(100), player.yCoordinate + exNum*60, exNum * 200 +random.nextInt(100));
	                    explosionsList.add(expAnim);
	                }
	                
	                // Add explosion of enemy ship.
	                for(int exNum = 0; exNum < 3; exNum++){
	                    Animation expAnim = new Animation(explosionAnimImg, 134, 134, 12, 45, false, eh.xCoordinate - random.nextInt(100), eh.yCoordinate + exNum*60, exNum * 200 +random.nextInt(100));
	                    explosionsList.add(expAnim);
	                }
	                
	                Sound.EXPLOSION.play();

	                if (eh.EnemyArray[eh.enemyType][0] != "ENEMY013") {
	                    // Increase the destroyed enemies counter.
	                    destroyedEnemies++;
	                    
	                    // Remove ship from the list.
	                    enemyShipList.remove(i);
	                }

                    // Because player crashed with enemy the game will be over so we don't need to check other enemies.
	                continue;
                }
            }
            
            // Check health.
            if(eh.health <= 0){
                // Add explosion of ship.
                Animation expAnim = new Animation(explosionAnimImg, 134, 134, 12, 45, false, eh.xCoordinate - eh.enemy_shipBodyImg.getWidth() / 3, eh.yCoordinate, 0); // Substring 1/3 explosion image height (explosionAnimImg.getHeight()/3) so that explosion is drawn more at the center of the ship.
                explosionsList.add(expAnim);
                Sound.EXPLOSION.play();
                
                // Check boss
                if (eh.EnemyArray[eh.enemyType][0] == "ENEMY013") bossDead = true;                

                // Increase the destroyed enemies counter.
                destroyedEnemies++;
                playerScore += Integer.parseInt(eh.EnemyArray[eh.enemyType][8]);
                
                // Remove ship from the list.
                enemyShipList.remove(i);
                
                // Ship was destroyed so we can move to next ship.
                continue;
            }
            
            // Enemy is shooting
            if (enemyShipList.get(i).yCoordinate > 100 && ((gameTime - enemyShipList.get(i).timeOfLastCreatedBullet) >= enemyShipList.get(i).timeBetweenNewBullets)) {
            	enemyShipList.get(i).timeOfLastCreatedBullet = gameTime;
                
                EnemyBullet b = new EnemyBullet( enemyShipList.get(i).xCoordinate
                		                       , enemyShipList.get(i).yCoordinate
                		                       , player
                		                       , eh
                		                       , enemyShipList.get(i).EnemyArray[enemyShipList.get(i).enemyType][3]
                		                       , enemyShipList.get(i).EnemyArray[enemyShipList.get(i).enemyType][4]
                		                       , enemyShipList.get(i).EnemyArray[enemyShipList.get(i).enemyType][5]);
                enemyBulletsList.add(b);
                
                if (enemyShipList.get(i).EnemyArray[enemyShipList.get(i).enemyType][0] == "ENEMY013") {
                    EnemyBullet b1 = new EnemyBullet( enemyShipList.get(i).xCoordinate - 50
  							                        , enemyShipList.get(i).yCoordinate - 15
							                        , player
							                        , eh
							                        , "BLUE_ROUND"
							                        , enemyShipList.get(i).EnemyArray[enemyShipList.get(i).enemyType][4]
							                        , "5");
                    EnemyBullet b2 = new EnemyBullet( enemyShipList.get(i).xCoordinate + 50
							                        , enemyShipList.get(i).yCoordinate - 15
							                        , player
							                        , eh
							                        , "BLUE_ROUND"
							                        , enemyShipList.get(i).EnemyArray[enemyShipList.get(i).enemyType][4]
							                        , "5");                    
                    enemyBulletsList.add(b1);                	
                    enemyBulletsList.add(b2);
                }
                
                Sound.ENEMY_SHOT.play();
            }
            
            // If the current enemy is left the screen we remove him from the list and update the runAwayEnemies variable.
            if(eh.isBottomScreen())
            {
                // Check boss
                if (eh.EnemyArray[eh.enemyType][0] == "ENEMY013") bossDead = true;
                
                enemyShipList.remove(i);
                runAwayEnemies++;
            }
        }
    }
    
    /**
     * Update stars.
     * It moves stars in space.
     */
    private void updateStars() {
		if (System.nanoTime() - Star.lastStr >= Star.timeBetween) {
			stars.add(new Star(Star.StrArr[Star.nextStr][0] - random.nextInt(800),
					        Star.StrArr[Star.nextStr][1],
					        Star.StrArr[Star.nextStr][2],
					        Star.StrArr[Star.nextStr][3]));
			Star.nextStr++;
            if(Star.nextStr >= Star.StrArr.length)
            	Star.nextStr = 0;
			
			Star.lastStr = System.nanoTime();
		}
		
		for (int i=0; i < stars.size(); i++) {
			stars.get(i).update();
			
			if (stars.get(i).y > 800) {
				stars.remove(i);
			}
		}    	
    }
    
    
    /**
     * Update bullets. 
     * It moves bullets.
     * Checks if the bullet is left the screen.
     * Checks if any bullets is hit any enemy.
     */
    private void updateBullets()
    {
        for(int i = 0; i < bulletsList.size(); i++)
        {
            Bullet bullet = bulletsList.get(i);
            
            // Move the bullet.
            bullet.Update();
            
            // Is left the screen?
            if(bullet.isItLeftScreen()){
                bulletsList.remove(i);
                // Bullet have left the screen so we removed it from the list and now we can continue to the next bullet.
                continue;
            }
            
            // Did hit any enemy?
            // Rectangle of the bullet image.
            Rectangle bulletRectangle = new Rectangle(bullet.x, bullet.y, Bullet.r + Bullet.radius, Bullet.r + Bullet.radius);
            // Go trough all enemis.
            for(int j = 0; j < enemyShipList.size(); j++)
            {
                Enemy eh = enemyShipList.get(j);

                // Current enemy rectangle.
                Rectangle enemyRectangel = new Rectangle(eh.xCoordinate, eh.yCoordinate, eh.enemy_shipBodyImg.getWidth(), eh.enemy_shipBodyImg.getHeight());

                // Is current bullet over currnet enemy?
                if(bulletRectangle.intersects(enemyRectangel))
                {
                    // Bullet hit the enemy so we reduce his health.
                    eh.health -= Bullet.damagePower;
                    
                    eh.isHit = true;
                    eh.lastTimeHit = System.nanoTime();
                    Sound.ENEMY_DAMAGE.play();
                    
                    // Bullet was also destroyed so we remove it.
                    bulletsList.remove(i);
                    
                    // That bullet hit enemy so we don't need to check other enemies.
                    break;
                }
            }
        }
    }

    /**
     * Update enemy bullets. 
     * It moves enemy bullets.
     * Checks if the bullet is left the screen.
     * Checks if any bullets is hit player.
     */
    private void updateEnemyBullets()
    {
        for(int i = 0; i < enemyBulletsList.size(); i++)
        {
        	EnemyBullet bullet = enemyBulletsList.get(i);
            
            // Move the bullet.
            bullet.Update();
            
            // Is left the screen?
            if(bullet.isItLeftScreen()){
            	enemyBulletsList.remove(i);
                // Bullet have left the screen so we removed it from the list and now we can continue to the next bullet.
                continue;
            }
            
            // Did hit any enemy?
            // Rectangle of the enemy bullet image.
            Rectangle bulletRectangle1;
            Rectangle bulletRectangle2;
            if (bullet.kindBullet == "RED_DOUBLE_LINES") {
            	bulletRectangle1 = new Rectangle(bullet.x - 35, bullet.y, bullet.bulletImg.getWidth(), bullet.bulletImg.getHeight());
                bulletRectangle2 = new Rectangle(bullet.x + 35, bullet.y, bullet.bulletImg.getWidth(), bullet.bulletImg.getHeight());
            }
            else if (bullet.kindBullet == "SHURIKEN") {
            	bulletRectangle1 = new Rectangle(bullet.x, bullet.y, bullet.bulletImg.getWidth() / 3, bullet.bulletImg.getHeight());
            	bulletRectangle2 = new Rectangle(bullet.x, bullet.y, bullet.bulletImg.getWidth() / 3, bullet.bulletImg.getHeight());
            }
            else if (bullet.kindBullet == "BLUE_BUBBLE") {
            	bulletRectangle1 = new Rectangle(bullet.x - 25, bullet.y - 10, bullet.bulletImg.getWidth() / 3, bullet.bulletImg.getHeight());
            	bulletRectangle2 = new Rectangle(bullet.x - 25, bullet.y - 10, bullet.bulletImg.getWidth() / 3, bullet.bulletImg.getHeight());
            }
            else if (bullet.kindBullet == "RAINBOW") {
            	bulletRectangle1 = new Rectangle(bullet.x - 20, bullet.y - 10, bullet.bulletImg.getWidth() / 3, bullet.bulletImg.getHeight());
            	bulletRectangle2 = new Rectangle(bullet.x - 20, bullet.y - 10, bullet.bulletImg.getWidth() / 3, bullet.bulletImg.getHeight());
            }           
            else if (bullet.kindBullet == "ROCKET") {
            	bulletRectangle1 = new Rectangle(bullet.x, bullet.y, bullet.bulletImg.getWidth() / 9, bullet.bulletImg.getHeight());
            	bulletRectangle2 = new Rectangle(bullet.x, bullet.y, bullet.bulletImg.getWidth() / 9, bullet.bulletImg.getHeight());
            }            
            else {
            	bulletRectangle1 = new Rectangle(bullet.x, bullet.y, bullet.bulletImg.getWidth(), bullet.bulletImg.getHeight());
            	bulletRectangle2 = new Rectangle(bullet.x, bullet.y, bullet.bulletImg.getWidth(), bullet.bulletImg.getHeight());
            }
            	
            // Player rectangle.
            Rectangle playerRectangel = new Rectangle(player.xCoordinate, player.yCoordinate, player.shipBodyImg.getWidth(), player.shipBodyImg.getHeight());

            // Is current bullet over player?
            if((bulletRectangle1.intersects(playerRectangel) || bulletRectangle2.intersects(playerRectangel)) && !PlayerShip.isGhost)
            {   
                // Bullet hit the player so we reduce his health.
                player.health -= bullet.damagePower;
                PlayerShip.isHit = true;
                PlayerShip.lastTimeHit = System.nanoTime();
                Sound.PLAYER_DAMAGE.play();
                
                // Bullet was also destroyed so we remove it.
                enemyBulletsList.remove(i);
                 
                // That bullet hit enemy so we don't need to check other enemies.
                //break;
            }
        }    	
    }
    

    
    /**
     * Updates all the animations of an explosion and remove the animation when is over.
     */
    private void updateExplosions()
    {
        for(int i = 0; i < explosionsList.size(); i++)
        {
            // If the animation is over we remove it from the list.
            if(!explosionsList.get(i).active)
                explosionsList.remove(i);
        }
    }
    
    
    /**
     * Creates a new life bonus if it's time.
     * 
     * @param gameTime Game time.
     */
    private void createBonusLife(long gameTime)
    {
        if(gameTime - BonusLife.timeOfLastCreatedBonus >= BonusLife.timeBetweenNewBonus)
        {
        	BonusLife bl = new BonusLife();
        	bonusLifeList.add(bl);
            BonusLife.timeOfLastCreatedBonus = gameTime;
        }
    }
    
    /**
     * Updates bonus life.
     */
    private void updateBonusLife()
    {
        for(int i = 0; i < bonusLifeList.size(); i++)
        {
        	BonusLife bl = bonusLifeList.get(i);
            
            bl.Update();
            
            // Is intersect with player?
            Rectangle playerRectangel = new Rectangle(player.xCoordinate, player.yCoordinate, player.shipBodyImg.getWidth(), player.shipBodyImg.getHeight());
            Rectangle bonusRectangel = new Rectangle(bl.xCoordinate, bl.yCoordinate, BonusLife.bonusLifeImg.getWidth(), BonusLife.bonusLifeImg.getHeight());
            if(playerRectangel.intersects(bonusRectangel)){
                if (player.life < 7) player.life += bl.life;
                player.health = player.healthInit;
                PlayerShip.lastTimeInfo = System.nanoTime();
                PlayerShip.infoText = "+LIFE";
                Sound.LIFE_BONUS.play();
                
                bonusLifeList.remove(i);
	            continue;
            }
        }
    }
    
    
    /**
     * Creates a new life bonus if it's time.
     * 
     * @param gameTime Game time.
     */
    private void createBonusEquipment(long gameTime)
    {
        if(gameTime - BonusEquipment.timeOfLastCreatedBonus >= BonusEquipment.timeBetweenNewBonus)
        {
        	BonusEquipment be = new BonusEquipment();
        	bonusEquipmentList.add(be);
        	BonusEquipment.timeOfLastCreatedBonus = gameTime;
        }
    }
    
    /**
     * Updates bonus life.
     */
    private void updateBonusEquipment()
    {
        for(int i = 0; i < bonusEquipmentList.size(); i++)
        {
        	BonusEquipment be = bonusEquipmentList.get(i);
            
            be.Update();
            
            // Is intersect with player?
            Rectangle playerRectangel = new Rectangle(player.xCoordinate, player.yCoordinate, player.shipBodyImg.getWidth(), player.shipBodyImg.getHeight());
            Rectangle bonusRectangel = new Rectangle(be.xCoordinate, be.yCoordinate, BonusEquipment.bonusEquipmentImg.getWidth(), BonusEquipment.bonusEquipmentImg.getHeight());
            if(playerRectangel.intersects(bonusRectangel)){
                PlayerShip.lastTimeInfo = System.nanoTime();
                
                if (BonusEquipment.LastEquipment == "INCREASE_DAMAGE") {
                	if (Bullet.damagePower < 100) Bullet.damagePower += 10;
                	PlayerShip.infoText = "+POWER";
                	BonusEquipment.LastEquipment = "INCREASE_BULLET";
                }
                else if (BonusEquipment.LastEquipment == "INCREASE_BULLET") {
                	if (Bullet.r < 28) Bullet.r += 4;
                	PlayerShip.infoText = "+SIZE";
                	BonusEquipment.LastEquipment = "INCREASE_DAMAGE";
                } 
                Sound.EQUIPMENT_BONUS.play();
                
                bonusEquipmentList.remove(i);
	            continue;
            }
        }
    }    
}
