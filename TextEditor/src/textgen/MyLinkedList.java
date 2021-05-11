package textgen;
import java.util.AbstractList;

public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	public MyLinkedList() {
		head=new LLNode<E>(null,null,tail);
		tail=new LLNode<E>(null,head,null);
		size = 0;
	}

	public boolean add(E element ) 
	{
		if (element == null) throw new NullPointerException();
			LLNode<E> node = new LLNode<E>(element);
			node.prev = tail.prev;
			tail.prev = node;
			node.next = tail;
			node.prev.next = node;
			this.size++;
			return true;
	}

	public E get(int index) 
	{
		if (index < 0 || index > size-1 || size == 0) throw new IndexOutOfBoundsException();
			LLNode<E> cur = head;
			for (int i = 0; i <= index; i++) {
				cur = cur.next;
			}
			return cur.data;
	}

	public void add(int index, E element ) 
	{
		if (element == null) throw new NullPointerException();
		if((size!=0) && ((index>size-1)|| (index<0))) throw new IndexOutOfBoundsException();
		if(size==0 && index!=0) throw new IndexOutOfBoundsException();

		if (size == 0 && index == 0){
			add(element);
		} else {
			LLNode<E> cur = head;
			for (int i = 0; i < index; i++) {
				cur = cur.next;
			}
			LLNode<E> node = new LLNode<E>(element);
			node.next=cur.next;
			cur.next.prev=node;
			node.prev=cur;
			cur.next=node;
			this.size++;
		}

	}

	public int size() 
	{
		return size;
	}

	public E remove(int index) 
	{
		if((size!=0) && ((index>size-1)|| (index<0))) throw new IndexOutOfBoundsException();
		if(size==0) throw new IndexOutOfBoundsException();
		LLNode<E> cur = head;
		for(int i=0;i<=index;i++){
			cur=cur.next;
		}
		cur.next.prev=cur.prev;
		cur.prev.next=cur.next;
		this.size--;
		return cur.data;
	}

	public E set(int index, E element) 
	{
		if(element==null) throw new NullPointerException();
		if((size!=0) && ((index>size-1)|| (index<0))) throw new IndexOutOfBoundsException();
		if(size==0 && index>=0) throw new IndexOutOfBoundsException();
		LLNode<E> cur = head;
		for(int i=0;i<=index;i++){
			cur=cur.next;
		}
		E val = cur.data;
		cur.data=element;
		return val;
	}

	@Override
	public String toString() {
		return "MyLinkedList [head=" + head + ", tail=" + tail + ", size=" + size + "]";
	}
}

class LLNode<E>
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e)
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E e, LLNode<E> p,LLNode<E> n)
	{
		this.data = e;
		this.prev = p;
		this.next = n;
	}

	@Override
	public String toString() {
		return "LLNode [prev=" + prev + ", next=" + next + ", data=" + data + "]";
	}

}
