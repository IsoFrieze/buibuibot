package com.dotsarecool.game.command;

import com.dotsarecool.game.BuiBuiBot;
import com.dotsarecool.game.Command;

public class JoinCommand extends Command {
	
	BuiBuiBot bui;

	public JoinCommand(BuiBuiBot bui) {
		super(bui);
		this.bui = bui;
	}
	
	public void execute(String chan, String user, boolean op, String login, String host, String text) {
		String [] args = text.split(" ");
		if (args.length > 1 && !args[1].equalsIgnoreCase(user)) return;
		
		this.bui.joinChannel("#" + args[1]);
		this.bui.channelMessage(chan, user, "Joined " + user + "'s channel.");
	}
	
}