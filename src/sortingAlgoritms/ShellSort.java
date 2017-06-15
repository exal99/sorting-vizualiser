package sortingAlgoritms;

import gui.SortingList;

public class ShellSort<E extends Comparable<E>> implements SortingMethod<E> {

	@Override
	/**
	 * Sorts the list with the ShellSort algorithm
	 */
	public void sort(SortingList<E> unsortedList) {
		int gap = unsortedList.size() / 2;
		while (gap > 0) {
			for (int nextPos = gap; nextPos < unsortedList.size(); nextPos++) {
				insert(unsortedList, nextPos, gap);
			}
			if (gap == 2) {
				gap = 1;
			} else {
				gap = (int) (gap / 2.2);
			}
		}
		
		for (int i = 0; i < unsortedList.size(); i++) {
			unsortedList.setSorted(i);
		}
	}
	
	private void insert(SortingList<E> unsortedList, int nextPos, int gap) {
		while ((nextPos > gap - 1) && (unsortedList.compare(nextPos, nextPos-gap) < 0)) {
			unsortedList.swap(nextPos, nextPos - gap);
			if (gap == 1) {
				unsortedList.setSorted(nextPos-gap);
				unsortedList.setSorted(nextPos);
			}
			nextPos -= gap;
		}
		
	}
	
	public String getName() {
		return "Shell Sort";
	}
	

}
