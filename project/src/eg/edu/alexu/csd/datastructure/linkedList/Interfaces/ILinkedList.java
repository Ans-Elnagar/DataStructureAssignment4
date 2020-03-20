package eg.edu.alexu.csd.datastructure.linkedList.Interfaces;

public interface ILinkedList {
	public void add(int index, Object element);
	public void add(Object element);
	public Object get(int index);
	public void set(int index, Object element);
	public void clear();
	public int size();
	public boolean isEmpty();
	public void remove(int index);
	public ILinkedList sublist(int fromIndex, int toIndex);
	public boolean contains(Object o);
}
