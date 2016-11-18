import java.util.*;
import java.io.*;

class OrderSet {
	private class Node {
		int l, r;
		int sum;
		Node () {
			sum = 0;
			l = -1;
			r = -1;
		}
	}
	private ArrayList <Node> t;
	private int maxn;
	private int now;
	
	OrderSet (int maxn) {
		this.maxn = maxn;
		t = new ArrayList <> ();
		t.add(new Node());
		now = 0;
	}
	private void update(int x, int c, int b, int e) {		
		Node p = t.get(c);
		if(b == e) {
			p.sum += 1;
			return ;
		}
		int m = (b + e) >> 1;
		if(x <= m) {
			if(p.l == -1) {
				p.l = ++now;
				t.add(new Node());
			}
			update(x, p.l, b, m);
		}
		else {
			if(p.r == -1) {
				p.r = ++now;
				t.add(new Node());
			}
			update(x, p.r, m+1, e);
		}
		p.sum = 0;
		if(p.l != -1) p.sum += t.get(p.l).sum;
		if(p.r != -1) p.sum += t.get(p.r).sum; 
	}
	private int range(int x, int y, int c, int b, int e) {
		if(c == -1) return 0;
		if(x <= b && e <= y) return t.get(c).sum;
		int m = (b + e) >> 1;
		int l = t.get(c).l;
		int r = t.get(c).r;
		return range(x, y, l, b, m) + range(x, y, r, m+1, e);
	}
	private int find(int x, int c, int b, int e) {
		if(c == -1) return -1;
		if(b == e) {
			return b;
		}
		int m = (b + e) >> 1;
		int l = t.get(c).l;
		int r = t.get(c).r;
		if(l != -1 && x <= t.get(l).sum) {
			return find(x, l, b, m);
		} else {
			if(l == -1) return find(x, r, m+1, e);
			else return find(x - t.get(l).sum, r, m+1, e);
		}
	}
	public void add(int x) {
		update(x, 0, 0, maxn);
	}
	public int get(int x) {
		return find(x+1, 0, 0, maxn);
	}
	public int count(int l, int r) {
		return range(l, r, 0, 0, maxn);
	} 
	public int size() {
		return t.get(0).sum;
	}
}

public class Orderset {
	public static void main(String[] Args) {
		OrderSet t = new OrderSet (1000);
		t.add(3);
		t.add(4);
		t.add(6);
		t.add(11);
		t.add(7);
		t.add(0);
		t.add(89);
		t.add(7);
		
		for(int i = 0; i < t.size(); i++) {
			System.out.println(t.get(i));
		}
		System.out.println(t.count(6, 260));
	}
}
