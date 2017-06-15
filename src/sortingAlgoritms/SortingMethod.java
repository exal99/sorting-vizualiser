package sortingAlgoritms;
import gui.SortingList;

public interface SortingMethod<E extends Comparable<E>> {
	
	/**
	 * Sorts the list
	 * @param unsortedList the list to be sorted
	 */
	public void sort(SortingList<E> unsortedList);
	
	/**
	 * Returns the name of the algorithm
	 * @return the name of the algorithm
	 */
	public String getName();
}
