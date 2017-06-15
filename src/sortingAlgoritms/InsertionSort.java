package sortingAlgoritms;

import gui.SortingList;

public class InsertionSort<E extends Comparable<E>> implements SortingMethod<E> {

	@Override
	/**
	 * Sorts the list with the insertions sort algorithm
	 */
	public void sort(SortingList<E> unsortedList) {
		for (int toInsert = 1; toInsert < unsortedList.size(); toInsert++) {
			int localInsert = toInsert;
			for (int compareTo = toInsert - 1; compareTo >= 0; compareTo--) {
				if (unsortedList.compare(localInsert, compareTo) < 0) {
					unsortedList.swap(localInsert, compareTo);
					localInsert = compareTo;
				} else {
					break;
				}
			}
		}
		for (int i = 0; i < unsortedList.size(); i++) {
			unsortedList.setSorted(i);
		}
	}

	@Override
	public String getName() {
		return "Insertion Sort";
	}
	
}
