package com.dotsarecool.game.command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.dotsarecool.game.BuiBuiBot;
import com.dotsarecool.game.Command;
import com.dotsarecool.game.Cooldown;

public class SoundCommand extends Command {

	public SoundCommand(BuiBuiBot bui) {
		super(bui);
	}
	
	public void execute(String chan, String user, boolean op, String login, String host, String text) {
		String [] args = text.split(" ");
		if (!op && !Cooldown.cooledDown("sound")) return;
		new Cooldown("sound", 300);
		
		if (args.length > 1) {
			String sound = text.substring(text.indexOf(" ") + 1).toLowerCase();
			try {
				BufferedReader in = new BufferedReader(new FileReader("res/sounds.txt"));
				
				String line = in.readLine();
				while (line != null && line.indexOf(sound) != 0) {
					line = in.readLine();
				}
				
				if (line == null) {
					bui.channelMessage(chan, user, sound + " sound effect not found.");
				} else {
					String soundFile = line.substring(sound.length() + 3);
					
					try {
						URL url = new URL(soundFile);
						Clip clip = AudioSystem.getClip();
						AudioInputStream stream = AudioSystem.getAudioInputStream(url);
						clip.open(stream);
						clip.start();
					} catch (Exception e) {
						e.printStackTrace();
						bui.channelMessage(chan, user, sound + " sound effect could not be played.");
						in.close();
						return;
					}
					
					bui.channelMessage(chan, user, sound + " sound effect played.");
				}
				
				in.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				bui.channelMessage(chan, user, "Sounds are unavailable. :(");
			}
		}
		
	}
	
}