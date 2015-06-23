package com.dotsarecool.game.command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.dotsarecool.game.BuiBuiBot;
import com.dotsarecool.game.Command;

public class WhatIsCommand extends Command {

	public WhatIsCommand(BuiBuiBot bui) {
		super(bui);
	}
	
	public void execute(String chan, String user, boolean op, String login, String host, String text) {
		String [] args = text.split(" ");
		
		if (args.length > 1) {
			String word = text.substring(text.indexOf(" ") + 1);
			try {
				BufferedReader in = new BufferedReader(new FileReader("res/whatis.txt"));
				
				String line = in.readLine();
				while (line != null && line.toLowerCase().indexOf(word.toLowerCase()) != 0) {
					line = in.readLine();
				}
				
				if (line == null) {
					try {
						PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("res/whatis_pending.txt", true)));
						out.printf("%25s %s\n", user, word);

						bui.channelMessage(chan, user, word + " not found. I'll look into it later.");
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
				bui.channelMessage(chan, user, "WhatIs is unavailable. :(");
			}
		}
		
	}
	
}