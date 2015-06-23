package com.dotsarecool.game.command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.dotsarecool.game.BuiBuiBot;
import com.dotsarecool.game.Command;

public class BanCommand extends Command {

	public BanCommand(BuiBuiBot bui) {
		super(bui);
	}
	
	public void execute(String chan, String user, boolean op, String login, String host, String text) {
		String [] args = text.split(" ");
		if (!op) return;
		
		if (args.length > 1) {
			String ban = text.substring(text.indexOf(" ") + 1);
			if (ban.equals("dotsarecoolp")) {
				bui.channelMessage(chan, user, "Nope!");
				return;
			}
			try {
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("res/bannedusers.txt", true)));
				out.printf("%s\n", ban);

				bui.channelMessage(chan, user, "I won't listen to " + ban + " anymore.");
				out.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				bui.channelMessage(chan, user, "Bans are unavailable. :(");
			}
		}
	}
	
}