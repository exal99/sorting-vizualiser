package sortingAlgoritms;

import gui.SortingList;

public class SelectionSort<E extends Comparable<E>> implements SortingMethod<E> {

	@Override
	/**
	 * Sorts the list using the selection sort algorithm
	 */
	public void sort(SortingList<E> unsortedList) {
		for (int i = 0; i < unsortedList.size(); i++) {
			int indSmalest = i;
			for (int j = i + 1; j < unsortedList.size(); j++) {
				if (unsortedList.compare(indSmalest, j) > 0) {
					indSmalest = j;
				}
			}
			unsortedList.swap(indSmalest, i);
			unsortedList.setSorted(i);
		}
	}

	@Override
	public String getName() {
		return "Selection Sort";
	}

}
