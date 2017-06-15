package gui;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class SortingList<E extends Comparable<E>> implements Iterable<SortingList.ListNode<E>>{
	
	private SortingList.ListNode<E>[] list;
	private ListEvent event;
	private int numComp;
	private int numSwaps;
	
	@SuppressWarnings("unchecked")
	/**
	 * Creates a new SortingList based on a Collection and a ListEven
	 * @param c the Collection to create the list from
	 * @param e the event to call whenever the list changes
	 */
	public SortingList(Collection<? extends E> c, ListEvent e) {
		this.list =  (ListNode<E>[]) new ListNode[c.size()];
		int ind = 0;
		for (E element : c) {
			if (element != null) {
				ListNode<E> el = new ListNode<E>(element);
				this.list[ind] = el;
				ind++;
			}
		}
		event = e;
	}
	
	/**
	 * Returns the ListNode at index ind
	 * @param ind the index
	 * @return the ListNode at index ind
	 */
	protected ListNode<E> get(int ind) {
		return list[ind];
	}
	
	/**
	 * Returns the size of the list
	 * @return the size of the list
	 */
	public int size() {
		return list.length;
	}
	
	/**
	 * Compares the element at ind1 with ind2
	 * @param ind1 the first element to compare
	 * @param ind2 the second element to compare
	 * @return the result of ind1 compared to ind2
	 */
	public int compare(int ind1, int ind2) {
		list[ind1].setCompare(true);
		list[ind2].setCompare(true);
		numComp++;
		event.onUpdate();
		list[ind1].setCompare(false);
		list[ind2].setCompare(false);
		return list[ind1].element.compareTo(list[ind2].element);
	}
	
	/**
	 * Swaps the element at index ind1 with ind2
	 * @param ind1 the element to swap
	 * @param ind2 the second elemnt to swap
	 */
	public void swap(int ind1, int ind2) {
		ListNode<E> item = list[ind1];
		list[ind1] = list[ind2];
		list[ind2] =  item;
		numSwaps++;
		event.onUpdate();
	}
	
	/**
	 * Sets the element at ind to sorted, meaning it is at it's final
	 * position
	 * @param ind the element that is sorted
	 */
	public void setSorted(int ind) {
		list[ind].setSorted(true);
	}
	
	public static class ListNode<E extends Comparable<E>> {
		private E element;
		private boolean compare;
		private boolean sorted;
		
		/**
		 * Creates a ListNode with a given value. A list node also keeps track
		 * of if the element is being compared to another element or if it's
		 * already sorted and in its final position
		 * @param value the value of the element
		 */
		public ListNode(E value) {
			element = value;
			compare = false;
			sorted = false;
		}
		
		/**
		 * Returns true if the element is being compared to another element
		 * @return true if the element is being compared to antother element
		 */
		public boolean isCompare() {
			return compare;
		}
		
		/**
		 * Sets the elements compare state to b
		 * @param b the new compare state
		 */
		private void setCompare(boolean b) {
			compare = b;
		}
		
		/**
		 * Sets the element sorted state to b
		 * @param b the new sorted state
		 */
		private void setSorted(boolean b) {
			sorted = b;
		}
		
		/**
		 * Returns true if the element is sorted, i.e. in its final position
		 * @return true if the element is sorted, i.e. in its final position 
		 */
		public boolean isSorted() {
			return sorted;
		}
		
		/**
		 * Returns the content of the ListNode
		 * @return the content of the LIstNode
		 */
		public E getElement() {
			return element;
		}
		
		/**
		 * Returns the content's string representation
		 */
		public String toString() {
			return element.toString();
		}
		
	}

	@Override
	/**
	 * Returns the list's iterator
	 */
	public Iterator<ListNode<E>> iterator() {
		return Arrays.asList(list).iterator();
	}
	
	/**
	 * Returns the number of comparisons made
	 * @return the number of comparisons made
	 */
	public int getNumComp() {
		return numComp;
	}

	/**
	 * Returns the number of swaps made
	 * @return the number of swaps made
	 */
	public int getNumSwaps() {
		return numSwaps;
	}

}
