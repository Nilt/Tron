package sound;

public class AudioController {

	public static AudioUnit SplashSound;
	public static AudioUnit bgMusic;
	
	private int musicPosition;
	private boolean playingMusic = false;
	
	private final static String MUSICPATH = "Assets/Sound/Music/";
	private final static String SFXPATH = "Assets/Sound/SFX/";
	
	public AudioController() {
		bgMusic = new AudioUnit(MUSICPATH + "Tron3.wav", 0);
		
	}
	
	
	public void playMusic() {
		bgMusic.play();
	}
	
	public void stopMusic() {
		bgMusic.stop();
	}
	
	public static void jump() {
		AudioUnit temp = new AudioUnit("Assets/Sound/SFX/jump.wav", -20f);
		temp.playOnce();
	}
	
	public static void pause() {
		AudioUnit temp = new AudioUnit(SFXPATH+"pause.wav", -20f);
		temp.playOnce();
	}
	
	public static void playSplash() {
		AudioUnit temp = new AudioUnit(SFXPATH+"splash.wav", 0);
		temp.playOnce();
	}
	
	public static void playExplo() {
		AudioUnit temp = new AudioUnit(SFXPATH+"expo.wav", -5f);
		temp.playOnce();
	}
	
	public void pauseMusic(boolean b) {
		if(b) {
			musicPosition = bgMusic.getMusicPosition();
			System.out.println("Music Poisitoin" + bgMusic.getMusicPosition());
			bgMusic.stop();
		} else if(!b) {
			bgMusic.playFromPosition(musicPosition);
			//bgMusic.play();
		}
	}
	
	public AudioUnit getBGMusic() {
		return bgMusic;
	}
}
