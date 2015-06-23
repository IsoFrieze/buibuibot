package com.dotsarecool.game;

import java.util.Map;
import java.util.TreeMap;


public class Cooldown {
	
	public static Map<String, Cooldown> timers = new TreeMap<>();
	
	private int millis;
	private long issued;

	public Cooldown(String name, int seconds) {
		this.millis = 1000 * seconds;
		this.issued = System.currentTimeMillis();
		
		timers.put(name, this);
	}
	
	public static boolean cooledDown(String name) {
		if (timers.containsKey(name)) {
			Cooldown cd = timers.get(name);
			long span = System.currentTimeMillis() - cd.issued;
			if (span > cd.millis) {
				timers.remove(name);
				return true;
			} else {
				return false;
			}
			
		} else {
			return true;
		}
	}
	
}