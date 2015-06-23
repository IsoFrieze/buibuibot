package com.dotsarecool.game.command;

import com.dotsarecool.game.BuiBuiBot;
import com.dotsarecool.game.Command;

public class LeaveCommand extends Command {

	public LeaveCommand(BuiBuiBot bui) {
		super(bui);
	}
	
	public void execute(String chan, String user, boolean op, String login, String host, String text) {
		String [] args = text.split(" ");
		if (!op) return;
		
		String channel = "#" + args[1];
		if (bui.isInChannel(channel)) {
			bui.sendMessage(channel, "Bye! o/");
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			bui.partChannel(channel);
		}
		
	}
	
}