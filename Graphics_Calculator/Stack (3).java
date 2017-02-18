
public class Stack {
	private int n;
	private Node first;


public Stack() {
    first = null;
    n = 0;
}
	
	
	
	
	
	public void push(String ch) {
	    Node oldfirst = first;
	    first = new Node();
	    first.data = ch;
	    first.next = oldfirst;	//creating link between the last and before last elements
	    n++;	//keeping track of the total elements number
	    
	}

	public String pop() {
	   
	    String ch = first.data;        // save item to return
	    first = first.next;            // delete first node
	    n--;
	    
	    return ch;   
}
	public String peak(){	//reads only the top of the stack
		return first.data;
	}
}
