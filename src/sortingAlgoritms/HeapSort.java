package sortingAlgoritms;

import gui.SortingList;

public class HeapSort<E extends Comparable<E>> implements SortingMethod<E> {

	@Override
	/**
	 * Sorts the list with the heap sort algorithm
	 */
	public void sort(SortingList<E> unsortedList) {
		for (int i = unsortedList.size() - 1; i > 0; i -= 2) {
			heapify(unsortedList, getParrent(i), unsortedList.size());
		}
		for (int too = unsortedList.size() - 1; too > 0; too--) {
			unsortedList.swap(0, too);
			unsortedList.setSorted(too);
			heapify(unsortedList, 0, too);
		}
		unsortedList.setSorted(0);
		unsortedList.setSorted(1);
	}
	
	
	private void heapify(SortingList<E> unsortedList, int posParrent, int too) {
		int left = getLeft(posParrent, unsortedList.size(), too);
		int right = getRight(posParrent, unsortedList.size(), too);
		if (right != -1) {
			if (unsortedList.compare(left, right) > 0 && unsortedList.compare(left, posParrent) > 0){
				unsortedList.swap(posParrent, left);
				heapify(unsortedList, left, too);
			} else if (unsortedList.compare(right, posParrent) > 0) {
				unsortedList.swap(posParrent, right);
				heapify(unsortedList, right, too);
			}
		} else if (left != -1) {
			if (unsortedList.compare(left, posParrent) > 0) {
				unsortedList.swap(posParrent, left);
			}
		}
	}
	
	private int getLeft(int parrent, int size, int too) {
		int left = 2*parrent + 1;
		return (left < size && left < too) ? left : -1;
	}
	
	private int getRight(int parrent, int size, int too) {
		int right  = 2*parrent + 2;
		return (right < size && right < too) ? right : -1;
	}
	
	private int getParrent(int child) {
		return (child-1)/2;
	}


	@Override
	public String getName() {
		return "Heap Sort";
	}

}
