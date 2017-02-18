
public class Queue {
	private int n;
	private Node first;
	private Node rear;

public Queue() {
    first = null;
    rear = null;
    n = 0;
}
public void enqueue(String ch) {	
    Node oldrear = rear;
    rear = new Node();
    rear.data = ch;
    rear.next = null;
    if (first==null)
    	first=rear;
    else     oldrear.next = rear;	//creating a link between the last and before last elements
    n++;	//keeping track of the total elements number
    //System.out.println("order is"+n);
}

public String dequeue() {
   
    String ch = first.data;        // save item to return
    first = first.next;            // delete first node
    n--;
    if (first==null) rear = null;
    return ch;                   // return the saved item
}
public int index()
{
	return n;
}




}