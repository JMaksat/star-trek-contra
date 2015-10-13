package ST;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Sound of game.
 * 
 * @author Maksat E.
 */

public class Sound {
	public static final AudioClip BOSS            = Applet.newAudioClip(Sound.class.getResource("/res/boss.wav"));
	public static final AudioClip ENEMY_DAMAGE    = Applet.newAudioClip(Sound.class.getResource("/res/enemy_damage.wav"));
	public static final AudioClip ENEMY_SHOT      = Applet.newAudioClip(Sound.class.getResource("/res/enemy_shot.wav"));
	public static final AudioClip EQUIPMENT_BONUS = Applet.newAudioClip(Sound.class.getResource("/res/equipment_bonus.wav"));
	public static final AudioClip EXPLOSION       = Applet.newAudioClip(Sound.class.getResource("/res/explosion.wav"));
	public static final AudioClip GAME            = Applet.newAudioClip(Sound.class.getResource("/res/game.wav"));
	public static final AudioClip GAME_OVER       = Applet.newAudioClip(Sound.class.getResource("/res/game_over.wav"));
	public static final AudioClip INTRO           = Applet.newAudioClip(Sound.class.getResource("/res/intro.wav"));
	public static final AudioClip LIFE_BONUS      = Applet.newAudioClip(Sound.class.getResource("/res/life_bonus.wav"));
	public static final AudioClip PLAYER_DAMAGE   = Applet.newAudioClip(Sound.class.getResource("/res/player_damage.wav"));
	public static final AudioClip PLAYER_SHOT     = Applet.newAudioClip(Sound.class.getResource("/res/player_shot.wav"));
}
