package gui;


import java.awt.CheckboxMenuItem;
import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import sortingAlgoritms.*;

public class VisulizerGui {
	private JFrame root;
	private SortVisulizer vizulizer;
	private final static int DEFAULT_SIZE = 100;
	private int size;
	private boolean quickRand;
	
	/**
	 * Creates a new VizuliserGUI
	 */
	public VisulizerGui() {
		root = new JFrame("Sort Vizulizer");
		quickRand = false;
		makeMenu();
		size = DEFAULT_SIZE;
		
		
		root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		root.getContentPane().setBackground(Color.BLACK);
		
		vizulizer = new SortVisulizer(Arrays.asList(getScrambledList(size)), size);
		root.add(vizulizer);
	}
	/**
	 * Returns the root window
	 * @return the root window
	 */
	public JFrame getRoot() {
		return root;
	}
	
	/**
	 * Creates all the menus and there items
	 */
	private void makeMenu() {
		MenuBar bar = new MenuBar();
		
		Menu start = new Menu("Start Sort");
		
		MenuItem bubble = new MenuItem("Bubble Sort");
		bubble.addActionListener(e -> start(new BubbleSort<Integer>()));
		MenuItem heap = new MenuItem("Heap Sort");
		heap.addActionListener(e -> start(new HeapSort<Integer>()));
		MenuItem insert = new MenuItem("Insertion Sort");
		insert.addActionListener(e -> start(new InsertionSort<Integer>()));
		MenuItem select = new MenuItem("Selection Sort");
		select.addActionListener(e -> start(new SelectionSort<Integer>()));
		MenuItem shell = new MenuItem("Shell Sort");
		shell.addActionListener(e -> start(new ShellSort<Integer>()));
		MenuItem quick = new MenuItem("Quick Sort");
		quick.addActionListener(e -> start(new QuickSort<Integer>(quickRand)));
		
		start.add(bubble);
		start.add(heap);
		start.add(insert);
		start.add(select);
		start.add(shell);
		start.add(quick);
		
		Menu settings = new Menu("Settings");
		MenuItem size = new MenuItem("Change Size");
		CheckboxMenuItem quickRandom = new CheckboxMenuItem("Random Left-Right Selection in QuickSort");
		quickRandom.addItemListener(l -> quickRand = !quickRand);
		size.addActionListener(e -> changeSize());
		
		Menu plotSettings = new Menu("Plot Settings");
		CheckboxMenuItem barPlot = new CheckboxMenuItem("Bar Plot", true);
		CheckboxMenuItem dotPlot = new CheckboxMenuItem("Dot Plot", false);
		//barPlot.addItemListener(l -> changePlotType(barPlot, dotPlot));
		barPlot.addItemListener(l -> {dotPlot.setState(!dotPlot.getState()); vizulizer.repaint(true);});
		dotPlot.addItemListener(l -> {barPlot.setState(!dotPlot.getState()); vizulizer.repaint(false);});
		plotSettings.add(barPlot);
		plotSettings.add(dotPlot);
		settings.add(plotSettings);
		
		settings.add(size);
		settings.add(quickRandom);
				
		bar.add(start);
		bar.add(settings);
		
		root.setMenuBar(bar);
	}
	
	/**
	 * Starts the sorting with the given algorithm
	 * @param sm the sorting algorithm to use
	 */
	private void start(SortingMethod<Integer> sm) {
		vizulizer.uppdateList(getScrambledList(size), size);
		vizulizer.start(sm);
	}
	
	/**
	 * Changes the size of the list being sorted
	 */
	private void changeSize() {
		String ans = JOptionPane.showInputDialog("Enter new size:");
		if (ans != null && ans.length() > 0) {
			try {
				size = Integer.parseInt(ans);
				vizulizer.uppdateList(getScrambledList(size), size);
			} catch (NumberFormatException e) {
				return;
			}
		}
	}
	/**
	 * Returns a list consisting of elements n, 1<=n<=size in random order
	 * @param size the size of the list
	 * @return a scrambled list of elements from 1 to size, inclusive
	 */
	private Integer[] getScrambledList(int size) {
		Random rand = new Random();
		Integer[] rList = new Integer[size];
		for (int i = 1; i <= size; i++) {
			int ind = rand.nextInt(size);
			while (rList[ind] != null) {
				ind = rand.nextInt(size);
			}
			rList[ind] = i;
		}
		return rList;
	}
}
