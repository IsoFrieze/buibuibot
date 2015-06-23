package com.dotsarecool.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

public class BuiBuiBot extends PircBot {
	
	ArrayList<Tuple<String,String>> ops;
	ArrayList<String> channels;
	ChatWindow window;
	
	public BuiBuiBot(String name) {
		ops = new ArrayList<>();
		channels = new ArrayList<>();
		window = new ChatWindow();
		setName(name);
	}
	
	public void onMessage(String chan, String user, String login, String host, String text) {
		if (text.charAt(0) != '$') {
			window.addMessage(user, text);
		}
		if (!isBanned(user)) {
			Command command = Command.getCommand(text);
			if (command != null) {
				boolean op = isOp(chan, user);
				command.execute(chan, user, op, login, host, text);
			}
		}
	}
	
	public void onJoin(String chan, String sender, String login, String host) {
		if (sender.equalsIgnoreCase("buibuibot")) {
			channels.add(chan);
			sendMessage(chan, "BuiBui Hello, world!");
		}
	}
	
	public void onPart(String chan, String sender, String login, String host) {
		if (sender.equalsIgnoreCase("buibuibot")) {
			channels.remove(chan);
		}
	}
	
	public void onUserMode(String user, String oper, String login, String host, String mode) {
		String chan = mode.substring(mode.indexOf("#"));
		chan = chan.substring(0, chan.indexOf(" "));
		if (mode.contains("+o")) {
			String realUser = mode.substring(mode.indexOf("+o") + 3);
			ops.add(new Tuple<String, String>(realUser, chan));
			
		} else if (mode.contains("-o")) {
			String realUser = mode.substring(mode.indexOf("-o") + 3);
			ops.remove(new Tuple<String, String>(realUser, chan));
		}
	}
	
	public void onVersion(String user, String login, String host, String target) {
		sendNotice(user, "\001VERSION BuiBuiBot:0.3\001");
	}
	
	public boolean isInChannel(String chan) {
		return this.channels.contains(chan);
	}
	
	public void channelMessage(String chan, String user, String text) {
		this.sendMessage(chan, "[" + user + "] " + text);
	}
	
	private boolean isOp(String chan, String user) {
		User [] users = getUsers(chan);
		
		for (User u : users) {
			String name = u.getNick();
			if (name.equalsIgnoreCase(user) && ops.contains(new Tuple<String,String>(name, chan))) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isBanned(String user) {
		try {
			BufferedReader in = new BufferedReader(new FileReader("res/bannedusers.txt"));
			String ban = in.readLine();
			while (ban != null) {
				if (ban.equals(user)) {
					in.close();
					return true;
				}
				ban = in.readLine();
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}