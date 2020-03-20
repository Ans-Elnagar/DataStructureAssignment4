package eg.edu.alexu.csd.datastructure.linkedList.Classes;
import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.ILinkedList;
public class Dlinkedlist implements ILinkedList {
	private static class Node{
		private Node next;
		private Node prev;
		private Object element;
		public Node() {
			
		}
		public Node(Object element,Node next,Node prev) {
			this.element=element;
			this.next=next;
			this.prev=prev;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public Node getPrev() {
			return prev;
		}
		public void setPrev(Node prev) {
			this.prev = prev;
		}
		public Object getElement() {
			return element;
		}
		public void setElement(Object element) {
			this.element = element;
		}
	}
	private int size;
	Node header,trailer;
	public Dlinkedlist() {
		header=new Node();
		trailer=new Node(null,null,header);
		header.setNext(trailer);
		size=0;
	}
	@Override
	public void add(int index, Object element) {
		if(index>size||index<0)
			throw new IndexOutOfBoundsException();
		else if (index==0) {
				Node p=new Node(element,header.getNext(),header);		
				if(size==0)
					trailer.setPrev(p);
				else {
					Node q=header.getNext();
					q.setPrev(p);
				}
				header.setNext(p);
				size++;
		}
		else if(index==size)
			add(element);
		else {
			Node prev=header;
			while(index-- > 0)
				prev=prev.getNext();
			Node next=prev.getNext();
			Node current=new Node(element,next,prev);
			prev.setNext(current);
			next.setPrev(current);
			size++;
		}
	}

	@Override
	public void add(Object element) {
		Node prev=trailer.getPrev();
		Node current=new Node(element,trailer,prev);
		prev.setNext(current);
		trailer.setPrev(current);
		size++;
	}

	@Override
	public Object get(int index) {
		if(index>=size||index<0)
			throw new IndexOutOfBoundsException();
		else {
			if (index>size/2) {
				Node p=trailer.getPrev();
				for(int i=size;i>index+1;i--) 
					p=p.getPrev();
				return p.getElement();
			}
			else {
				Node p=header.getNext();
				for(int i=0;i<=index-1;i++)
					p=p.getNext();
				return p.getElement();
			}
		}		
	}

	@Override
	public void set(int index, Object element) {
		if(index>=size||index<0)
			throw new IndexOutOfBoundsException();
		else {
			if (index>size/2) {
				Node p=trailer;
				for(int i=size;i>index;i--) 
					p=p.getPrev();
				p.setElement(element);
			}
			else {
				Node p=header;
				for(int i=0;i<=index;i++)
					p=p.getNext();
				p.setElement(element);
			}
		}
	}
	@Override
	public void clear() {
		header.setNext(trailer);
		trailer.setPrev(header);
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
		else {
			if (index==0) {
				Node q=header.getNext().getNext();
				header.setNext(q);
				q.setPrev(header);
			}
			else if(index==size-1) {
				Node q=trailer.getPrev().getPrev();
				q.setNext(trailer);
				trailer.setPrev(q);
			}
			else {
				Node prev=header.getNext();
				for(int i=1;i<index;i++)
					prev=prev.getNext();
				Node next=prev.getNext().getNext();
				prev.setNext(next);
				next.setPrev(prev);
				}
			size--;
			}
			
	}

	@Override
	public ILinkedList sublist(int fromIndex, int toIndex) {
		Dlinkedlist list =new Dlinkedlist();
		if (fromIndex>=size||toIndex>=size||fromIndex<0||toIndex<0)
			throw new IndexOutOfBoundsException();			
		else {
			Node p=header.getNext();
			for(int i=0;i<fromIndex;i++)
				p=p.getNext();
			for(int i=fromIndex;i<=toIndex;i++) {
				list.add(p.getElement());
				p=p.getNext();
			}
			return list;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(Object o) {
		Node p=header.getNext();
		while(p!=trailer) {
			if (p.getElement().equals(o))
				return true;
			p=p.getNext();
		}
		return false;
	}
}