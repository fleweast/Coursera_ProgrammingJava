package textgen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyLinkedListTester {

    private static final int LONG_LIST_LENGTH = 10;

    MyLinkedList<String> shortList;
    MyLinkedList<Integer> emptyList;
    MyLinkedList<Integer> emptyList1;
    MyLinkedList<Integer> emptyListNew;
    MyLinkedList<Integer> longerList;
    MyLinkedList<Integer> list1;

    @Before
    public void setUp() throws Exception {
        shortList = new MyLinkedList<String>();
        shortList.add("A");
        shortList.add("B");

        emptyList = new MyLinkedList<Integer>();
        emptyList1 = new MyLinkedList<Integer>();
        emptyListNew = new MyLinkedList<Integer>();
        longerList = new MyLinkedList<Integer>();
        for (int i = 0; i < LONG_LIST_LENGTH; i++) {
            longerList.add(i);
        }
        list1 = new MyLinkedList<Integer>();
        list1.add(65);
        list1.add(21);
        list1.add(42);
    }

    @Test
    public void testGet() {

        try {
            emptyList.get(0);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {}

        assertEquals("Check first", "A", shortList.get(0));
        assertEquals("Check second", "B", shortList.get(1));

        try {
            shortList.get(-1);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {}
        try {
            shortList.get(2);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {}

        for (int i = 0; i < LONG_LIST_LENGTH; i++) {
            assertEquals("Check " + i + " element", (Integer) i, longerList.get(i));
        }

        try {
            longerList.get(-1);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {}
        try {
            longerList.get(LONG_LIST_LENGTH);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {}
    }

    @Test
    public void testRemove() {
        int a = list1.remove(0);
        assertEquals("Remove: check a is correct ", 65, a);
        assertEquals("Remove: check element 0 is correct ", (Integer) 21, list1.get(0));
        assertEquals("Remove: check size is correct ", 2, list1.size());

        try {
            emptyList.remove(-1);
            fail("Check IndexOutOfBounds Exception");
        } catch (IndexOutOfBoundsException e) {}
        try {
            emptyList.remove(34);
            fail("Check IndexOutOfBounds Exception");
        } catch (IndexOutOfBoundsException e) {}
    }

    @Test
    public void testAddEnd() {
        try {
            emptyList.add(null);
            fail("Check null Pointer Exception");
        } catch (NullPointerException e) {}
        try {
            list1.add(null);
            fail("Check null Pointer Exception");
        } catch (NullPointerException e) {}
        boolean a = longerList.add(30);
        assertEquals("add: check a is correct ", true, a);
        assertEquals("add: check element 10 is correct ", (Integer) 30, longerList.get(10));
        assertEquals("add: check size is correct ", 11, longerList.size());

        boolean a1 = emptyList.add(3);
        assertEquals("add: check a1 is correct ", true, a1);
        assertEquals("add: check element 0 is correct ", (Integer) 3, emptyList.get(0));
        assertEquals("add: check size is correct ", 1, emptyList.size());
    }

    @Test
    public void testSize() {
        longerList.add(2, 70);
        assertEquals("add: check size is correct ", 11, longerList.size());
        emptyList.add(13);
        assertEquals("add: check size is correct ", 1, emptyList.size());
        emptyList1 = new MyLinkedList<Integer>();
        assertEquals("check size is correct ", 0, emptyList1.size());
    }

    @Test
    public void testAddAtIndex() {
        try {
            emptyListNew.add(1, null);
            fail("Check null Pointer Exception and IndexOutOfBoundsException");
        } catch (NullPointerException e) {
        } catch (IndexOutOfBoundsException e1) {}

        try {
            list1.add(2, null);
            fail("Check null Pointer Exception");
        } catch (NullPointerException e) {}

        try {
            emptyList.add(11, 20);
            fail("Check IndexOutOfBounds Exception");
        } catch (IndexOutOfBoundsException e) {}

        try {
            list1.add(-2, 78);
            fail("Check IndexOutOfBounds Exception");
        } catch (IndexOutOfBoundsException e) {}

        longerList.add(2, 90);
        assertEquals("add: check element added at position 2 is correct ", (Integer) 90, longerList.get(2));
        assertEquals("add: check size is correct ", 11, longerList.size());

        emptyListNew.add(0, 55);
        assertEquals("add: check element added at position 0 is correct ", (Integer) 55, emptyListNew.get(0));
        assertEquals("add: check size is correct ", 1, emptyListNew.size());
    }

    @Test
    public void testSet() {
        try {
            emptyListNew.set(1, null);
            fail("Check null Pointer Exception and IndexOutOfBoundsException");
        } catch (NullPointerException e) {
        } catch (IndexOutOfBoundsException e1) {}

        try {
            list1.set(2, null);
            fail("Check null Pointer Exception");
        } catch (NullPointerException e) {}

        try {
            emptyList.set(11, 20);
            fail("Check IndexOutOfBounds Exception");
        } catch (IndexOutOfBoundsException e) {}

        try {
            list1.set(-2, 78);
            fail("Check IndexOutOfBounds Exception");
        } catch (IndexOutOfBoundsException e) {}

        int a = longerList.set(2, 12);
        assertEquals("set: check a is correct ", 2, a);
        assertEquals("set: check element 2 is correct ", (Integer) 12, longerList.get(2));
        assertEquals("set: check size is correct ", 10, longerList.size());
    }
}