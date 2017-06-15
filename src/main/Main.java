package main;

import gui.VisulizerGui;

public class Main {
	public static void main(String[] args) {
		VisulizerGui gui = new VisulizerGui();
		gui.getRoot().setSize(800, 600);
		gui.getRoot().setVisible(true);
	}
}
