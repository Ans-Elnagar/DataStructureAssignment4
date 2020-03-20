package eg.edu.alexu.csd.datastructure.linkedList.Tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import eg.edu.alexu.csd.datastructure.linkedList.Classes.Dlinkedlist;
class DlinkedlistTest {
	Dlinkedlist list = new Dlinkedlist();
	@Test
	void checkAddLast() {
		int[] arr= {1,2,3,4,5};
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		for(int i=0;i<list.size();i++) {
			assertEquals(list.get(i), arr[i]);
		}
	}
	
	@Test
	void addWithWrongIndex() {
		list.add(12111);
		list.add(5413);
	assertThrows(IndexOutOfBoundsException.class,()-> list.add(96,654));
	}
	@Test
	void addInDifferentIndecies() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(0,5);
		list.add(2,10);
		list.add(5,15);
		list.add(3,5);
		int[] arr= {5,1,10,5,2,3,15};
		for(int i=0;i<list.size();i++) {
			assertEquals(list.get(i), arr[i]);
		}
	}
	@Test
	void checkRemovingElements() {
		list.add(1);
		list.remove(0);
		list.add(2);
		list.add(3);
		list.add(0,5);
		list.remove(2);
		list.add(10);
		list.add(2,15);
		list.remove(1);
		list.add(0,3);
		int[] arr= {3,5,15,10};
		for(int i=0;i<list.size();i++) {
			assertEquals(list.get(i), arr[i]);
		}
	}
	@Test
	void removeWrongIndex() {
		list.add(12111);
		list.add(5413);
	assertThrows(IndexOutOfBoundsException.class,()-> list.remove(3));
	}
	@Test
	void setElements() {
		int[] arr= {0,4,3,4,20};
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.set(0, 0);
		list.set(1, 4);
		list.add(5);
		list.set(4, 20);
		for(int i=0;i<list.size();i++) 
			assertEquals(list.get(i), arr[i]);
	}
	@Test
	void setWrongIndex() {
		list.add(12111);
		list.add(5413);
	assertThrows(IndexOutOfBoundsException.class,()-> list.set(3,5130));
	}
	@Test
	void clearTest() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.clear();
		assertTrue(list.isEmpty());
	}
	@Test
	void containTest() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		assertTrue(list.contains(5));
		assertFalse(list.contains(3623));
	}
	@Test
	void sublistTest() {
		Dlinkedlist test=new Dlinkedlist();
		int[] arr= {3,4,5};
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		test=(Dlinkedlist) list.sublist(2, 4);
		for(int i=0;i<test.size();i++) {
			assertEquals(test.get(i), arr[i]);
		}
	}

}
