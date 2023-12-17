package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip; //use this to import audio file
	URL soundURL[] = new URL[30];//to store the file path of these sound files
	
	public Sound() {
		
		soundURL[0]= getClass().getResource("/soud/BlueBoyAdventure.wav");
		soundURL[1]= getClass().getResource("/soud/burning.wav");
		soundURL[2]= getClass().getResource("/soud/coin.wav");
		soundURL[3]= getClass().getResource("/soud/gameover.wav");
		soundURL[4]= getClass().getResource("/soud/hitmonster.wav");
		soundURL[5]= getClass().getResource("/soud/receivedamage.wav");
		soundURL[6]= getClass().getResource("/soud/unlock.wav");
		soundURL[7]= getClass().getResource("/soud/powerup.wav");
	}
	
	public void setFile(int i) {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) {
			e.printStackTrace();
	        System.out.println("Erreur lors de l'ouverture du fichier audio.");
		}
	}
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
}
