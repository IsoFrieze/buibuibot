package com.dotsarecool.game.command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.dotsarecool.game.BuiBuiBot;
import com.dotsarecool.game.Command;

public class DollarCommand extends Command {

	public DollarCommand(BuiBuiBot bui) {
		super(bui);
	}
	
	public void execute(String chan, String user, boolean op, String login, String host, String text) {
		String [] args = text.split(" ");
		if (!op) return;
		
		if (args.length > 1) {
			String word = text.substring(text.indexOf(" ") + 1);
			try {
				BufferedReader in = new BufferedReader(new FileReader("res/dollars.txt"));
				
				String line = in.readLine();
				while (line != null && line.indexOf(word) != 0) {
					line = in.readLine();
				}
				
				if (line == null) {
					try {
						PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("res/dollars.txt", true)));
						String term = word.substring(0,word.indexOf(" "));
						String definition = word.substring(word.indexOf(" ") + 1);
						out.printf("%s - %s\n", term, definition);

						bui.channelMessage(chan, user, "Definition for " + term + " added.");
						out.close();
						
					} catch (Exception e) {
						e.printStackTrace();
						bui.channelMessage(chan, user, word + " not found.");
					}
				} else {
					bui.channelMessage(chan, user, line);
				}
				
				in.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				bui.channelMessage(chan, user, "$ is unavailable. :(");
			}
		}
		
	}
	
}