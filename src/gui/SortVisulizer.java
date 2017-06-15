package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Collection;

import sortingAlgoritms.SortingMethod;

public class SortVisulizer extends DoubleBuffer {
	private SortingList<Integer> list;
	private int maxElement;
	private static final long serialVersionUID = 1L;
	private boolean barPlot;
	private SortingMethod<Integer> sort;
	
	/**
	 * Creates a new SortVisulizer for visualizing the sorting a list
	 * @param list the collection too be sorted
	 * @param maxElement the biggest value in the list
	 */
	public SortVisulizer(Collection<Integer> list, int maxElement) {
		super();
		this.list = new SortingList<Integer>(list, ()->update(getGraphics()));
		this.maxElement = maxElement;
		this.barPlot = true;
	}
	
	/**
	 * Updates the list being sorted to the new list
	 * @param list the new list
	 * @param maxElement the new max value
	 */
	public void uppdateList(Integer[] list, int maxElement) {
		this.list = new SortingList<Integer>(Arrays.asList(list), ()->update(getGraphics()));
		this.maxElement = maxElement;
		repaint();
	}
	
	/**
	 * Repaints the component according to barPlot. If barPlot is false
	 * then the visualization will plot the values as dots instead of bars
	 * @param barPlot the type of plot
	 */
	public void repaint(boolean barPlot) {
		this.barPlot = barPlot;
		repaint();
	}
	
	@Override
	/**
	 * Paints the list onto the buffer to prevent studer. This method
	 * is never manually called, instead it is called by DoubleBuffer.paint
	 * 
	 * @param g the Graphics-object to paint on.
	 */
	public void paintBuffer(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.WHITE);
		g2.drawString("Algorithm: " + ((sort != null) ? sort.getName() : ""), 0, 10);
		g2.drawString("Comparasons: " + list.getNumComp(), 0, 25);
		g2.drawString("Swaps: " + list.getNumSwaps(), 0, 40);
		double x = 0;
		for (int ind = 0; ind < list.size(); ind++) {
			SortingList.ListNode<Integer> i = list.get(ind);
			double height = getHeight();
			double width = getWidth();
			double rectWidth = width/list.size();
			double rectHeight = ((double) i.getElement())/maxElement * height;
			Rectangle2D.Double rect = new Rectangle2D.Double(x, height - rectHeight, rectWidth, (barPlot) ? rectHeight : rectWidth);
			x += width / list.size();
			if (i.isCompare()) {
				g2.setColor(Color.RED);
			} else if (i.isSorted()) {
				g2.setColor(Color.GREEN);
			} else {
				g2.setColor(Color.WHITE);
			}
			g2.fill(rect);
			g2.setColor(Color.BLACK);
		}
	}
	
	/**
	 * Starts the sorting process with the given SortingMethod
	 * @param s the sorting algorithm to be used
	 */
	public void start(SortingMethod<Integer> s) {
		sort = s;
		s.sort(list);
	}
	
}