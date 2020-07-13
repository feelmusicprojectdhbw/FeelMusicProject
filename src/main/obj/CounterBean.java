package main.obj;

public class CounterBean {
	private int counter = 0;
	
	public static CounterBean[] newArrayInstance() {
		return new CounterBean[] {new CounterBean()};
	}
	
	public void reset() {
		this.counter = 0;
	}
	
	public boolean isSmaller(int n) {
		return counter < n;
	}
	
	public int getCounter() {
		return counter;
	}
	
	public void increment() {
		this.counter += 1;
	}
}
