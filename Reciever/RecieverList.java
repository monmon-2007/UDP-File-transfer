package Reciever;

public class RecieverList {
	
	private Node head;
	private int NumOfRec;
	
	public RecieverList() {
		head = new Node(null);
		NumOfRec = 0;
	}
	public void add (String Name){
		Node node = new Node(Name);
		Node temp = head;
        while(temp.getNext() != null){
        	temp = temp.getNext();
        }
        temp.setNext(node);
        node.setPrevious(temp);
        NumOfRec++;
	}
	
	public void remove(String Name){
		String data;
		Node temp = head;
		if(head.getName()== Name){
			head = head.getNext();
		}else{
		while(temp.getNext()!= null){
			data = temp.getName();
			if(data == Name){
				Node prev = temp.getPrevious();
				Node next = temp.getNext();
				prev.setNext(next);
				next.setPrevious(prev);
				break;
			}
		}
	   }
	}
		private class Node
		{
			// reference to the next node in the chain,
			// or null if there isn't one.
			Node next;
			Node previous;
			// data carried by this node.
			// could be of any type you need.
			String Name;
			boolean isrecieved;


			// Node constructor
			public Node (String Name)
			{
				next = null;
				previous = null;
				this.Name = Name;
				this.isrecieved = false;
			}

			// another Node constructor if we want to
			// specify the node to point to.
			public Node(String Name, Node _next, Node _previous)
			{
				next = _next;
				previous = _previous;
				this.Name = Name;
				this.isrecieved = false;
			}

			// these methods should be self-explanatory
			public String getName()
			{
				return Name;
			}

			public void setName (String Name)
			{
				this.Name = Name;
			}

			public Node getNext()
			{
				return next;
			}
			
			public void setNext(Node _next)
			{
				next = _next;
			}
			
			public Node getPrevious ()
			{
				return previous;
			}
			
			public void setPrevious(Node _previous)
			{
				previous = _previous;
			}

			
		
		}
}
