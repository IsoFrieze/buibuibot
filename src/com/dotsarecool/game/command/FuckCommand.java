package com.dotsarecool.game.command;

import com.dotsarecool.game.BuiBuiBot;
import com.dotsarecool.game.Command;
import com.dotsarecool.game.Cooldown;

public class FuckCommand extends Command {

	public FuckCommand(BuiBuiBot bui) {
		super(bui);
	}
	
	public void execute(String chan, String user, boolean op, String login, String host, String text) {
		if (user.equals("satwikp") || !Cooldown.cooledDown("fuck")) return;
		new Cooldown("fuck", 10);
		
		bui.channelMessage(chan, user, "Fuck you, satwik! SwiftRage");
	}
	
}