package com.dotsarecool.game.command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.dotsarecool.game.BuiBuiBot;
import com.dotsarecool.game.Command;

public class FeatureCommand extends Command {

	public FeatureCommand(BuiBuiBot bui) {
		super(bui);
	}
	
	public void execute(String chan, String user, boolean op, String login, String host, String text) {
		String [] args = text.split(" ");
		
		if (args.length > 1) {
			String word = text.substring(text.indexOf(" ") + 1);
			try {
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("res/features.txt", true)));
				out.printf("%25s %s\n", user, word);

				bui.channelMessage(chan, user, "Your suggestion is appreciated!");
				out.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				bui.channelMessage(chan, user, "Feature suggestions are unavailable. :(");
			}
		}
		
	}
	
}