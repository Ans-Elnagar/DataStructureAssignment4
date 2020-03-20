package eg.edu.alexu.csd.datastructure.linkedList.Classes;
import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.ILinkedList;
public class Slinkedlist implements ILinkedList{
   private static class Node {
		private Object element;
		private Node next;
		public Node(Object value,Node n) {
			setElement(value);
			setNext(n);
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node n) {
			next = n;
		}
		public Object getElement() {
			return element;
		}
		public void setElement(Object value) {
			element = value;
		}
		
    }

    private int size;
    Node head,tail;
    public Slinkedlist() {
    	head=null;
    	tail=null;
    	size=0;
    }

    @Override
    public int size() {
    	return size;
    }

	@Override
	//index starts from zero as usual
	public void add(int index, Object element) {
		Node v= new Node(element,null);
		if(index>size||index<0)
			throw new IndexOutOfBoundsException();	
		else if (index==0) {
			v.setNext(head);
			head=v;
			if (size==0)
				tail=v;
		}
		else if (index==size) {
			add(element);
			return; // to avoid size++ 
			//As the size already increase in the previous function call
		}
		else {
			Node p = head;
			while (index-- > 1)
				p=p.getNext();
			Node q=p;
			p=p.getNext();
			q.setNext(v);
			v.setNext(p);
		}
		size++;
	}

	@Override
	public void add(Object element) {
		Node v= new Node(element,null);
		if(tail!=null) {
			tail.setNext(v);
			tail=v;
		}else {
			head=v;
			tail=v;
		}
		size++;
	}

	@Override
	public Object get(int index) {
		if(index>=size||index<0)
			throw new IndexOutOfBoundsException();
		else {
			Node p=head;
			while(index-- > 0)
				p=p.getNext();
			return p.getElement();
		}
	}

	@Override
	public void set(int index, Object element) {
		if(index>=size||index<0)
			throw new IndexOutOfBoundsException();
		else {
			Node p=head;
			while(index-- > 0)
				p=p.getNext();
			p.setElement(element);
		}
	}

	@Override
	public void clear() {
		head = tail = null;
		size=0;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public void remove(int index) {
		if(index>=size||index<0)
			throw new IndexOutOfBoundsException();
		if (index==0) {
			if (head == tail)
				head = tail = null;
			else
				head = head.getNext();
		} else if(index==size-1) {
			Node p=head;
			while(index-- > 1)
				p=p.getNext();
			tail=p;
			tail.setNext(null);
		} else {
			Node p=head;
			while(index-- > 1)
				p=p.getNext();
			p.setNext( (p.getNext()).getNext() );
		}
		size--;
	}

	@Override
	public ILinkedList sublist(int fromIndex, int toIndex) {
		Slinkedlist list = new Slinkedlist();
		if (fromIndex>=size||toIndex>=size||fromIndex<0||toIndex<0)
			throw new IndexOutOfBoundsException();
		Node p=head;
		for(int i=0;i<fromIndex;i++)
			p=p.getNext();
		for(int i=fromIndex;i<=toIndex;i++) {
			list.add(p.getElement());
			p=p.getNext();
		}
		return list;
	}

	@Override
	public boolean contains(Object o) {
		Node p=head;
		while(p!=null) {
			if (p.getElement().equals(o))
				return true;
			p=p.getNext();
		}
		return false;
	}
}
