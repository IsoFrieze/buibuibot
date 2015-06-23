package com.dotsarecool.game.command;

import com.dotsarecool.game.BuiBuiBot;
import com.dotsarecool.game.Command;

public class HelpCommand extends Command {

	public HelpCommand(BuiBuiBot bui) {
		super(bui);
	}
	
	public void execute(String chan, String user, boolean op, String login, String host, String text) {
		bui.channelMessage(chan, user, "For more info, go to http://www.dotsarecool.com/twitch/buibuibot.html");
	}
	
}