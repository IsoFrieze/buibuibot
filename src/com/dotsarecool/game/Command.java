package com.dotsarecool.game;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.TreeMap;

import com.dotsarecool.game.command.*;

public abstract class Command {
	
	public static Map<String, Class<?>> map = new TreeMap<String, Class<?>>();
	public static BuiBuiBot bot;
	public static char COMMAND_PREFIX = '$';
	
	public BuiBuiBot bui;
	
	protected Command(BuiBuiBot bui) {
		this.bui = bui;
	}
	
	public abstract void execute(String chan, String user, boolean op, String login, String host, String text);
	
	public static void setupCommands() {
		map.put("ban", BanCommand.class);
		map.put("die", DieCommand.class);
		map.put("leave", LeaveCommand.class);
		map.put("quote", QuoteCommand.class);
		map.put("whatis", WhatIsCommand.class);
		map.put("sound", SoundCommand.class);
		map.put("play", SoundCommand.class);
		map.put("feature", FeatureCommand.class);
		map.put("suggest", FeatureCommand.class);
		map.put("join", JoinCommand.class);
		map.put("help", HelpCommand.class);
		map.put("fuck", FuckCommand.class);
		map.put("commands", HelpCommand.class);
		map.put("$", DollarCommand.class);
	}
	
	// returns a new Command if text is a command, null if not
	public static Command getCommand(String text) {
		if (text.charAt(0) == COMMAND_PREFIX) {
			String command;
			if (text.indexOf(' ') == -1) {
				command = text.substring(1);
			} else {
				command = text.substring(1, text.indexOf(' '));
			}
			command = command.toLowerCase();
			
			if (map.containsKey(command)) {
				Class<?> clas = map.get(command);
				try {
					Constructor<?> cons = clas.getConstructor(BuiBuiBot.class);
					Object instance = cons.newInstance(bot);
					return (Command)instance;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		} else if (text.toLowerCase().indexOf("what if") == 0) {
			return new WhatIfCommand(bot);
		}
		return null;
	}
	
}