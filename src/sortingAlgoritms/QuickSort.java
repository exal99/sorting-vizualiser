package sortingAlgoritms;

import java.util.Random;
import gui.SortingList;

public class QuickSort<E extends Comparable<E>> implements SortingMethod<E> {
	
	private boolean random;
	
	/**
	 * Creates a new QuickSort sorter. If rand is false then it will always sort
	 * the left side recusivly and the the right side, but if rand is true then
	 * it will randomly select the left or the right side
	 * @param rand the chois of random or not
	 */
	public QuickSort(boolean rand) {
		random = rand;
	}
	
	@Override
	/**
	 * Sorts the list using the quick sort algorithm
	 */
	public void sort(SortingList<E> unsortedList) {
		quickSort(unsortedList, 0, unsortedList.size() - 1, new Random());
	}
	
	private void quickSort(SortingList<E> unsortedList, int first, int last, Random rand) {
		if (first < last) {
			int pivIndex = partision(unsortedList, first, last);
			unsortedList.setSorted(pivIndex);
			if (!random || rand.nextBoolean()) {
				quickSort(unsortedList, first, pivIndex - 1, rand);
				for (int i = first; i < pivIndex; i++) {
					unsortedList.setSorted(i);
				}
				quickSort(unsortedList, pivIndex + 1, last, rand);
				for (int i = pivIndex + 1; i <= last; i++) {
					unsortedList.setSorted(i);
				}
			} else {
				quickSort(unsortedList, pivIndex + 1, last, rand);
				for (int i = pivIndex + 1; i <= last; i++) {
					unsortedList.setSorted(i);
				}
				quickSort(unsortedList, first, pivIndex - 1, rand);
				for (int i = first; i < pivIndex; i++) {
					unsortedList.setSorted(i);
				}
				
			}
		}
	}
	
	private int partision(SortingList<E> unsortedList, int first, int last) {
		bubbleSort(unsortedList, first, last);
		unsortedList.swap(first, (first + last) / 2);
		int up = first;
		int down = last;
		int pivot = first;
		do {
			while (up < last && unsortedList.compare(pivot, up) >= 0) {
				up++;
			}
			while (unsortedList.compare(pivot, down) < 0) {
				down--;
			}
			if (up < down) {
				unsortedList.swap(up, down);
			}
		} while (up < down);
		unsortedList.swap(down, first);
		return down;
	}
	
	private void bubbleSort(SortingList<E> unsortedList, int first, int last) {
		int middle = (first + last) / 2;
		
		if (unsortedList.compare(middle, first) < 0) {
			unsortedList.swap(first, middle);
		} if (unsortedList.compare(last, middle) < 0) {
			unsortedList.swap(middle, last);
		} if (unsortedList.compare(middle, first) < 0) {
			unsortedList.swap(first, middle);
		}
	}

	@Override
	public String getName() {
		return "Quick Sort";
	}

}
