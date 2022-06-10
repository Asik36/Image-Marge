import java.awt.Point;

public class Stack {
	private Node top;
	public Stack()
	{
	}
	public void push(MyPix i)
	{
		// null
		if(top == null)
		{
			top = new Node(i);
			return;
		}
		Node tmp = new Node(i);
		tmp.next = top;
		top = tmp;
	}
	public void pop()
	{
		if(top == null)
		{
			return;
		}
		top = top.next;
	}
	public Node top()
	{
		return top;
	}
	public void print()
	{
		Node temp = top;
		
		while(temp != null) {
			System.out.print(temp.getInfo().pixel + " , ");
			temp = temp.next;
		}
		System.out.println();
	}
	
	public int getSize()
	{
		if(top == null)
		{
			return 0;
		}
		Node temp = top;

		int count = 0;
		while(temp != null) {
		temp = temp.next;
		count++;
			
		}
		
			return count;
	}

	public static void main(String[] args) {
		Stack st = new Stack();
		st.push(new MyPix(12,new Point(0,0)));
		System.out.println(st.top.getInfo());
		st.push(new MyPix(12,new Point(0,0)));
		System.out.println(st.top.getInfo());
		st.push(new MyPix(12,new Point(0,0)));
		System.out.println(st.top.getInfo());
		st.push(new MyPix(12,new Point(0,0)));
		System.out.println(st.top.getInfo());

		



	}

	

}
