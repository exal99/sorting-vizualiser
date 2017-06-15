package sortingAlgoritms;

import gui.SortingList;

public class BubbleSort<E extends Comparable<E>> implements SortingMethod<E> {

	@Override
	/**
	 * Sorts the list with the bubble sort algorithm
	 */
	public void sort(SortingList<E> unsortedList) {
		boolean changed;
		int upperBound = 1;
		do {
			changed = false;
			for (int i = 0; i < unsortedList.size() - upperBound; i++) {
				if (unsortedList.compare(i, i+1) > 0) {
					unsortedList.swap(i, i+1);
					changed = true;
				}
			}
			unsortedList.setSorted(unsortedList.size() - upperBound);
			upperBound++;
		} while (changed);
		for (int i = 0; i < unsortedList.size(); i++) {
			unsortedList.setSorted(i);
		}
	}

	@Override
	public String getName() {
		return "Bubble Sort";
	}

}
