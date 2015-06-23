package com.dotsarecool.game.command;

import com.dotsarecool.game.BuiBuiBot;
import com.dotsarecool.game.Command;

public class DieCommand extends Command {

	public DieCommand(BuiBuiBot bui) {
		super(bui);
	}
	
	public void execute(String chan, String user, boolean op, String login, String host, String text) {
		if (!user.equalsIgnoreCase("dotsarecoolp")) return;
		
		bui.sendAction(chan, "is now dead.");
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		bui.disconnect();
		System.exit(0);
	}
	
}