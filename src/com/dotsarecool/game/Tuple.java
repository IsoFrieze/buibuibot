package com.dotsarecool.game;

public class Tuple<A, B> {
	
	public A a;
	public B b;
	
	public Tuple(A a, B b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof Tuple<?,?>)) {
			return false;
		}
		Tuple<?,?> t = (Tuple<?,?>)o;
		return t.a.equals(this.a) && t.b.equals(this.b);
	}
}
