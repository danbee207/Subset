package substa.model;

public class Pair {
	private Object first;
	private Object second;
	
	public Pair(){
	}
	
	public Pair(Object f, Object s){
		first = f;
		second = s;
	}

	public Object getFirst() {
		return first;
	}

	public void setFirst(Object first) {
		this.first = first;
	}

	public Object getSecond() {
		return second;
	}

	public void setSecond(Object second) {
		this.second = second;
	}

	
}
