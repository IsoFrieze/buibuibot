package com.dotsarecool.game.command;

import java.util.Random;

import com.dotsarecool.game.BuiBuiBot;
import com.dotsarecool.game.Command;
import com.dotsarecool.game.Cooldown;

public class WhatIfCommand extends Command {

	public WhatIfCommand(BuiBuiBot bui) {
		super(bui);
	}
	
	public void execute(String chan, String user, boolean op, String login, String host, String text) {
		if (!op && !Cooldown.cooledDown("whatif")) return;
		new Cooldown("whatif", 30);
		
		Random rand = new Random();
		String [] messages = {
				"I'll eat my shorts.",
				"the world will go on.",
				"the planet will explode.",
				"absolutely nothing will happen.",
				"switchpalacecorner won't abuse the $sound command.",
				"bramz won't suck at smw.",
				"it's the end of the world as we know it.",
		};
		
		bui.channelMessage(chan, user, text.substring(5) + ", then " + messages[rand.nextInt(messages.length)]);
	}
	
}