package com.dotsarecool.game.command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

import com.dotsarecool.game.BuiBuiBot;
import com.dotsarecool.game.Command;

public class QuoteCommand extends Command {

	public QuoteCommand(BuiBuiBot bui) {
		super(bui);
	}
	
	public void execute(String chan, String user, boolean op, String login, String host, String text) {
		String [] args = text.split(" ");
		
		if (args.length <= 1) {
			Random rand = new Random();
			try {
				BufferedReader in = new BufferedReader(new FileReader("res/quotes.txt"));
				int quoteCount = Integer.parseInt(in.readLine());
				
				if (quoteCount == 0) {
					bui.channelMessage(chan, user, "No quotes yet!");
					
				} else {
					int quoteNum = rand.nextInt(quoteCount);
					
					for (int i = 0; i < quoteNum; i++) {
						in.readLine();
					}
					bui.channelMessage(chan, user, in.readLine());
				}
				in.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				bui.channelMessage(chan, user, "Quotes are unavailable. :(");
			}
		} else if (args[1].equals("add")) {
			if (args.length <= 2) {
				bui.channelMessage(chan, user, "You forgot to put the quote, silly!");
			} else {
				try {
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("res/quotes_pending.txt", true)));
					out.printf("%25s %s\n", user, text.substring(11)); // TODO fix constant
					
					bui.channelMessage(chan, user, "Quote added for review!");
					out.close();
					
				} catch (Exception e) {
					e.printStackTrace();
					bui.channelMessage(chan, user, "Quotes are unavailable. :(");
				}
			}
		}
		
	}
	
}